package cn.karent.nanhang.UI;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import cn.karent.nanhang.R;
import cn.karent.nanhang.activity.CourseActivity;
import cn.karent.nanhang.activity.ScoreActivity;

/**
 * Created by wan on 2016/12/26.
 * 底部菜单栏的UI
 */
public class BottomControlUI {
    /**
     * MainActivity的引用
     */
    private Activity mActivity;
    /**
     * 我的课表的引用
     */
    private View mCourse;
    /**
     * 我的资料的引用
     */
    private View mInformation;

    public BottomControlUI(Activity activity) {
        mActivity = activity;
        mCourse = activity.findViewById(R.id.main_course);
        mCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(mActivity, CourseActivity.class);
                mActivity.startActivity(intent);
            }
        });

        mInformation = activity.findViewById(R.id.main_information);
        mInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(mActivity, ScoreActivity.class);
                mActivity.startActivity(intent);
            }
        });
    }


}
