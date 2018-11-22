//package com.himorfosis.doaku;
//
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.session.MediaSessionManager;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by him on 6/22/2018.
 */

public class DaftarDoaSecond extends AppCompatActivity {
//
//    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;
//    public static final String Broadcast_PLAY_NEW_AUDIO = "com.himorfosis.doaku.PlayNewAudio";
//
//    ArrayList<DoaClassFix> listdoa = new ArrayList<>();
//    DoaListAdapterFix adapter;
//
//    private PlayerServiceSecond player;
//    boolean serviceBound = false;
//    ArrayList<DoaClassFix> datadoa;
//
//    Toolbar toolbar;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.daftardoa);
//
//        toolbar = (Toolbar) findViewById(R.id.toolbarsearch);
//        toolbar.setTitle("Doa sehari hari");
//        toolbar.setNavigationIcon(R.drawable.kembali);
//
//        ListView list  = (ListView) findViewById(R.id.list);
//
//        ListDoa.listdoa();
//
//        adapter = new DoaListAdapterFix(getApplicationContext(), ListDoa.listdoa());
//
//        Collections.sort(ListDoa.listdoa(), new Comparator<DoaClassFix>() {
//            @Override
//            public int compare(DoaClassFix o1, DoaClassFix o2) {
//                return 0;
//            }
//        });
//
//        list.setAdapter(adapter);
//
//        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                playDoa(position);
//
//                Log.e("posisi doa", "" +position);
//
//            }
//        });
//
//    }
//
//    @Override
//    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
//        super.onSaveInstanceState(outState, outPersistentState);
//        outState.putBoolean("serviceStatus", serviceBound);
//    }
//
//    @Override
//    protected void onRestoreInstanceState(Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
//        serviceBound = savedInstanceState.getBoolean("serviceStatus");
//    }
//
//    //Binding this Client to the AudioPlayer Service
//    private ServiceConnection serviceConnection = new ServiceConnection() {
//        @Override
//        public void onServiceConnected(ComponentName name, IBinder service) {
//            // We've bound to LocalService, cast the IBinder and get LocalService instance
//            PlayerServiceSecond.LocalBinder binder = (PlayerServiceSecond.LocalBinder) service;
//            player = binder.getService();
//            serviceBound = true;
//
//        }
//
//        @Override
//        public void onServiceDisconnected(ComponentName name) {
//            serviceBound = false;
//        }
//    };
//
//
//    private void playDoa(int indexDoa) {
//
//        if (!serviceBound) {
//
//            PlayerStorageUtil storage = new PlayerStorageUtil(getApplicationContext());
//            storage.storeDoa(datadoa);
//            storage.storeDoaIndex(indexDoa);
//
//            Intent playerIntent = new Intent(this, PlayerServiceSecond.class);
//            startService(playerIntent);
//            bindService(playerIntent, serviceConnection, Context.BIND_AUTO_CREATE);
//
//        } else {
//
//            PlayerStorageUtil storage = new PlayerStorageUtil(getApplicationContext());
//            storage.storeDoaIndex(indexDoa);
//
//            //Service is active
//            //Send a broadcast to the service -> PLAY_NEW_AUDIO
//            Intent broadcastIntent = new Intent(Broadcast_PLAY_NEW_AUDIO);
//            sendBroadcast(broadcastIntent);
//
//        }
//
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        if (serviceBound) {
//            unbindService(serviceConnection);
//            //service is active
//            player.stopSelf();
//        }
//    }
}
