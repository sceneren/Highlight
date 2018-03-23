package com.zhy.highlight;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;
import com.yline.application.SDKConfig;
import com.yline.application.SDKManager;

/**
 * <pre>
 * 内存溢出检测应用
 * Created by isanwenyu@163.com on 2016/12/9.
 * </pre>
 */
public class LeakCanaryApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        SDKManager.init(this, new SDKConfig()); // 提供一些帮助类
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
    }
}
