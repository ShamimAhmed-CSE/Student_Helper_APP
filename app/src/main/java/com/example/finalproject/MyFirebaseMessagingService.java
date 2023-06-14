package com.example.finalproject;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
        Log.d("FCM Token", token);
        // Save the new FCM token to your server if needed
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        // Handle the received message and show the notification
        sendNotification(remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody());
        if (remoteMessage.getData().size() > 0) {
            // Handle the data message here
            String text = remoteMessage.getData().get("text");

            // Store the text in a suitable data structure for later use
            // Store the text in SharedPreferences for later use
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("textData", text);
            editor.apply();


            // Trigger an event or update UI if necessary
            // For example, you can use an event bus or broadcast an intent
            // to inform the selected activity about the new data.
        }
    }

    @SuppressLint("MissingPermission")
    private void sendNotification(String title, String messageBody) {
        // Build and show the notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "channel_id")
                .setContentTitle(title)
                .setContentText(messageBody)
                .setSmallIcon(R.drawable.ic_baseline_notifications_active_24)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        //notificationManager.notify(0, builder.build());
        notificationManager.notify(0, builder.build());
    }
}
