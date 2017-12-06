package com.lch.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

/**
 * Created by bbt-team on 2017/12/6.
 */

public final class Nav {
    private static Context sContext;

    public static void init(Context context) {
        sContext = context.getApplicationContext();
    }

    public static void openActivity(Class<? extends Activity> clazz) {
        Intent it = new Intent(sContext, clazz);
        it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        sContext.startActivity(it);
    }

    public static void openActivity(Class<? extends Activity> clazz, Activity context) {
        Intent it = new Intent(context, clazz);
        context.startActivity(it);
    }

    public static void openActivity(Intent it) {
        it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        sContext.startActivity(it);
    }

    public static void openActivity(Intent it, Activity context) {
        context.startActivity(it);
    }
}
