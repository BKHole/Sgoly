package com.libt.sgoly.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.libt.sgoly.R;
import com.libt.sgoly.db.User;
import com.libt.sgoly.manager.ActivityManager;
import com.libt.sgoly.manager.UIManager;

public class SettingActivity extends BaseActivity {

    private RelativeLayout editInfo;
    private RelativeLayout modifyPassword;
    private RelativeLayout clearCache;
    private RelativeLayout feedBack;
    private RelativeLayout update;
    private RelativeLayout about;
    private ImageView loginOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        TextView title = (TextView) findViewById(R.id.titlebar_text_title);
        title.setVisibility(View.VISIBLE);
        title.setText("设置");
        ImageView back = (ImageView) findViewById(R.id.titlebar_img_back);
        back.setVisibility(View.VISIBLE);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        init();
    }

    private void init() {
        editInfo = (RelativeLayout) findViewById(R.id.setting_edit_info);
        modifyPassword = (RelativeLayout) findViewById(R.id.setting_modify_password);
        clearCache = (RelativeLayout) findViewById(R.id.setting_clear_cache);
        feedBack = (RelativeLayout) findViewById(R.id.setting_feedback);
        update = (RelativeLayout) findViewById(R.id.setting_update);
        about = (RelativeLayout) findViewById(R.id.setting_about);
        loginOut = (ImageView) findViewById(R.id.setting_login_out);

        editInfo.setOnClickListener(clickListener);
        modifyPassword.setOnClickListener(clickListener);
        clearCache.setOnClickListener(clickListener);
        feedBack.setOnClickListener(clickListener);
        update.setOnClickListener(clickListener);
        about.setOnClickListener(clickListener);
        loginOut.setOnClickListener(clickListener);
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == editInfo) {
                UIManager.showPersonalInfo(SettingActivity.this);
            } else if (view == modifyPassword) {
                UIManager.showModifyPassword(SettingActivity.this);
            }else if (view==clearCache){

            }else if (view== feedBack){

            }else if (view==update){

            }else if (view==about){

            }else if (view==loginOut){
                User.logOut();
                ActivityManager.finishAll();
            }
        }
    };
}
