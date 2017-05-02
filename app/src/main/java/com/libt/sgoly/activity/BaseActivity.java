package com.libt.sgoly.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.widget.Toast;

import com.libt.sgoly.AppConstant;
import com.libt.sgoly.R;
import com.libt.sgoly.manager.ActivityManager;
import com.libt.sgoly.util.Accompaniment;

import cn.bmob.v3.Bmob;

public abstract class BaseActivity extends AppCompatActivity {

    public Accompaniment accompaniment = new Accompaniment(this, R.raw.tag_inventoried);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        // 初始化 Bmob SDK
        // 使用时请将第二个参数Application ID替换成你在Bmob服务器端创建的Application ID
        Bmob.initialize(this, AppConstant.Bmob_APPID);
        accompaniment.init();
        ActivityManager.addActivity(this);
    }

    Toast mToast;

    public void showToast(String text) {
        if (!TextUtils.isEmpty(text)) {
            if (mToast == null) {
                mToast = Toast.makeText(getApplicationContext(), text,
                        Toast.LENGTH_SHORT);
            } else {
                mToast.setText(text);
            }
            mToast.show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManager.removeActivity(this);
    }
}
