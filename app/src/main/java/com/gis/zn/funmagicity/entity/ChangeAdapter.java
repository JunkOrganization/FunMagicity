package com.gis.zn.funmagicity.entity;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gis.zn.funmagicity.R;

import java.util.ArrayList;

import javax.security.auth.callback.Callback;

import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by zhaoning on 2018/3/8.
 */

public class ChangeAdapter extends BaseAdapter implements View.OnClickListener {

    private ArrayList<Scenery> itemList;
    private Context mContext;
    private Callback mCallback;
    private int mCurPosition;//定义该变量来标记当前item的点击位置

    public Context getContext() {
        return mContext;
    }

    //定义回调接口实现ListView内Item的内部控件的点击事件
    public interface Callback {
        public void click(View v);
    }

    public ChangeAdapter(Context mContext, ArrayList<Scenery> itemList, Callback mCallback, int mCurPosition) {
        this.mContext = mContext;
        this.itemList = itemList;
        this.mCallback = mCallback;
        this.mCurPosition = mCurPosition;
    }

    @Override
    public void onClick(View view) {
        mCallback.click(view);
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int i) {
        return itemList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_spot_order, null);
            viewHolder = new ViewHolder();
            viewHolder.sceneryImage=(ImageView)convertView.findViewById(R.id.spot_order_img);
            viewHolder.sceneryName=(TextView)convertView.findViewById(R.id.spot_order_name);
            viewHolder.sceneryPath=(TextView)convertView.findViewById(R.id.spot_order_path);
            viewHolder.up = (ImageView) convertView.findViewById(R.id.list_item_up);
            convertView.setTag(R.id.tag_viewholder, viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag(R.id.tag_viewholder);
        }
        viewHolder.sceneryName.setText(itemList.get(position).getName());
        BmobFile bmobfile = itemList.get(position).getImage();
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

        //根据点击或者向上向下操作的item的当前位置，来控制向上和向下的按钮的可见与否
//        if (mCurPosition == position && mCurPosition == 0) {
//            viewHolder.downBtn.setVisibility(View.VISIBLE);
//        } else if (mCurPosition == position && mCurPosition == itemList.size() - 1) {
//            viewHolder.upBtn.setVisibility(View.VISIBLE);
//        } else if (mCurPosition == position && mCurPosition != 0 && mCurPosition != itemList.size() - 1) {
//            viewHolder.upBtn.setVisibility(View.VISIBLE);
//            viewHolder.downBtn.setVisibility(View.VISIBLE);
//        } else {
//            viewHolder.upBtn.setVisibility(View.INVISIBLE);
//            viewHolder.downBtn.setVisibility(View.INVISIBLE);
//        }

        //设置item向下移动的点击时间并标志其位置
        viewHolder.up.setOnClickListener(this);
        viewHolder.up.setTag(position);

        //设置整个item的点击时间并标志其位置
        convertView.setOnClickListener(this);
        convertView.setTag(R.id.tag_item_click, position);

        return convertView;
    }

    class ViewHolder {
        ImageView sceneryImage;
        TextView sceneryName;
        TextView sceneryPath;
        ImageView up;
    }

    public void refresh(int currentPosition) {
        mCurPosition = currentPosition;
        notifyDataSetChanged();
    }
}
