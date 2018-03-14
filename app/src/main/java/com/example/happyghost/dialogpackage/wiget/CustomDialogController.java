package com.example.happyghost.dialogpackage.wiget;

import android.view.View;

/**
 * @author Zhao Chenping
 * @creat 2018/3/14.
 * @description
 */

public class CustomDialogController {
    private View mView;
    private int mResId;
    private CharSequence title;
    private CharSequence messsage;

    private boolean hasTitle;
    private int theme;

    public void setView(View view) {
        this.mView = view;
    }
    public View getView(){
        return mView;
    }

    public void setResId(int resId) {
        this.mResId = resId;
    }

    public int getmResId() {
        return mResId;
    }

    public void setTitle(CharSequence title) {
        this.title = title;
    }

    public CharSequence getTitle() {
        return title;
    }

    public void setMesssage(CharSequence messsage) {
        this.messsage = messsage;
    }

    public CharSequence getMesssage() {
        return messsage;
    }

    public void setHasTitle(boolean hasTitle) {
        this.hasTitle = hasTitle;
    }
    public boolean isHasTitle() {
        return hasTitle;
    }

    public void setTheme(int theme) {
        this.theme = theme;
    }

    public int getTheme() {
        return theme;
    }
}
