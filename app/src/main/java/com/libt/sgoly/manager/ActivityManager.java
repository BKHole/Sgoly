package com.libt.sgoly.manager;

import android.app.Activity;
import android.widget.Toast;

import com.libt.sgoly.AppContext;
import com.libt.sgoly.db.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import cn.bmob.v3.BmobUser;

import static cn.bmob.v3.BmobUser.logOut;

/**
 * 活动管理器
 * Created by Administrator on 2017/4/11 0011.
 */

public class ActivityManager {
    public static boolean isExit = false;
    public static List<Activity> activities=new ArrayList<>();

    public static void addActivity(Activity activity){
        activities.add(activity);
    }

    public static void removeActivity(Activity activity){
        activities.remove(activity);
    }

    public static void finishAll(){
        for (Activity activity:activities){
            if (!activity.isFinishing()){
                activity.finish();
            }
        }
    }

    /**
     * 双击退出程序
     * @param activity
     */
    public static void exitBy2Click(Activity activity) {
        Timer tExit;
        if (!isExit) {
            isExit = true;
            Toast.makeText(activity.getApplicationContext(), "再按一次退出", Toast.LENGTH_SHORT).show();
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false;
                }
            }, 2000);

        } else {
            User.logOut();
            finishAll();
        }
    }
}
