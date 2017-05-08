package com.libt.sgoly.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.libt.sgoly.AppConstant;
import com.libt.sgoly.R;
import com.libt.sgoly.db.User;
import com.libt.sgoly.manager.UIManager;
import com.libt.sgoly.util.LogUtils;
import com.libt.sgoly.util.ToastUtils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadFileListener;
import de.hdodenhof.circleimageview.CircleImageView;

public class PersonInfoActivity extends BaseActivity {

    private CircleImageView avatarLayout;
    private TextView nicknameLayout;
    private TextView birthLayout;
    private TextView addressLayout;
    private TextView sexLayout;
    private TextView regionLayout;
    private TextView mottoLayout;
    private ImageView back;

    private AlertDialog photoDialog;
    public static final String PHOTO_IMAGE_FILE_NAME = "fileImg.jpg";
    public static final int CAMERA_REQUEST_CODE = 100;
    public static final int IMAGE_REQUEST_CODE = 101;
    public static final int RESULT_REQUEST_CODE = 102;
    private static final int REQUEST_BLUETOOTH_PERMISSION = 10;
    private File tempFile = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_info);
        TextView title = (TextView) findViewById(R.id.titlebar_text_title);
        title.setVisibility(View.VISIBLE);
        title.setText("个人信息");

        init();
        initView();
    }

    private void init() {
        avatarLayout = (CircleImageView) findViewById(R.id.setting_avatar);
        nicknameLayout = (TextView) findViewById(R.id.setting_nickname);
        birthLayout = (TextView) findViewById(R.id.setting_birth);
        addressLayout = (TextView) findViewById(R.id.setting_address);
        sexLayout = (TextView) findViewById(R.id.setting_sex);
        regionLayout = (TextView) findViewById(R.id.setting_region);
        mottoLayout = (TextView) findViewById(R.id.setting_motto);
        back = (ImageView) findViewById(R.id.titlebar_img_back);
        back.setVisibility(View.VISIBLE);

        avatarLayout.setOnClickListener(clickListener);
        nicknameLayout.setOnClickListener(clickListener);
        birthLayout.setOnClickListener(clickListener);
        addressLayout.setOnClickListener(clickListener);
        sexLayout.setOnClickListener(clickListener);
        regionLayout.setOnClickListener(clickListener);
        mottoLayout.setOnClickListener(clickListener);
        back.setOnClickListener(clickListener);
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == avatarLayout) {
                if (BmobUser.getCurrentUser() == null) {
                    ToastUtils.showToastShort("您还没有登录");
                } else {
                    showDialog();
                }
            } else if (view == nicknameLayout) {
                UIManager.showModifyPersonalInfo(PersonInfoActivity.this, "修改昵称");
            } else if (view == birthLayout) {
                UIManager.showModifyPersonalInfo(PersonInfoActivity.this, "年龄");
            } else if (view == addressLayout) {
                UIManager.showModifyPersonalInfo(PersonInfoActivity.this, "地址");
            } else if (view == sexLayout) {
                UIManager.showModifyPersonalInfo(PersonInfoActivity.this, "性别");
            } else if (view == regionLayout) {

            } else if (view == mottoLayout) {
                UIManager.showModifyPersonalInfo(PersonInfoActivity.this, "个性签名");
            } else if (view == back) {
                onBackPressed();
            }
        }
    };

    public void initView() {

        if (BmobUser.getCurrentUser() != null) {
            User userEntity = BmobUser.getCurrentUser(User.class);
            if (userEntity.getAvatar() != null) {
                Glide.with(this).load(userEntity.getAvatar().getFileUrl()).into(avatarLayout);
            }
            nicknameLayout.setText(userEntity.getNickname());
            sexLayout.setText(userEntity.isSex() ? "男" : "女");
            birthLayout.setText(userEntity.getBirth());
            addressLayout.setText(userEntity.getAddress());
            mottoLayout.setText(userEntity.getMotto());
        }
    }

    /**
     * 点击头像的提示对话框
     */
    private void showDialog() {
        photoDialog = new AlertDialog.Builder(this).create();
        photoDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        photoDialog.show();
        Window window = photoDialog.getWindow();
        window.setContentView(R.layout.dialog_photo); // 修改整个dialog窗口的显示
        window.setGravity(Gravity.BOTTOM);

        WindowManager.LayoutParams lp = photoDialog.getWindow().getAttributes();
        DisplayMetrics dm = new DisplayMetrics();
        window.getWindowManager().getDefaultDisplay().getMetrics(dm);
        lp.width = dm.widthPixels;
        photoDialog.getWindow().setAttributes(lp); // 设置宽度

        photoDialog.findViewById(R.id.btn_camera).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toCamera();
            }
        });
        photoDialog.findViewById(R.id.btn_picture).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toPicture();
            }
        });
        photoDialog.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                photoDialog.dismiss();
            }
        });
    }

    /**
     * 跳转相机
     */
    public void toCamera() {
        requestWESPermission(); // 安卓6.0以上需要申请权限
        photoDialog.dismiss();
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); // 调用系统的拍照功能
        // 判断内存卡是否可用，可用的话就进行储存
        intent.putExtra(MediaStore.EXTRA_OUTPUT,
                Uri.fromFile(new File(Environment.getExternalStorageDirectory(), PHOTO_IMAGE_FILE_NAME)));
        startActivityForResult(intent, CAMERA_REQUEST_CODE);
    }

    /**
     * 跳转相册
     */
    private void toPicture() {
        photoDialog.dismiss();
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(intent, IMAGE_REQUEST_CODE);
    }

    /**
     * 裁剪
     *
     * @param uri
     */
    private void startPhotoZoom(Uri uri) {
        if (uri == null) {
            LogUtils.e("JAVA", "裁剪uri == null");
            return;
        }
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 设置裁剪
        intent.putExtra("crop", "true");
        // 裁剪宽高比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // 裁剪图片的质量
        intent.putExtra("outputX", 320);
        intent.putExtra("outputY", 320);
        // 发送数据
        intent.putExtra("return-data", true);
        startActivityForResult(intent, RESULT_REQUEST_CODE);
    }

    /**
     * 设置icon并上传服务器
     *
     * @param data
     */
    private void setImageToView(Intent data) {
        Bundle bundle = data.getExtras();
        if (bundle != null) {
            final Bitmap bitmap = bundle.getParcelable("data");
            final BmobFile bmobFile = new BmobFile(bitmapToFile(bitmap));

            bmobFile.uploadblock(new UploadFileListener() {
                @Override
                public void done(BmobException e) {
                    if (e == null) {
                        // 此时上传成功
                        User user = new User();
                        user.setAvatar(bmobFile);// 获取文件并赋值给实体类
                        BmobUser bmobUser = BmobUser.getCurrentUser();
                        user.update(bmobUser.getObjectId(), new UpdateListener() {
                            @Override
                            public void done(BmobException e) {
                                if (e == null) {
                                    avatarLayout.setImageBitmap(bitmap);
                                    ToastUtils.showToastShort("头像更新成功");
                                } else {
                                    ToastUtils.showToastShort("头像更新失败");
                                }
                            }
                        });
                    } else {
                        ToastUtils.showToastShort("头像更新失败");
                    }
                    // 既然已经设置了图片，我们原先的就应该删除
                    if (tempFile != null) {
                        tempFile.delete();
                        LogUtils.i("JAVA", "tempFile已删除");
                    }
                }

                @Override
                public void onProgress(Integer value) {
                    // 返回的上传进度（百分比）
                }
            });
        }
    }

    /**
     * Bitmap转File
     */
    public File bitmapToFile(Bitmap bitmap) {
        tempFile = new File(Environment.getExternalStorageDirectory(), PHOTO_IMAGE_FILE_NAME);
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(tempFile));
            if (bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos)) {
                bos.flush();
                bos.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tempFile;
    }

    /**
     * 动态申请权限
     */
    private void requestWESPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            int checkCallPhonePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if (checkCallPhonePermission != PackageManager.PERMISSION_GRANTED) {
                // 判断是否需要 向用户解释，为什么要申请该权限
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE))
                    ToastUtils.showToastShort("Need write external storage permission.");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_BLUETOOTH_PERMISSION);
                return;
            } else {
            }
        } else {
        }
    }

    /**
     * 授权回调处理
     */
    @Override
    public void onRequestPermissionsResult(int permsRequestCode, String[] permissions, int[] grantResults) {
        switch (permsRequestCode) {
            case REQUEST_BLUETOOTH_PERMISSION:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // 授权成功
                } else {
                    // 授权拒绝
                }
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            // 登录
            case AppConstant.RESULT_UPDATE_INFO:
                if (User.getCurrentUser() != null) {
                    initView();
                }
                break;
            // 退出登录
            case AppConstant.RESULT_UPDATE_EXIT_INFO:
                if (User.getCurrentUser() == null) {
                    BmobUser.logOut();
                }
                break;
            case IMAGE_REQUEST_CODE: // 相册数据
                if (data != null) {
                    startPhotoZoom(data.getData());
                }
                break;
            case CAMERA_REQUEST_CODE: // 相机数据
                tempFile = new File(Environment.getExternalStorageDirectory(), PHOTO_IMAGE_FILE_NAME);
                startPhotoZoom(Uri.fromFile(tempFile));
                break;
            case RESULT_REQUEST_CODE: // 有可能点击舍弃
                if (data != null) {
                    // 拿到图片设置, 然后需要删除tempFile
                    setImageToView(data);
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //SPUtils.putImage(this, "profile_image", avatarLayout); // 保存
    }
}
