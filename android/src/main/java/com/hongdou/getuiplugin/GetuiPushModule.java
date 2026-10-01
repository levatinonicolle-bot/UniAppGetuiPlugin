package com.hongdou.getuiplugin;

import android.os.Handler;
import android.os.Looper;

import com.alibaba.fastjson.JSONObject;

import io.dcloud.feature.uniapp.annotation.UniJSMethod;
import io.dcloud.feature.uniapp.bridge.UniJSCallback;
import io.dcloud.feature.uniapp.common.UniModule;

/** UniApp module used by library/getuiPush.js. */
public class GetuiPushModule extends UniModule {
    private static final Handler MAIN = new Handler(Looper.getMainLooper());
    private static volatile String clientId = "";
    private static volatile UniJSCallback receiveCallback;
    private static volatile UniJSCallback clickCallback;

    @UniJSMethod(uiThread = false)
    public void getClientId(JSONObject options, final UniJSCallback callback) {
        final String value = clientId;
        MAIN.post(new Runnable() {
            @Override
            public void run() {
                if (callback != null) callback.invoke(value);
            }
        });
    }

    @UniJSMethod(uiThread = false)
    public void onReceive(JSONObject options, UniJSCallback callback) {
        receiveCallback = callback;
    }

    @UniJSMethod(uiThread = false)
    public void onClick(JSONObject options, UniJSCallback callback) {
        clickCallback = callback;
    }

    static void setClientId(String value) {
        clientId = value == null ? "" : value;
    }

    static void dispatchReceive(final JSONObject data) {
        final UniJSCallback callback = receiveCallback;
        if (callback != null) {
            MAIN.post(new Runnable() {
                @Override
                public void run() {
                    callback.invokeAndKeepAlive(data);
                }
            });
        }
    }

    static void dispatchClick(final JSONObject data) {
        final UniJSCallback callback = clickCallback;
        if (callback != null) {
            MAIN.post(new Runnable() {
                @Override
                public void run() {
                    callback.invokeAndKeepAlive(data);
                }
            });
        }
    }
}
