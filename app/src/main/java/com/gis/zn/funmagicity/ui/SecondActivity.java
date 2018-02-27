package com.gis.zn.funmagicity.ui;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.gis.zn.funmagicity.MainActivity;
import com.gis.zn.funmagicity.R;
import com.gis.zn.funmagicity.entity.Label1Mapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class SecondActivity extends BaseActivity implements View.OnClickListener {

    private boolean sub_label1_value;
    private boolean sub_label2_value;
    private boolean sub_label6_value;
    private ArrayList<Integer> list;
    private ArrayList<Integer> list_label2 = new ArrayList<>();
    private ArrayList<Integer> list_res = new ArrayList<>();

    @Bind(R.id.sub_label1)
    TextView sub_label1;
    @Bind(R.id.sub_label1_1)
    CheckBox sub_label1_1;
    @Bind(R.id.sub_label1_2)
    CheckBox sub_label1_2;
    @Bind(R.id.sub_label1_3)
    CheckBox sub_label1_3;
    @Bind(R.id.sub_label1_4)
    CheckBox sub_label1_4;
    @Bind(R.id.sub_label1_5)
    CheckBox sub_label1_5;
    @Bind(R.id.sub_label1_6)
    CheckBox sub_label1_6;

    @Bind(R.id.sub_label2)
    TextView sub_label2;
    @Bind(R.id.sub_label2_1)
    CheckBox sub_label2_1;
    @Bind(R.id.sub_label2_2)
    CheckBox sub_label2_2;
    @Bind(R.id.sub_label2_3)
    CheckBox sub_label2_3;
    @Bind(R.id.sub_label2_4)
    CheckBox sub_label2_4;

    @Bind(R.id.user_info2)
    ImageView user_info2;
    @Bind(R.id.btn_submit)
    Button btn_submit;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    Intent intent = new Intent(SecondActivity.this, SceneryActivity.class);
                    intent.putIntegerArrayListExtra("scenery_res", list_res);
                    startActivity(intent);
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ButterKnife.bind(this);

        sub_label1_value = (Boolean) getIntent().getBooleanExtra("sub_label1", true);
        sub_label2_value = (Boolean) getIntent().getBooleanExtra("sub_label2", true);
        sub_label6_value = (Boolean) getIntent().getBooleanExtra("sub_label6", true);
        list = (ArrayList<Integer>) getIntent().getIntegerArrayListExtra("scenery_res");

        showLog(list.toString());
        initView();
    }


    private void initView() {
        if (!sub_label1_value && !sub_label6_value) {
            hideSubLabel1();
        }
        if (!sub_label2_value && !sub_label6_value) {
            hideSubLabel2();
        }
        btn_submit.setOnClickListener(this);
        user_info2.setOnClickListener(this);
    }

    private boolean is_permitted() {
        if (sub_label1_value && !sub_label1_1.isChecked() && !sub_label1_2.isChecked() &&
                !sub_label1_3.isChecked() && !sub_label1_4.isChecked() && !sub_label1_5.isChecked() && !sub_label1_6.isChecked()) {
            toast("特色美食请至少选择一项 ^ ^");
            return false;
        }
        if (sub_label2_value && !sub_label2_1.isChecked() && !sub_label2_2.isChecked() &&
                !sub_label2_3.isChecked() && !sub_label2_4.isChecked()) {
            toast("风景摄影请至少选择一项 ^ ^");
            return false;
        }
        return true;
    }

    private void hideSubLabel1() {
        sub_label1.setVisibility(View.INVISIBLE);
        sub_label1_1.setVisibility(View.INVISIBLE);
        sub_label1_2.setVisibility(View.INVISIBLE);
        sub_label1_3.setVisibility(View.INVISIBLE);
        sub_label1_4.setVisibility(View.INVISIBLE);
        sub_label1_5.setVisibility(View.INVISIBLE);
        sub_label1_6.setVisibility(View.INVISIBLE);
    }

    private void hideSubLabel2() {
        sub_label2.setVisibility(View.INVISIBLE);
        sub_label2_1.setVisibility(View.INVISIBLE);
        sub_label2_2.setVisibility(View.INVISIBLE);
        sub_label2_3.setVisibility(View.INVISIBLE);
        sub_label2_4.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_submit:
                if (is_permitted()) {
                    searchByLabel2();
                }
                break;
            case R.id.user_info2:
                startActivity(new Intent(SecondActivity.this, UserInfoActivity.class));
                break;
        }
    }

    private void searchByLabel2() {
        //sub label1
        BmobQuery<Label1Mapping> query1 = new BmobQuery<Label1Mapping>();
        query1.addWhereEqualTo("labelId", 14);
        BmobQuery<Label1Mapping> query2 = new BmobQuery<Label1Mapping>();
        query2.addWhereEqualTo("labelId", 15);
        BmobQuery<Label1Mapping> query3 = new BmobQuery<Label1Mapping>();
        query3.addWhereEqualTo("labelId", 16);
        BmobQuery<Label1Mapping> query4 = new BmobQuery<Label1Mapping>();
        query4.addWhereEqualTo("labelId", 17);
        BmobQuery<Label1Mapping> query5 = new BmobQuery<Label1Mapping>();
        query4.addWhereEqualTo("labelId", 18);

//        label2
        BmobQuery<Label1Mapping> query6 = new BmobQuery<Label1Mapping>();
        query2.addWhereEqualTo("labelId", 10);
        BmobQuery<Label1Mapping> query7 = new BmobQuery<Label1Mapping>();
        query3.addWhereEqualTo("labelId", 12);
        BmobQuery<Label1Mapping> query8 = new BmobQuery<Label1Mapping>();
        query4.addWhereEqualTo("labelId", 13);

        List<BmobQuery<Label1Mapping>> queries = new ArrayList<BmobQuery<Label1Mapping>>();
        if (sub_label1_1.isChecked())
            queries.add(query1);
        if (sub_label1_2.isChecked())
            queries.add(query2);
        if (sub_label1_3.isChecked())
            queries.add(query3);
        if (sub_label1_4.isChecked())
            queries.add(query4);
        if (sub_label1_5.isChecked())
            queries.add(query5);
        if (sub_label1_6.isChecked()) {
            queries.add(query1);
            queries.add(query2);
            queries.add(query3);
            queries.add(query4);
        }

        if (sub_label2_1.isChecked())
            queries.add(query6);
        if (sub_label2_2.isChecked())
            queries.add(query7);
        if (sub_label2_3.isChecked())
            queries.add(query8);
        if (sub_label2_4.isChecked()) {
            queries.add(query6);
            queries.add(query7);
            queries.add(query8);
        }


        BmobQuery<Label1Mapping> mainQuery = new BmobQuery<Label1Mapping>();
        mainQuery.or(queries);
//返回50条数据，如果不加上这条语句，默认返回10条数据
        mainQuery.setLimit(200);
//执行查询方法
        mainQuery.findObjects(new FindListener<Label1Mapping>() {
            @Override
            public void done(List<Label1Mapping> object, BmobException e) {
                if (e == null) {
//                    toast("查询成功：共" + object.size() + "条数据。");
                    sceneryResult(object);

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Message message = new Message();
                            showLog("thread3 success");
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

    private void sceneryResult(List<Label1Mapping> label1MappingList) {
        HashMap hashMap1 = new HashMap<Integer, Label1Mapping>();
        for (Label1Mapping label1Mapping : label1MappingList) {
            hashMap1.put(label1Mapping.getSceneryId(), label1Mapping);
        }
        for (Object obj : hashMap1.values()) {
            Label1Mapping label1Mapping = (Label1Mapping) obj;
            list_label2.add(label1Mapping.getSceneryId());
        }
        showLog("hashmap success");

        HashMap hashMap2 = new HashMap<Integer, Integer>();
        for (Integer i : list) {
            hashMap2.put(i, i);
        }
        for (Integer i : list_label2) {
            hashMap2.put(i, i);
        }
        for (Object obj : hashMap2.values()) {
            Integer x = (Integer) obj;
            list_res.add(x);
        }
    }
}
