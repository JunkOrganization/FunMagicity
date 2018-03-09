package com.gis.zn.funmagicity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.gis.zn.funmagicity.entity.ChangeAdapter;
import com.gis.zn.funmagicity.entity.Scenery;
import com.gis.zn.funmagicity.entity.SpotOrderAdapter;
import com.gis.zn.funmagicity.ui.BaseActivity;
import com.gis.zn.funmagicity.ui.LoginActivity;
import com.gis.zn.funmagicity.ui.RoutingActivity;
import com.gis.zn.funmagicity.ui.TestActivity;
import com.gis.zn.funmagicity.ui.UserInfoActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobUser;

public class SpotsOrderActivity extends BaseActivity implements ChangeAdapter.Callback {

    @Bind(R.id.fab_order)
    FloatingActionButton fab_order;
    @Bind(R.id.back_order_spots)
    ImageView back_order_spots;
    @Bind(R.id.user_info_order_spots)
    ImageView user_info_order_spots;
    @Bind(R.id.listview_ordered)
    ListView listview_ordered;

    private List<Scenery> mSceneryList = new ArrayList<>();
    private ArrayList<Scenery> mScenerySelectedList = new ArrayList<>();

    private ChangeAdapter adapter;
    private int currentPosition = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spots_order);
        ButterKnife.bind(this);
        mSceneryList = (List<Scenery>) getIntent().getSerializableExtra("SpotList");
        selectSpots();

        initView();

//        SpotOrderAdapter sceneryAdapter = new SpotOrderAdapter(SpotsOrderActivity.this, R.layout.item_spot_order, mScenerySelectedList);
//        listview_ordered.setAdapter(sceneryAdapter);

//        listview_ordered.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Intent intent = new Intent(SpotsOrderActivity.this, RoutingActivity.class);
//                if (mScenerySelectedList.size() > 1) {
//                    Scenery start;
//                    Scenery end;
//                    if (i < mScenerySelectedList.size() - 1) {
//                        start = mScenerySelectedList.get(i);
//                        end = mScenerySelectedList.get(i + 1);
//                    } else {
//                        start = mScenerySelectedList.get(i - 1);
//                        end = mScenerySelectedList.get(i);
//                    }
//                    intent.putExtra("start", start);
//                    intent.putExtra("end", end);
//                    startActivity(intent);
//                } else {
//                    Toast.makeText(SpotsOrderActivity.this, "请至少选择两个景点才可生成路线", Toast.LENGTH_LONG).show();
//                }
//
//            }
//        });
    }

    void initView() {
//        user_info_order_spots.setOnClickListener(this);
//        back_order_spots.setOnClickListener(this);

        adapter = new ChangeAdapter(this, mScenerySelectedList, this, currentPosition);
        listview_ordered.setAdapter(adapter);

        user_info_order_spots.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BmobUser currentUser = BmobUser.getCurrentUser();
                if(currentUser==null)
                {
                    startActivity(new Intent(SpotsOrderActivity.this, LoginActivity.class));
                }
                else {
                    startActivity(new Intent(SpotsOrderActivity.this, UserInfoActivity.class));

                }
            }
        });

        back_order_spots.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SpotsOrderActivity.this, Main2Activity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }

    void selectSpots() {
        for (Scenery scenery : mSceneryList) {
            if (scenery.isChecked()) {
                mScenerySelectedList.add(scenery);
            }
        }
    }
//
//    @Override
//    public void onClick(View view) {
//        switch (view.getId()){
//            case R.id.user_info_order_spots:
//                startActivity(new Intent(SpotsOrderActivity.this, UserInfoActivity.class));
//                break;
//            case R.id.back_order_spots:
//                Intent intent = new Intent(SpotsOrderActivity.this, Main2Activity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(intent);
//                break;
//        }
//    }

    @Override
    public void click(View v) {
        int curPosition;
        int mCurPosition;
        switch (v.getId()) {
            //整个item点击事件的处理逻辑
            case R.id.tag_item_click:
//                mCurPosition = (int) v.getTag(R.id.tag_item_click);
//                currentPosition = mCurPosition;
//                adapter.refresh(currentPosition);
//
//                Log.e("mmm", "日了狗");
//
//                Intent intent = new Intent(SpotsOrderActivity.this, RoutingActivity.class);
//                if (mScenerySelectedList.size() > 1) {
//                    Scenery start;
//                    Scenery end;
//                    if (currentPosition < mScenerySelectedList.size() - 1) {
//                        start = mScenerySelectedList.get(currentPosition);
//                        end = mScenerySelectedList.get(currentPosition + 1);
//                    } else {
//                        start = mScenerySelectedList.get(currentPosition - 1);
//                        end = mScenerySelectedList.get(currentPosition);
//                    }
//                    intent.putExtra("start", start);
//                    intent.putExtra("end", end);
//                    startActivity(intent);
//                } else {
//                    Toast.makeText(SpotsOrderActivity.this, "请至少选择两个景点才可生成路线", Toast.LENGTH_LONG).show();
//                }

                break;

            //向上图标按键点击事件的处理逻辑
            case R.id.list_item_up:
                curPosition = (int) v.getTag();
                if (curPosition != 0) {
                    Scenery upFirst = mScenerySelectedList.get(curPosition);
                    Scenery upSecond = mScenerySelectedList.get(curPosition - 1);
                    mScenerySelectedList.remove(curPosition);
                    mScenerySelectedList.remove(curPosition - 1);
                    mScenerySelectedList.add(curPosition - 1, upFirst);
                    mScenerySelectedList.add(curPosition, upSecond);
                    currentPosition = curPosition - 1;
                    adapter.refresh(currentPosition);
                }
                break;

            case R.id.spot_order_name:
                mCurPosition = (int) v.getTag();
                currentPosition = mCurPosition;
                adapter.refresh(currentPosition);

                Log.e("我是猪", String.valueOf(currentPosition));

                Intent intent2 = new Intent(SpotsOrderActivity.this, RoutingActivity.class);
                if (mScenerySelectedList.size() > 1) {
                    Scenery start;
                    Scenery end;
                    if (currentPosition < mScenerySelectedList.size() - 1) {
                        start = mScenerySelectedList.get(currentPosition);
                        end = mScenerySelectedList.get(currentPosition + 1);
                    } else {
                        start = mScenerySelectedList.get(currentPosition - 1);
                        end = mScenerySelectedList.get(currentPosition);
                    }
                    intent2.putExtra("start", start);
                    intent2.putExtra("end", end);
                    startActivity(intent2);
                } else {
                    Toast.makeText(SpotsOrderActivity.this, "请至少选择两个景点才可生成路线", Toast.LENGTH_LONG).show();
                }
                break;

            default:
                break;
        }

    }
}
