package com.libt.sgoly.manager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.libt.sgoly.activity.CollectionActivity;
import com.libt.sgoly.activity.FruitDetailActivity;
import com.libt.sgoly.activity.LoginActivity;
import com.libt.sgoly.activity.MainActivity;
import com.libt.sgoly.activity.ModifyPasswordActivity;
import com.libt.sgoly.activity.ModifyPersonalInfo;
import com.libt.sgoly.activity.PersonInfoActivity;
import com.libt.sgoly.activity.PublishMomentActivity;
import com.libt.sgoly.activity.RegisterActivity;
import com.libt.sgoly.activity.SearchActivity;
import com.libt.sgoly.activity.SettingActivity;
import com.libt.sgoly.db.Fruit;
import com.libt.sgoly.db.User;

import static android.R.attr.name;

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
    public static void showFruitDetail(Context context, Fruit fruit) {
        Intent intent = new Intent(context, FruitDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("fruit", fruit);
        intent.putExtras(bundle);
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
    /**
     * 显示搜索
     *
     * @param context
     */
    public static void showSearch(Context context) {
        Intent intent = new Intent(context, SearchActivity.class);
        context.startActivity(intent);
    }
    /**
     * 显示修改个人信息
     *
     * @param context
     */
    public static void showModifyPersonalInfo(Context context, String data) {
        Intent intent = new Intent(context, ModifyPersonalInfo.class);
        intent.putExtra("type", data);
        context.startActivity(intent);
    }
}
