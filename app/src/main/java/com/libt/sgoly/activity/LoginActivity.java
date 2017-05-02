package com.libt.sgoly.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.libt.sgoly.R;
import com.libt.sgoly.db.User;
import com.libt.sgoly.manager.UIManager;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;


public class LoginActivity extends BaseActivity {

    public static final String TAG = "LoginActivity";

    private EditText loginName;
    private EditText loginPassword;
    private ImageView login;
    private TextView scan;
    private TextView findback;
    private ImageView sign;
    private ImageView mLayoutBg;
    private ImageView mAccountBg;
    private ImageView mPasswordBg;

    private String name;
    private String password;
    private User user;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init() {
        loginName= (EditText) findViewById(R.id.login_username);
        loginPassword= (EditText) findViewById(R.id.login_password);
        login= (ImageView) findViewById(R.id.login_in);
        scan= (TextView) findViewById(R.id.login_scan);
        findback= (TextView) findViewById(R.id.login_findback);
        sign= (ImageView) findViewById(R.id.login_sign);

        dialog = new ProgressDialog(this);
        dialog.setMessage("正在登录...");
        dialog.setCanceledOnTouchOutside(false);

        login.setOnClickListener(clickListener);
        scan.setOnClickListener(clickListener);
        findback.setOnClickListener(clickListener);
        sign.setOnClickListener(clickListener);

    }

    View.OnClickListener clickListener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == login) {
                login();
            } else if (view == scan) {
                showToast("有待开发");
            } else if (view == findback) {
                showToast("有待开发");
            } else if (view == sign) {
                UIManager.showRegister(LoginActivity.this);
            }
        }
    };

    /**
     * 验证账号和密码是否正确
     */
    private void login() {
        name = loginName.getText().toString().trim();
        password = loginPassword.getText().toString().trim();

        if (TextUtils.isEmpty(name)|| TextUtils.isEmpty(password)) {
            showToast("用户名或者密码不能为空");
            return;
        } else {
            dialog.show();
            queryPersonByUsername(name, password);
        }
    }

    private void queryPersonByUsername(String username, final String password) {
        BmobQuery<User> bmobQuery = new BmobQuery<>();
        bmobQuery.addWhereEqualTo("username", username);
        bmobQuery.getObject(username, new QueryListener<User>() {
            @Override
            public void done(User user, BmobException e) {
                if(e==null){
                    if(user.getPassword().equals(password)){
                        UIManager.showMain(LoginActivity.this);
                        dialog.dismiss();
                        LoginActivity.this.finish();
                    }else{
                        showToast("登录密码错误！");
                        dialog.dismiss();
                    }
                }else{
                    Log.i("bmob","失败："+e.getMessage()+","+e.getErrorCode());
                    showToast("用户不存在，请注册");
                    dialog.dismiss();
                }
            }
        });
    }

}
