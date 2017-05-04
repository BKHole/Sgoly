package com.libt.sgoly.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.libt.sgoly.R;
import com.libt.sgoly.db.User;
import com.libt.sgoly.manager.UIManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

public class ModifyPasswordActivity extends BaseActivity {

    private ImageView back;
    private Button save;
    private EditText editOldPassword;
    private EditText editNewPassword;
    private EditText editConfirmPassword;
    private String reg = "^[\\@A-Za-z0-9\\!\\#\\$\\%\\^\\&\\*\\.\\~]{6,22}$";
    private String oldPassword;
    private String newPassword;
    private String confirmNewPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_password);
        init();
    }

    private void init() {
        back= (ImageView) findViewById(R.id.password_img_back);
        save= (Button) findViewById(R.id.modify_password_save);
        editOldPassword= (EditText) findViewById(R.id.old_password);
        editNewPassword= (EditText) findViewById(R.id.new_password);
        editConfirmPassword= (EditText) findViewById(R.id.confirm_password);

        back.setOnClickListener(clickListener);
        save.setOnClickListener(clickListener);
    }

    View.OnClickListener clickListener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view== back){
                accompaniment.start();
                onBackPressed();
            }else if (view== save){
                accompaniment.start();
                modifyPassword();
            }
        }
    };

    private void modifyPassword() {
        oldPassword = editOldPassword.getText().toString().trim();
        newPassword = editNewPassword.getText().toString().trim();
        confirmNewPassword = editConfirmPassword.getText().toString().trim();
        if(newPassword.length()< 6){
            showToast("密码不能小于6位");
            return;
        }

        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(newPassword);
        if(!matcher.matches()){
            showToast("密码格式不正确");
            return;
        }
        if (newPassword.equals(confirmNewPassword)) {
            User.updateCurrentUserPassword(oldPassword, newPassword, new UpdateListener() {
                @Override
                public void done(BmobException e) {
                    if (e == null) {
                        showToast("密码修改成功" );
                        UIManager.showLogin(ModifyPasswordActivity.this);
                        ModifyPasswordActivity.this.finish();
                    } else {
                        showToast("密码修改失败：" + e.getMessage());
                    }
                }
            });
        } else {
            showToast("密码不一致");
        }
    }
}
