package com.libt.sgoly.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.libt.sgoly.R;
import com.libt.sgoly.manager.UIManager;

public class StartActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_start);
        UIManager.showRegister(this);
    }
}
