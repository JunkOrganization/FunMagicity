package com.gis.zn.funmagicity.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.gis.zn.funmagicity.R;

/**
 * Created by zhaoning on 2018/2/18.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
    }

    private void initView(){

    }

    @Override
    public void onClick(View v) {

    }

    // 假设我现在输入用户名和密码，但是我不点击登陆，而是直接退出了
    @Override
    protected void onDestroy() {

    }
}
