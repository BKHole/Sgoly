package com.libt.sgoly.fragment.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.libt.sgoly.AppConstant;
import com.libt.sgoly.R;
import com.libt.sgoly.adapter.MomentsAdapter;
import com.libt.sgoly.db.Moments;
import com.libt.sgoly.db.User;
import com.libt.sgoly.manager.UIManager;
import com.libt.sgoly.util.LogUtils;
import com.libt.sgoly.util.ToastUtils;
import com.libt.sgoly.view.RefreshLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobDate;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;


public class MomentsFragment extends Fragment {

    View view;
    private List<Moments> momentsList = new ArrayList<>();
    private MomentsAdapter momentsAdapter;
    private RefreshLayout swipeRefresh;
    private RecyclerView recyclerView;
    private boolean loading = false; // 判断是否在加载更多,避免重复请求网络
    private int currentPage = 0; // 当前页面

    private static final int STATE_REFRESH = 0; // 下拉刷新
    private static final int STATE_MORE = 1; // 加载更多
    private int limit = 5; // 每页的数据是10条
    private String lastTime;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.frag_faxian, container, false);

        TextView title= (TextView) view.findViewById(R.id.titlebar_text_title);
        title.setVisibility(View.VISIBLE);
        title.setText("发现");
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        swipeRefresh = (RefreshLayout) view.findViewById(R.id.swipe_refresh);
        //initFruits();

        initRefreshLayout();


        return view;
    }

    /**
     * 初始化RefreshLayout
     */
    private void initRefreshLayout() {
        // 设置进度动画的颜色
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        // 设置进度圈的大小,只有两个值:DEFAULT、LARGE
        swipeRefresh.setSize(SwipeRefreshLayout.DEFAULT);
        // true:下拉过程会自动缩放,230:下拉刷新的高度
        swipeRefresh.setProgressViewEndTarget(true, 230);

        // 进入页面就执行下拉动画
        swipeRefresh.post(new Runnable() {
            @Override
            public void run() {
                getData(0, STATE_REFRESH);
            }
        });
        // 下拉刷新操作
        swipeRefresh.setOnRefreshListener(new RefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData(0, STATE_REFRESH);
            }
        });
        // 上拉加载更多操作
        swipeRefresh.setOnLoadListener(new RefreshLayout.OnLoadListener() {
            @Override
            public void onLoad() {
                if (!loading) {
                    loading = true;
                    getData(currentPage, STATE_MORE);
                }
            }
        });
        // ListView条目点击事件
        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(
                getActivity(), DividerItemDecoration.VERTICAL));//添加分割线
        momentsAdapter =new MomentsAdapter(momentsList);
        recyclerView.setAdapter(momentsAdapter);
    }

    /**
     * 分页获取数据
     * @param page 页码
     * @param actionType istView的操作类型（下拉刷新、上拉加载更多）
     */
    public void getData(final int page, final int actionType) {
        BmobQuery<Moments> query = new BmobQuery<>();
        query.order("-createdAt"); // 按时间降序查询
        query.include("author"); // 希望在查询帖子信息的同时也把发布人的信息查询出来
        if(actionType == STATE_MORE) { //加载更多
            // 处理时间查询
            Date date = null;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                date = sdf.parse(lastTime);
            } catch (java.text.ParseException e) {
                e.printStackTrace();
            }
            // 只查询小于等于最后一个item发表时间的数据
            query.addWhereLessThanOrEqualTo("createdAt", new BmobDate(date));
            // query.setSkip(page*limit+1); // 跳过之前页数并去掉重复数据
        } else {
            query.setSkip(0);
        }
        query.setLimit(limit);//设置每页数据个数
        //查找数据
        query.findObjects(new FindListener<Moments>() {
            @Override
            public void done(List<Moments> list, BmobException e) {
                if (e==null) {
                    if (list.size()>0) {
                        if(actionType == STATE_REFRESH) {
                            currentPage = 0;
                            momentsList.clear();
                        }
                        for (Moments td : list) {
                            momentsList.add(td);
                        }
                        currentPage++;
                        momentsAdapter.notifyDataSetChanged();
                        lastTime = momentsList.get(momentsList.size()-1).getCreatedAt(); // 获取最后时间
                        if (actionType == STATE_MORE) {
                            swipeRefresh.setLoading(false); // 结束旋转ProgressBar
                        }
                        LogUtils.i("JAVA", "第"+currentPage+"页数据加载完成");
                    } else if (actionType == STATE_MORE) {
                        ToastUtils.showToastShort("没有更多数据了");
                        swipeRefresh.setLoading(false); // 结束旋转ProgressBar
                    } else if (actionType == STATE_REFRESH) {
                        ToastUtils.showToastShort("服务器没有数据");
                    }
                    loading = false;
                    swipeRefresh.setRefreshing(false); // 请求完成结束刷新状态
                } else {
                    if (actionType == STATE_MORE) {
                        swipeRefresh.setLoading(false); // 结束旋转ProgressBar
                    }
                    ToastUtils.showToastShort("请求服务器异常,请稍后重试");
                    loading = false;
                    swipeRefresh.setRefreshing(false);
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            // 登录
            case AppConstant.RESULT_UPDATE_INFO:
                if (User.getCurrentUser()!=null) {
                    getData(0, STATE_REFRESH);
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab_add_moment);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (User.getCurrentUser()!=null) {
                    ToastUtils.showToastShort(User.getCurrentUser().getUsername());
                    UIManager.showPublishMoment(getActivity());
                } else {
                    ToastUtils.showToastShort("您还没有登录");
                }
            }
        });
    }

    private void refreshFruits() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //initFruits();
                        momentsAdapter.notifyDataSetChanged();
                        swipeRefresh.setRefreshing(false);
                    }
                });
            }
        }).start();
    }

    //private void initFruits() {
    //    momentsList.clear();
    //    for (int i = 0; i < momentses.length; i++) {
    //        //Random random = new Random();
    //        //int index = random.nextInt(momentses.length);
    //        momentsList.add(momentses[i]);
    //    }
    //}
}
