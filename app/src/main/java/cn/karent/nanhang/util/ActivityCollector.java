package cn.karent.nanhang.util;

import android.app.Activity;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wan on 2017/1/3.
 * 活动集合
 */
public class ActivityCollector {

    public static List<Activity> activities = new ArrayList<>();

    public static void addActivity(Activity activity) {
        activities.add(activity);
    }

    public static void removeActicity(Activity activity) {
        activities.remove(activity);
    }

    public static void finishCurrent() {
        Activity a = activities.remove(activities.size() - 1);
        a.finish();
    }

    public static void finishAll() {
        for(Activity a : activities) {
            if( !a.isFinishing()) {
                a.finish();
            }
        }
    }


}
