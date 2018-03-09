package com.gis.zn.funmagicity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.gis.zn.funmagicity.entity.Label1Mapping;
import com.gis.zn.funmagicity.entity.Scenery;
import com.gis.zn.funmagicity.entity.SceneryAdapter;
import com.gis.zn.funmagicity.ui.BaseActivity;
import com.gis.zn.funmagicity.ui.LoginActivity;
import com.gis.zn.funmagicity.ui.SceneryActivity;
import com.gis.zn.funmagicity.ui.UserInfoActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class SelectSpotsActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.user_info_select_spots)
    ImageView user_info;
    @Bind(R.id.back_select_spots)
    ImageView back_select_spots;

    private ArrayList<Integer> list;
    private ArrayList<Integer> listop = new ArrayList<>();
    private List<Scenery> mSceneryList = new ArrayList<>();


    @Bind(R.id.listview_select)
    ListView listview_select;

    @Bind(R.id.fab_selected)
    FloatingActionButton fab_selected;

    private boolean[] label1List = new boolean[10];
    private boolean[] label2List = new boolean[10];
    private boolean[] sub1LabelList;//null
    private boolean[] sub2LabelList;

    private ArrayList<Integer> label1SpotsIdList = new ArrayList<>();
    private ArrayList<Integer> label2SpotsIdList = new ArrayList<>();
    private ArrayList<Integer> spotsIdList = new ArrayList<>();

    private int val_label1_1 = 1;
    private int val_label1_2 = 2;
    private int val_label1_3 = 3;
    private int val_label1_4 = 4;
    private int val_label1_5 = 4;

    private int val_label2_1 = 5;
    private int val_label2_2 = 6;
    private int val_label2_3 = 7;
    private int val_label2_4 = 8;
    private int val_label2_5 = 9;

    private int val_sub1_label1 = 14;
    private int val_sub1_label2 = 15;
    private int val_sub1_label3 = 16;
    private int val_sub1_label4 = 17;
    private int val_sub1_label5 = 18;

    private int val_sub2_label1 = 10;
    private int val_sub2_label2 = 12;
    private int val_sub2_label3 = 13;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    List<Label1Mapping> label1Mappings = (List<Label1Mapping>) msg.obj;
                    sceneryResult(label1Mappings, label1SpotsIdList);
                    showLog("SelectSpotsActivity searchByLabel1 " + label1SpotsIdList.size());
                    if (label2SpotsIdList.size() != 0) {
                        random_and_select();
                        updateList();
                    }
                    break;
                case 2:
                    List<Label1Mapping> label2Mappings = (List<Label1Mapping>) msg.obj;
                    sceneryResult(label2Mappings, label2SpotsIdList);
                    showLog("SelectSpotsActivity searchByLabel2 " + label2SpotsIdList.size());
                    if (label1SpotsIdList.size() != 0) {
                        random_and_select();
                        updateList();
                    }
                    break;
                default:
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_spots);

        ButterKnife.bind(this);
        initView();

        label1List = (boolean[]) getIntent().getExtras().getBooleanArray("label1_list");
        label2List = (boolean[]) getIntent().getExtras().getBooleanArray("label2_list");
        if (label2List[1])
            sub1LabelList = (boolean[]) getIntent().getExtras().getBooleanArray("sub1_label_list");
        if (label2List[2])
            sub2LabelList = (boolean[]) getIntent().getExtras().getBooleanArray("sub2_label_list");
        showLog("SelectSpotsActivity label1List: ");
        for (boolean b : label1List)
            showLog(b + " ");
        showLog("label2List: ");
        for (boolean b : label2List)
            showLog(b + " ");
        showLog("sub1_label_list: ");
//        for (boolean b : sub1LabelList)
        showLog(sub1LabelList + " ");
        showLog("sub2_label_list: ");
