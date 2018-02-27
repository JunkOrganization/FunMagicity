package com.gis.zn.funmagicity.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.gis.zn.funmagicity.MainActivity;
import com.gis.zn.funmagicity.R;
import com.gis.zn.funmagicity.entity.MyUser;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class UserInfoActivity extends BaseActivity implements View.OnClickListener{

    @Bind(R.id.btn_user_info_back)
    Button btn_user_info_back;
    @Bind(R.id.user_name)
    TextView user_name;
    @Bind(R.id.user_phone)
    TextView user_phone;
    @Bind(R.id.user_last_login)
    TextView user_last_login;
    @Bind(R.id.user_register_date)
    TextView user_register_date;
    @Bind(R.id.btn_connect)
    Button btn_connect;
    @Bind(R.id.btn_login_out)
    Button btn_login_out;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        ButterKnife.bind(this);
        initView();

    }

    private void initView(){
        BmobUser currentUser = BmobUser.getCurrentUser(); // 现在的currentUser是null了
        if(currentUser==null)
        {
            finish();
            startActivity(new Intent(UserInfoActivity.this, LoginActivity.class));
        }


        btn_login_out.setOnClickListener(this);
        btn_user_info_back.setOnClickListener(this);
        getCurrentUser();
    }

    private void getCurrentUser(){
        BmobUser currentUser = BmobUser.getCurrentUser(); // 现在的currentUser是null了
//        MyUser userInfo = BmobUser.getCurrentUser(MyUser.class);
//        String username = (String) BmobUser.getObjectByKey("username");
//MyUser中的扩展属性

//        BmobQuery<BmobUser> query = new BmobQuery<BmobUser>();
//        query.addWhereEqualTo("username", "lucky");
//        query.findObjects(new FindListener<BmobUser>() {
//            @Override
//            public void done(List<BmobUser> object, BmobException e) {
//                if(e==null){
//                    toast("查询用户成功:"+object.size());
//                }else{
//                    toast("更新用户信息失败:" + e.getMessage());
//                }
//            }
//        });

        user_name.setText(currentUser.getUsername());
        user_phone.setText(currentUser.getMobilePhoneNumber());
        user_last_login.setText(currentUser.getUpdatedAt());
        user_register_date.setText(currentUser.getCreatedAt());

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_login_out:
                login_out();
                break;
            case R.id.btn_user_info_back:
                finish();
                break;
        }
    }

    private void login_out(){
        BmobUser.logOut();  //清除缓存用户对象
        BmobUser currentUser = BmobUser.getCurrentUser(); // 现在的currentUser是null了
        finish();
        startActivity(new Intent(UserInfoActivity.this, LoginActivity.class));

    }
}
