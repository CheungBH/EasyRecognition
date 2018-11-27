package com.arcsoft.sdk_demo;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
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

public class GallaryAdapter extends BaseAdapter {
    private List<ImgBean> szBeanList;
    private Context context;
    private int screnWidth;
    private int screnHeight;
    private Handler handler;


    public GallaryAdapter(List<ImgBean> list, Context cont,int screnW,int screnH,Handler handl) {
        this.szBeanList = list;
        context = cont;
        screnWidth = screnW;
        screnHeight = screnH;
        handler=handl;
    }

    @Override
    public int getCount() {
        return szBeanList == null ? 0 : szBeanList.size();
    }

    @Override
    public ImgBean getItem(int position) {
        return szBeanList == null ? null : szBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.gallery_item, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        final ImgBean szBean = szBeanList.get(position);
        vh.tuTxv.setText("" + szBean.getImgName());
        ViewGroup.LayoutParams para =vh.tuIv.getLayoutParams();
        para.width=screnWidth;
        para.height=screnHeight;
        Glide.with(context).load(new File(szBean.getImgFilePath())).into(vh.tuIv);
        vh.downTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Message mag=new Message();
                mag.what=1;
                mag.obj=szBean;
                handler.sendMessage(mag);
            }
        });
        vh.delTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Message mag=new Message();
                mag.what=2;
                mag.arg1=position;
                mag.obj=szBean;
                handler.sendMessage(mag);
            }
        });

        return convertView;
    }

    public void mUpImgData(List<ImgBean> imgSearchList) {
        szBeanList = imgSearchList;
        notifyDataSetChanged();
    }

    class ViewHolder {
        private TextView tuTxv,downTv,delTv;
        private ImageView tuIv;

        public ViewHolder(View v) {
            tuIv = (ImageView) v.findViewById(R.id.tuIv);
            delTv= (TextView) v.findViewById(R.id.delTv);
            downTv= (TextView) v.findViewById(R.id.downTv);
            tuTxv = (TextView) v.findViewById(R.id.tuTxv);
        }
    }
}
