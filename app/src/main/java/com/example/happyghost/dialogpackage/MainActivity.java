package com.example.happyghost.dialogpackage;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.happyghost.dialogpackage.adapter.CustomItemDecoration;
import com.example.happyghost.dialogpackage.adapter.MyAdapter;
import com.zcp.customdialoglib.CustomDialog;
import com.zcp.customdialoglib.dialoglist.ListDialogAdapter;
import com.zcp.customdialoglib.utils.DialogPakageUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView listView;
    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        Button button = (Button) findViewById(R.id.main_btn);
        Button progressButton = (Button) findViewById(R.id.main_btn_progress);
        Button bottomButton = (Button) findViewById(R.id.main_btn_bottom);
        Button downloadButton = (Button) findViewById(R.id.main_btn_download);
        Button listButton = (Button) findViewById(R.id.main_btn_list);
        Button advertiseButton = (Button) findViewById(R.id.main_btn_advertise);
        button.setOnClickListener(this);
        progressButton.setOnClickListener(this);
        bottomButton.setOnClickListener(this);
        downloadButton.setOnClickListener(this);
        listButton.setOnClickListener(this);
        advertiseButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.main_btn:
                DialogPakageUtils.getDefaultDialog(getSupportFragmentManager(), "0", "提示", "世界正在爆炸，请上天!",false, "否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }, "是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this,"上天完成!",Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.main_btn_progress:
                final CustomDialog customDialog = DialogPakageUtils.creatProdressDialog(getSupportFragmentManager(),
                        "1", R.layout.custom_dialog_progressbar,
                        getWidthScrreen(this) / 4, 0.5f);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        customDialog.dismiss();
                    }
                }, 5000);
                break;
            case R.id.main_btn_bottom:
                CustomDialog bottomDialog = DialogPakageUtils.creatBottomDialog(getSupportFragmentManager(), "2", R.layout.custom_dialog_bottom,
                        Gravity.BOTTOM, R.style.MyDialogAnimation_Translate_bottom, getWidthScrreen(this), 0, 0.2f,true);
                bottomDialog.setOnBindViewListener(new CustomDialog.OnBindViewListener() {
                    @Override
                    public void getBindView(View view) {
                        final LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.custom_ll_bottom);
                        int childCount = linearLayout.getChildCount();
                        for (int i = 0; i < childCount; i++) {
                            final int finalI = i;
                            linearLayout.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Toast.makeText(MainActivity.this,finalI+"finalI",Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                });
                break;
            case R.id.main_btn_download:
                final CustomDialog dialogView =DialogPakageUtils.creatDownLoadInfoDialog(getSupportFragmentManager(), "3",
                        R.layout.custom_dialog_download,R.style.MyDialogAnimation_Translate_bottom,getWidthScrreen(this),0,0.2f,false);
                dialogView.setOnBindViewListener(new CustomDialog.OnBindViewListener() {
                    @Override
                    public void getBindView(View view) {
                        final ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.custom_pb_downlaod);
                        final TextView textView = (TextView) view.findViewById(R.id.custom_tv_download);
                        progressBar.setMax(100);
                        CountDownTimer downTimer = new CountDownTimer(10000,1000) {
                            @Override
                            public void onTick(long l) {
                                progressBar.setProgress((10000/100-(int)l/100));
                                textView.setText((10000/100-(int)l/100)+"%");
                            }

                            @Override
                            public void onFinish() {
                                Toast.makeText(MainActivity.this, "下载完成!", Toast.LENGTH_SHORT).show();
                                progressBar.setProgress(100);
                                dialogView.dismiss();
                            }
                        }.start();

                    }
                });
                break;
            case R.id.main_btn_list:
                List<String> dataList = new ArrayList();
                for (int i = 0; i < 20; i++) {
                    dataList.add("这是第"+i+"条数据!");
                }
                listView = new RecyclerView(MainActivity.this);
                layoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
                listView.setLayoutManager(layoutManager);
                listView.addItemDecoration(new CustomItemDecoration(MainActivity.this,LinearLayoutManager.VERTICAL));
                MyAdapter myAdapter = new MyAdapter(R.layout.custom_dialog_list_item);
                myAdapter.setData(dataList);
                final CustomDialog dialog = DialogPakageUtils.creatListDialog(getSupportFragmentManager(), "4", listView, myAdapter,
                        getWidthScrreen(this), getHeightScrreen(this) / 3,R.style.MyDialogAnimation_Translate_Left,Gravity.BOTTOM, false);
                myAdapter.setOnItemClickListener(new ListDialogAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(ListDialogAdapter adapter, View view, int position) {
                        String item = (String) adapter.getItem(position);
                        Toast.makeText(MainActivity.this,"点击了:"+item,Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                break;
            case R.id.main_btn_advertise:
                final CustomDialog advertisedialog = DialogPakageUtils.creatAdvertiseDialog(getSupportFragmentManager(), "5", R.layout.custom_dialog_advertise,
                        R.style.MyDialogAnimation_Rotate, getWidthScrreen(this), 0, 0.5f, true);
                advertisedialog.setOnBindViewListener(new CustomDialog.OnBindViewListener() {
                    @Override
                    public void getBindView(View view) {
                        ImageView imageView = (ImageView) view.findViewById(R.id.custon_iv_advertise_cancel);
                        imageView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                advertisedialog.dismiss();
                            }
                        });
                    }
                });
                break;
        }
    }
    public static int getHeightScrreen(Activity activity){
        DisplayMetrics outMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
        int heightPixels = outMetrics.heightPixels;
        return heightPixels;
    }
    public static int getWidthScrreen(Activity activity){
        DisplayMetrics outMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
        int widthPixels = outMetrics.widthPixels;
        return widthPixels;
    }
}
