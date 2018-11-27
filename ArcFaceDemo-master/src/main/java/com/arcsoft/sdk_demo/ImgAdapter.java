package com.arcsoft.sdk_demo;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.List;

/**
 * Created by Administrator on 2018/10/24/024.
 */

public class ImgAdapter extends BaseAdapter {
    private List<ImgBean> szBeanList;
    private Context context;

    public ImgAdapter(List<ImgBean> list,Context cont) {
        this.szBeanList = list;
        context=cont;
    }

    @Override
    public int getCount() {
        return szBeanList == null ? 0 : szBeanList.size();
    }

    @Override
    public ImgBean getItem(int position) {
        return  szBeanList == null ? null : szBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if(convertView == null){
            convertView = View.inflate(parent.getContext(), R.layout.tu_item,null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        }else{
            vh = (ViewHolder) convertView.getTag();
        }
        ImgBean szBean= szBeanList.get(position);
        vh.tuTxv.setText(""+szBean.getImgName());
        Glide.with(context).load(new File(szBean.getImgFilePath())).into(vh.tuIv);
        return convertView;
    }

    public void mUpImgData(List<ImgBean> imgSearchList) {
        szBeanList=imgSearchList;
        notifyDataSetChanged();
    }

    class ViewHolder{
        private TextView tuTxv;
        private ImageView tuIv;

        public ViewHolder(View v){
            tuIv =  (ImageView) v.findViewById(R.id.tuIv);
            tuTxv =  (TextView) v.findViewById(R.id.tuTxv);
        }
    }

    public List<ImgBean> getSzBeanList() {
        return szBeanList;
    }

    public void setSzBeanList(List<ImgBean> szBeanList) {
        this.szBeanList = szBeanList;
    }
}
