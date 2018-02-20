package com.gis.zn.funmagicity.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.gis.zn.funmagicity.MainActivity;
import com.gis.zn.funmagicity.R;
import com.gis.zn.funmagicity.entity.MyUser;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by zhaoning on 2018/2/18.
 */

public class RegisterActivity extends BaseActivity {

    @InjectView(R.id.register_account)
    EditText et_account;
    @InjectView(R.id.register_password)
    EditText et_password;
    @InjectView(R.id.register_password2)
    EditText et_pwd_again;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ButterKnife.inject(this);
    }

    @OnClick(R.id.btn_login)
    void login(View view){
        Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.btn_register)
    void register(View view){
        registerUser();
    }

    private void registerUser(){
        String account = et_account.getText().toString();
        String password = et_password.getText().toString();
        String pwd = et_pwd_again.getText().toString();
        if (TextUtils.isEmpty(account)) {
            showToast("用户名不能为空");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            showToast("密码不能为空");
            return;
        }
        if (!password.equals(pwd)) {
            showToast("两次密码不一样");
            return;
        }
        final ProgressDialog progress = new ProgressDialog(RegisterActivity.this);
        progress.setMessage("正在注册中...");
        progress.setCanceledOnTouchOutside(false);
        progress.show();
        final MyUser user = new MyUser();
        user.setUsername(account);
        user.setPassword(password);
        user.signUp(new SaveListener<MyUser>() {
            @Override
            public void done(MyUser myUser,BmobException e) {
                if(e==null){
                    // TODO Auto-generated method stub
                    progress.dismiss();
                    toast("注册成功---用户名："+user.getUsername());
                    Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                    intent.putExtra("from", "login");
                    startActivity(intent);
                    finish();
                }else{
                    toast("注册失败：code="+e.getErrorCode()+"，错误描述："+e.getLocalizedMessage());
                }
            }
        });
    }
}