//        for (boolean b : sub2LabelList)
        showLog(sub1LabelList + " ");

        select_spots();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.user_info_select_spots:
                BmobUser currentUser = BmobUser.getCurrentUser();
                if (currentUser == null) {
                    startActivity(new Intent(SelectSpotsActivity.this, LoginActivity.class));
                    break;
                } else {
                    startActivity(new Intent(SelectSpotsActivity.this, UserInfoActivity.class));
                    break;
                }
            case R.id.back_select_spots:
                Intent intent = new Intent(SelectSpotsActivity.this, Main2Activity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
            case R.id.fab_selected:
                Intent intent2 = new Intent(SelectSpotsActivity.this, SpotsOrderActivity.class);
                intent2.putExtra("SpotList", (Serializable) mSceneryList);
                startActivity(intent2);
                break;
        }
    }

    private void initView() {
        user_info.setOnClickListener(this);
        back_select_spots.setOnClickListener(this);
        fab_selected.setOnClickListener(this);
    }

    private void select_spots() {
        searchByLabel1();
        searchByLabel2();
    }

    private void searchByLabel1() {

        //label1
        BmobQuery<Label1Mapping> query1 = new BmobQuery<Label1Mapping>();
        query1.addWhereEqualTo("labelId", val_label1_1);
        BmobQuery<Label1Mapping> query2 = new BmobQuery<Label1Mapping>();
        query2.addWhereEqualTo("labelId", val_label1_2);
        BmobQuery<Label1Mapping> query3 = new BmobQuery<Label1Mapping>();
        query3.addWhereEqualTo("labelId", val_label1_3);
        BmobQuery<Label1Mapping> query4 = new BmobQuery<Label1Mapping>();
        query4.addWhereEqualTo("labelId", val_label1_4);

        List<BmobQuery<Label1Mapping>> queries1 = new ArrayList<BmobQuery<Label1Mapping>>();

        if (label1List[1])
            queries1.add(query1);
        if (label1List[2])
            queries1.add(query2);
        if (label1List[3])
            queries1.add(query3);
        if (label1List[4] || label1List[5])
            queries1.add(query4);
//        if (label1List[6]) {
//            queries1.add(query1);
//            queries1.add(query2);
//            queries1.add(query3);
//            queries1.add(query4);
//        }
        BmobQuery<Label1Mapping> mainQuery1 = new BmobQuery<Label1Mapping>();
        mainQuery1.or(queries1);

//返回50条数据，如果不加上这条语句，默认返回10条数据
        mainQuery1.setLimit(200);
//执行查询方法
        mainQuery1.findObjects(new FindListener<Label1Mapping>() {
            @Override
            public void done(final List<Label1Mapping> object, BmobException e) {
                if (e == null) {
                    showLog("label1查询成功：共" + object.size() + "条数据。");

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Message message = new Message();
                            message.obj = object;
                            message.what = 1;
                            mHandler.sendMessage(message);
                        }
                    }).start();
                } else {
                    Log.i("bmob", "失败：" + e.getMessage() + "," + e.getErrorCode());
                }
            }
        });
    }

    private void searchByLabel2() {

        //label2
        BmobQuery<Label1Mapping> query1 = new BmobQuery<Label1Mapping>();
        query1.addWhereEqualTo("labelId", val_label2_1);
        BmobQuery<Label1Mapping> query2 = new BmobQuery<Label1Mapping>();
        query2.addWhereEqualTo("labelId", val_label2_2);
        BmobQuery<Label1Mapping> query3 = new BmobQuery<Label1Mapping>();
        query3.addWhereEqualTo("labelId", val_label2_3);
        BmobQuery<Label1Mapping> query4 = new BmobQuery<Label1Mapping>();
        query4.addWhereEqualTo("labelId", val_label2_4);
        BmobQuery<Label1Mapping> query5 = new BmobQuery<Label1Mapping>();
        query5.addWhereEqualTo("labelId", val_label2_5);

        BmobQuery<Label1Mapping> query6 = new BmobQuery<Label1Mapping>();
        query6.addWhereEqualTo("labelId", val_sub1_label1);
        BmobQuery<Label1Mapping> query7 = new BmobQuery<Label1Mapping>();
        query7.addWhereEqualTo("labelId", val_sub1_label2);
        BmobQuery<Label1Mapping> query8 = new BmobQuery<Label1Mapping>();
        query8.addWhereEqualTo("labelId", val_sub1_label3);
        BmobQuery<Label1Mapping> query9 = new BmobQuery<Label1Mapping>();
        query9.addWhereEqualTo("labelId", val_sub1_label4);
        BmobQuery<Label1Mapping> query10 = new BmobQuery<Label1Mapping>();
        query10.addWhereEqualTo("labelId", val_sub1_label5);

        BmobQuery<Label1Mapping> query11 = new BmobQuery<Label1Mapping>();
        query11.addWhereEqualTo("labelId", val_sub2_label1);
        BmobQuery<Label1Mapping> query12 = new BmobQuery<Label1Mapping>();
        query12.addWhereEqualTo("labelId", val_sub1_label2);
        BmobQuery<Label1Mapping> query13 = new BmobQuery<Label1Mapping>();
        query13.addWhereEqualTo("labelId", val_sub1_label3);
        List<BmobQuery<Label1Mapping>> queries1 = new ArrayList<BmobQuery<Label1Mapping>>();

        if (label2List[1])
            queries1.add(query1);
        if (label2List[2])
            queries1.add(query2);
        if (label2List[3])
            queries1.add(query3);
        if (label2List[4])
            queries1.add(query4);
        if (label2List[5])
            queries1.add(query5);

        if (null != sub1LabelList) {
            if (sub1LabelList[1])
                queries1.add(query6);
            if (sub1LabelList[2])
                queries1.add(query7);
            if (sub1LabelList[3])
                queries1.add(query8);
            if (sub1LabelList[4])
                queries1.add(query9);
            if (sub1LabelList[5])
                queries1.add(query10);
        }

        if (null != sub2LabelList) {
            if (sub2LabelList[1])
                queries1.add(query11);
            if (sub2LabelList[2])
                queries1.add(query12);
            if (sub2LabelList[3])
                queries1.add(query13);
        }

        BmobQuery<Label1Mapping> mainQuery = new BmobQuery<Label1Mapping>();
        mainQuery.or(queries1);

//返回50条数据，如果不加上这条语句，默认返回10条数据
        mainQuery.setLimit(200);
//执行查询方法
        mainQuery.findObjects(new FindListener<Label1Mapping>() {
            @Override
            public void done(final List<Label1Mapping> object, BmobException e) {
                if (e == null) {
                    showLog("label2查询成功：共" + object.size() + "条数据。");
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Message message = new Message();
                            message.obj = object;
                            message.what = 2;
                            mHandler.sendMessage(message);
                        }
                    }).start();

//                    if(!label2_1.isChecked()&&!label2_2.isChecked()&&!label2_6.isChecked()){
//                        Intent intent=new Intent(MainActivity.this, SceneryActivity.class);
//                        intent.putExtra("scenery_res", (Serializable) object);
//                        startActivity(intent);
//                    }
//                    else {
//                        Intent intent=new Intent(MainActivity.this, SecondActivity.class);
//                        intent.putExtra()
//                        intent.putExtra("scenery_res", (Serializable) object);
//                        startActivity(intent);
//                    }
                } else {
                    Log.i("bmob", "失败：" + e.getMessage() + "," + e.getErrorCode());
                }
            }
        });
    }

    private void sceneryResult(List<Label1Mapping> labelMappingList, ArrayList<Integer> list) {
        HashMap hashMap1 = new HashMap<Integer, Label1Mapping>();
        for (Label1Mapping label1Mapping : labelMappingList) {
            hashMap1.put(label1Mapping.getSceneryId(), label1Mapping);
        }
        for (Object obj : hashMap1.values()) {
            Label1Mapping labelMapping = (Label1Mapping) obj;
            list.add(labelMapping.getSceneryId());
        }
        showLog("hashmap success " + hashMap1.size());
    }

    private void random_and_select() {
        label1SpotsIdList.retainAll(label2SpotsIdList);
        Collections.shuffle(label1SpotsIdList);
        showLog("random_and_select" + label1SpotsIdList.size());
        for (Integer i : label1SpotsIdList) {
            showLog(i.toString());
        }
        int limit = label1SpotsIdList.size() > 10 ? 10 : label1SpotsIdList.size();
        for (int i = 0; i < limit; i++) {
            spotsIdList.add(label1SpotsIdList.get(i));
        }
    }

    private void updateList() {
        for (final Integer i : spotsIdList) {
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
                                        SceneryAdapter sceneryAdapter = new SceneryAdapter(SelectSpotsActivity.this, R.layout.item_scenery, mSceneryList);
                                        listview_select.setAdapter(sceneryAdapter);
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
        showLog("sceneryResultFinal:" + mSceneryList.size());
    }

}
