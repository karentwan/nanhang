package cn.karent.nanhang.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import cn.karent.nanhang.R;
import cn.karent.nanhang.UI.ProgressDialog;
import cn.karent.nanhang.adapter.ScoreAdapter;
import cn.karent.nanhang.adapter.WeekAdapter;
import cn.karent.nanhang.model.Score;
import cn.karent.nanhang.util.PopupWindowUtil;
import cn.karent.nanhang.util.ScreenUtil;

/**
 * Created by wan on 2016/12/26.
 * 显示成绩的Activity
 */
public class ScoreActivity extends Activity implements View.OnClickListener{
    /**
     * 显示成绩的列表
     */
    private ListView mScoreList;
    /**
     * 分数的Adapter
     */
    private ScoreAdapter mScoreAdapter;
    /**
     * 选择星期的View
     */
    private View mCheckWeek;
    /**
     * 弹出框
     */
    private PopupWindow mCheckWeekPopupWindow;
    /**
     * PopupWindow的宽度
     */
    private int mPopupWidth;
    /**
     * 选择年份的ListView
     */
    private ListView mList;
    /**
     * 用来接收所有的事件但不消费，用于消除PopupWindow无法消失的情况
     */
    private View mCancelPopup;
    /**
     * 显示当前的年份
     */
    private TextView mCurrentYear;
    /**
     * 显示周数的Adapter
     */
    private WeekAdapter mWeekAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score_layout);
        mScoreList = (ListView)findViewById(R.id.score_list);
        mScoreAdapter = new ScoreAdapter(this, R.layout.score_item_layout);
        fillContent();
        mScoreList.setAdapter(mScoreAdapter);
        mCheckWeek = findViewById(R.id.score_checkWeek);
        mCheckWeek.setOnClickListener(this);
        mPopupWidth = ScreenUtil.dp2px(250);
        mCancelPopup = findViewById(R.id.score_cancelPopup);
        mCancelPopup.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if( mCheckWeekPopupWindow != null ) {
                    mCheckWeekPopupWindow.dismiss();
                    mCheckWeekPopupWindow = null;
                }
                return false;
            }
        });
        mCurrentYear = (TextView)findViewById(R.id.score_week);
        //显示加载对话框
        new ProgressDialog.Builder(this).create().show();
    }

    /**
     * 填充测试数据
     */
    private void fillContent() {
        Score s = new Score();
        s.setName("美食与文化");
        s.setCourseTime("(总学时：20小时)");
        s.setCourseProperty("全校任选课");
        s.setProperty("选修");
        s.setWay("考查");
        s.setScore(93);
        s.setCredit(1);
        mScoreAdapter.add(s);
        mScoreAdapter.add(s);
        mScoreAdapter.add(s);
        mScoreAdapter.add(s);
        mScoreAdapter.add(s);
        mScoreAdapter.add(s);
        mScoreAdapter.add(s);
    }

    /**
     * 初始化popupWindow当中的数据
     */
    private void initPopupWindow() {
        if( mWeekAdapter != null) {
//            mList.setAdapter(mWeekAdapter);
            return;
        }
        mWeekAdapter = new WeekAdapter(this, R.layout.check_week_layout);
        mWeekAdapter.setWeekTextView(mCurrentYear);
        int year = 2015;
        for(int i = 26; i > 0; i--) {
            String s = year + "-" + (year + 1);
            if( i % 2 == 0) {
                s += "(上学期)";
            } else {
                s += "(下学期)";
            }
            mWeekAdapter.add(s);
            year -= 1;
        }
//        mList.setAdapter(mWeekAdapter);
    }

    /**
     * 弹出选择周数的框
     * @param v
     */
    @Override
    public void onClick(View v) {
      if( mCheckWeekPopupWindow == null ) {
//            mCheckWeekPopupWindow = new PopupWindow(this);
//            View contentView = LayoutInflater.from(this).inflate(R.layout.checkweek_popup_layout, null);
//            mCheckWeekPopupWindow.setContentView(contentView);
//            mCheckWeekPopupWindow.setWidth(mPopupWidth);
//            mCheckWeekPopupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
//            mList = (ListView) contentView.findViewById(R.id.checkweek_list);
//            mCheckWeekPopupWindow.setBackgroundDrawable(null);
//            mCheckWeekPopupWindow.setFocusable(false);
//            mCheckWeekPopupWindow.setOutsideTouchable(true);
//            mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                    mCurrentYear.setText(((TextView)view).getText());
//                    //让popupWindow消失
//                    mCheckWeekPopupWindow.dismiss();
//                    mCheckWeekPopupWindow = null;
//                }
//            });
            //去除背景
            initPopupWindow();
            mCheckWeekPopupWindow = PopupWindowUtil.createPopupWindow(this, mPopupWidth, mWeekAdapter, new PopupWindowUtil.ItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                      mCurrentYear.setText(((TextView)view).getText());
                      //让popupWindow消失
                      mCheckWeekPopupWindow.dismiss();
                      mCheckWeekPopupWindow = null;
                }
            });
            //计算要偏移的像素
            int offsetX = (ScreenUtil.getScreenWidth() - mPopupWidth) / 2;
            mCheckWeekPopupWindow.showAsDropDown(mCheckWeek, offsetX, 0);
        }
    }


}
