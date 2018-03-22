package com.zcp.customdialoglib;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * @author Zhao Chenping
 * @creat 2018/3/14.
 * @description
 */

public class CustomDialog extends DialogFragment {
    private static final int DIALOG_DISSMISS = 0;
    private View mView;
    private boolean needTitle = true;
    final CustomDialogController controller;
    private Handler mHandler ;
    private OnBindViewListener mListener;

    public CustomDialog(){
        controller = new CustomDialogController();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        if(controller.isDefaultDialog()){
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), getTheme());
            builder.setNegativeButton(controller.getNegatuveString(),controller.getNegativeListenee());
            builder.setPositiveButton(controller.getPositiveString(),controller.getpositiveListener());
            AlertDialog dialog = builder.create();
            dialog.setTitle(controller.getTitle());
            dialog.setMessage(controller.getMesssage());
            mHandler = new MyHandler(dialog);
            return dialog;
        }else {
            Dialog dialog = new Dialog(getActivity(), getTheme());
            mHandler = new MyHandler(dialog);
            return dialog;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(!controller.isHasTitle()){
            getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        }
        if(controller.getmResId()==0&&controller.getView()!=null){
            mView = controller.getView();
        }else if(controller.getmResId()!=0){
            mView = inflater.inflate(controller.getmResId(),container,false);
        }else if(controller.isListDialog()){
            mView =controller.getListView();
        }
        if(mListener!=null){
            mListener.getBindView(mView);
        }
        return mView;
    }

    @Override
    public void onStart() {
        super.onStart();
        if(controller.getDissMissTime()>0){
           mHandler.sendEmptyMessageDelayed(DIALOG_DISSMISS,controller.getDissMissTime());
        }
        getDialog().setCancelable(controller.isCanacelable());
        setWindow();

    }

    private void setWindow() {
        Window window = getDialog().getWindow();
        if (window != null) {
            //设置窗体背景色透明
            if(controller.isTransparent()){
                window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            }else if(controller.isListDialog()){
                window.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
            }
            //设置宽高
            WindowManager.LayoutParams layoutParams = window.getAttributes();
            if (controller.getDialogWidth() > 0) {
                layoutParams.width = controller.getDialogWidth();
            } else {
                layoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
            }
            if (controller.getDialogHeight() > 0) {
                layoutParams.height = controller.getDialogHeight();
            } else {
                layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
            }
            //透明度
            layoutParams.dimAmount = controller.getDimAmount();
            //位置
            layoutParams.gravity = controller.getGravity();
            if(controller.getTheme()>0){
                layoutParams.windowAnimations = controller.getTheme();
            }
            window.setAttributes(layoutParams);
        }
    }
    public interface OnBindViewListener{
        void getBindView(View view);
    }
    public void setOnBindViewListener(OnBindViewListener listener){
        this.mListener = listener;
    }

    @Override
    public void onPause() {
        super.onPause();
        mHandler.removeCallbacksAndMessages(null);
    }

    public static class MyHandler extends Handler{
        private WeakReference<Dialog> mDialog;
        public MyHandler(Dialog dialog){
            mDialog = new WeakReference<Dialog>(dialog);
        }
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case DIALOG_DISSMISS:
                    mDialog.get().dismiss();
            }
        }
    }
    public static class Builder{
        public final CustomDialogController.ControllerParams mDialogcontroller;
        private ArrayList<Object> dataList;
        private RecyclerView listView;

        public Builder(){
            mDialogcontroller = new CustomDialogController.ControllerParams();
        }
        public  Builder setView(View view){
            mDialogcontroller.mView = view;
            return this;
        }
        public Builder setIsDefaultDialog(boolean isDefaultDialog){
            mDialogcontroller.isDefaultDialog = isDefaultDialog;
            return this;
        }
        public Builder setResId(@LayoutRes int resId){
            mDialogcontroller.mResId=resId;
            return this;
        }
        public Builder setTitle(CharSequence title){
            mDialogcontroller.title=title;
            return this;
        }
        public Builder setMessage(CharSequence message){
            mDialogcontroller.messsage = message;
            return this;
        }
        public Builder setHasTitle(boolean isNeedTitle){
            mDialogcontroller.hasTitle = isNeedTitle;
            return this;
        }
        public Builder setTheme(int theme){
            mDialogcontroller.theme=theme;
            return this;
        }
        public Builder setDialogHeight(int dialogHeight){
            mDialogcontroller.dialogHeight = dialogHeight;
            return this;
        }
        public Builder setDialogWidth(int width){
            mDialogcontroller.dialogWidth = width;
            return this;
        }
        public Builder setDimAmount(float dimAmount){
            mDialogcontroller.dimAmount = dimAmount;
            return this;
        }
        public Builder setGravity(int gravity){
            mDialogcontroller.gravity = gravity;
            return this;
        }
        public Builder setCancelable(boolean isCancelable){
            mDialogcontroller.isCancelable = isCancelable;
            return this;
        }
        public Builder setPositiveString(String info){
            mDialogcontroller.positiveString = info;
            return this;
        }
        public Builder setPositiveListener(DialogInterface.OnClickListener positiveListener){
            mDialogcontroller.positiveListener = positiveListener;
            return this;
        }
        public Builder setNegativeString(String info){
            mDialogcontroller.negatuveString = info;
            return this;
        }
        public Builder setNegativeListener(DialogInterface.OnClickListener negativeListenee){
            mDialogcontroller.negativeListenee = negativeListenee;
            return this;
        }
        public Builder setDialogDismissTime(int dismissTime){
            mDialogcontroller.dissMissTime = dismissTime;
            return this;
        }
        public Builder setIsTransparentDialog(boolean isTransparentDialog){
            mDialogcontroller.isTransparent = isTransparentDialog;
            return this;
        }
        public CustomDialog creat(){
            CustomDialog mDialog = new CustomDialog();
            mDialogcontroller.apply(mDialog.controller);
            return mDialog;
        }
        public CustomDialog show(FragmentManager manager, String tag){
            CustomDialog customDialog = creat();
            customDialog.show(manager,tag);
            return customDialog;
        }


        public Builder setIsListDialog(boolean isListDialog) {
            mDialogcontroller.isListDialog = isListDialog;
            return this;
        }
//        public Builder setBaseAdapter(ListDialogAdapter baseAdapter) {
//            mDialogcontroller.baseAdapter = baseAdapter;
//            return this;
//        }
        public Builder setBaseAdapter(RecyclerView.Adapter baseAdapter) {
            mDialogcontroller.baseAdapter = baseAdapter;
            return this;
        }

        public Builder setListView(RecyclerView listView) {
            mDialogcontroller.listView = listView;
            return this;
        }
    }
}
