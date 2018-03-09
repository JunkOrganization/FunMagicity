package com.gis.zn.funmagicity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.gis.zn.funmagicity.ui.BaseActivity;
import com.gis.zn.funmagicity.ui.LoginActivity;
import com.gis.zn.funmagicity.ui.UserInfoActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobUser;

public class Main2Activity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.btn_go)
    Button btn_go;
    @Bind(R.id.main_user_info)
    FloatingActionButton main_user_info;
    @Bind(R.id.main_uesename)
    TextView main_uesename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_go:
                startActivity(new Intent(Main2Activity.this, Label1Activity.class));
                break;
            case R.id.main_user_info:
                BmobUser currentUser = BmobUser.getCurrentUser();
                if(currentUser==null)
                {
                    startActivity(new Intent(Main2Activity.this, LoginActivity.class));
                    break;
                }
                else {
                    startActivity(new Intent(Main2Activity.this, UserInfoActivity.class));
                    break;
                }

        }
    }

    private void initView(){
        btn_go.setOnClickListener(this);
        main_user_info.setOnClickListener(this);
        BmobUser currentUser = BmobUser.getCurrentUser();
        main_uesename.setText(currentUser.getUsername());
    }
}
