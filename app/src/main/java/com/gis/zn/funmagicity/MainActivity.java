package com.gis.zn.funmagicity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.gis.zn.funmagicity.entity.MyUser;
import com.gis.zn.funmagicity.ui.BaseActivity;
import com.gis.zn.funmagicity.ui.LoginActivity;
import com.gis.zn.funmagicity.ui.RegisterActivity;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class MainActivity extends BaseActivity {

    private Button test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);

        initView();

//        MyUser p2 = new MyUser();
//        p2.setUsername("zz");
//        p2.setPassword("zz");
//        p2.setPwd("zz");
//        p2.signUp(new SaveListener<String>() {
//            @Override
//            public void done(String objectId,BmobException e) {
//                if(e==null){
//                    Toast.makeText(getApplicationContext(),"添加数据成功，返回objectId为："+objectId,Toast.LENGTH_LONG);
//                }else{
//                    Toast.makeText(getApplicationContext(),"创建数据失败：" + e.getMessage(),Toast.LENGTH_LONG);
//                }
//            }
//        });

//        BmobUser bu = new BmobUser();
//        bu.setUsername("zn");
//        bu.setPassword("zz");
//        bu.setEmail("sendi@163.com");
////注意：不能用save方法进行注册
//        bu.signUp(new SaveListener<MyUser>() {
//            @Override
//            public void done(MyUser s, BmobException e) {
//                if(e==null){
//                    toast("注册成功:" +s.toString());
//                }else{
//                    toast("注册失败:" +e);
//                }
//            }
//        });

        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"test：",Toast.LENGTH_LONG).show();
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));

            }
        });
    }

    private void initView(){
        test=(Button)findViewById(R.id.btn_test);
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
}
