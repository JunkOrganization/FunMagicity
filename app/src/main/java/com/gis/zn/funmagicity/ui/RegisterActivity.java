package com.gis.zn.funmagicity.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.gis.zn.funmagicity.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zhaoning on 2018/2/18.
 */

public class RegisterActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ButterKnife.inject(this);
    }

    @OnClick(R.id.btn_login)
    void login(View view){
        finish();
    }

    @OnClick(R.id.btn_register)
    void register(View view){

    }

}
