package com.himorfosis.doaku;

import android.*;
import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.app.DownloadManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.security.DigestException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import pub.devrel.easypermissions.EasyPermissions;

public class PlayDoa extends AppCompatActivity implements MediaPlayer.OnCompletionListener, MediaPlayer.OnBufferingUpdateListener, EasyPermissions.PermissionCallbacks {


    private Handler handler = new Handler();
    private static final int NOTIFICATION_ID = 9;


    Button btnplay, btnnext, btnprev;

    TextView ayat, terjemahan, judul, audioProgress, audioMax;
    RelativeLayout share, download;

    LinearLayout linear;
    String namadoa;

    //seekbar
    int position;
    SeekBar seekBar;
    Thread updateseekbar;


    DoaClassData data = ListDoa.liststreaming().get(position);

    //media player
    int putar = 0;
    MediaPlayer player;
    public static int startAudio = 0, endAudio = 0;


    //notifikasi
    NotificationManager notificationManager;
    NotificationCompat.Builder builder;

    //download
    File directory = Environment.getExternalStorageDirectory();
    private Uri UriDownload;
    ArrayList<Long> downloadList = new ArrayList<>();
    DownloadManager downloadManager;
    private ProgressDialog progressDialog;
    public static final int progress_bar_type = 0;
    private static final int WRITE_REQUEST_CODE = 300;
    //    private NotificationManager notifDownload;
    private NotificationCompat.Builder notifBuild;

    //ukuran font
    int ukuranfontlatin, ukuranfontarab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.playdoa);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.toolbar);

        player = new MediaPlayer();

        judul = (TextView) getSupportActionBar().getCustomView().findViewById(R.id.toolbartext);

        Button kembali = (Button) getSupportActionBar().getCustomView().findViewById(R.id.kembali);
        kembali.setVisibility(View.VISIBLE);

        ayat = (TextView) findViewById(R.id.ayat);
        terjemahan = (TextView) findViewById(R.id.terjemahan);
        btnnext = (Button) findViewById(R.id.next);
        btnplay = (Button) findViewById(R.id.play);
        btnprev = (Button) findViewById(R.id.previous);
        seekBar = (SeekBar) findViewById(R.id.seekbar);
        linear = (LinearLayout) findViewById(R.id.linear);
        share = (RelativeLayout) findViewById(R.id.fabshare);
        download = (RelativeLayout) findViewById(R.id.fabdownload);
        audioProgress = (TextView) findViewById(R.id.audioprogress);
        audioMax = (TextView) findViewById(R.id.audiomax);

        ukuranfontlatin = Utilities.getIntPref("ukuranfontlatin", "font", getApplicationContext());
        ukuranfontarab = Utilities.getIntPref("ukuranfont", "font", getApplicationContext());

        Log.e("ukuran font arab", "" + ukuranfontarab);
        Log.e("ukuran font latin", "" + ukuranfontlatin);

        latinFontSize();
        arabicFontSize();

        // get parsing value from daftar doa

        Bundle bundle = getIntent().getExtras();

        String pos = bundle.getString("key");

        position = Integer.parseInt(pos);

        Log.e("posisi play doa", "" + position);

        // membuat seekbar berjalan

        updateseekbar = new Thread() {

            @Override
            public void run() {
                int durasi = player.getDuration();
                int currentposition = 0;

                while (currentposition < durasi) {
                    try {

                        sleep(500);
                        currentposition = player.getCurrentPosition();
                        seekBar.setProgress(currentposition);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };


//        final DoaClassFix data = ListDoa.listdoa().get(position);
        data = ListDoa.liststreaming().get(position);

//        stopPlaying();

//        data.getPlayDoa();
        judul.setText(data.getNama());
        judul.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        judul.setSelected(true);
        judul.setSingleLine(true);

        ayat.setText(data.getAyat());
        terjemahan.setText(data.getTerjemahan());

        //memutar audio dari file
//        player = MediaPlayer.create(getApplicationContext(),data.getPlayDoa());

        //memutar audio streaming
//        player.setDataSource(getApplicationContext(), data.getPlayDoa());

        player = new MediaPlayer();

        playdoa();


        notifikasi(getApplicationContext());

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                player.seekTo(seekBar.getProgress());

                Log.e("max seekbar", "" + seekBar.getMax());
                Log.e("progress seekbar", "" + seekBar.getProgress());

                if (seekBar.getMax() == 100) {

                    btnplay.setBackgroundResource(R.drawable.play);
                }

            }
        });

        btnplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (putar == 2) {
                    player.pause();
                    putar = 0;
                    btnplay.setBackgroundResource(R.drawable.play);

                } else if (putar == 0) {
                    player.start();
                    putar = 2;
                    btnplay.setBackgroundResource(R.drawable.pause);
                }

            }
        });

        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (position == 2) {

                    position = 0;

                    playnext();

                    notifikasi(getApplicationContext());

                    Log.e("next posisi", "" + position);


                } else {

                    playnext();

                    notifikasi(getApplicationContext());

                    Log.e("next posisi", "" + position);

                }
            }
        });

        btnprev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (position == 0) {

                    position = 2;
                    playprev();

                    notifikasi(getApplicationContext());

                    Log.e("prev position", "" + position);

                } else {

                    playprev();

                    notifikasi(getApplicationContext());

                    Log.e("prev position", "" + position);
                }
            }
        });

        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                player.stop();
                finish();

                Utilities.clearSharedPreferance("PlayDoa", getApplicationContext());
                notificationManager.cancel(NOTIFICATION_ID);

            }
        });

        Intent notifyIntent = new Intent(getApplicationContext(), PlayDoa.class);
        notifyIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);


        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Utilities.ShowToast(getApplicationContext(), "Yeay share");

                Intent intent = new Intent(Intent.ACTION_SEND_MULTIPLE);

                //memilih type file yang akan di kirim
                intent.setType("text/plain");
