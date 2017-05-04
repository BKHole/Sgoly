package com.libt.sgoly.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.libt.sgoly.R;
import com.libt.sgoly.db.User;
import com.libt.sgoly.manager.UIManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

import static org.kymjs.kjframe.ui.ViewInject.toast;

public class RegisterActivity extends BaseActivity {

    private ImageView back;
    private ImageView signUp;
    private EditText editPhone;
    private EditText editPassword;
    private EditText editConfirmPassword;
    //密码规则 正则
    private String reg = "^[\\@A-Za-z0-9\\!\\#\\$\\%\\^\\&\\*\\.\\~]{6,22}$";
    private String phone;
    private String password;
    private String confirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
    }

    private void init() {
        back= (ImageView) findViewById(R.id.sign_back);
        signUp= (ImageView) findViewById(R.id.sign_up);
        editPhone= (EditText) findViewById(R.id.sign_phone);
        editPassword= (EditText) findViewById(R.id.sign_password);
        editConfirmPassword= (EditText) findViewById(R.id.sign_confirm_password);

        back.setOnClickListener(clickListener);
        signUp.setOnClickListener(clickListener);
    }

    View.OnClickListener clickListener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.sign_back:
                    accompaniment.start();
                    RegisterActivity.this.finish();
                    break;
                case R.id.sign_up:
                    accompaniment.start();
                    register();
                    break;
            }
        }
    };

    private void register() {
        phone = editPhone.getText().toString().trim();
        password = editPassword.getText().toString().trim();
        confirmPassword = editConfirmPassword.getText().toString().trim();
        if(password.length() < 6){
            toast("密码不能小于6位");
            return;
        }

        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(password);
        if(!matcher.matches()){
            showToast("密码格式不正确");
            return;
        }
        if (password.equals(confirmPassword)) {
            User user=new User();
            user.setUsername(phone);
            user.setPassword(password);
            user.signUp(new SaveListener<User>() {
                @Override
                public void done(User s, BmobException e) {
                    if (e == null) {
                        showToast("注册成功，返回objectId为：" + s.getObjectId());
                        UIManager.showLogin(RegisterActivity.this);
                        RegisterActivity.this.finish();
                    } else {
                        showToast("注册失败：" + e.getMessage());
                    }
                }
            });

        } else {
            showToast("密码不一致");
        }
    }
}
