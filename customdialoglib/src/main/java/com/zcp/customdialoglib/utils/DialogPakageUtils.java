package com.zcp.customdialoglib.utils;


import android.content.DialogInterface;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;

import com.zcp.customdialoglib.CustomDialog;


/**
 * @author Zhao Chenping
 * @creat 2018/3/14.
 * @description  dialogfragment封装类
 */

public class DialogPakageUtils {
    /**
     *  @describe 获取默认dialog
     *  @author happyGhost
     *  @time 2018/3/15  13:29
     *  @param
     */
    public static void getDefaultDialog(FragmentManager manage,String tag,String title,String message,
                                        boolean canCelable,String negativeString,DialogInterface.OnClickListener dialogInterface,
                                        String positiveString, DialogInterface.OnClickListener positiveDialogInterface){
        new CustomDialog.Builder()
                .setTitle(title)
                .setIsDefaultDialog(true)
                .setMessage(message)
                .setCancelable(canCelable)
                .setNegativeString(negativeString)
                .setNegativeListener(dialogInterface)
                .setPositiveString(positiveString)
                .setPositiveListener(positiveDialogInterface)
                .creat()
                .show(manage,tag);
    }

    /**
     * @param
     * @describe 创建正在加载对话框
     * @author happyGhost
     * @time 2018/3/15  14:41
     */
    public static CustomDialog creatProdressDialog(FragmentManager manage, String tag, int resLayout,
                                                   int width, float dimAmount){
        CustomDialog customDialog = new CustomDialog.Builder()
                .setHasTitle(false)
                .setCancelable(false)
                .setResId(resLayout)
                .setDialogWidth(width)
                .setDimAmount(dimAmount)
                .creat();
        customDialog.show(manage,tag);
        return customDialog;
    }

    /**
     * @param
     * @describe 创建自定义弹出dialog
     * @author happyGhost
     * @time 2018/3/16  10:32
     */
    public static CustomDialog creatBottomDialog(FragmentManager manage,String tag,
                                         int reslayout,int gravity,int animationStyle, int width,
                                         int height,float dimAmount,boolean isFullScreen){
        CustomDialog customDialog = new CustomDialog.Builder()
                .setHasTitle(false)
                .setCancelable(true)
                .setResId(reslayout)
                .setGravity(gravity)
                .setTheme(animationStyle)
                .setDialogWidth(width)
                .setDialogHeight(height)
                .setDimAmount(dimAmount)
                .setIsTransparentDialog(isFullScreen)
                .creat();
        customDialog.show(manage,tag);
        return customDialog;
    }

    /**
     * @param
     * @describe 创建下载dialog
     * @author happyGhost
     * @time 2018/3/16  10:33
     */
    public static CustomDialog creatDownLoadInfoDialog(FragmentManager manage,String tag,int resLayout,int animationStyle,
                                                       int width,int height,float dimAmount,boolean isFullScreen){
        CustomDialog dialog = new CustomDialog.Builder()
                .setHasTitle(false)
                .setCancelable(false)
                .setResId(resLayout)
                .setTheme(animationStyle)
                .setDialogWidth(width)
                .setDialogHeight(height)
                .setDimAmount(dimAmount)
                .setIsTransparentDialog(isFullScreen)
                .creat();
        dialog.show(manage,tag);
        return dialog;
    }
    /**
     *  @describe 创建列表dialog
     *  @author happyGhost
     *  @time 2018/3/19  13:56
     *  @param
     */
    public static CustomDialog creatListDialog(FragmentManager manage, String tag, RecyclerView listView,
                                               RecyclerView.Adapter adapter,int dialogWidth,int dialogHeight,
                                               int animationStyle,int gravity,boolean cancelable){
        CustomDialog dialog = new CustomDialog.Builder()
                .setHasTitle(false)
                .setCancelable(cancelable)
                .setIsListDialog(true)
                .setListView(listView)
                .setBaseAdapter(adapter)
                .setTheme(animationStyle)
                .setDialogWidth(dialogWidth)
                .setDialogHeight(dialogHeight)
                .setGravity(gravity)
                .setDimAmount(0.3f)
                .creat();
        dialog.show(manage,tag);
        return dialog;
    }

    /**
     * @param
     * @describe 创建广告对话框
     * @author happyGhost
     * @time 2018/3/21  17:35
     */
    public static CustomDialog creatAdvertiseDialog(FragmentManager manage, String tag,int resLayout,
                                                    int animationStyle, int width,int height,float dimAmount,
                                                    boolean isTransparent){
        CustomDialog dialog = new CustomDialog.Builder()
                .setHasTitle(false)
                .setCancelable(false)
                .setResId(resLayout)
                .setTheme(animationStyle)
                .setDialogWidth(width)
                .setDialogHeight(height)
                .setIsTransparentDialog(isTransparent)
                .setDimAmount(dimAmount)
                .creat();
        dialog.show(manage,tag);
        return dialog;
    }

}
