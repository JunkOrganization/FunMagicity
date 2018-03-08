package com.gis.zn.funmagicity.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.gis.zn.funmagicity.R;
import com.tencent.lbssearch.TencentSearch;
import com.tencent.lbssearch.httpresponse.BaseObject;
import com.tencent.lbssearch.httpresponse.HttpResponseListener;
import com.tencent.lbssearch.object.Location;
import com.tencent.lbssearch.object.param.WalkingParam;
import com.tencent.lbssearch.object.result.DrivingResultObject;
import com.tencent.lbssearch.object.result.RoutePlanningObject;
import com.tencent.lbssearch.object.result.TransitResultObject;
import com.tencent.lbssearch.object.result.WalkingResultObject;
import com.tencent.mapsdk.raster.model.LatLng;
import com.tencent.mapsdk.raster.model.PolylineOptions;
import com.tencent.tencentmap.mapsdk.map.MapActivity;
import com.tencent.tencentmap.mapsdk.map.MapView;
import com.tencent.tencentmap.mapsdk.map.TencentMap;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RoutingActivity extends MapActivity {

    private String region = "上海";
    private String startEt = "思源路";
    private String endEt = "南京东路";

    private Location start;
    private Location destination;

    private TencentMap tencentMap;
    private Location[] locations;
    private ListView lvPlans;
    private MapView mapView;

    private List<WalkingResultObject.Route> walkRoutes;
    private List<DrivingResultObject.Route> driveRoutes;
    private List<TransitResultObject.Route> transitRoutes;

    @Bind(R.id.mapview)
    MapView mapview;
    @Bind(R.id.fab_walk)
    FloatingActionButton fab_walk;
    @Bind(R.id.fab_next_line1)
    FloatingActionButton fab_next_line;

    HttpResponseListener directionResponseListener =
            new HttpResponseListener() {

                @Override
                public void onSuccess(int arg0, BaseObject arg1) {
                    // TODO Auto-generated method stub
                    if (arg1 == null) {
                        return;
                    }
                    Log.e("searchdemo", "plan success");
                    RoutePlanningObject obj = (RoutePlanningObject) arg1;
//                    vanishPlans();
                    tencentMap.clearAllOverlays();
                    tencentMap.setCenter(new LatLng(31.22847, 121.4064));
                    tencentMap.setZoom(15);
                    if (obj instanceof WalkingResultObject) {
                        WalkingResultObject walkObj = (WalkingResultObject) obj;
                        walkRoutes = walkObj.result.routes;
                    }
                    drawSolidLine(walkRoutes.get(0).polyline);
//                    roadPlanAdapter.setPlanObject(obj);
//                    roadPlanAdapter.notifyDataSetChanged();
//                    showPlans();
                }

                @Override
                public void onFailure(int arg0, String arg1, Throwable arg2) {
                    // TODO Auto-generated method stub
                    Toast.makeText(RoutingActivity.this, arg1, Toast.LENGTH_SHORT).show();
                }
            };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tencent_map);
        ButterKnife.bind(this);
        mapview.onCreate(savedInstanceState);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        initView();
        bindListener();

//        fab_walk.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//
//
//            }
//        });
//        fab_next_line.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
    }

    void initView() {
        //获取TencentMap实例
        tencentMap = mapview.getMap();
//设置地图中心点
        tencentMap.setCenter(new LatLng(31.22847, 121.4064));
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

    protected Location[] getCoords() {
        start = new Location(31.22847f, 121.4064f);
        destination = new Location(31.240821f,121.491104f );
        Location[] locations = {start, destination};
        return locations;
    }

    protected void bindListener() {
        View.OnClickListener onClickListener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (locations == null) {
                    locations = getCoords();
                    if (locations[0] == null) {
                        Toast.makeText(RoutingActivity.this,
                                "起点坐标不合规则", Toast.LENGTH_LONG).show();
                        return;
                    }
                    if (locations[1] == null) {
                        Toast.makeText(RoutingActivity.this,
                                "终点坐标不合规则", Toast.LENGTH_LONG).show();
                        return;
                    }
                }
                fab_next_line.setSelected(false);

                switch (v.getId()) {
                    case R.id.fab_walk:
                        getWalkPlan();
                        Log.e("searchdemo", "walk click");
                        fab_walk.setSelected(true);
                        break;
//                    case R.id.iv_drive:
//                        getDrivePlan();
//                        Log.e("searchdemo", "drive click");
//                        btndrive.setSelected(true);
//                        break;
//                    case R.id.iv_transit:
//                        getTransitPlan();
//                        Log.e("searchdemo", "transit click");
//                        btnTransit.setSelected(true);
//                        break;

                    default:
                        break;
                }
            }
        };
        fab_walk.setOnClickListener(onClickListener);
//        btndrive.setOnClickListener(onClickListener);
//        btnTransit.setOnClickListener(onClickListener);

    }

    /**
     * 步行规划，只能设置起点和终点
     */
    protected void getWalkPlan() {
        TencentSearch tencentSearch = new TencentSearch(this);
        WalkingParam walkingParam = new WalkingParam();
        walkingParam.from(locations[0]);
        walkingParam.to(locations[1]);
        tencentSearch.getDirection(walkingParam, directionResponseListener);
    }

    protected void vanishPlans() {
        TranslateAnimation ta = new TranslateAnimation(0, 0, 0, mapView.getBottom());
        ta.setDuration(200);
        ta.setFillAfter(true);
        ta.setInterpolator(this, android.R.anim.decelerate_interpolator);
        ta.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // TODO Auto-generated method stub
                lvPlans.setVisibility(View.GONE);
                lvPlans.clearAnimation();
            }
        });
        lvPlans.startAnimation(ta);
    }

    /**
     * 将路线以实线画到地图上
     *
     * @param locations
     */
    protected void drawSolidLine(List<Location> locations) {
        tencentMap.addPolyline(new PolylineOptions().
                addAll(getLatLngs(locations)).
                color(0xff2200ff));
    }

    protected List<LatLng> getLatLngs(List<Location> locations) {
        List<LatLng> latLngs = new ArrayList<LatLng>();
        for (Location location : locations) {
            latLngs.add(new LatLng(location.lat, location.lng));
        }
        return latLngs;
    }

}