//                intent.setType("image/*");

                intent.putExtra(Intent.EXTRA_SUBJECT, "Berbagi Doaku");
                intent.putExtra(Intent.EXTRA_TEXT, data.getNama() + "\n\n" + data.getAyat() + "\n\n" + data.getTerjemahan());
                startActivity(Intent.createChooser(intent, "Bagikan"));

            }
        });

        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Utilities.ShowToast(getApplicationContext(), "Yeay download");
//                checkAndCreateDirectory("/DoaKu");

                if (cekSDCard()) {

                    if (EasyPermissions.hasPermissions(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                        new DownloadFile().execute(data.getPlayDoa());


                    } else {

                        EasyPermissions.requestPermissions(PlayDoa.this, getString(R.string.write_file), WRITE_REQUEST_CODE, Manifest.permission.READ_EXTERNAL_STORAGE);
                    }

//                    storagePermission();

                } else {

                    Utilities.ShowToast(getApplicationContext(), "SD Card tidak tersedia");
                }

            }
        });

    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
        player.stop();
        finish();
        Utilities.clearSharedPreferance("PlayDoa", getApplicationContext());

        notificationManager.cancel(NOTIFICATION_ID);

    }

    public void playdoa() {

        judul.setText(data.getNama());
        judul.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        judul.setSelected(true);
        judul.setSingleLine(true);

        ayat.setText(data.getAyat());
        terjemahan.setText(data.getTerjemahan());

        try {

            player.reset();
            player.setDataSource(data.getPlayDoa());
            player.prepare(); // might take long! (for buffering, etc)

            Log.e("try play", "" + data.getPlayDoa());

        } catch (Exception e) {
            e.printStackTrace();
        }

        player.start();

        putar = 2;
        btnplay.setBackgroundResource(R.drawable.pause);
        updateseekbar.start(); // memulai seekbar

        startAudio = player.getDuration();
        seekBar.setMax(startAudio);

        audioMax.setText(String.format("%d : %d", TimeUnit.MILLISECONDS.toMinutes(startAudio),
                TimeUnit.MILLISECONDS.toSeconds(startAudio) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(startAudio))));

        audioProgress.setText(String.format("%d : %d", TimeUnit.MILLISECONDS.toMinutes(startAudio),
                TimeUnit.MILLISECONDS.toMinutes(startAudio) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(startAudio))));
        handler.postDelayed(UpdateAudioTime, 100);

    }

    public void playnext() {

        data = ListDoa.liststreaming().get(position + 1);

        Log.e("next post", "");

        player.stop();
//        player.release();
        position = (position + 1) % ListDoa.liststreaming().size();

        playdoa();

        if (putar == 0) {

            putar = 2;
            btnplay.setBackgroundResource(R.drawable.pause);

        }
    }

    public void playprev() {

        data = ListDoa.liststreaming().get(position - 1);

        player.stop();
//        player.release();
        position = (position - 1 < 0) ? ListDoa.listdoa().size() - 1 : position - 1;

        playdoa();

        if (putar == 0) {

            putar = 2;
            btnplay.setBackgroundResource(R.drawable.pause);

        }
    }

    public void notifikasi(Context context) {

        data = ListDoa.liststreaming().get(position);

        builder = new NotificationCompat.Builder(context);
        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.drawable.play);
        builder.setContentTitle(data.getNama());
        builder.setContentText("DoaKu Playing");

        Log.e("posisi", " " + position);

        notificationManager.notify(NOTIFICATION_ID, builder.build());

