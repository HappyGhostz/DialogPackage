package com.example.happyghost.dialogpackage.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.example.happyghost.dialogpackage.R;
import com.zcp.customdialoglib.dialoglist.BaseDialogViewHolder;
import com.zcp.customdialoglib.dialoglist.ListDialogAdapter;

import java.util.List;

/**
 * @author Zhao Chenping
 * @creat 2018/3/19.
 * @description
 */

public class MyAdapter extends ListDialogAdapter<String,BaseDialogViewHolder> {

    public MyAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }
    public MyAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseDialogViewHolder holder, final String s) {
        holder.setText(R.id.custon_tv_item,s);
//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(mContext,"点击了:"+s,Toast.LENGTH_SHORT).show();
//            }
//        });
    }
}
