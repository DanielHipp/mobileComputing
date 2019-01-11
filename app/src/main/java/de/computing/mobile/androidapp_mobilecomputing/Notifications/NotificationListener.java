package de.computing.mobile.androidapp_mobilecomputing.Notifications;

import android.app.Notification;
import android.content.Context;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;

public class NotificationListener extends NotificationListenerService {

    Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    @Override
    public void onNotificationPosted(StatusBarNotification sbn){
        String pack =sbn.getPackageName();
        String ticker = "";
        if(sbn.getNotification().tickerText != null){
            ticker = sbn.getNotification().tickerText.toString();
        }
        Bundle extras = sbn.getNotification().extras;
        String title = extras.getString("android.title");
        String text = extras.getCharSequence("android.text").toString();
        int iconSmall = extras.getInt(Notification.EXTRA_SMALL_ICON);
        Icon icon = sbn.getNotification().getLargeIcon();

        Log.i("Package", pack);
        Log.i("Ticker", ticker);
        Log.i("Title", title);
        Log.i("Text", text);
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn){
        Log.i("Msg", "Notification removed");
    }
}
