package com.gis.zn.funmagicity.entity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gis.zn.funmagicity.R;
import com.gis.zn.funmagicity.SpotInfoActivity;

import java.util.List;

import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by zhaoning on 2018/3/5.
 */

public class SpotOrderAdapter extends ArrayAdapter<Scenery> {
    private int resourseId;

    public SpotOrderAdapter(@NonNull Context context, int textViewResourceId, @NonNull List<Scenery> objects) {
        super(context,textViewResourceId, objects);
        resourseId=textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        final Scenery scenery=getItem(position);
        View view;
        SpotOrderAdapter.ViewHolder viewHolder;
        if(convertView==null){
            view= LayoutInflater.from(getContext()).inflate(resourseId,parent,false);
            viewHolder = new SpotOrderAdapter.ViewHolder();
            viewHolder.sceneryImage=(ImageView)view.findViewById(R.id.spot_order_img);
            viewHolder.sceneryName=(TextView)view.findViewById(R.id.spot_order_name);
            viewHolder.sceneryPath=(TextView)view.findViewById(R.id.spot_order_path);
            view.setTag(viewHolder);
        }
        else {
            view=convertView;
            viewHolder=(SpotOrderAdapter.ViewHolder)view.getTag();//重新获取viewHolder
        }
        viewHolder.sceneryName.setText(scenery.getName());
        viewHolder.sceneryPath.setText(scenery.getIntro());

        BmobFile bmobfile = scenery.getImage();
        if(bmobfile!= null){
            Log.i("bmobfile",bmobfile.getUrl()+" | "+bmobfile.getFileUrl());
            Uri uri = Uri.parse(bmobfile.getUrl());
            Glide.with(getContext())
                    .load(uri)
                    .placeholder(R.drawable.test)//图片加载出来前，显示的图片
                    .error(R.drawable.test)//图片加载失败后，显示的图片
                    .thumbnail( 0.2f )
                    .into(viewHolder.sceneryImage);
//            downloadFile(bmobfile,sceneryImage);
        }
        return view;
    }

    class ViewHolder{
        ImageView sceneryImage;
        TextView sceneryName;
        TextView sceneryPath;

    }
}
