//package com.himorfosis.doaku;
//
//import android.app.NotificationManager;
//import android.app.PendingIntent;
//import android.app.Service;
//import android.content.BroadcastReceiver;
//import android.content.Context;
//import android.content.Intent;
//import android.content.IntentFilter;
//import android.graphics.BitmapFactory;
//import android.media.MediaPlayer;
//import android.os.Build;
//import android.os.IBinder;
//import android.provider.MediaStore;
//import android.support.annotation.Nullable;
//import android.support.v4.app.NotificationCompat;
//import android.support.v4.media.session.MediaSessionCompat;
//import android.telephony.PhoneStateListener;
//import android.telephony.TelephonyManager;
//
//import java.util.ArrayList;
//
///**
// * Created by him on 6/14/2018.
// */
//
//public class PlayerService extends Service {
//
//    //player
//    private MediaPlayer mediaPlayer = new MediaPlayer();
//
//    //receiver
//    //receiver
//    private BroadcastReceiver broadcastReceiver;
//    private IntentFilter filter;
//    private MediaSessionCompat mediaSession;
//
//    ArrayList<DoaClassFix> arraylistdoa = new ArrayList<>();
//
//    int intNomorDoa;
//    DoaClassFix data;
//    private int indexdoa = -1;
//    private DoaClassFix activedoa;
//
//    //Handle incoming phone calls
//    private boolean ongoingCall = false;
//    private PhoneStateListener phoneStateListener;
//    private TelephonyManager telephonyManager;
//
//
//    @Override
//    public void onCreate() {
//        super.onCreate();
//
//        String pos = Utilities.getValueName("ArrayDoa", "posisi", getApplicationContext()) != null ? Utilities.getValueName("ArrayDoa", "posisi", getApplicationContext()) : "0";
//
//        intNomorDoa = Integer.parseInt(pos);
//
//        data = ListDoa.listdoa().get(intNomorDoa);
//
//        //init receiver
//        filter = new IntentFilter();
//        filter.addAction("stop");
//        filter.addAction("start");
//        broadcastReceiver = new BroadcastReceiver() {
//            @Override
//            public void onReceive(Context context, Intent intent) {
//                String action = intent.getAction();
//                if (action.equals("stop")) {
//                    if (mediaPlayer != null) {
//                        if (mediaPlayer.isPlaying()) {
//                            mediaPlayer.stop();
//                        }
//                    }
//                } else if (action.equals("start")) {
//                    try {
//                        mediaPlayer = MediaPlayer.create(getApplicationContext(), data.getPlayDoa());
//                        mediaPlayer.start();
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        };
//
//        registerReceiver(broadcastReceiver, filter);
//    }
//
//    @Nullable
//    @Override
//    public IBinder onBind(Intent intent) {
//        return null;
//    }
//
//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
//        try {
//
//            mediaPlayer = MediaPlayer.create(getApplicationContext(), data.getPlayDoa());
//            mediaPlayer.start();
//
//            if (indexdoa != -1 && indexdoa < arraylistdoa.size()) {
//
//                activedoa = arraylistdoa.get(indexdoa);
//
//            } else  {
//
//                stopSelf();
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
////            stopSelf();
//
//        }
//
//        showNotif();
//        return START_STICKY;
//    }
//
//    private void showNotif() {
//
////        StatusPlay statusPlay;
//
//        Intent stopIntent = new Intent(this, PlayerReceiver.class);
//        stopIntent.setAction("exit");
//        PendingIntent exitPendingIntent = PendingIntent.getBroadcast(this, 12345, stopIntent, PendingIntent.FLAG_UPDATE_CURRENT);
//
//        Intent pauseIntent = new Intent(this, PlayerReceiver.class);
//        pauseIntent.setAction("stop");
//        PendingIntent stopPendingIntent = PendingIntent.getBroadcast(this, 12345, pauseIntent, PendingIntent.FLAG_UPDATE_CURRENT);
//
//        Intent playIntent = new Intent(this, PlayerReceiver.class);
//        playIntent.setAction("start");
//        PendingIntent playPendingIntent = PendingIntent.getBroadcast(this, 12345, playIntent, PendingIntent.FLAG_UPDATE_CURRENT);
//
//
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
//
//        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//
//            builder.setSmallIcon(R.drawable.logodoaku)
//                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.logodoaku))
//                    .setColor(getResources().getColor(R.color.colorPrimary))
//                    .setContentText("DoaKu Diputar")
//                    .setStyle(new android.support.v4.media.app.NotificationCompat.MediaStyle())
//                    .setContentTitle(data.getNama())
//                    .addAction(android.R.drawable.ic_media_play, "Play", playPendingIntent)
//                    .addAction(android.R.drawable.ic_media_pause, "Stop", stopPendingIntent)
//                    .addAction(android.R.drawable.ic_menu_close_clear_cancel, "Keluar", exitPendingIntent)
//
//                    ;
//
////            builder.setSmallIcon(android.R.drawable.ic_media_play)
////            builder.setTicker("Doaku Diputer")
////            builder.setContentTitle(data.getNama());
//
////            builder.setColor(getResources().getColor(R.color.putih));
//
//        } else {
//
//            builder.setSmallIcon(R.drawable.logodoaku)
//                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.logodoaku))
//                    .setOngoing(true)
//                    .setContentTitle(data.getNama())
//                    .setContentText("DoaKu Diputar")
//                    .addAction(android.R.drawable.ic_media_play, "pause", playPendingIntent)
//                    .addAction(android.R.drawable.ic_media_pause, "stop", stopPendingIntent)
//                    .addAction(android.R.drawable.ic_menu_close_clear_cancel, "exit", exitPendingIntent);
//
//        }
//
//        startForeground(115, builder.build());
//    }
//
//
//    private void hideNotif() {
//        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//        notificationManager.cancel(115);
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        if (mediaPlayer != null) {
//            if (mediaPlayer.isPlaying()) {
//                mediaPlayer.stop();
//            }
//        }
//        unregisterReceiver(broadcastReceiver);
//        hideNotif();
//    }
//
//}
