package com.gis.zn.funmagicity.entity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
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

import org.w3c.dom.Text;

import java.io.File;
import java.util.List;

import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.DownloadFileListener;

/**
 * Created by zhaoning on 2018/2/28.
 */

public class SceneryAdapter extends ArrayAdapter<Scenery> {
    private int resourseId;

    public SceneryAdapter(@NonNull Context context, int textViewResourceId, @NonNull List<Scenery> objects) {
        super(context,textViewResourceId, objects);
        resourseId=textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        final Scenery scenery=getItem(position);
        View view;
        ViewHolder viewHolder;
        if(convertView==null){
            view=LayoutInflater.from(getContext()).inflate(resourseId,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.sceneryImage=(ImageView)view.findViewById(R.id.scen_img);
            viewHolder.sceneryName=(TextView)view.findViewById(R.id.scen_name);
            viewHolder.sceneryIntro=(TextView)view.findViewById(R.id.scen_intro);
            viewHolder.mCheckBox=(CheckBox) view.findViewById(R.id.checkBox);
            view.setTag(viewHolder);
        }
        else {
            view=convertView;
            viewHolder=(ViewHolder)view.getTag();//重新获取viewHolder
        }
        viewHolder.sceneryName.setText(scenery.getName());
        viewHolder.sceneryIntro.setText(scenery.getIntro());

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
        viewHolder.mCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckBox cb=(CheckBox) view.findViewById(R.id.checkBox);
                if(cb.isChecked()){
                    scenery.setChecked(true);
                }
                else
                    scenery.setChecked(false);
                Log.i("SceneryAdapter", "checkout onClick: "+scenery.getName()+scenery.isChecked());
            }
        });

        viewHolder.sceneryImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), SpotInfoActivity.class);
                intent.putExtra("scenery",scenery);
                getContext().startActivity(intent);
            }
        });
        viewHolder.sceneryName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), SpotInfoActivity.class);
                intent.putExtra("scenery",scenery);
                getContext().startActivity(intent);
            }
        });
        viewHolder.sceneryIntro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), SpotInfoActivity.class);
                intent.putExtra("scenery",scenery);
                getContext().startActivity(intent);
            }
        });
        return view;
    }

    class ViewHolder{
        ImageView sceneryImage;
        TextView sceneryName;
        TextView sceneryIntro;
        CheckBox mCheckBox;

    }

}
