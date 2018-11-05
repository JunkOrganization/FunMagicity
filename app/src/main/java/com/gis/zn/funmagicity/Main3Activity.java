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
import android.widget.TextView;

import com.gis.zn.funmagicity.entity.Label1Mapping;
import com.gis.zn.funmagicity.entity.Picture;
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

public class Main3Activity extends BaseActivity implements View.OnClickListener {

    private ArrayList<Integer> list;
    private ArrayList<Integer> listop = new ArrayList<>();
    private List<Scenery> mSceneryList = new ArrayList<>();
    private List<Picture> mPictures = new ArrayList<>();


    @Bind(R.id.listview_select)
    ListView listview_select;

    @Bind(R.id.fab_selected)
    FloatingActionButton fab_selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_spots);

        ButterKnife.bind(this);
        initView();

        searchAllPics();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.user_info_select_spots:
                BmobUser currentUser = BmobUser.getCurrentUser();
                if (currentUser == null) {
                    startActivity(new Intent(Main3Activity.this, LoginActivity.class));
                    break;
                } else {
                    startActivity(new Intent(Main3Activity.this, UserInfoActivity.class));
                    break;
                }
            case R.id.back_select_spots:
                Intent intent = new Intent(Main3Activity.this, Main2Activity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
            case R.id.fab_selected:
                Intent intent2 = new Intent(Main3Activity.this, SpotsOrderActivity.class);
                intent2.putExtra("SpotList", (Serializable) mSceneryList);
                startActivity(intent2);
                break;
        }
    }

    private void initView() {
        fab_selected.setOnClickListener(this);
    }

    private void searchAllPics() {

        BmobQuery<Picture> mainQuery1 = new BmobQuery<Picture>();
        mainQuery1.addWhereEqualTo("status", 0);

//返回50条数据，如果不加上这条语句，默认返回10条数据
        mainQuery1.setLimit(20);
//执行查询方法
        mainQuery1.findObjects(new FindListener<Picture>() {
            @Override
            public void done(final List<Picture> object, BmobException e) {
                if (e == null) {
                    showLog("label1查询成功：共" + object.size() + "条数据。");
                    for (Picture picture : object) {
                        mPictures.add(picture);
                        showLog("sceneryResultFinal:" + mSceneryList.size());
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            List<Picture>  mPics = new ArrayList<>();
                            mPics = (List<Picture>)object;
                            SceneryAdapter sceneryAdapter = new SceneryAdapter(Main3Activity.this, R.layout.item_scenery, mPics);
                            listview_select.setAdapter(sceneryAdapter);
                        }
                    });
                } else {
                    Log.i("bmob", "失败：" + e.getMessage() + "," + e.getErrorCode());
                }
            }
        });
    }


}
