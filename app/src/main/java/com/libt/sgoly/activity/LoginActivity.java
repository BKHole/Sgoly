package com.libt.sgoly.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.libt.sgoly.R;

public class LoginActivity extends AppCompatActivity {

    public static final String TAG = "LoginActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
}