//        startForegroundService(115, builder.build());
//        startForeground(115, builder.build());
    }

    @Override
    public void onCompletion(MediaPlayer player) {
        /** MediaPlayer onCompletion event handler. Method which calls then song playing is complete*/
        btnplay.setBackgroundResource(R.drawable.pause);
    }

    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {
        /** Method which updates the SeekBar secondary progress by current song loading from URL position*/
        seekBar.setSecondaryProgress(percent);
    }


    BroadcastReceiver onComplete = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            long referenceID = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);

            downloadList.remove(referenceID);

            if (downloadList.isEmpty()) {

                NotificationCompat.Builder builder = new NotificationCompat.Builder(PlayDoa.this)
                        .setContentTitle("sedang mendownload");


                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.notify(455, builder.build());
            }

        }
    };

    private Runnable UpdateAudioTime = new Runnable() {
        @Override
        public void run() {

            startAudio = player.getCurrentPosition();
            audioProgress.setText(String.format("%d : %d", TimeUnit.MILLISECONDS.toMinutes(startAudio),
                    TimeUnit.MILLISECONDS.toSeconds(startAudio) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(startAudio))));

            seekBar.setProgress(startAudio);
            handler.postDelayed(this, 100);

        }
    };


    private void addriwayat() {

        SharedPref sharedPref = new SharedPref();

        List getRiwayat = sharedPref.getRiwayat(getApplicationContext());

        if (getRiwayat != null) {

            for (int i = 0; i < getRiwayat.size(); i++) {

                if (getRiwayat.get(i).equals(data.getNama())) {

                    sharedPref.removeRiwayat(getApplicationContext(), data.getNama());
                    Utilities.ShowLog("data riwayat hapus", "" + position);

                    sharedPref.addRiwayat(getApplicationContext(), data.getNama());
                    Utilities.ShowLog("data riwayat diperbarui", "" + position);

                } else {

                    sharedPref.addRiwayat(getApplicationContext(), data.getNama());
                    Utilities.ShowLog("data riwayat simpan", "" + position);

                }

                getRiwayat = sharedPref.getRiwayat(getApplicationContext());

                Utilities.ShowLog("loop new data riwayat", "" + getRiwayat);

            }

        } else {

            Utilities.ShowLog("data null riwayat simpan", "" + position);
            sharedPref.addRiwayat(getApplicationContext(), data.getNama());

        }

    }

    // download progress

    public static boolean cekSDCard() {
        if (Environment.getExternalStorageState().equals(

                Environment.MEDIA_MOUNTED)) {
            return true;
        }

        return false;

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, PlayDoa.this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        //Download the file once permission is granted
//        url = editTextUrl.getText().toString();
//        new DownloadFile().execute(url);

        DownloadManager downloadManager = (DownloadManager) getApplicationContext().getSystemService(Context.DOWNLOAD_SERVICE);
//
        UriDownload = Uri.parse(data.getPlayDoa());
//
        DownloadManager.Request request = new DownloadManager.Request(UriDownload);

        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
//            request.setAllowedOverRoaming(false);
        request.setTitle("Sedang mendownload");
        request.setDescription(data.getNama() + ".mp3"); //memberi nama file
//            request.setVisibleInDownloadsUi(true);

        downloadManager.enqueue(request);

    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        Log.d("", "Permission has been denied");
    }

    /**
     * Async Task to download file from URL
     */
    private class DownloadFile extends AsyncTask<String, String, String> {

        private ProgressDialog progressDialog;
        private String fileName;
        private String folder;
        private boolean isDownloaded;

        /**
         * Before starting background thread
         * Show Progress Bar Dialog
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            this.progressDialog = new ProgressDialog(PlayDoa.this);
            this.progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            this.progressDialog.setCancelable(false);
            this.progressDialog.show();
        }

        /**
         * Downloading file in background thread
         */
        @Override
        protected String doInBackground(String... f_url) {
            int count;
            try {
                URL url = new URL(f_url[0]);
                URLConnection connection = url.openConnection();
                connection.connect();
                // getting file length
                int lengthOfFile = connection.getContentLength();


                // input stream to read file - with 8k buffer
                InputStream input = new BufferedInputStream(url.openStream(), 8192);

                String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

                //Extract file name from URL
                fileName = f_url[0].substring(f_url[0].lastIndexOf('/') + 1, f_url[0].length());

                //Append timestamp to file name
                fileName = timestamp + "_" + fileName;

                //External directory path to save file
                folder = Environment.getExternalStorageDirectory().toString();
                //+ File.separator + data.getNama() +".mp3";
                File directory = new File(folder + "/Doaku audio"); // membuat directory untuk menyimpan

                directory.mkdirs();

                String filename = data.getNama() + ".mp3";

                File file = new File(directory, filename);

                //Create androiddeft folder if it does not exist
//                File directory = new File(folder);

                if (!file.exists()) {
                    file.mkdirs();
                }

                // notifikasi download progress

                notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                notifBuild = new NotificationCompat.Builder(getApplicationContext());
                notifBuild.setContentText("Download file")
                        .setContentText("download in pogress")
                        .setSmallIcon(R.drawable.logodoaku);

                new Thread(
                        new Runnable() {
                            @Override
                            public void run() {

                                int i;
                                int id = 1;

                                for (i = 0; i <= 100; i += 5) {

                                    notifBuild.setProgress(100, i, false);
                                    notificationManager.notify(id, notifBuild.build());

                                    try {

                                        Thread.sleep(1 * 1000);
                                    } catch (InterruptedException e) {

                                        Log.e("", "sleep error");
                                    }
                                }

                                notifBuild.setContentText("Download sukses")
                                        .setProgress(0, 0, false);
                                notificationManager.notify(id, notifBuild.build());

                            }
                        }

                ).start();


                // Output stream to write file
                OutputStream output = new FileOutputStream(folder + fileName);

                byte data[] = new byte[1024];

                long total = 0;

                while ((count = input.read(data)) != -1) {
                    total += count;
                    // publishing the progress....
                    // After this onProgressUpdate will be called
                    publishProgress("" + (int) ((total * 100) / lengthOfFile));
                    Log.d("", "Progress: " + (int) ((total * 100) / lengthOfFile));

                    // writing data to file
                    output.write(data, 0, count);
                }

                // flushing output
                output.flush();

                // closing streams
                output.close();
                input.close();
                return "Downloaded at: " + folder + fileName;

            } catch (Exception e) {
                Log.e("Error: ", e.getMessage());
            }

            return "Something went wrong";
        }

        /**
         * Updating progress bar
         */
        protected void onProgressUpdate(String... progress) {
            // setting progress percentage
            progressDialog.setProgress(Integer.parseInt(progress[0]));
        }


        @Override
        protected void onPostExecute(String message) {
            // dismiss the dialog after the file was downloaded
            this.progressDialog.dismiss();

            // Display File path after downloading
//            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
        }
    }

    private void latinFontSize() {

        if (ukuranfontlatin == 0) {

            terjemahan.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);

        } else if (ukuranfontlatin == 1) {

            terjemahan.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);

        } else if (ukuranfontlatin == 2) {

            terjemahan.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);

        } else if (ukuranfontlatin == 3) {

            terjemahan.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);

        } else if (ukuranfontlatin == 4) {

            terjemahan.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);

        } else if (ukuranfontlatin == 5) {

            terjemahan.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22);

        } else if (ukuranfontlatin == 6) {

            terjemahan.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);

        }

    }

    private void arabicFontSize() {

        if (ukuranfontarab == 0) {

            ayat.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);

        } else if (ukuranfontarab == 1) {

            ayat.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);

        } else if (ukuranfontarab == 2) {

            ayat.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);

        } else if (ukuranfontarab == 3) {

            ayat.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);

        } else if (ukuranfontarab == 4) {

            ayat.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);

        } else if (ukuranfontarab == 5) {

            ayat.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22);

        } else if (ukuranfontarab == 6) {

            ayat.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);

            Log.e("ukuran font", "24");
        }
    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if(grantResults[0]== PackageManager.PERMISSION_GRANTED){
//
//            File direct = new File(Environment.getExternalStorageDirectory() + "/Doaku");
//
//            if (!direct.exists()) {
//
//                direct.mkdirs();
//            }
//
//            DownloadManager downloadManager = (DownloadManager) getApplicationContext().getSystemService(Context.DOWNLOAD_SERVICE);
//
//            UriDownload = Uri.parse(data.getPlayDoa());
//
//            DownloadManager.Request request = new DownloadManager.Request(UriDownload);
//
//            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
////            request.setAllowedOverRoaming(false);
//            request.setTitle("Sedang mendownload");
//            request.setDescription(data.getPlayDoa() + ".mp3");
////            request.setVisibleInDownloadsUi(true);
//            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "/DoaKu");
//
//            downloadManager.enqueue(request);
//
//        }
//    }
//
//    @Override
//    public void onPermissionsGranted(int requestCode, List<String> perms) {
//        //Download the file once permission is granted
////        url = editTextUrl.getText().toString();
////        new DownloadFile().execute(url);
//    }
//
//    @Override
//    public void onPermissionsDenied(int requestCode, List<String> perms) {
//        Log.d("on permission denied", "Permission has been denied");
//    }
//
//    /**
//     * Async Task to download file from URL
//     */
//    private class DownloadFile extends AsyncTask<String, String, String> {
//
//        private ProgressDialog progressDialog;
//        private String fileName;
//        private String folder;
//        private boolean isDownloaded;
//
//        /**
//         * Before starting background thread
//         * Show Progress Bar Dialog
//         */
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            this.progressDialog = new ProgressDialog(PlayDoa.this);
//            this.progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
//            this.progressDialog.setCancelable(false);
//            this.progressDialog.show();
//        }
//
//        /**
//         * Downloading file in background thread
//         */
//        @Override
//        protected String doInBackground(String... f_url) {
//            int count;
//            try {
//                URL url = new URL(f_url[0]);
//                URLConnection connection = url.openConnection();
//                connection.connect();
//                // getting file length
//                int lengthOfFile = connection.getContentLength();
//
//
//                // input stream to read file - with 8k buffer
//                InputStream input = new BufferedInputStream(url.openStream(), 8192);
//
//                String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
//
//                //Extract file name from URL
//                fileName = f_url[0].substring(f_url[0].lastIndexOf('/') + 1, f_url[0].length());
//
//                //Append timestamp to file name
//                fileName = timestamp + "_" + fileName;
//
//                //External directory path to save file
//                folder = Environment.getExternalStorageDirectory() + File.separator + "androiddeft/";
//
//                //Create androiddeft folder if it does not exist
//                File directory = new File(folder);
//
//                if (!directory.exists()) {
//                    directory.mkdirs();
//                }
//
//                // Output stream to write file
//                OutputStream output = new FileOutputStream(folder + fileName);
//
//                byte data[] = new byte[1024];
//
//                long total = 0;
//
//                while ((count = input.read(data)) != -1) {
//                    total += count;
//                    // publishing the progress....
//                    // After this onProgressUpdate will be called
//                    publishProgress("" + (int) ((total * 100) / lengthOfFile));
//                    Log.d("", "Progress: " + (int) ((total * 100) / lengthOfFile));
//
//                    // writing data to file
//                    output.write(data, 0, count);
//                }
//
//                // flushing output
//                output.flush();
//
//                // closing streams
//                output.close();
//                input.close();
//                return "Downloaded at: " + folder + fileName;
//
//            } catch (Exception e) {
//                Log.e("Error: ", e.getMessage());
//            }
//
//            return "Something went wrong";
//        }
//
//        /**
//         * Updating progress bar
//         */
//        protected void onProgressUpdate(String... progress) {
//            // setting progress percentage
//            progressDialog.setProgress(Integer.parseInt(progress[0]));
//        }
//
//
//        @Override
//        protected void onPostExecute(String message) {
//            // dismiss the dialog after the file was downloaded
//            this.progressDialog.dismiss();
//
//            // Display File path after downloading
//            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
//        }
//    }

}
