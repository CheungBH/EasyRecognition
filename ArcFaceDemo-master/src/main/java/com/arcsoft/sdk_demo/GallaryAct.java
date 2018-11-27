package com.arcsoft.sdk_demo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.GridView;
import android.widget.Toast;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/11/23/023.
 */

public class GallaryAct extends Activity {
    private Gallery gallery1;
    private List<ImgBean> imgList;
    private GallaryAdapter imgAdapt;
    private int screenWidth = 300; // 屏幕宽（像素，如：480px）
    private int screenHeight = 600; // 屏幕高（像素，如：800p）
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1://下载
                    ImgBean bean = (ImgBean) msg.obj;
                    if (bean != null) {
                        File lujing = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                        if (!lujing.exists()) {
                            lujing.mkdirs();
                        }
                        String newPafi = lujing.getAbsolutePath() + "/" + bean.getImgName() + ".jpg";
                        Log.i("mmc", "newPafi-----=" + newPafi);
//                        Toast.makeText(GallaryAct.this, "下载中" + lujing.getAbsolutePath() + "查看下载资源", Toast.LENGTH_SHORT).show();
                        FileUtils.copyFile(handler, bean.getImgFilePath(), newPafi);

                    }
                    break;
                case 2://删除
                    ImgBean bean2 = (ImgBean) msg.obj;
                    String newPafi = bean2.getImgFilePath();
                    File delFile=new File(newPafi);
                    if (delFile.exists()) {
                        delFile.delete();
                    }
                    setResult(66);
                    imgList.remove(msg.arg1);
                    imgAdapt.mUpImgData(imgList);
                    Toast.makeText(GallaryAct.this, "删除成功！", Toast.LENGTH_LONG).show();
                    break;
                case 3:
                    File lujing3 =Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                    Toast.makeText(GallaryAct.this, "下载成功！请到" + lujing3.getAbsolutePath() + "查看下载资源", Toast.LENGTH_LONG).show();
                    break;
                default:break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery);
        gallery1 = findViewById(R.id.gallery1);

        screenWidth = getWindowManager().getDefaultDisplay().getWidth();
        screenHeight = getWindowManager().getDefaultDisplay().getHeight();

        imgList = (List<ImgBean>) getIntent().getExtras().get("imgList");
     int poi= (int) getIntent().getExtras().get("curPoi");
        List<ImgBean> tmpList=new ArrayList<>();
        tmpList.add(imgList.get(poi));
        for (int i = 0, len = imgList.size(); i < len; i++) {
            if(i!=poi){
                tmpList.add(imgList.get(i));
            }
        }
        imgAdapt = new GallaryAdapter(tmpList, GallaryAct.this, screenWidth, screenHeight - 250, handler);
        gallery1.setAdapter(imgAdapt);
    }


}
