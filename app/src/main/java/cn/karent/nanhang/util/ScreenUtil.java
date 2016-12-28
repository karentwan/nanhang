package cn.karent.nanhang.util;

import android.util.DisplayMetrics;
import android.util.Log;

/**
 * Created by wan on 2016/12/17.
 */
public class ScreenUtil {

    /**
     * 获取屏幕的密度
     * @return
     */
    public static float getDensity() {
        DisplayMetrics dm = MyApplication.getContext().getResources().getDisplayMetrics();
        float density = dm.density;
        Log.d("density", density + "");
        return density;
    }

    public static DisplayMetrics getDislayMetrics() {
        return MyApplication.getContext().getResources().getDisplayMetrics();
    }

    public static int px2dp(float p) {
        float density = getDensity();
        return (int)(p / density + 0.5f);
    }

    public static int dp2px(float dip) {
        return (int)(dip * getDensity() + 0.5f);
    }

    /**
     * 获取屏幕宽度
     * @return
     */
    public static int getScreenWidth() {
        return getDislayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return getDislayMetrics().heightPixels;
    }


}
