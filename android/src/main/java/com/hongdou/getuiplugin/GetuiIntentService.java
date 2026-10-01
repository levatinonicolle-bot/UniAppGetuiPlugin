package com.hongdou.getuiplugin;

import android.content.Context;

import com.alibaba.fastjson.JSONObject;
import com.igexin.sdk.GTIntentService;
import com.igexin.sdk.message.GTNotificationMessage;
import com.igexin.sdk.message.GTTransmitMessage;

import java.nio.charset.Charset;

/** Converts Getui callbacks into UniApp module callbacks. */
public class GetuiIntentService extends GTIntentService {
    @Override
    public void onReceiveClientId(Context context, String cid) {
        GetuiPushModule.setClientId(cid);
    }

    @Override
    public void onReceiveMessageData(Context context, GTTransmitMessage message) {
        byte[] bytes = message.getPayload();
        String payload = bytes == null ? "" : new String(bytes, Charset.forName("UTF-8"));
        JSONObject data = new JSONObject();
        data.put("payload", payload);
        data.put("clientid", message.getClientId());
        data.put("taskid", message.getTaskId());
        GetuiPushModule.dispatchReceive(data);
    }

    @Override
    public void onNotificationMessageClicked(Context context, GTNotificationMessage message) {
        JSONObject data = new JSONObject();
        data.put("title", message.getTitle());
        data.put("content", message.getContent());
        data.put("clientid", message.getClientId());
        try {
            Object payload = message.getClass().getMethod("getPayload").invoke(message);
            if (payload != null) data.put("payload", String.valueOf(payload));
        } catch (Exception ignored) {
            // Older Getui SDK builds do not expose notification payload.
        }
        GetuiPushModule.dispatchClick(data);
    }

    @Override
    public void onNotificationMessageArrived(Context context, GTNotificationMessage message) {
        // Notification display is handled by Getui. Only click and payload
        // events are forwarded to JavaScript.
    }
}
