package com.lch.app;

import android.app.Application;

import com.blankj.utilcode.util.Utils;
import com.lch.netkit.NetKit;

/**
 * Created by bbt-team on 2017/12/6.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                e.printStackTrace();
            }
        });
        super.onCreate();
        Utils.init(this);
        Nav.init(this);
        NetKit.setLogEnable(true);
        NetKit.init();
    }
}
