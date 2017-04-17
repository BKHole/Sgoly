package com.libt.sgoly.manager;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * 活动管理器
 * Created by Administrator on 2017/4/11 0011.
 */

public class ActivityManager {
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
}
