package com.gis.zn.funmagicity.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.gis.zn.funmagicity.MainActivity;
import com.gis.zn.funmagicity.R;
import com.gis.zn.funmagicity.entity.MyUser;

import butterknife.ButterKnife;
import butterknife.Bind;
import butterknife.OnClick;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;

/**
 * Created by zhaoning on 2018/2/18.
 */

public class LoginActivity extends BaseActivity {

    @Bind(R.id.btn_login)
    Button btn_login;
    @Bind(R.id.btn_register)
    Button btn_register;
    @Bind(R.id.login_username)
    EditText et_login_username;
    @Bind(R.id.register_password)
    EditText et_login_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
        initView();
    }

    @OnClick(R.id.btn_login)
    void login(View view){
        login();
    }

    @OnClick(R.id.btn_register)
    void register(View view){
        Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
        startActivity(intent);
        finish();
    }

    private void login(){
        String account = et_login_username.getText().toString();
        String password = et_login_password.getText().toString();

        if (TextUtils.isEmpty(account)) {
            showToast("账号不能为空");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            showToast("密码不能为空");
            return;
        }
        final ProgressDialog progress = new ProgressDialog(LoginActivity.this);
        progress.setMessage("正在登录中...");
        progress.setCanceledOnTouchOutside(false);
        progress.show();
        //V3.3.9提供的新的登录方式，可传用户名/邮箱/手机号码
        BmobUser.loginByAccount( account, password, new LogInListener<MyUser>() {

            @Override
            public void done(MyUser myUser, BmobException ex) {
                // TODO Auto-generated method stub
                progress.dismiss();
                if(ex == null){
                    toast("登录成功---用户名："+ myUser.getUsername());
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    intent.putExtra("from", "login");
                    startActivity(intent);
                    finish();
                }else{
                    toast("登录失败：code="+ex.getErrorCode()+"，错误描述："+ex.getLocalizedMessage());
                }
            }
        });
    }

    private void initView(){
        MyUser userInfo = BmobUser.getCurrentUser(MyUser.class);
        if(userInfo != null){
            // 允许用户使用应用
            et_login_username.setText(userInfo.getUsername());
        }else{
            //缓存用户对象为空时， 可打开用户注册界面…
        }
    }
}
