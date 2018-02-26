package com.gis.zn.funmagicity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.gis.zn.funmagicity.entity.MyUser;
import com.gis.zn.funmagicity.ui.BaseActivity;
import com.gis.zn.funmagicity.ui.LoginActivity;
import com.gis.zn.funmagicity.ui.TestActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobUser;

public class MainActivity extends BaseActivity implements View.OnClickListener{

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

    @Bind(R.id.btn_next_step)
    Button btn_next_step;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        judgeLogined();
        initView();

        btn_next_step.setOnClickListener(this);

    }

    private void initView(){
    }

    private void judgeLogined(){
        MyUser userInfo = BmobUser.getCurrentUser(MyUser.class);
        if(userInfo != null){
            // 允许用户使用应用
        }else{
            //缓存用户对象为空时， 可打开用户注册界面…
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_next_step:
                Toast.makeText(MainActivity.this, "button.click", Toast.LENGTH_SHORT).show();
                break;
        }
    }


}
