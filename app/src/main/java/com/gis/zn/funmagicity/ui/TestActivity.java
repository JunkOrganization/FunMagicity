package com.gis.zn.funmagicity.ui;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.gis.zn.funmagicity.R;
import com.gis.zn.funmagicity.SelectSpotsActivity;
import com.gis.zn.funmagicity.SpotInfoActivity;
import com.gis.zn.funmagicity.SpotsOrderActivity;
import com.gis.zn.funmagicity.entity.Scenery;
import com.gis.zn.funmagicity.entity.SceneryAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class TestActivity extends BaseActivity {

    @Bind(R.id.listview_test)
    ListView listview_test;
    @Bind(R.id.fab_test)
    FloatingActionButton fab_test;

    private ArrayList<Integer> mIntegers = new ArrayList<>();
    private List<Scenery> mSceneryList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);
        initView();

        for(int i = 1;i<10;i++){
            mIntegers.add(i);
        }
        initListView();


        listview_test.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                showLog("setOnItemClickListener");
                Scenery scenery = mSceneryList.get(position);
                Intent intent=new Intent(TestActivity.this, SpotInfoActivity.class);
                intent.putExtra("scenery",scenery);
                startActivity(intent);
            }
        });
    }

    private void initView(){
        fab_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(TestActivity.this, SpotsOrderActivity.class);
                intent2.putExtra("SpotList",(Serializable)mSceneryList);
                startActivity(intent2);
            }
        });
    }

    private void initListView(){
        for (final Integer i : mIntegers) {
            BmobQuery<Scenery> query = new BmobQuery<Scenery>();
            int a = i.intValue();
            showLog(String.valueOf(a));
            query.addWhereEqualTo("id", a);
//执行查询方法
            query.findObjects(new FindListener<Scenery>() {
                @Override
                public void done(List<Scenery> object, BmobException e) {
                    if (e == null) {
                        for (Scenery scenery : object) {
                            mSceneryList.add(scenery);
                            showLog("sceneryResultFinal:" + mSceneryList.size());
                            {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
//                                        SceneryAdapter sceneryAdapter = new SceneryAdapter(TestActivity.this, R.layout.item_scenery, mSceneryList);
//                                        listview_test.setAdapter(sceneryAdapter);
                                    }
                                });

                            }
                        }
                    } else {
                        Log.i("bmob", "失败：" + e.getMessage() + "," + e.getErrorCode());
                    }
                }
            });
        }
    }
}
