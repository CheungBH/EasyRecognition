package com.arcsoft.sdk_demo;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/11/23/023.
 */

public class ImgBean implements Serializable{
    private String imgName;
    private String imgNameFormat;
    private String imgFilePath;

    public ImgBean(String tmpName, String tmpNameForm, String path) {
        imgName=tmpName;
        imgNameFormat=tmpNameForm;
        imgFilePath=path;
    }

    public String getImgNameFormat() {
        return imgNameFormat;
    }

    public void setImgNameFormat(String imgNameFormat) {
        this.imgNameFormat = imgNameFormat;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public String getImgFilePath() {
        return imgFilePath;
    }

    public void setImgFilePath(String imgFilePath) {
        this.imgFilePath = imgFilePath;
    }
}
