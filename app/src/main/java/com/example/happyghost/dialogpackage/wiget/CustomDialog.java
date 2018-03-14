package com.example.happyghost.dialogpackage.wiget;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

/**
 * @author Zhao Chenping
 * @creat 2018/3/14.
 * @description
 */

public class CustomDialog extends DialogFragment {

    private View mView;
    private int mRes=-1;
    private boolean needTitle = true;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), getTheme());
        AlertDialog dialog = builder.create();
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(!needTitle){
            getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        }
        mView = getCustomView(inflater,container,savedInstanceState);
        return mView;
    }

    private View getCustomView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(mView!=null){
            return mView;
        }else if(mRes!=-1){
            mView = inflater.inflate(mRes, null);
            return mView;
        }
        return null;
    }
    public static class Builder{
        public final CustomDialogController mDialogcontroller;
        public Builder(){
            mDialogcontroller = new CustomDialogController();
        }
        public  Builder setView(View view){
            mDialogcontroller.setView(view);
            return this;
        }
        public Builder setResId(@LayoutRes int resId){
            mDialogcontroller.setResId(resId);
            return this;
        }
        public Builder setTitle(CharSequence title){
            mDialogcontroller.setTitle(title);
            return this;
        }
        public Builder setMessage(CharSequence message){
            mDialogcontroller.setMesssage(message);
            return this;
        }
        public Builder setHasTitle(boolean isNeedTitle){
            mDialogcontroller.setHasTitle(isNeedTitle);
            return this;
        }
        public Builder setTheme(int theme){
            mDialogcontroller.setTheme(theme);
            return this;
        }
        public CustomDialog creat(){
            CustomDialog mDialog = new CustomDialog();
            mDialog.mView=mDialogcontroller.getView();
            mDialog.mRes = mDialogcontroller.getmResId();
            mDialog.getDialog().setTitle(mDialogcontroller.getTitle());
            return mDialog;
        }
    }
}
