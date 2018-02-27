package com.gis.zn.funmagicity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gis.zn.funmagicity.entity.Label1Mapping;
import com.gis.zn.funmagicity.entity.MyUser;
import com.gis.zn.funmagicity.entity.Scenery;
import com.gis.zn.funmagicity.ui.BaseActivity;
import com.gis.zn.funmagicity.ui.LoginActivity;
import com.gis.zn.funmagicity.ui.TestActivity;
import com.gis.zn.funmagicity.ui.UserInfoActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.label1_friends)
    CheckBox label1_friends;
    @Bind(R.id.label1_parent_child)
    CheckBox label1_parent_child;
    @Bind(R.id.label1_lovers)
    CheckBox label1_lovers;
    @Bind(R.id.label1_colleague)
    CheckBox label1_colleague;
    @Bind(R.id.label1_random)
    CheckBox label1_random;
    @Bind(R.id.user_info1)
    ImageView user_info1;

    @Bind(R.id.btn_next_step)
    Button btn_next_step;

    @Bind(R.id.label2_1)
    CheckBox label2_1;
    @Bind(R.id.label2_2)
    CheckBox label2_2;
    @Bind(R.id.label2_3)
    CheckBox label2_3;
    @Bind(R.id.label2_4)
    CheckBox label2_4;
    @Bind(R.id.label2_5)
    CheckBox label2_5;
    @Bind(R.id.label2_6)
    CheckBox label2_6;

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

    private int label1_friends_id = 1;
    private int label1_parent_child_id = 2;
    private int label1_lovers_id = 3;
    private int label1_colleague_id = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        judgeLogined();
        initView();

    }

    private void initView() {
        btn_next_step.setOnClickListener(this);
        user_info1.setOnClickListener(this);
        hideSubLabel1();
    }

    private void judgeLogined() {
        MyUser userInfo = BmobUser.getCurrentUser(MyUser.class);
        if (userInfo != null) {
            // 允许用户使用应用
        } else {
            //缓存用户对象为空时， 可打开用户注册界面…
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_next_step:
                if (is_permitted()) {
                    searchByLabel1();
                }
                break;
            case R.id.user_info1:
                startActivity(new Intent(MainActivity.this, UserInfoActivity.class));
        }
    }

    private boolean is_permitted() {
        if (!label1_friends.isChecked() && !label1_parent_child.isChecked() && !label1_lovers.isChecked()
                && !label1_colleague.isChecked() && !label1_random.isChecked()) {
            toast("出行小伙伴请至少选择一项 ^ ^");
            return false;
        }
        if (!label2_1.isChecked() && !label2_2.isChecked() && !label2_3.isChecked() &&
                !label2_4.isChecked() && !label2_5.isChecked() && !label2_6.isChecked()) {
            toast("旅行风格请至少选择一项 ^ ^");
            return false;
        }
        if(label2_1.isChecked()&&!sub_label1_1.isChecked()&&!sub_label1_2.isChecked()&&
                !sub_label1_3.isChecked()&&!sub_label1_4.isChecked()&&!sub_label1_5.isChecked()&&!sub_label1_6.isChecked())
        {
            toast("特色美食请至少选择一项 ^ ^");
            return false;
        }
        if(label2_2.isChecked()&&!sub_label2_1.isChecked()&&!sub_label2_2.isChecked()&&
                !sub_label2_3.isChecked()&&!sub_label2_4.isChecked())
        {
            toast("风景摄影请至少选择一项 ^ ^");
            return false;
        }
        return true;
    }


    private void searchByLabel1() {

        BmobQuery<Label1Mapping> query1 = new BmobQuery<Label1Mapping>();
        query1.addWhereEqualTo("labelId", label1_friends_id);
        BmobQuery<Label1Mapping> query2 = new BmobQuery<Label1Mapping>();
        query2.addWhereEqualTo("labelId", label1_parent_child_id);
        BmobQuery<Label1Mapping> query3 = new BmobQuery<Label1Mapping>();
        query3.addWhereEqualTo("labelId", label1_lovers_id);
        BmobQuery<Label1Mapping> query4 = new BmobQuery<Label1Mapping>();
        query4.addWhereEqualTo("labelId", label1_colleague_id);

        List<BmobQuery<Label1Mapping>> queries = new ArrayList<BmobQuery<Label1Mapping>>();
        if (label1_friends.isChecked())
            queries.add(query1);
        if (label1_parent_child.isChecked())
            queries.add(query2);
        if (label1_lovers.isChecked())
            queries.add(query3);
        if (label1_colleague.isChecked())
            queries.add(query4);
        if (label1_random.isChecked()) {
            queries.add(query1);
            queries.add(query2);
            queries.add(query3);
            queries.add(query4);
        }

        BmobQuery<Label1Mapping> mainQuery = new BmobQuery<Label1Mapping>();
        mainQuery.or(queries);
//返回50条数据，如果不加上这条语句，默认返回10条数据
        mainQuery.setLimit(50);
//执行查询方法
        mainQuery.findObjects(new FindListener<Label1Mapping>() {
            @Override
            public void done(List<Label1Mapping> object, BmobException e) {
                if (e == null) {
                    toast("查询成功：共" + object.size() + "条数据。");
                    sceneryResult(object);
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
        toast("查询成功：共" + hashMap1.size() + "条数据。");
    }

    private void hideSubLabel1(){
        sub_label1.setVisibility(View.INVISIBLE);
        sub_label1_1.setVisibility(View.INVISIBLE);
        sub_label1_2.setVisibility(View.INVISIBLE);
        sub_label1_3.setVisibility(View.INVISIBLE);
        sub_label1_4.setVisibility(View.INVISIBLE);
        sub_label1_5.setVisibility(View.INVISIBLE);
        sub_label1_6.setVisibility(View.INVISIBLE);
    }
}
