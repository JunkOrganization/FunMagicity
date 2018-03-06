package com.gis.zn.funmagicity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.gis.zn.funmagicity.entity.Scenery;
import com.gis.zn.funmagicity.entity.SpotOrderAdapter;
import com.gis.zn.funmagicity.ui.BaseActivity;
import com.gis.zn.funmagicity.ui.RoutingActivity;
import com.gis.zn.funmagicity.ui.TestActivity;
import com.gis.zn.funmagicity.ui.UserInfoActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SpotsOrderActivity extends BaseActivity implements View.OnClickListener{

    @Bind(R.id.fab_order)
    FloatingActionButton fab_order;
    @Bind(R.id.back_order_spots)
    ImageView back_order_spots;
    @Bind(R.id.user_info_order_spots)
    ImageView user_info_order_spots;
    @Bind(R.id.listview_ordered)
    ListView listview_ordered;

    private List<Scenery> mSceneryList = new ArrayList<>();
    private List<Scenery> mScenerySelectedList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spots_order);
        ButterKnife.bind(this);
        mSceneryList = (List<Scenery>) getIntent().getSerializableExtra("SpotList");
        selectSpots();

        SpotOrderAdapter sceneryAdapter = new SpotOrderAdapter(SpotsOrderActivity.this, R.layout.item_spot_order, mScenerySelectedList);
        listview_ordered.setAdapter(sceneryAdapter);

        listview_ordered.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(SpotsOrderActivity.this, RoutingActivity.class);
                startActivity(intent);
            }
        });
    }

    void initView() {
        user_info_order_spots.setOnClickListener(this);
        back_order_spots.setOnClickListener(this);
    }

    void selectSpots() {
        for (Scenery scenery : mSceneryList) {
            if (scenery.isChecked()) {
                mScenerySelectedList.add(scenery);
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.user_info_order_spots:
                startActivity(new Intent(SpotsOrderActivity.this, UserInfoActivity.class));
                break;
            case R.id.back_order_spots:
                Intent intent = new Intent(SpotsOrderActivity.this, Main2Activity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
        }
    }
}
