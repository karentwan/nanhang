package cn.karent.nanhang.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.PopupWindow;
import android.widget.TextView;
import cn.karent.nanhang.R;
import cn.karent.nanhang.UI.CourseUI;
import cn.karent.nanhang.adapter.WeekAdapter;
import cn.karent.nanhang.model.CourseItem;
import cn.karent.nanhang.util.PopupWindowUtil;
import cn.karent.nanhang.util.ScreenUtil;

/**
 * Created by wan on 2016/12/28.
 * 课表查询的Activity
 */
public class CourseActivity extends Activity implements View.OnClickListener{

    /**
     * 显示周数的Adapter
     */
    private WeekAdapter mWeekAdapter;
    /**
     * 显示当前第几周
     */
    private TextView mWeekText;
    /**
     * 弹出框
     */
    private PopupWindow mPopupWindow;
    /**
     * PopupWindow的宽度
     */
    private int mWidth;
    /**
     * 取消PopupWindow
     */
    private View mCancelPopupWindow;
    /**
     * 界面右上角的设置按钮
     */
    private TextView mSetting;
    /**
     * 课程信息
     */
    private CourseItem[][] mCourseDetail = new CourseItem[12][7];

    private CourseUI mCourseUI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_layout);
        mWeekText = (TextView)findViewById(R.id.course_week);
        mWidth = ScreenUtil.dp2px(200);
        //初始化周数
        initAdapter();
        mWeekText.setOnClickListener(this);
        //取消popWindow的窗口
        mCancelPopupWindow = findViewById(R.id.course_cancelPopup);
        mCancelPopupWindow.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if( mPopupWindow != null) {
                    mPopupWindow.dismiss();
                    mPopupWindow = null;
                }
                return false;
            }
        });
        //将设置按钮显示
        mSetting = (TextView) findViewById(R.id.back_setting);
        mSetting.setVisibility(View.VISIBLE);
        initCourseDetail();
        mCourseUI = (CourseUI) findViewById(R.id.course_detail);
        mCourseUI.setChildren(mCourseDetail, 12, 7);
    }

    /**
     * 初始化周数的Adapter
     */
    private void initAdapter() {
        if( mWeekAdapter != null)
            return;
        mWeekAdapter = new WeekAdapter(this, R.layout.check_week_layout);
        mWeekAdapter.setWeekTextView(mWeekText);
        for(int i = 1; i <= 20; i++) {
            mWeekAdapter.add("第" + i + "周");
        }
    }

    @Override
    public void onClick(View v) {
        if( mPopupWindow == null) {
            mPopupWindow = PopupWindowUtil.createPopupWindow(this, mWidth, mWeekAdapter, new PopupWindowUtil.ItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    mWeekText.setText(((TextView)view).getText());
                    //让popupWindow消失
                    mPopupWindow.dismiss();
                    mPopupWindow = null;
                }
            });
        }
        //计算要偏移的距离
        int offsetX = ScreenUtil.dp2px(-70);
        int offsetY = ScreenUtil.dp2px(9);
        mPopupWindow.showAsDropDown(mWeekText, offsetX, offsetY);
    }

    /**
     * 初始化课程信息，测试数据
     */
    private void initCourseDetail() {
        for(int i = 0; i < 12; i++) {
            for(int j = 0; j < 7; j++) {
                mCourseDetail[i][j] = null;
            }
        }
        //周一
        mCourseDetail[0][0] = new CourseItem();
        mCourseDetail[0][0].setName("现代测试技术B211");
        mCourseDetail[1][0] = new CourseItem();
        mCourseDetail[1][0].setName("现代测试技术B211");

        mCourseDetail[2][0] = new CourseItem();
        mCourseDetail[2][0].setName("微机原理及应用AE203");
        mCourseDetail[3][0] = new CourseItem();
        mCourseDetail[3][0].setName("微机原理及应用AE203");

        mCourseDetail[4][0] = new CourseItem();
        mCourseDetail[4][0].setName("电磁场理论A210");
        mCourseDetail[5][0] = new CourseItem();
        mCourseDetail[5][0].setName("电磁场理论A210");

        mCourseDetail[6][0] = new CourseItem();
        mCourseDetail[6][0].setName("传感器与电子测量A312");
        mCourseDetail[7][0] = new CourseItem();
        mCourseDetail[7][0].setName("传感器与电子测量A312");

        mCourseDetail[8][0] = new CourseItem();
        mCourseDetail[8][0].setName("传感器与电子测量A综合楼南513");
        mCourseDetail[9][0] = new CourseItem();
        mCourseDetail[9][0].setName("传感器与电子测量A综合楼南513");
        mCourseDetail[10][0] = new CourseItem();
        mCourseDetail[10][0].setName("传感器与电子测量A综合楼南513");
        mCourseDetail[11][0] = new CourseItem();
        mCourseDetail[11][0].setName("传感器与电子测量A综合楼南513");
        //周二
        mCourseDetail[0][1] = new CourseItem();
        mCourseDetail[0][1].setName("数据结构与算法B211");
        mCourseDetail[1][1] = new CourseItem();
        mCourseDetail[1][1].setName("数据结构与算法B211");

        mCourseDetail[4][1] = new CourseItem();
        mCourseDetail[4][1].setName("面向对象程序设计A307");
        mCourseDetail[5][1] = new CourseItem();
        mCourseDetail[5][1].setName("面向对象程序设计A307");

        mCourseDetail[6][1] = new CourseItem();
        mCourseDetail[6][1].setName("面向对象程序设计综合楼南307");
        mCourseDetail[7][1] = new CourseItem();
        mCourseDetail[7][1].setName("面向对象程序设计综合楼南307");
        //周三
        mCourseDetail[2][2] = new CourseItem();
        mCourseDetail[2][2].setName("现代测试技术B211");
        mCourseDetail[3][2] = new CourseItem();
        mCourseDetail[3][2].setName("现代测试技术B211");
        mCourseDetail[4][2] = new CourseItem();
        mCourseDetail[4][2].setName("现代测试技术B211");
        mCourseDetail[5][2] = new CourseItem();
        mCourseDetail[5][2].setName("现代测试技术B211");
        //周四
        mCourseDetail[0][3] = new CourseItem();
        mCourseDetail[0][3].setName("面向对象程序设计A309");
        mCourseDetail[1][3] = new CourseItem();
        mCourseDetail[1][3].setName("面向对象程序设计A309");
        mCourseDetail[2][3] = new CourseItem();
        mCourseDetail[2][3].setName("传感器与电子测量B309");
        mCourseDetail[3][3] = new CourseItem();
        mCourseDetail[3][3].setName("传感器与电子测量B309");
        //周五
        mCourseDetail[0][4] = new CourseItem();
        mCourseDetail[0][4].setName("数据结构与算法B207");
        mCourseDetail[1][4] = new CourseItem();
        mCourseDetail[1][4].setName("数据结构与算法B207");
        mCourseDetail[2][4] = new CourseItem();
        mCourseDetail[2][4].setName("微机原理及应用AE203");
        mCourseDetail[3][4] = new CourseItem();
        mCourseDetail[3][4].setName("微机原理及应用AE203");

        mCourseDetail[8][4] = new CourseItem();
        mCourseDetail[8][4].setName("形式与政策2E301");
        mCourseDetail[9][4] = new CourseItem();
        mCourseDetail[9][4].setName("形式与政策2E301");
        mCourseDetail[10][4] = new CourseItem();
        mCourseDetail[10][4].setName("形式与政策2E301");
        //周六
        mCourseDetail[0][5] = new CourseItem();
        mCourseDetail[0][5].setName("数据结构与算法综合楼南303");
        mCourseDetail[1][5] = new CourseItem();
        mCourseDetail[1][5].setName("数据结构与算法综合楼南303");
        mCourseDetail[4][5] = new CourseItem();
        mCourseDetail[4][5].setName("数据结构与算法综合楼南303");
        mCourseDetail[5][5] = new CourseItem();
        mCourseDetail[5][5].setName("数据结构与算法综合楼南303");


    }

}
