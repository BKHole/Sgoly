package com.libt.sgoly.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.libt.sgoly.R;

public class ModifyPasswordActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_password);
        ImageView back= (ImageView) findViewById(R.id.password_img_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}
