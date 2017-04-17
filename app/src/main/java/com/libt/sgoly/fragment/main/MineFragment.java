package com.libt.sgoly.fragment.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.libt.sgoly.R;


public class MineFragment extends Fragment {
    View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_wode, container, false);
        TextView title = (TextView) view.findViewById(R.id.titlebar_text_title);
        title.setVisibility(View.VISIBLE);
        title.setText("我的");
        return view;
    }
}
