package com.libt.sgoly.fragment.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.libt.sgoly.R;
import com.libt.sgoly.adapter.FruitAdapter;
import com.libt.sgoly.db.Fruit;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;


public class FruitFragment extends Fragment {
    View view;
    private Fruit[] fruits = {new Fruit("Apple", R.drawable.apple), new Fruit("Banana", R.drawable.banana),
            new Fruit("Orange", R.drawable.orange), new Fruit("Watermelon", R.drawable.watermelon),
            new Fruit("Pear", R.drawable.pear), new Fruit("Grape", R.drawable.grape),
            new Fruit("Pineapple", R.drawable.pineapple), new Fruit("Strawberry", R.drawable.strawberry),
            new Fruit("Cherry", R.drawable.cherry), new Fruit("Mango", R.drawable.mango)};

    private List<Fruit> fruitList = new ArrayList<>();

    private FruitAdapter adapter;
    private SwipeRefreshLayout swipeRefresh;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_leyuan, container, false);
        initFruits();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(this.getActivity(), 2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new FruitAdapter(fruitList);
        recyclerView.setAdapter(adapter);
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

    //@Override
    //public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    //    super.onActivityCreated(savedInstanceState);
    //    swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
    //    swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
    //        @Override
    //        public void onRefresh() {
    //            new Handler().postDelayed(new Runnable() {
    //
    //                @Override
    //                public void run() {
    //                    initFruits();
    //                    adapter.notifyDataSetChanged();
    //                    swipeRefresh.setRefreshing(false);
    //                }
    //            }, 2000);
    //        }
    //
    //
    //    });
    //}

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
                        adapter.notifyDataSetChanged();
                        swipeRefresh.setRefreshing(false);
                    }
                });
            }
        }).start();
    }

    private void initFruits() {
        fruitList.clear();
        for (int i = 0; i < 50; i++) {
            Random random = new Random();
            int index = random.nextInt(fruits.length);
            fruitList.add(fruits[index]);
        }
        //BmobQuery<Fruit> query = new BmobQuery<>();
        ////返回50条数据，如果不加上这条语句，默认返回10条数据
        //query.setLimit(10);
        ////执行查询方法
        //query.findObjects(new FindListener<Fruit>() {
        //    @Override
        //    public void done(List<Fruit> object, BmobException e) {
        //        if (e == null) {
        //            for (Fruit fruit : object) {
        //                fruitList.add(fruit);
        //            }
        //            adapter.notifyDataSetChanged();
        //        } else {
        //            Log.i("bmob", "失败：" + e.getMessage() + "," + e.getErrorCode());
        //        }
        //    }
        //});
    }
}
