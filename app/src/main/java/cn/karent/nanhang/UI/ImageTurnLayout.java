package cn.karent.nanhang.UI;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;
import cn.karent.nanhang.util.ScreenUtil;

/**
 * Created by wan on 2016/12/24.
 * 首页图片轮播图，banner
 */
public class ImageTurnLayout extends ViewGroup {

    private Context mContext;

    private Scroller mScroller;
    /**
     * 当前的View的宽度
     */
    private int mWidth;
    /**
     * 上次手指一动的坐标
     */
    private float mOldX;
    /**
     * 右边界
     */
    private int mRightEdge;

    /**
     * 指示当前到了哪个Child
     */
    private int mIndicator;

    private Paint mPaint;
    //小圆点的半径
    private float mRadius;
    /**
     * 两个指示器之间的空白
     */
    private float mBlank;
    /**
     * 坐标的空白
     */
    private float mLeftBlank;
    /**
     * 小圆点的y坐标
     */
    private int mIndicatorY;

    public ImageTurnLayout(Context context) {
        super(context);
    }

    public ImageTurnLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mScroller = new Scroller(context);
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.WHITE);
        mRadius = ScreenUtil.dp2px(5);
        //此处设置一样大小
        mBlank = 2 * mRadius;
        mIndicatorY = ScreenUtil.dp2px(135);
        //不设置这个将不会调用diaw方法
        setWillNotDraw(false);
    }

    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        mWidth = width;
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int indicatorTotal = 0;
        //测量每一个子项应该都是充满屏幕的
        for( int i = 0; i < getChildCount(); i++) {
            int widthSpec = MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY);
            int heightSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
            View child = getChildAt(i);
            child.measure(widthSpec, heightSpec);
            indicatorTotal += mRadius;
            indicatorTotal += mBlank;
        }
        indicatorTotal -= mBlank;
        mLeftBlank = (width - indicatorTotal) / 2 + 0.5f;
        //保存右边界
        mRightEdge = getChildAt(getChildCount() - 1).getLeft();

    }

    public void onLayout(boolean changed, int l, int t, int r, int b) {
        //布局
        for(int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            child.layout(i * child.getMeasuredWidth(), 0,  (i + 1) * child.getMeasuredWidth(), child.getMeasuredHeight());
        }
    }

    public void draw(Canvas c) {
        super.draw(c);
        drawIndicator(c);
    }


    /**
     * 绘制指示器
     * @param canvas
     */
    private void drawIndicator(Canvas canvas) {
        for(int i = 0; i < getChildCount(); i++) {
            //代表当前显示的哪个页面
            if( i == mIndicator) {
                mPaint.setColor(Color.BLUE);
            } else {
                mPaint.setColor(Color.WHITE);
            }
            canvas.drawCircle(getScrollX() + mLeftBlank + i * (mRadius + mBlank),  mIndicatorY, mRadius, mPaint);
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        return super.onInterceptTouchEvent(ev);
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch( action ) {
            case MotionEvent.ACTION_DOWN:
                mOldX = event.getRawX();
                return true;
            case MotionEvent.ACTION_MOVE:
                float x = event.getRawX();
                int moveX = (int)(mOldX - x);
                //滚动
                if( getScrollX() + moveX >= mRightEdge ) {
                    scrollTo(mRightEdge, 0);
                } else if( getScrollX() + moveX <= 0) {
                    scrollTo(0, 0);
                } else {
                    scrollBy(moveX, 0);
                }
                //计算指示器的位置
                mIndicator = getScrollX() / mWidth;
                break;
            case MotionEvent.ACTION_UP:
                //手指释放，计算该到哪张
                int next = (int)(getScrollX() * 1.0f  / mWidth + 0.5f);
                int dx = mWidth * next - getScrollX();
                mScroller.startScroll(getScrollX(), 0, dx, 0);
                break;
            default:
                break;
        }
        invalidate();
        return super.onTouchEvent(event);
    }

    @Override
    public void computeScroll() {
        if( mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            mIndicator = getScrollX() / mWidth;
            invalidate();
        }
    }
}
