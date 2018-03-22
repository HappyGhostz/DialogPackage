package com.zcp.customdialoglib;

import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
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
    private int dialogHeight;
    private int dialogWidth;
    private float dimAmount;
    private int gravity;
    private boolean canacelable;
    private String negatuveString;
    private DialogInterface.OnClickListener negativeListenee;
    private String positiveString;
    private DialogInterface.OnClickListener positiveListener;
    private long dissMissTime;
    private boolean isTransparent;
    private boolean isDefaultDialog;
    private RecyclerView listView;
    private boolean isListDialog;
    private RecyclerView.Adapter listAdapter;

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

    public void setDialogHeight(int dialogHeight) {
        this.dialogHeight = dialogHeight;
    }

    public int getDialogHeight() {
        return dialogHeight;
    }

    public void setDialogWidth(int dialogWidth) {
        this.dialogWidth = dialogWidth;
    }

    public int getDialogWidth() {
        return dialogWidth;
    }

    public void setDimAmount(float dimAmount) {
        this.dimAmount = dimAmount;
    }

    public float getDimAmount() {
        return dimAmount;
    }

    public void setGravity(int gravity) {
        this.gravity = gravity;
    }

    public int getGravity() {
        return gravity;
    }

    public void setCanacelable(boolean canacelable) {
        this.canacelable = canacelable;
    }

    public boolean isCanacelable() {
        return canacelable;
    }

    public void setNegatuveString(String negatuveString) {
        this.negatuveString = negatuveString;
    }

    public String getNegatuveString() {
        return negatuveString;
    }

    public void setNegativeListenee(DialogInterface.OnClickListener negativeListenee) {
        this.negativeListenee = negativeListenee;
    }

    public DialogInterface.OnClickListener getNegativeListenee() {
        return negativeListenee;
    }

    public void setPositiveString(String positiveString) {
        this.positiveString = positiveString;
    }

    public String getPositiveString() {
        return positiveString;
    }

    public void setPositiveListener(DialogInterface.OnClickListener positiveListener) {
        this.positiveListener = positiveListener;
    }

    public DialogInterface.OnClickListener getpositiveListener() {
        return positiveListener;
    }

    public void setDissMissTime(long dissMissTime) {
        this.dissMissTime = dissMissTime;
    }

    public long getDissMissTime() {
        return dissMissTime;
    }

    public void setIsTransparent(boolean isTransparent) {
        this.isTransparent = isTransparent;
    }

    public boolean isTransparent() {
        return isTransparent;
    }

    public void setIsDefaultDialog(boolean isDefaultDialog) {
        this.isDefaultDialog = isDefaultDialog;
    }

    public boolean isDefaultDialog() {
        return isDefaultDialog;
    }

    public void setListView(RecyclerView listView) {
        this.listView = listView;
    }

    public View getListView() {
        return listView;
    }

    public void setIsListDialog(boolean isListDialog) {
        this.isListDialog = isListDialog;
    }

    public boolean isListDialog() {
        return isListDialog;
    }

    public void setListAdapter(RecyclerView.Adapter listAdapter) {
        this.listAdapter = listAdapter;
        if(listView!=null&&listAdapter!=null){
            listView.setAdapter(listAdapter);
        }
    }

    public RecyclerView.Adapter getListAdapter() {
        return listAdapter;
    }

    public static class ControllerParams{
        public View mView;
        public int mResId=0;
        public CharSequence title;
        public CharSequence messsage;
        public int dialogHeight;
        public int dialogWidth;
        public float dimAmount;
        public int gravity;

        public boolean hasTitle;
        public int theme;
        public boolean isCancelable;
        public String negatuveString;
        public DialogInterface.OnClickListener negativeListenee;
        public String positiveString;
        public DialogInterface.OnClickListener positiveListener;
        public long dissMissTime;
        public boolean isTransparent;
        public boolean isDefaultDialog;
        public boolean isListDialog;
        public RecyclerView.Adapter baseAdapter;
        public RecyclerView listView;

        public void apply(CustomDialogController controller) {
            if (mView != null) {
                controller.setView(mView);
            }
            controller.setResId(mResId == 0 ? 0 : mResId);
            controller.setTitle(title);
            controller.setMesssage(messsage);
            controller.setHasTitle(hasTitle);
            controller.setTheme(theme == 0 ? 0 : theme);
            controller.setDialogHeight(dialogHeight);
            controller.setDialogWidth(dialogWidth);
            controller.setDimAmount(dimAmount);
            controller.setGravity(gravity);
            controller.setCanacelable(isCancelable);
            controller.setNegatuveString(negatuveString);
            controller.setNegativeListenee(negativeListenee);
            controller.setPositiveString(positiveString);
            controller.setPositiveListener(positiveListener);
            controller.setDissMissTime(dissMissTime);
            controller.setIsTransparent(isTransparent);
            controller.setIsDefaultDialog(isDefaultDialog);
            controller.setIsListDialog(isListDialog);
            controller.setListView(listView);
            controller.setListAdapter(baseAdapter);
        }
    }
}
