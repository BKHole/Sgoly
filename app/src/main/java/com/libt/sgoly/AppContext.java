package com.libt.sgoly;

import android.app.Application;

/**
 * 全局应用程序类：用于保存和调用全局应用配置及访问网络数据
 * Created by Administrator on 2017/4/20 0020.
 */

public class AppContext extends Application {
    private boolean login;

    public boolean isLogin() {
        return login;
    }
}
