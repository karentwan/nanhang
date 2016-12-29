package cn.karent.nanhang.UI;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import cn.karent.nanhang.R;
import cn.karent.nanhang.util.ScreenUtil;

/**
 * Created by wan on 2016/12/28.
 * 绘制当前的天数
 */
public class CourseDateUI extends View {
    /**
     * 当前的View的偏移位置
     */
    private int mOffsetX;
    /**
     * 当前View的宽度
     */
    private int mWidth;
    /**
     * 当前View的高度
     */
    private int mHeight;
    /**
     * 今天是星期几，便于绘制背景图，默认星期一，从0开始
     */
    private int mCurrentDate = 3;
    /**
     * 画笔
     */
    private Paint mPaint;
    /**
     * 每个格子所占的大小
     */
    private int mPerWidth;
    /**
     * 是否是第一次加载
     */
    private boolean mLoadonce = true;
    /**
     * 文字的大小
     */
    private int mTextSize;
    /**
     * 要画的日期
     */
    private String[] mDates = new String[] {
            "一", "二", "三", "四", "五", "六", "日"
    };
    /**
     * 圈出当前星期几的背景图片
     */
    private Bitmap mCurrentDateBg;
    /**
     * mCurrentDateBg对象所占的矩形
     */
    private Rect mSourceRect;
    /**
     * 文字的Y轴偏移距离
     */
    private int mTextOffsetY;
    /**
     * 背景的y轴偏移距离
     */
    private int mDateBgOffsetY;
    /**
     * 正常日期的颜色
     */
    private int mNormalColor = Color.rgb(66, 66, 66);
    /**
     * 周末的颜色
     */
    private int mWeekendColor = Color.rgb(255, 0, 0);

    public CourseDateUI(Context context, AttributeSet attrs) {
        super(context, attrs);
        mHeight = ScreenUtil.dp2px(45);
        mOffsetX = ScreenUtil.dp2px(20);
        //画笔初始化
        mPaint = new Paint();
        //反锯齿
        mPaint.setAntiAlias(true);
        mTextSize = ScreenUtil.dp2px(15);
        mPaint.setTextSize(mTextSize);
        mCurrentDateBg = BitmapFactory.decodeResource(context.getResources(), R.drawable.week_point_icon);
        mSourceRect = new Rect(0, 0, mCurrentDateBg.getWidth(), mCurrentDateBg.getHeight());
        mTextOffsetY = ScreenUtil.dp2px(3);
        mDateBgOffsetY = ScreenUtil.dp2px(4);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if( mLoadonce ) {
            mWidth = MeasureSpec.getSize(widthMeasureSpec);
            mPerWidth = (int)((mWidth - mOffsetX) * 1.0f / 7 + 0.5f);
            mLoadonce = false;
        }
        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //绘制文字开始
        String s = null;
        //获得字体的基准线
        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
        for( int i = 0; i < 7; i++) {
            s = mDates[i];
            //测量文字的宽度
            float strWidth = mPaint.measureText(s);
            int x = mOffsetX + i * mPerWidth ;
            //因为文字是基于基准线绘制的，所以坐标不应该严格的按照getHeight() / 2绘制，这样还是不会在中心
            float y = getHeight() / 2 + (Math.abs(fontMetrics.ascent) - fontMetrics.descent) / 2 + mTextOffsetY;
            //绘制当前是星期几
            if( mCurrentDate == i) {
                Rect r1 = new Rect(x, mDateBgOffsetY, x + mPerWidth, mHeight);
                canvas.drawBitmap(mCurrentDateBg, mSourceRect, r1, mPaint);
            }
            //星期六和星期天应该用红色绘制
            if( i == 5 || i == 6) {
                mPaint.setColor(mWeekendColor);
            }  else {
                mPaint.setColor(mNormalColor);
            }
            x += (mPerWidth - strWidth) / 2;
            canvas.drawText(s, x, y, mPaint);
        }
    }

    public void setCurrentDate(int currentDate) {
        this.mCurrentDate = currentDate;
    }

    public int getCurrentDate() {
        return this.mCurrentDate;
    }

}
