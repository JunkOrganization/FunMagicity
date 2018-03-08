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
import android.widget.TextView;
import android.widget.Toast;

import com.gis.zn.funmagicity.R;
import com.gis.zn.funmagicity.entity.Scenery;
import com.tencent.lbssearch.TencentSearch;
import com.tencent.lbssearch.httpresponse.BaseObject;
import com.tencent.lbssearch.httpresponse.HttpResponseListener;
import com.tencent.lbssearch.object.Location;
import com.tencent.lbssearch.object.param.DrivingParam;
import com.tencent.lbssearch.object.param.RoutePlanningParam;
import com.tencent.lbssearch.object.param.TransitParam;
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

    private double start_lon;
    private double start_lat;
    private double end_lon;
    private double end_lat;

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
    @Bind(R.id.fab_car)
    FloatingActionButton fab_car;
    @Bind(R.id.fab_bus)
    FloatingActionButton fab_bus;
    @Bind(R.id.start_des)
    TextView start_des;
    @Bind(R.id.end_des)
    TextView end_des;
    @Bind(R.id.time)
    TextView time;

    HttpResponseListener directionResponseListener =
            new HttpResponseListener() {

                @Override
                public void onSuccess(int arg0, BaseObject arg1) {
                    // TODO Auto-generated method stub
                    String strLabel = "";
                    String strDesc = "";
                    if (arg1 == null) {
                        return;
                    }
                    Log.e("searchdemo", "plan success");
                    RoutePlanningObject obj = (RoutePlanningObject) arg1;
//                    vanishPlans();
                    tencentMap.clearAllOverlays();
                    tencentMap.setCenter(new LatLng(start_lat, start_lon));
                    tencentMap.setZoom(16);
                    if (obj instanceof WalkingResultObject) {
                        WalkingResultObject walkObj = (WalkingResultObject) obj;
                        walkRoutes = walkObj.result.routes;
                        drawSolidLine(walkRoutes.get(0).polyline);

                        WalkingResultObject.Route route = walkRoutes.get(0);
                        strLabel = new String("距离：" + getDistance(route.distance) +
                                ", 预计用时：" + getDuration(route.duration));
            /*
             * RoutePlanningObject.Step 每段路线的详细信息
			 * 附加描述 accessorial_desc 如“进入主路”
			 * 动作描述 act_desc
			 * 方向 dir_desc
			 * 距离 distance
			 * 用时 duration
			 * 默认提供的路段说明 instruction
			 * 路段点串在总路线点串中的位置 polyline_idx(起点和终点)
			 * 路名 road_name
			 */
                        if (route.steps != null && route.steps.size() > 0) {
                            strDesc = new String(route.steps.get(0).instruction + "... ...");
                        } else {
                            strDesc = new String("暂无详情");
                        }

                        Toast.makeText(RoutingActivity.this, strDesc, Toast.LENGTH_LONG).show();
                        time.setText(strLabel);
                    } else if (obj instanceof DrivingResultObject) {
                        DrivingResultObject drivingObj = (DrivingResultObject) obj;
                        driveRoutes = drivingObj.result.routes;
                        drawSolidLine(driveRoutes.get(0).polyline);


                        DrivingResultObject.Route route = driveRoutes.get(0);
                        strLabel = new String("距离：" + getDistance(route.distance) +
                                ", 预计用时：" + getDuration(route.duration));
                        if (route.steps != null && route.steps.size() > 0) {
                            strDesc = new String(route.steps.get(0).instruction + "... ...");
                        } else {
                            strDesc = new String("暂无详情");
                        }
                        Toast.makeText(RoutingActivity.this, strDesc, Toast.LENGTH_LONG).show();
                        time.setText(strLabel);
                    } else if (obj instanceof TransitResultObject) {
                        TransitResultObject transitObj = (TransitResultObject) obj;
                        transitRoutes = transitObj.result.routes;
                        List<TransitResultObject.Segment> segments =
                                transitRoutes.get(0).steps;
                        for (TransitResultObject.Segment segment : segments) {
                            if (segment instanceof TransitResultObject.Walking) {
                                drawDotLine(((TransitResultObject.Walking) segment).polyline);
                            }
                            if (segment instanceof TransitResultObject.Transit) {
                                drawSolidLine(((TransitResultObject.Transit) segment).lines.get(0).polyline);
                            }
                        }

                        TransitResultObject.Route route = transitRoutes.get(0);
                        strLabel = new String("距离：" + getDistance(route.distance) +
                                "，预计用时：" + getDuration(route.duration));
			/*
			 * TransitResultObject.Segment是TransitResultObject.Walking和
			 * TransitResultObject.Transit的父类
			 * 其中TransitResultObject.Walking的结构基本与WalkingResultObject.Route相同
			 * TransitResultObject.Transit中包含lines字段，只有lines的第一个元素才会有路线点串
			 */
                        if (segments == null) {
                            strDesc = new String("暂无详情");
                        }
                        StringBuilder stringBuilder = new StringBuilder();
                        for (int i = 0; i < segments.size(); i++) {
                            TransitResultObject.Segment segment = segments.get(i);
                            if (segment instanceof TransitResultObject.Transit) {
                                List<TransitResultObject.Line> lines = ((TransitResultObject.Transit) segment).lines;
                                if (lines == null) {
                                }
                                stringBuilder.append(lines.get(0).title);
                                if (lines.size() > 1) {
                                    for (int j = 1; j < lines.size(); j++) {
                                        stringBuilder.append("/" + lines.get(j).title);
                                    }
                                }
                            }
                            if (i != 0 && i < segments.size() - 1
                                    && segments.get(i + 1) instanceof TransitResultObject.Transit) {
                                stringBuilder.append(" -> ");
                            }
                        }
                        strDesc = stringBuilder.toString();
                        Toast.makeText(RoutingActivity.this, strDesc, Toast.LENGTH_LONG).show();
                        time.setText(strLabel);

                    }
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

        Scenery start = (Scenery) getIntent().getSerializableExtra("start");
        Scenery end = (Scenery) getIntent().getSerializableExtra("end");
        start_lon = start.getLongitude();
        start_lat = start.getLatitude();
        end_lon = end.getLongitude();
        end_lat = end.getLatitude();
        start_des.setText(start.getName());
        end_des.setText(end.getName());

        mapview.onCreate(savedInstanceState);

        Log.e("start_lon", String.valueOf(start_lon));

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
        tencentMap.setCenter(new LatLng(start_lat, start_lon));
//设置缩放级别
        tencentMap.setZoom(14);
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
        start = new Location((float) start_lat, (float) start_lon);
        destination = new Location((float) end_lat, (float) end_lon);
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

                fab_walk.setSelected(false);
                fab_car.setSelected(false);
                fab_bus.setSelected(false);

                switch (v.getId()) {
                    case R.id.fab_walk:
                        getWalkPlan();
                        Log.e("searchdemo", "walk click");
                        fab_walk.setSelected(true);
                        break;
                    case R.id.fab_car:
                        getDrivePlan();
                        Log.e("searchdemo", "drive click");
                        fab_car.setSelected(true);
                        break;
                    case R.id.fab_bus:
                        getTransitPlan();
                        Log.e("searchdemo", "transit click");
                        fab_bus.setSelected(true);
                        break;
                    default:
                        break;
                }
            }
        };
        fab_walk.setOnClickListener(onClickListener);
        fab_car.setOnClickListener(onClickListener);
        fab_bus.setOnClickListener(onClickListener);

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
     * 驾车规划，支持途经点和策略设置，具体信息见文档
     */
    protected void getDrivePlan() {
        TencentSearch tencentSearch = new TencentSearch(this);
        DrivingParam drivingParam = new DrivingParam();
        drivingParam.from(locations[0]);
        drivingParam.to(locations[1]);
        //策略
        drivingParam.policy(RoutePlanningParam.DrivingPolicy.LEAST_DISTANCE);
        //途经点
//		drivingParam.addWayPoint(new Location(39.898938f, 116.348648f));
        tencentSearch.getDirection(drivingParam, directionResponseListener);
    }

    /**
     * 公交换乘，支持策略，具体信息见文档
     */
    protected void getTransitPlan() {
        TencentSearch tencentSearch = new TencentSearch(this);
        TransitParam transitParam = new TransitParam();
        transitParam.from(locations[0]);
        transitParam.to(locations[1]);
        //策略
        transitParam.policy(RoutePlanningParam.TransitPolicy.LEAST_TIME);
        tencentSearch.getDirection(transitParam, directionResponseListener);
    }

    /**
     * 将路线以实线画到地图上
     *
     * @param locations
     */
    protected void drawSolidLine(List<Location> locations) {
        tencentMap.addPolyline(new PolylineOptions().
                addAll(getLatLngs(locations)).
                color(0xffFF8432));
    }

    /**
     * 将路线以虚线画到地图上，用于公交中的步行
     *
     * @param locations
     */
    protected void drawDotLine(List<Location> locations) {
        tencentMap.addPolyline(new PolylineOptions().
                addAll(getLatLngs(locations)).
                color(0xffFF8432).
                setDottedLine(true));
    }

    protected List<LatLng> getLatLngs(List<Location> locations) {
        List<LatLng> latLngs = new ArrayList<LatLng>();
        for (Location location : locations) {
            latLngs.add(new LatLng(location.lat, location.lng));
        }
        return latLngs;
    }

    /**
     * 将距离转换成米或千米
     *
     * @param distance
     * @return
     */
    protected String getDistance(float distance) {
        if (distance < 1000) {
            return Integer.toString((int) distance) + "米";
        } else {
            return Float.toString((float) ((int) (distance / 10)) / 100) + "千米";
        }
    }

    /**
     * 将时间转换成小时+分钟
     *
     * @param duration
     * @return
     */
    protected String getDuration(float duration) {
        if (duration < 60) {
            return Integer.toString((int) duration) + "分钟";
        } else {
            return Integer.toString((int) (duration / 60)) + "小时"
                    + Integer.toString((int) (duration % 60)) + "分钟";
        }
    }
}
