package com.gis.zn.funmagicity.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.gis.zn.funmagicity.R;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

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

//        test.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(MainActivity.this,"test：",Toast.LENGTH_LONG).show();
//                startActivity(new Intent(MainActivity.this, TestActivity.class));
//
//            }
//        });
    }
}
