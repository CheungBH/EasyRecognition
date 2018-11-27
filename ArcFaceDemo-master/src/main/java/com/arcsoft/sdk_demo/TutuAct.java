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
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.support.v4.content.FileProvider;
import android.widget.Toast;

import com.yanzhenjie.album.Album;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/11/23/023.
 */

public class TutuAct extends Activity {
    private EditText sousuoEt, nameEt;
    private Button soBtn, addBtn;
    private GridView gridV;
    private String alubmPhoPath = "";
    private String guPath;
    private List<ImgBean> imgList = new ArrayList<ImgBean>();
    private ImgAdapter imgAdapt;
    private List<ImgBean> imgSearchList;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==3){
                Toast.makeText(TutuAct.this,"添加成功！",Toast.LENGTH_SHORT).show();
                huoquImgGrid(1);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tutu);
        sousuoEt = findViewById(R.id.sousuoEt);
        nameEt = findViewById(R.id.nameEt);
        soBtn = findViewById(R.id.soBtn);
        addBtn = findViewById(R.id.addBtn);
        gridV = findViewById(R.id.gridV);
        gridV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(TutuAct.this, GallaryAct.class);
                intent.putExtra("imgList", (Serializable) imgAdapt.getSzBeanList());
                intent.putExtra("curPoi",i);
               startActivityForResult(intent,6);
            }
        });
        soBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchImg();
            }
        });
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlbumAction();
            }
        });
        guPath = getExternalFilesDir("").getAbsolutePath();
        huoquImgGrid(0);

    }

    private void huoquImgGrid(int flag) {
        Log.i("imgUri", "---huoquImgGrid-000-");
        File file = new File(guPath);
        File imgs[] = file.listFiles();
        Log.i("imgUri", "---huoquImgGrid-1111---=" + imgs.length);
        ImgBean mbean;
        String tmpName;
        String tmpNameForm;
        imgList.clear();
        for (int i = 0, len = imgs.length; i < len; i++) {
            tmpNameForm = imgs[i].getName();
            tmpName = tmpNameForm.substring(0, tmpNameForm.lastIndexOf("."));
            Log.i("imgUri", i + "---getName--=" + imgs[i].getName());
            Log.i("imgUri", i + "---tmpName=" + tmpName);
            mbean = new ImgBean(tmpName, tmpNameForm, guPath + "/" + tmpNameForm);
            imgList.add(mbean);
        }
        if(flag==0){
            imgAdapt = new ImgAdapter(imgList, TutuAct.this);
            gridV.setAdapter(imgAdapt);
        }else if(flag==1){
            imgAdapt.mUpImgData(imgList);
        }
    }

    private void searchImg() {
        searchImgGrid(1);
    }

    private void searchImgGrid(int flg) {
        String searh=sousuoEt.getText().toString().trim();
        imgSearchList=new ArrayList<>();
        ImgBean tmpObj;
        Boolean hasBool=false;
        for (int i = 0, len = imgList.size(); i < len; i++) {
            tmpObj=imgList.get(i);
            if(searh.length()>0&&tmpObj.getImgName().contains(searh)){
                imgSearchList.add(tmpObj);
                hasBool=true;
            }
        }
        if (!hasBool&&searh.length()>0) {
            Toast.makeText(TutuAct.this, "未搜到资源", Toast.LENGTH_SHORT).show();
        }
        if(searh.length()>0){
            imgAdapt.mUpImgData(imgSearchList);
        }else{
            huoquImgGrid(1);
        }

    }

    private void showAlbumAction() {
        String addName = nameEt.getText().toString().trim();
        if (addName.length() < 1) {
            Toast.makeText(TutuAct.this, "先命名", Toast.LENGTH_SHORT).show();
            return;
        }
        alubmPhoPath = getExternalFilesDir("").getAbsolutePath();
        File mPath=new File(alubmPhoPath);
        if(!mPath.exists()){
            mPath.mkdirs();
        }
        Log.i("imgUri", "getFilesDir---alubmPhoPath000000--=" + alubmPhoPath);
        alubmPhoPath = alubmPhoPath + "/" + addName + ".jpg";
        Log.i("imgUri", "getFilesDir---alubmPhoPath111111--=" + alubmPhoPath);
        Album.startAlbum(TutuAct.this, 100, 1);

    }

    @SuppressLint("WrongConstant")
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            if (resultCode == RESULT_OK) { // 判断是否成功。
                // 拿到用户选择的图片路径List：
                List<String> pathList = Album.parseResult(data);
                Log.i("imgUri", "pathList---=" + pathList.size());
                if(pathList.size()>0){
                    FileUtils.copyFile(handler,pathList.get(0), alubmPhoPath);
                }
                for (int i = 0, len = pathList.size(); i < len; i++) {
                    Log.i("imgUri", i + "--onActivityResult--name--=" + pathList.get(i));
                }
            } else if (resultCode == RESULT_CANCELED) { // 用户取消选择。
                // 根据需要提示用户取消了选择。
            }
        }else if(requestCode == 6&&resultCode==66){
            huoquImgGrid(1);
        }
    }


}
