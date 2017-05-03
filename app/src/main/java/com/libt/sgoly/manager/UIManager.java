package com.libt.sgoly.manager;

import android.content.Context;
import android.content.Intent;

import com.libt.sgoly.activity.CollectionActivity;
import com.libt.sgoly.activity.FruitDetailActivity;
import com.libt.sgoly.activity.LoginActivity;
import com.libt.sgoly.activity.MainActivity;
import com.libt.sgoly.activity.ModifyPasswordActivity;
import com.libt.sgoly.activity.PersonInfoActivity;
import com.libt.sgoly.activity.PublishMomentActivity;
import com.libt.sgoly.activity.RegisterActivity;
import com.libt.sgoly.activity.SettingActivity;

/**
 * 管理activity跳转
 * Created by Administrator on 2017/4/20 0020.
 */

public class UIManager {
    /**
     * 显示主界面
     *
     * @param context
     */
    public static void showMain(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    /**
     * 显示登录
     *
     * @param context
     */
    public static void showLogin(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    /**
     * 显示注册
     *
     * @param context
     */
    public static void showRegister(Context context) {
        Intent intent = new Intent(context, RegisterActivity.class);
        context.startActivity(intent);
    }


    /**
     * 显示设置
     *
     * @param context
     */
    public static void showSetting(Context context) {
        Intent intent = new Intent(context, SettingActivity.class);
        context.startActivity(intent);
    }

    /**
     * 显示发布动态
     *
     * @param context
     */
    public static void showPublishMoment(Context context) {
        Intent intent = new Intent(context, PublishMomentActivity.class);
        context.startActivity(intent);
    }

    /**
     * 显示水果详情
     *
     * @param context
     */
    public static void showFruitDetail(Context context) {
        Intent intent = new Intent(context, FruitDetailActivity.class);
        context.startActivity(intent);
    }

    /**
     * 显示个人信息
     *
     * @param context
     */
    public static void showPersonalInfo(Context context) {
        Intent intent = new Intent(context, PersonInfoActivity.class);
        context.startActivity(intent);
    }

    /**
     * 显示收藏
     *
     * @param context
     */
    public static void showCollection(Context context) {
        Intent intent = new Intent(context, CollectionActivity.class);
        context.startActivity(intent);
    }

    /**
     * 显示修改密码
     *
     * @param context
     */
    public static void showModifyPassword(Context context) {
        Intent intent = new Intent(context, ModifyPasswordActivity.class);
        context.startActivity(intent);
    }
}
