package com.example.happyghost.dialogpackage.wiget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.example.happyghost.dialogpackage.R;

/**
 * @author Zhao Chenping
 * @creat 2018/3/21.
 * @description 设置默认图片，圆形，圆角，椭圆图片
 */

public class RoundImageView extends ImageView {

    private int mImageMode;
    private Paint mPaint;
    /**
     * 普通模式
     */
    private static final int MODE_NONE = 0;
    /**
     * 圆形模式
     */
    private static final int MODE_CIRCLE = 1;
    /**
     * 圆角模式
     */
    private static final int MODE_ROUND = 2;
    /**
     * 椭圆模式
     */
    private static final int MODE_OVAL = 3;
    private int mImageRoundValue;
    private int mRadius;
    private RectF mRectF;
    private int mCircleValue;
    private Matrix mMatrix;

    public RoundImageView(Context context) {
        this(context,null);
    }

    public RoundImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,-1);
    }

    public RoundImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context,attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RoundImageView);
        mImageMode = typedArray.getInt(R.styleable.RoundImageView_type, 0);
        mImageRoundValue = typedArray.getDimensionPixelSize(R.styleable.RoundImageView_radius, 10);
        typedArray.recycle();
        initPaint();

    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mMatrix = new Matrix();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //若是圆形模式宽高一致
        if(mImageMode==MODE_CIRCLE){
            mCircleValue = Math.min(getMeasuredHeight(), getMeasuredWidth());
            mRadius = mCircleValue /2;
            setMeasuredDimension(mCircleValue, mCircleValue);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mRectF = new RectF(getPaddingLeft(), getPaddingTop(), getWidth()-getPaddingRight(), getHeight()-getPaddingBottom());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(null==getDrawable()){
            return;
        }
        setBitmapShader();
        if(mImageMode == MODE_NONE){
            super.onDraw(canvas);
        }else if(mImageMode == MODE_CIRCLE){
            canvas.drawCircle(mRadius,mRadius,mRadius,mPaint);
        }else if(mImageMode == MODE_ROUND){
            canvas.drawRoundRect(mRectF,mImageRoundValue,mImageRoundValue,mPaint);
        }else if (mImageMode == MODE_OVAL){
            canvas.drawOval(mRectF,mPaint);
        }
    }

    private void setBitmapShader() {
        Bitmap bitmap = getBitmapForDrawable();
        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        float scale = 1.0f;
        if(mImageMode == MODE_CIRCLE){
            int minValue = Math.min(bitmap.getWidth(), bitmap.getHeight());
            scale  = mCircleValue*1.0f/minValue;
        }else if (mImageMode==MODE_OVAL||mImageMode==MODE_ROUND){
            // 如果图片的宽或者高与view的宽高不匹配，计算出需要缩放的比例；缩放后的图片的宽高，一定要大于我们view的宽高；所以我们这里取大值；
            float maxScale = Math.max(getWidth() * 1.0f / bitmap.getWidth(), getHeight() * 1.0f / bitmap.getHeight());
            scale = maxScale;
        }
        // shader的变换矩阵，我们这里主要用于放大或者缩小
        mMatrix.setScale(scale,scale);
        bitmapShader.setLocalMatrix(mMatrix);
        mPaint.setShader(bitmapShader);

    }

    private Bitmap getBitmapForDrawable() {
        Drawable drawable = getDrawable();
        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0,0,width,height);
        drawable.draw(canvas);
        return bitmap;
    }

    public void setmImageMode(int mImageMode) {
        this.mImageMode = mImageMode;
        invalidate();
    }

    public void setmImageRoundValue(int mImageRoundValue) {
        this.mImageRoundValue = mImageRoundValue;
        invalidate();
    }
}
