package com.libt.sgoly.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.libt.sgoly.R;

public class SettingActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        TextView title= (TextView) findViewById(R.id.titlebar_text_title);
        title.setVisibility(View.VISIBLE);
        title.setText("设置");
        ImageView back= (ImageView) findViewById(R.id.titlebar_img_back);
        back.setVisibility(View.VISIBLE);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}
