package com.himorfosis.doaku;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;

/**
 * Created by him on 5/28/2018.
 */

public class NotificationGenerator {

    public static final String NOTIFY_PREVIOUS = "com.himorfosis.doaku.previous";
    public static final String NOTIFY_DELETE = "com.himorfosis.doaku.delete";
    public static final String NOTIFY_PAUSE = "com.himorfosis.doaku.pause";
    public static final String NOTIFY_PLAY = "com.himorfosis.doaku.play";
    public static final String NOTIFY_NEXT = "com.himorfosis.doaku.next";

    private static final int NOTIFICATION_ID_OPEN_ACTIVITY = 9;
    private static final int NOTIFICATION_ID_CUSTOM_BIG = 9;

    public static void openActivityNotification(Context context){

        NotificationCompat.Builder nc = new NotificationCompat.Builder(context);
        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        Intent notifyIntent = new Intent(context, PlayDoa.class);

        notifyIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        nc.setContentIntent(pendingIntent);

        // problemnya disini

        nc.setSmallIcon(R.mipmap.ic_launcher);
        nc.setAutoCancel(true);
        nc.setContentTitle("Notification Demo");
        nc.setContentText("Click please");

        nm.notify(NOTIFICATION_ID_OPEN_ACTIVITY, nc.build());

    }

    public static void customBigNotification(Context context){

//        RemoteViews expandedView = new RemoteViews(context.getPackageName(), R.layout.notifikasiplay);

        NotificationCompat.Builder nc = new NotificationCompat.Builder(context);
        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent notifyIntent = new Intent(context, PlayDoa.class);

        notifyIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        nc.setContentIntent(pendingIntent);
        nc.setSmallIcon(R.drawable.play);
        nc.setAutoCancel(true);
//        nc.setCustomBigContentView(expandedView);
        nc.setContentTitle("DoaKu Playing");
//        nc.setContentText("");
//        nc.getBigContentView().setTextViewText(R.id.textSongName, "Adele");

//        setListeners(expandedView, context);

        nm.notify(NOTIFICATION_ID_CUSTOM_BIG, nc.build());
    }

    private static void setListeners(RemoteViews view, Context context){
        Intent previous = new Intent(NOTIFY_PREVIOUS);
        Intent delete = new Intent(NOTIFY_DELETE);
        Intent pause = new Intent(NOTIFY_PAUSE);
        Intent next = new Intent(NOTIFY_NEXT);
        Intent play = new Intent(NOTIFY_PLAY);

        PendingIntent pPrevious = PendingIntent.getBroadcast(context, 0, previous, PendingIntent.FLAG_UPDATE_CURRENT);
        view.setOnClickPendingIntent(R.id.btnPrevious, pPrevious);


        PendingIntent pDelete = PendingIntent.getBroadcast(context, 0, delete, PendingIntent.FLAG_UPDATE_CURRENT);
        view.setOnClickPendingIntent(R.id.btnDelete, pDelete);


        PendingIntent pPause = PendingIntent.getBroadcast(context, 0, pause, PendingIntent.FLAG_UPDATE_CURRENT);
        view.setOnClickPendingIntent(R.id.btnPause, pPause);


        PendingIntent pNext = PendingIntent.getBroadcast(context, 0, next, PendingIntent.FLAG_UPDATE_CURRENT);
        view.setOnClickPendingIntent(R.id.btnNext, pNext);


        PendingIntent pPlay = PendingIntent.getBroadcast(context, 0, play, PendingIntent.FLAG_UPDATE_CURRENT);
        view.setOnClickPendingIntent(R.id.btnPlay, pPlay);

    }
}
