package com.hongdou.getuiplugin;

import android.app.Application;

import com.igexin.sdk.PushManager;

import io.dcloud.feature.uniapp.UniAppHookProxy;

/** Initializes Getui in the UniApp application process. */
public class GetuiPushHook implements UniAppHookProxy {
    @Override
    public void onCreate(Application application) {
        PushManager.getInstance().preInit(application);
        PushManager.getInstance().initialize(application);
    }

    @Override
    public void onSubProcessCreate(Application application) {
        PushManager.getInstance().preInit(application);
        PushManager.getInstance().initialize(application);
    }
}
