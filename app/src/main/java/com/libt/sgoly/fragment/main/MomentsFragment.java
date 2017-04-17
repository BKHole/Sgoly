package com.libt.sgoly.fragment.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class MomentsFragment extends Fragment {

    View view;
    private List<Moments> momentsList = new ArrayList<>();
    private Moments[] momentses={new Moments("大哥王振华", R.drawable.proce, "【简单四步，学会复印文件】①预热：在复印前按设备上的任意键将设备唤醒；②检查原稿：需要复印的材料对比度要高，材料上不要有书钉、大头针等硬物；③放置原稿：掀开复印机上盖，将要复印的原稿面朝下，对准刻度，盖上盖子；④设定份数、大小、色彩，检查是否缺纸，最后复印。",R.drawable.zone_bg, "2017-4-13", AppConstant.COMMENTS_STATUS_0),
    new Moments("猪猪爱讲冷笑话",R.drawable.portrait,"美国知名脱口秀Jimmy Kimmel Live（吉米鸡毛秀）在最新节目里针对美联航扔下亚洲医生乘客事件火力全开， 大怼美联航！最后的宣传片很棒了，建议美联航拿去使用，看的真是大快人心！！",R.drawable.pig, "2017-4-12", AppConstant.COMMENTS_STATUS_1),
    new Moments("贾克斯",R.drawable.object, "来玩游戏",R.drawable.zone, "2017-4-11", AppConstant.COMMENTS_STATUS_0)};
    private MomentsAdapter momentsAdapter;
    private SwipeRefreshLayout swipeRefresh;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.frag_faxian, container, false);

        TextView title= (TextView) view.findViewById(R.id.titlebar_text_title);
        title.setVisibility(View.VISIBLE);
        title.setText("发现");
        initFruits();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        momentsAdapter =new MomentsAdapter(momentsList);
        recyclerView.setAdapter(momentsAdapter);
        swipeRefresh = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh);
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshFruits();
            }
        });
        return view;
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
                        initFruits();
                        momentsAdapter.notifyDataSetChanged();
                        swipeRefresh.setRefreshing(false);
                    }
                });
            }
        }).start();
    }
    private void initFruits() {
        momentsList.clear();
        for (int i = 0; i < momentses.length; i++) {
            //Random random = new Random();
            //int index = random.nextInt(momentses.length);
            momentsList.add(momentses[i]);
        }
    }
}
