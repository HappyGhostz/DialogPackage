package com.example.happyghost.dialogpackage.adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @author Zhao Chenping
 * @creat 2018/3/20.
 * @description
 */

public class CustomItemDecoration extends RecyclerView.ItemDecoration {
    private int[] ATTRS = new int[]{android.R.attr.listDivider};
    private Drawable mDivider;
    private int mOrientation;

    public CustomItemDecoration(Context context,int orientation){
        TypedArray typedArray = context.obtainStyledAttributes(ATTRS);
        mDivider = typedArray.getDrawable(0);
        typedArray.recycle();
        setOrientation(orientation);
    }

    private void setOrientation(int orientation) {
        if(orientation!= LinearLayoutManager.HORIZONTAL&&orientation!=LinearLayoutManager.VERTICAL){
            throw new IllegalArgumentException("not init orientation");
        }
        mOrientation = orientation;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        if(mOrientation == LinearLayoutManager.HORIZONTAL){
            drawVerticalLine(c,parent);
        }else if(mOrientation ==  LinearLayoutManager.VERTICAL){
            drawHorizontalLine(c,parent);
        }
    }

    private void drawHorizontalLine(Canvas canvas, RecyclerView recyclerView) {
        int left = recyclerView.getPaddingLeft();
        int right = recyclerView.getWidth() - recyclerView.getPaddingRight();
        int childCount = recyclerView.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = recyclerView.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) childAt.getLayoutParams();
            int top = childAt.getBottom()+layoutParams.bottomMargin;
            int bottom = top + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left,top,right,bottom);
            mDivider.draw(canvas);
        }
    }

    private void drawVerticalLine(Canvas canvas, RecyclerView recyclerView) {
        int top = recyclerView.getPaddingTop();
        int bottom = recyclerView.getHeight() - recyclerView.getPaddingBottom();
        int childCount = recyclerView.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = recyclerView.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams)childView.getLayoutParams();
            int left = childView.getRight() + layoutParams.rightMargin;
            int right = left+mDivider.getIntrinsicWidth();
            mDivider.setBounds(left,top,right,bottom);
            mDivider.draw(canvas);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if(mOrientation==LinearLayoutManager.VERTICAL){
            outRect.set(0,0,0,mDivider.getIntrinsicHeight());
        }else if(mOrientation==LinearLayoutManager.HORIZONTAL){
            outRect.set(0,00,mDivider.getIntrinsicWidth(),0);
        }
    }

    public void setDividerDrawable(Drawable drawable){
        this.mDivider = drawable;
    }

}
