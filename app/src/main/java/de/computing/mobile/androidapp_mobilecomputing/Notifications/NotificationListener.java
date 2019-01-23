package de.computing.mobile.androidapp_mobilecomputing.Notifications;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;

import de.computing.mobile.androidapp_mobilecomputing.Connector;
import de.computing.mobile.androidapp_mobilecomputing.ImageController.ImageCompressor;

import static de.computing.mobile.androidapp_mobilecomputing.Activitys.StartActivity.notificationOn;

public class NotificationListener extends NotificationListenerService {

    private Context context;
    private ImageCompressor compr;
    private Connector conn;
    private StatusBarNotification sbn_old = null;

    private boolean connected = false;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        compr = new ImageCompressor();
        conn = new Connector();
    }

    @Override
    public void onListenerConnected(){
        connected = true;
    }

    @Override
    public void onNotificationPosted(StatusBarNotification sbn){
        if(connected) {
            if(notificationOn) {
                sbn_old = sbn;
                Icon icon = sbn.getNotification().getSmallIcon();

                Bitmap bitmap = drawableToBitmap(icon.loadDrawable(context));
                String send = compr.changeToImageString(bitmap);

                conn.sendVolleyMessage("I" + send, context);
            }
        }
    }

    private static Bitmap drawableToBitmap (Drawable drawable) {
        Bitmap bitmap = null;

        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if (bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }

        if (drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888); // Single color bitmap will be created of 1x1 pixel
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }
}
