package com.gis.zn.funmagicity.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.gis.zn.funmagicity.R;
import com.gis.zn.funmagicity.entity.Scenery;
import com.gis.zn.funmagicity.entity.SceneryAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class SceneryActivity extends BaseActivity {

    private ArrayList<Integer> list;
    private ArrayList<Integer> listop=new ArrayList<>();
    private List<Scenery> mSceneryList=new ArrayList<>();


    @Bind(R.id.listview)
    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scenery);
        ButterKnife.bind(this);

        list = (ArrayList<Integer>) getIntent().getIntegerArrayListExtra("scenery_res");
        for(int i=0;i<10;i++){
            listop.add(list.get(i));
        }
        showLog("sceneryResultFinal:"+mSceneryList.size());


                sceneryResultFinal();

    }


    private void sceneryResultFinal(){
        for(final Integer i:listop){
            BmobQuery<Scenery> query = new BmobQuery<Scenery>();
            int a=i.intValue();
            showLog(String.valueOf(a));
            query.addWhereEqualTo("id", a);
//执行查询方法
            query.findObjects(new FindListener<Scenery>() {
                @Override
                public void done(List<Scenery> object, BmobException e) {
                    if(e==null){
                        for (Scenery scenery : object) {
                            mSceneryList.add(scenery);
                            showLog("sceneryResultFinal:"+mSceneryList.size());
                            {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        SceneryAdapter sceneryAdapter=new SceneryAdapter(SceneryActivity.this,R.layout.item_scenery,mSceneryList);
                                        mListView.setAdapter(sceneryAdapter);
                                    }
                                });

                            }
                        }
                    }else{
                        Log.i("bmob","失败："+e.getMessage()+","+e.getErrorCode());
                    }
                }
            });
        }
        showLog("sceneryResultFinal:"+mSceneryList.size());
    }
}
