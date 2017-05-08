package com.libt.sgoly.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.libt.sgoly.R;
import com.libt.sgoly.db.User;
import com.libt.sgoly.util.LogUtils;
import com.libt.sgoly.util.ToastUtils;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

import static android.R.attr.name;

public class ModifyPersonalInfo extends Activity {

    private ImageView back;
    private Button save;
    private EditText content;
    private TextView tip;
    private String type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_personal_info);
        type=getIntent().getStringExtra("type");
        TextView title = (TextView) findViewById(R.id.modify_title);
        title.setText(type);
        init();
        initView();
    }

    private void init() {
        back = (ImageView) findViewById(R.id.modify_back);
        save = (Button) findViewById(R.id.modify_save);
        content = (EditText) findViewById(R.id.modify_content);
        tip = (TextView) findViewById(R.id.modify_tip);

        back.setOnClickListener(clickListener);
        save.setOnClickListener(clickListener);
    }


    /**
     * 字数提示
     */
    private void initView() {
        content.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tip.setText(s.length() + "/24");
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }


    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == back) {
                onBackPressed();
            } else if (view == save) {
                if (type.equals("修改昵称")){
                    saveNickname();
                }else if (type.equals("个性签名")){
                    saveMotto();
                }else if (type.equals("性别")){
                    saveSex();
                }else if (type.equals("年龄")){
                    saveAge();
                }else if (type.equals("地址")){
                    saveAddress();
                }
            }
        }
    };

    /**
     * 保存昵称
     */
    private void saveNickname() {
        String nickname=content.getText().toString().trim();
        if (!TextUtils.isEmpty(nickname)){
            User userEntity = new User();
            userEntity.setNickname(nickname);
            BmobUser bmobUser = BmobUser.getCurrentUser();
            userEntity.update(bmobUser.getObjectId(), new UpdateListener() {
                @Override
                public void done(BmobException e) {
                    if (e == null) {
                        //修改成功
                        ToastUtils.showToastShort("修改成功");
                        ModifyPersonalInfo.this.finish();
                    } else {
                        ToastUtils.showToastShort("修改失败");
                        ModifyPersonalInfo.this.finish();
                        LogUtils.i("JAVA", "修改失败"+e.toString());
                    }
                }
            });
        }else{
            ToastUtils.showToastShort("输入内容不能为空");
        }
    }

    /**
     * 保存个性签名
     */
    private void saveMotto(){
        String motto=content.getText().toString().trim();
        if (!TextUtils.isEmpty(motto)){
            User userEntity = new User();
            userEntity.setMotto(motto);
            BmobUser bmobUser = BmobUser.getCurrentUser();
            userEntity.update(bmobUser.getObjectId(), new UpdateListener() {
                @Override
                public void done(BmobException e) {
                    if (e == null) {
                        //修改成功
                        ToastUtils.showToastShort("修改成功");
                        ModifyPersonalInfo.this.finish();
                    } else {
                        ToastUtils.showToastShort("修改失败");
                        ModifyPersonalInfo.this.finish();
                        LogUtils.i("JAVA", "修改失败"+e.toString());
                    }
                }
            });
        }else{
            ToastUtils.showToastShort("输入内容不能为空");
        }
    }

    /**
     * 保存性别
     */
    private void saveSex(){
        String sex=content.getText().toString().trim();
        if (!TextUtils.isEmpty(sex)){
            User userEntity = new User();
            if (sex.equals("boy")) {
                userEntity.setSex(true);
            } else {
                userEntity.setSex(false);
            }
            BmobUser bmobUser = BmobUser.getCurrentUser();
            userEntity.update(bmobUser.getObjectId(), new UpdateListener() {
                @Override
                public void done(BmobException e) {
                    if (e == null) {
                        //修改成功
                        ToastUtils.showToastShort("修改成功");
                        ModifyPersonalInfo.this.finish();
                    } else {
                        ToastUtils.showToastShort("修改失败");
                        ModifyPersonalInfo.this.finish();
                        LogUtils.i("JAVA", "修改失败"+e.toString());
                    }
                }
            });
        }else{
            ToastUtils.showToastShort("输入内容不能为空");
        }
    }

    /**
     * 保存地址
     */
    private void saveAddress(){
        String address=content.getText().toString().trim();
        if (!TextUtils.isEmpty(address)){
            User userEntity = new User();
            userEntity.setAddress(address);
            BmobUser bmobUser = BmobUser.getCurrentUser();
            userEntity.update(bmobUser.getObjectId(), new UpdateListener() {
                @Override
                public void done(BmobException e) {
                    if (e == null) {
                        //修改成功
                        ToastUtils.showToastShort("修改成功");
                        ModifyPersonalInfo.this.finish();
                    } else {
                        ToastUtils.showToastShort("修改失败");
                        ModifyPersonalInfo.this.finish();
                        LogUtils.i("JAVA", "修改失败"+e.toString());
                    }
                }
            });
        }else{
            ToastUtils.showToastShort("输入内容不能为空");
        }
    }

    /**
     * 保存年龄
     */
    private void saveAge(){
        String age=content.getText().toString().trim();
        if (!TextUtils.isEmpty(age)){
            User userEntity = new User();
            userEntity.setBirth(age);
            BmobUser bmobUser = BmobUser.getCurrentUser();
            userEntity.update(bmobUser.getObjectId(), new UpdateListener() {
                @Override
                public void done(BmobException e) {
                    if (e == null) {
                        //修改成功
                        ToastUtils.showToastShort("修改成功");
                        ModifyPersonalInfo.this.finish();
                    } else {
                        ToastUtils.showToastShort("修改失败");
                        ModifyPersonalInfo.this.finish();
                        LogUtils.i("JAVA", "修改失败"+e.toString());
                    }
                }
            });
        }else{
            ToastUtils.showToastShort("输入内容不能为空");
        }
    }
}
