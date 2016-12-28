package cn.karent.nanhang.activity;

import android.app.Activity;
import android.os.Bundle;
import cn.karent.nanhang.R;
import cn.karent.nanhang.adapter.ScoreAdapter;

/**
 * Created by wan on 2016/12/28.
 * 课表查询的Activity
 */
public class CourseActivity extends Activity {

    /**
     * 显示周数的Adapter
     */
    private ScoreAdapter mWeekAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_layout);
    }
}
