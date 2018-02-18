package com.gis.zn.funmagicity.application;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;

import cn.bmob.v3.Bmob;

/**
 * Created by zhaoning on 2018/2/18.
 */

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
        SDKInitializer.initialize(getApplicationContext());//————百度地图初始化

        //提供以下两种方式进行初始化操作：

        //第一：默认初始化
        Bmob.initialize(this, "fd84f053ddd55a4a081bf768e85e12cc","bmob");
        // 注:自v3.5.2开始，数据sdk内部缝合了统计sdk，开发者无需额外集成，传渠道参数即可，不传默认没开启数据统计功能
        //Bmob.initialize(this, "Your Application ID","bmob");

        //第二：自v3.4.7版本开始,设置BmobConfig,允许设置请求超时时间、文件分片上传时每片的大小、文件的过期时间(单位为秒)，
        //BmobConfig config =new BmobConfig.Builder(this)
        ////设置appkey
        //.setApplicationId("Your Application ID")
        ////请求超时时间（单位为秒）：默认15s
        //.setConnectTimeout(30)
        ////文件分片上传时每片的大小（单位字节），默认512*1024
        //.setUploadBlockSize(1024*1024)
        ////文件的过期时间(单位为秒)：默认1800s
        //.setFileExpiration(2500)
        //.build();
        //Bmob.initialize(config);
    }
}
