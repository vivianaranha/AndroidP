package com.vivianaranha.notificationwithimage;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ContentResolver;
import android.content.Context;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private static final String MY_NOTIFICATION_CHANNEL = "notification_channel";
    private NotificationManager notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button b = findViewById(R.id.notify_button);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fireNotification();
            }
        });

        String name = "Hi Notification";
        String description = "I am a notification";
        NotificationChannel channel = new NotificationChannel(MY_NOTIFICATION_CHANNEL, name,
                NotificationManager.IMPORTANCE_HIGH);
        channel.setDescription(description);

        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if(notificationManager != null) {
            notificationManager.createNotificationChannel(channel);
        }

    }

    private void fireNotification() {

        Notification.Person person = new Notification.Person()
                .setName("Vivian")
                .setIcon(Icon.createWithResource(this, R.drawable.mario));

        Notification.MessagingStyle.Message message = new Notification.MessagingStyle.Message("I am a Notification",
                new Date().getTime(), person);

        String path = String.format("%s://%s/%s/%s", ContentResolver.SCHEME_ANDROID_RESOURCE,
                getResources().getResourcePackageName(R.drawable.vivianaranha),
                getResources().getResourceTypeName(R.drawable.vivianaranha),
                getResources().getResourceEntryName(R.drawable.vivianaranha));

        Uri uri = Uri.parse(path);
        message.setData("image/jpeg", uri);

        Notification notification = new Notification.Builder(this, MY_NOTIFICATION_CHANNEL)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setStyle(new Notification.MessagingStyle("Vivian Aranha")
                    .addMessage(message))
                .build();

        notificationManager.notify(1, notification);

    }


}
