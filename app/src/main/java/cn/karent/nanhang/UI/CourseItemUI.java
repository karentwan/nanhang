package cn.karent.nanhang.UI;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import cn.karent.nanhang.model.CourseItem;
import cn.karent.nanhang.util.ScreenUtil;
import cn.karent.nanhang.util.TextUtil;

/**
 * Created by wan on 2016/12/29.
 * 每一个Item，负责绘制出自身
 */
public class CourseItemUI {

    /**
     * 圆角矩形所占的大小
     */
    private RectF mSelfBound;

    private CourseItem mCourse;
    /**
     * 坐标与宽高
     */
    private int mX;

    private int mY;

    private int mWidth;

    private int mHeight;
    /**
     * 背景颜色
     */
    private int mBackColor;

    private Paint mPaint;
    /**
     * 圆角矩形的圆角半径
     */
    private int mRadius;
    /**
     * 圆角矩形离整个边框的x和y距离
     */
    private int mDistanceX, mDistanceY;
    /**
     * 字体颜色
     */
    private int mTextColor = Color.rgb(0xff, 0xfd, 0xfc);
    /**
     * 字体的大小
     */
    private int mTextSize;
    /**
     * 是否只有一节课
     */
    private boolean mSingle = false;

    public CourseItemUI() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mDistanceX = ScreenUtil.dp2px(2);
        mDistanceY = ScreenUtil.dp2px(3);
        mRadius = ScreenUtil.dp2px(3);
        mTextSize = ScreenUtil.dp2px(12);
    }

    /**
     * 绘制函数，这里面将会绘制出自身，是一个圆角矩形
     * @param canvas
     */
    public void draw(Canvas canvas) {
        drawBackground(canvas);
        drawText(canvas);
    }

    /**
     * 绘制背景，圆角矩形
     * @param canvas
     */
    private void drawBackground(Canvas canvas) {
        mPaint.setColor(mBackColor);
        //填充风格
        mPaint.setStyle(Paint.Style.FILL);
        if( mSelfBound == null)
            initSelfBound();
        canvas.drawRoundRect(mSelfBound, mRadius, mRadius, mPaint);

    }

    /**
     * 初始化圆角矩形
     */
    private void initSelfBound() {
        mSelfBound = new RectF();
        mSelfBound.left = mX + mDistanceX;
        mSelfBound.right = mX + mWidth - mDistanceX;
        mSelfBound.top = mY + mDistanceY;
        mSelfBound.bottom = mY + mHeight - mDistanceY;
    }

    /**
     * 绘制文字
     * @param canvas
     */
    private void drawText(Canvas canvas) {
        //一个中文占两个字节,也就是说一行绘制6个字节
        String s = mCourse.getName();
        if( s != null) {
            //开始绘制
            mPaint.setColor(mTextColor);
            //测量之前必须先设置字体的大小，否则测量的数据不准确
            mPaint.setTextSize(mTextSize);
            Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
            //总共的长度
            float perW = mPaint.measureText("a");
            int length = TextUtil.measureChineseMixLength(s);
            float strWidth = perW * length;
            //每一个字节的宽度
            //计算一共要绘制多少行
            int row =(int) (strWidth / (perW * 6));
            if( strWidth % (perW * 6) != 0) {
                row += 1;
            }
            //判断row行的字体一共多高，便于让它绘制在中间
            float h = Math.abs(fontMetrics.bottom - fontMetrics.top);//每一行文字的高度
            float totalH = h * row;//总共高度
            if( totalH >= mHeight) {
                row = 2;
                totalH = h * row;
                mSingle = true;
            }
            //计算整个文本离top的距离和left的距离
            float oY = (mHeight - totalH) / 2;
            float x1 = perW * 6;
            float oX = (mWidth - x1) / 2;
            int i = 0;
            for( ; i < row - 1; i++) {
                float y = mY + oY + i * h + h / 2;
                canvas.drawText(s.substring(i * 3, i * 3 + 3), mX + oX, y, mPaint);
            }
            float y = mY + oY + i * h + h / 2;
            if( mSingle ) {
                canvas.drawText(s.substring(i * 3, i * 3 + 2) + "...", mX + oX, y, mPaint);
            } else {
                //绘制最后一行文字
                canvas.drawText(s.substring(i * 3, s.length()), mX + oX, y, mPaint);
            }
        }
    }

    public void setBackColor(int backColor) {
        this.mBackColor = backColor;
    }

    public int getmX() {
        return mX;
    }

    public void setmX(int mX) {
        this.mX = mX;
    }

    public int getmY() {
        return mY;
    }

    public void setmY(int mY) {
        this.mY = mY;
    }

    public int getmWidth() {
        return mWidth;
    }

    public void setmWidth(int mWidth) {
        this.mWidth = mWidth;
    }

    public int getmHeight() {
        return mHeight;
    }

    public void setmHeight(int mHeight) {
        this.mHeight = mHeight;
    }

    public CourseItem getmCourse() {
        return mCourse;
    }

    public void setmCourse(CourseItem mCourse) {
        this.mCourse = mCourse;
    }

    public int getmBackColor() {
        return mBackColor;
    }

    public void setmBackColor(int mBackColor) {
        this.mBackColor = mBackColor;
    }
}
