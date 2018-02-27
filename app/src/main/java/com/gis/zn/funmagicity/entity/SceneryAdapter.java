package com.gis.zn.funmagicity.entity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gis.zn.funmagicity.R;

import org.w3c.dom.Text;

import java.util.List;

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
        Scenery scenery=getItem(position);
        View view= LayoutInflater.from(getContext()).inflate(resourseId,parent,false);
        ImageView sceneryImage=(ImageView)view.findViewById(R.id.scen_img);
        TextView sceneryName=(TextView)view.findViewById(R.id.scen_name);
        TextView sceneryIntro=(TextView)view.findViewById(R.id.scen_intro);

        sceneryName.setText(scenery.getName());
        sceneryIntro.setText(scenery.getIntro());

        return view;
    }

}
