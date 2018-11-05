package com.gis.zn.funmagicity;

import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gis.zn.funmagicity.entity.Picture;
import com.gis.zn.funmagicity.entity.Scenery;
import com.gis.zn.funmagicity.ui.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SpotInfoActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.spot_info_title)
    TextView spot_info_title;
    @Bind(R.id.spot_info_intro)
    TextView spot_info_intro;
    //    @Bind(R.id.spot_info_back)
//    FloatingActionButton spot_info_back;
    @Bind(R.id.img_spot_info)
    ImageView img_spot_info;

    private Picture scenery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spot_info);
        ButterKnife.bind(this);

        scenery = (Picture) getIntent().getSerializableExtra("scenery");
        initView();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
//            case R.id.spot_info_back:
//                finish();
//                break;

        }
    }

    void initView() {
        spot_info_title.setText(scenery.getDescription());
        spot_info_intro.setText(scenery.getUsername());
        if (scenery.getImage() != null) {
            Uri uri = Uri.parse(scenery.getImage().getUrl());
            Glide.with(getApplicationContext())
                    .load(uri)
                    .placeholder(R.drawable.test)//图片加载出来前，显示的图片
                    .error(R.drawable.test)//图片加载失败后，显示的图片
                    .into(img_spot_info);
        }

//        spot_info_back.setOnClickListener(this);
    }
}
