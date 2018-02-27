package cn.karent.nanhang.UI;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import cn.karent.nanhang.R;
import cn.karent.nanhang.model.CourseItem;
import cn.karent.nanhang.util.ScreenUtil;

/**
 * Created by wan on 2016/12/28.
 * 自定义课程的绘制界面
 */
public class CourseUI extends View{
    /**
     * 背景图片
     */
    private Bitmap mBackground;
    /**
     * 画笔
     */
    private Paint mPaint;
    /**
     * 每个格子的高度
     */
    private int mPerHeight;
    /**
     * 文字的颜色
     */
    private int mTextColor = Color.rgb(66, 66, 66);
    /**
     * 旁边的宽度
     */
    private int mSideWidth;
    /**
     * 当前自定义View的宽度
     */
    private int mWidth;

    private int mTextSize;

    private Rect mBitmapRect;
    /**
     * 每一个表格的宽度
     */
    private int mPerWidth;
    /**
     * 是否是第一次加载
     */
    private boolean mLoadonce = true;
    /**
     * 每节课的信息
     */
    private CourseItem[][] mChildren;
    /**
     * 根据课程信息生成的界面ui
     */
    private Set<CourseItemUI> mChildrenUI = new HashSet<>();
    /**
     * 行和列
     */
    private int mRow, mColumn;
    /**
     * 颜色
     */
    private int[] mColors = new int[] {
        Color.rgb(0xf6, 0x94, 0xa0), Color.rgb(0xfe, 0xa1, 0x64), Color.rgb(0xc6, 0x9e, 0xe4),
            Color.rgb(0x3e, 0xd3, 0xad), Color.rgb(0xc4, 0xd8, 0x45), Color.rgb(0xf8, 0x94, 0xa0),
//            Color.rgb(0xa2, 0x53, 0x5c)
            Color.rgb(34, 221, 221)
    };

    public CourseUI(Context context) {
        super(context);
    }

    public CourseUI(Context context, AttributeSet attrs) {
        super(context, attrs);
        mBackground = BitmapFactory.decodeResource(context.getResources(), R.drawable.kec_back);
        mSideWidth = ScreenUtil.dp2px(20);
        mPerHeight = ScreenUtil.dp2px(45);
        mTextSize = ScreenUtil.dp2px(13);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(mTextSize);
        mBitmapRect = new Rect(0, 0, mBackground.getWidth(), mBackground.getHeight());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height = mPerHeight * 12;
        if( mLoadonce ) {
            mWidth = MeasureSpec.getSize(widthMeasureSpec);
            mPerWidth = (mWidth - mSideWidth) / 7;
            initChildrenUI();
            mLoadonce = false;
        }
        setMeasuredDimension(mWidth, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBackgroundAndNet(canvas);
        drawSideIndicate(canvas);
        drawChildren(canvas);
    }

    /**
     * 绘制背景和网格线
     * @param canvas
     */
    private void drawBackgroundAndNet(Canvas canvas) {
        Rect r = new Rect(0, 0, mWidth, mPerHeight * 12);
        mPaint.setColor(Color.rgb(0xcc, 0xd8, 0xd8));
        canvas.drawBitmap(mBackground, mBitmapRect, r, mPaint);
        int y = mPerHeight * 12;
        int x = mSideWidth;
        //绘制竖线
        for(int i = 0; i < 8; i++) {
            x = mSideWidth + i * mPerWidth;
            canvas.drawLine(x, 0, x, y, mPaint);
        }
        x = mWidth;
        //画竖线
        for(int i = 0; i < 13; i++) {
            y = i * mPerHeight;
            canvas.drawLine(mSideWidth, y, x, y, mPaint);
        }
    }

    /**
     * 绘制里面的课程表详细信息
     * @param canvas
     */
    private void drawChildren(Canvas canvas) {
        Iterator<CourseItemUI> iter =  mChildrenUI.iterator();
        while( iter.hasNext() ) {
            iter.next().draw(canvas);
        }
    }

    /**
     * 绘制旁边的第几节课
     * @param canvs
     */
    private void drawSideIndicate(Canvas canvs) {
        mPaint.setColor(Color.rgb(0x85, 0x90, 0x90));
        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
        //绘制文字开始
        for(int i = 1; i <= 12; i++) {
            //计算中心线
            float strWidth = mPaint.measureText(i + "");
            float x = (mSideWidth - strWidth) / 2;
            float y = (i - 1) * mPerHeight + mPerHeight / 2 + Math.abs(fontMetrics.ascent - fontMetrics.descent) / 2;
            canvs.drawText(i + "", x, y, mPaint);
        }
    }

    /**
     * 设置课程表信息
     * @param courseItems  所有课程
     * @param row           行数
     * @param column        列数
     */
    public void setChildren(CourseItem[][] courseItems, int row, int column) {
        mChildren = courseItems;
        mRow = row;
        mColumn = column;
    }

    /**
     * 初始化CourseItemUI数据
     */
    private void initChildrenUI() {
        CourseItemUI courseItemUI = null;
        //将CourseItem转换为CourseItemUI
        for(int j = 0; j < mColumn; j++) {
            for(int i = 0; i < mRow; i++) {
                CourseItem c = mChildren[i][j];
                if( i % 2 == 0 && c != null) {
                    courseItemUI = new CourseItemUI();
                    int x = mSideWidth + j * mPerWidth;
                    int y = i * mPerHeight;
                    courseItemUI.setmX(x);
                    courseItemUI.setmY(y);
                    courseItemUI.setmWidth(mPerWidth);
                    courseItemUI.setmCourse(c);
                    courseItemUI.setBackColor(mColors[j]);
                } else {
                    if( i % 2 == 0 && c == null) {
                        //说明没有课
                    } else {
                        if( c != null ) {
                            if( courseItemUI != null) {
                                courseItemUI.setmHeight(2 * mPerHeight);
                                mChildrenUI.add(courseItemUI);
                            }
                            courseItemUI = null;
                        } else {
                            if( courseItemUI != null) {
                                courseItemUI.setmHeight(mPerHeight);
                                mChildrenUI.add(courseItemUI);
                            }
                            courseItemUI = null;
                        }
                    }
                }
            }
        }
    }

}
