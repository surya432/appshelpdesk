package com.surya432.skripsi.Service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.surya432.skripsi.Activity.DetailTiketActivity;
import com.surya432.skripsi.R;

import java.util.Map;
import java.util.Random;

public class FirebaseFCM extends FirebaseMessagingService {
    private static final String TAG = FirebaseFCM.class.getSimpleName();
    private Integer NOTIF_ID = new Random().nextInt();
    private String NOTIFICATION_CHANNEL_ID = "com.surya432.skripsi";

    public FirebaseFCM() {
    }

    public static String getToken(Context context) {
        return context.getSharedPreferences("_", MODE_PRIVATE).getString("token", "");
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // ...

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());

            if (/* Check if data needs to be processed by long running job */ true) {
                // For long-running tasks (10 seconds or more) use Firebase Job Dispatcher.
                // scheduleJob();
            } else {
                // Handle message within 10 seconds
                // handleNow();
            }

        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
            Map<String, String> data = remoteMessage.getData();
            String idTiket = data.get("DATAFCM").toString();
            showNotification(remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getBody(),idTiket);
        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
    }



    @Override
    public void onNewToken(String token) {
        Log.d(TAG, "Refreshed token: " + token);
        getSharedPreferences("_", MODE_PRIVATE).edit().putString("token", token).apply();
    }

    private void showNotification(String title, String body, String data) {
        Log.e(TAG, "showNotification: " + data);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "Notification", NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setDescription("Satu Pesan baru");
            notificationChannel.enableLights(true);
            notificationChannel.enableVibration(true);
            notificationChannel.setLightColor(Color.BLUE);
            notificationChannel.setVibrationPattern(new long[]{500, 1000, 500, 1000, 500, 1000, 0});
            notificationManager.createNotificationChannel(notificationChannel);
        }
        Intent notificationIntent = new Intent(this, DetailTiketActivity.class);
        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        notificationIntent.putExtra("DATAFCM", data);
        PendingIntent contentIntent = PendingIntent.getActivity(this, NOTIF_ID, notificationIntent, PendingIntent.FLAG_ONE_SHOT);
        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);
        notificationBuilder.setSmallIcon(R.drawable.logounim2)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setContentTitle(title)
                .setContentText(body)
                .setSound(soundUri)
                .setContentInfo("Pesan Baru")
                .setOnlyAlertOnce(true)
                .setContentIntent(contentIntent);
        notificationManager.notify(NOTIF_ID, notificationBuilder.build());
    }
}
