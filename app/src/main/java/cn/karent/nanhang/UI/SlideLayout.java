package cn.karent.nanhang.UI;

import android.content.Context;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import cn.karent.nanhang.R;
import cn.karent.nanhang.util.ScreenUtil;

/**
 * Created by wan on 2016/12/6.
 * 侧滑菜单，内容会偏移
 */
public class SlideLayout extends RelativeLayout {

    private Context mContext;

    private static final int MOTION_VELOCITY = 300;

    /*
        是否是第一次调用onLayout方法
     */
    private boolean mLoadOnece = false;

    /*
        左边侧滑菜单的布局参数
     */
    private MarginLayoutParams mLeftParams;

    /*
        右边内容的布局参数
     */
    private MarginLayoutParams mMenuParams;

    /*
        左边的侧滑View
     */
    private View mLeftView;

    /*
        中间的内容View
     */
    private View mMenuView;

    private float mOldX;

    private float mOldY;

    /*
     * 屏幕的宽度
     */
    private int mScreenWidth;

    /**
     * 控件能够移动到的左边界
     */
    private int mLeftEdge = 300;

    public SlideLayout(Context context) {
        super(context);
        mContext = context;
    }

    public SlideLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    /**
     * 布局
     */
    public void onLayout(boolean change, int l, int t, int r, int b) {
        super.onLayout(change, l, t, r, b);
        if( change && !mLoadOnece) {
            mLeftEdge = ScreenUtil.dp2px(300);
            mLeftView = findViewById(R.id.slide_content);
            mMenuView = findViewById(R.id.slide_menu);
            mLeftParams =  (MarginLayoutParams) mLeftView.getLayoutParams();
            mMenuParams = (MarginLayoutParams)mMenuView.getLayoutParams();
            DisplayMetrics dm = getResources().getDisplayMetrics();
            mScreenWidth = dm.widthPixels;
            mLoadOnece = true;
        }
    }

    /**
     * 切换菜单的显示和隐藏
     */
    public void switchMenu() {
        if( mLeftParams.leftMargin == 0) {
            showMenu();
        } else {
            hideMenu();
        }
    }

    /**
     * 显示菜单
     */
    public void showMenu() {
Log.d("slideLayout", "显示菜单！");
        new SmoothScrollTack().execute(-mLeftEdge, mLeftParams.leftMargin);
    }

    /**
     * 隐藏菜单
     */
    public void hideMenu() {
        new SmoothScrollTack().execute(0, mLeftParams.leftMargin);
    }


    /**
     * 修改View的边距来达到移动的效果
     * @param leftMargin
     */
    private void modifyLeftMargin(int leftMargin) {
        //如果左边距大于0代表将要向右变压缩，应该禁止
        if( leftMargin > 0 ) {
            mLeftParams.leftMargin = 0;
            mMenuView.setTranslationX(0);
            //控制左边界滑动
        } else if( leftMargin < -mLeftEdge) {
            mLeftParams.leftMargin = -mLeftEdge;
            mMenuView.setTranslationX(-mLeftEdge / 2);
        } else {
            mLeftParams.leftMargin = leftMargin;
            mMenuView.setTranslationX(leftMargin / 2);
        }
        mLeftParams.width = mScreenWidth;
        mLeftView.setLayoutParams(mLeftParams);
    }

    /**
     * 当侧滑停止的时候来出来接下来的滑动
     */
    private class SmoothScrollTack extends AsyncTask<Integer, Integer, Integer> {

        @Override
        protected void onProgressUpdate(Integer... values) {
            int leftMargin = values[0];
            modifyLeftMargin(leftMargin);
        }

        /**
         * 计算下一个leftMargin值,第一个参数的目标值
         * 第二个参数是当前的边距
         * @param params
         * @return
         */
        @Override
        protected Integer doInBackground(Integer... params) {
            int targetLeftMargin = params[0];
            int currentLeftMargin = params[1];
            int leftMargin = currentLeftMargin;
            int step = targetLeftMargin == 0 ? 10 : -10;
            while(true) {
                leftMargin += step;
                //判断是否滑动完成
                if( leftMargin < -mLeftEdge ) {
                    leftMargin = -mLeftEdge;
                    break;
                }
                if( leftMargin > 0 ) {
                    leftMargin = 0;
                    break;
                }
                publishProgress(leftMargin);
                try {
                    Thread.sleep(5);
                } catch( InterruptedException e) {
                    e.printStackTrace();
                }
            }
            publishProgress(leftMargin);
            return leftMargin;
        }
    }

}
