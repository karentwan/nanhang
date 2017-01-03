package cn.karent.nanhang.UI;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.PorterDuff;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import cn.karent.nanhang.R;

/**
 * Created by wan on 2016/12/27.
 * 重复绘制小圆点
 */
public class ProgressView extends View {
    /**
     * 全局上下文
     */
    private Context mContext;
    /**
     * 图像变换矩阵
     */
    private Matrix mMatrix = new Matrix();
    /**
     * 要绘制的背景图，也就是那个旋转的点点
     */
    private Bitmap mBitmap;
    /**
     * 图片的宽度
     */
    private int mWidth;
    /**
     * 图片的宽度
     */
    private int mHeight;

    private LoopAnimation mLoopAnimation;

    private boolean mLoop = true;

    public ProgressView(Context context) {
        super(context);
    }

    public ProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        //从资源加载bitmap对象
        mBitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.pic_dlg_loading);
        mWidth = mBitmap.getWidth();
        mHeight = mBitmap.getHeight();
        //执行循环
        mLoopAnimation = new LoopAnimation();
        mLoopAnimation.execute();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        canvas.scale(mWidth / 2, mHeight / 2);
        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        mMatrix.preRotate(6, mWidth / 2, mHeight / 2);
        canvas.drawBitmap(mBitmap, mMatrix, null);
    }

    /**
     * 循环动画，不停的触发旋转动画
     */
    class LoopAnimation extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            invalidate();
        }

        @Override
        protected Void doInBackground(Void... params) {
            int rotate = 0;
            while( mLoop ) {
                try {
                    Thread.sleep(100);
                } catch(InterruptedException e) {
                    e.printStackTrace();
                }
                publishProgress();
            }
            return null;
        }
    }

    /**
     * 停止循环
     * @param loop
     */
    public void setLoop(boolean loop) {
        mLoop = loop;
    }

}
