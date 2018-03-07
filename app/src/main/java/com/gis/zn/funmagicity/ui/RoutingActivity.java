package com.gis.zn.funmagicity.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.gis.zn.funmagicity.R;
import com.tencent.mapsdk.raster.model.LatLng;
import com.tencent.tencentmap.mapsdk.map.MapActivity;
import com.tencent.tencentmap.mapsdk.map.MapView;
import com.tencent.tencentmap.mapsdk.map.TencentMap;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RoutingActivity extends MapActivity {

    private String region = "上海";
    private String startEt = "思源路";
    private String endEt = "南京东路";

    private TencentMap tencentMap;



    @Bind(R.id.mapview)
    MapView mapview;
    @Bind(R.id.fab_down1)
    FloatingActionButton fab_down;
    @Bind(R.id.fab_next_line1)
    FloatingActionButton fab_next_line;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tencent_map);
        ButterKnife.bind(this);
        mapview.onCreate(savedInstanceState);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        initView();

        fab_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();



            }
        });
        fab_next_line.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    void initView() {
        //获取TencentMap实例
        tencentMap = mapview.getMap();
//设置卫星底图
        tencentMap.setSatelliteEnabled(true);
//设置实时路况开启
        tencentMap.setTrafficEnabled(true);
//设置地图中心点
        tencentMap.setCenter(new LatLng(39, 116));
//设置缩放级别
        tencentMap.setZoom(11);
    }

    @Override
    protected void onDestroy() {
        mapview.onDestroy();
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        mapview.onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        mapview.onResume();
        super.onResume();
    }

    @Override
    protected void onStop() {
        mapview.onStop();
        super.onStop();
    }
}
