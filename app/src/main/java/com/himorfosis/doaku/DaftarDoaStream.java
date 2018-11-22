package com.himorfosis.doaku;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by him on 6/29/2018.
 */

public class DaftarDoaStream extends AppCompatActivity {

    ArrayList<DoaClassData> listdoa = new ArrayList<>();
    DoaListAdapter adapter;
    ListView list;
    private MediaPlayer player;
    int putar = 0;
    int position;
    int klikposition;
    SharedPref sharedPref;

    Toolbar toolbar;

    // cek koneksi
    ConnectivityManager connect;

    private boolean networkConnected() {
        boolean haveConnectWifi = false;
        boolean haveConnectMobile = false;

        connect = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo[] netInfo = connect.getAllNetworkInfo();

        for (NetworkInfo net : netInfo) {

            if (net.getTypeName().equals("WIFI"))
                if (net.isConnected())
                    haveConnectWifi = true;

            if (net.getTypeName().equals("mobile"))
                if (net.isConnected())
                    haveConnectMobile = true;

        }

        return haveConnectMobile || haveConnectWifi;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daftardoa);

        sharedPref = new SharedPref();

        toolbar = (Toolbar) findViewById(R.id.toolbarsearch);
        toolbar.setTitle("Doa sehari hari");
        toolbar.setNavigationIcon(R.drawable.kembali);


        setSupportActionBar(toolbar);

        final Button play = (Button) findViewById(R.id.play);
        Button next = (Button) findViewById(R.id.next);
        Button prev = (Button) findViewById(R.id.previous);
        list = (ListView) findViewById(R.id.list);
//        TextView doaplay = (TextView) findViewById(R.id.doaplay);

        ListDoa.liststreaming();

        play.setBackgroundResource(R.drawable.play);

        Log.e("Jumlah list doa", "" + ListDoa.liststreaming());

//        adapter = new DoaListAdapter (getApplicationContext(), ListDoa.liststreaming());

        adapter = new DoaListAdapter(getApplicationContext(), ListDoa.liststreaming());

        Collections.sort(ListDoa.liststreaming(), new Comparator<DoaClassData>() {
            @Override
            public int compare(DoaClassData o1, DoaClassData o2) {
                return 0;
            }
        });

//        ListDoa.listdoa().addAll(listfix);

        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                klikposition = position;

//                DoaClassFix data = ListDoa.listdoa().get(position);
                DoaClassData data = ListDoa.liststreaming().get(position);

                Log.e("posisi", "" + position);

                if (networkConnected()) {

                    if (position > 2) {

                        Utilities.ShowToast(getApplicationContext(), " Data belum dimasukkan");

                    } else {

                        String pos = Integer.toString(position);

                        // parsing vaue position

                        Intent intent = new Intent(getApplicationContext(), PlayDoa.class);

                        intent.putExtra("key", pos);

                        startActivity(intent);

                    }

                } else {

                    Utilities.ShowToast(getApplicationContext(), "Tidak ada koneksi");

                }

            }
        });


        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                if (position == 0) {

//                    Utilities.putPrefName("PlayDoa", "posisi", "0", getApplicationContext());
//
//                    Intent intent = new Intent(getApplicationContext(), PlayDoa.class);
//                    startActivity(intent);
//                } else {
//
//                    if (putar == 2) {
//
//                        player.pause();
//                        putar = 0;
//                        play.setBackgroundResource(R.drawable.play);
//                    } else if (putar == 0) {
//
//                        player.start();
//                        putar = 2;
//                        play.setBackgroundResource(R.drawable.pause);
//                    }
//
//                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                if (position == 0) {
//
//                    Toast.makeText(getApplicationContext(), "silahkan pilih doa",
//                            Toast.LENGTH_SHORT).show();
//                } else {
//
////                    DoaClassFix data = listdoa.get(position);
//                    DoaClassData data = listdoa.get(position);
//                    player.stop();
//                    player.release();
//                    position = (position + 1) % listdoa.size();
//                    data.getPlayDoa();
//                    player = MediaPlayer.create(getApplicationContext(), data.getPlayDoa());
//                    player.start();
//
//                    if (putar == 0) {
//
//                        putar = 2;
//                        play.setBackgroundResource(R.drawable.pause);
//
//                    }
//
//                    Toast.makeText(getApplicationContext(), data.getNama(),
//                            Toast.LENGTH_SHORT).show();
//
//                }
            }
        });

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (position == 0) {

                    Toast.makeText(getApplicationContext(), "silahkan pilih doa",
                            Toast.LENGTH_SHORT).show();
                } else {

                    DoaClassData data = listdoa.get(position);
                    player.stop();
                    player.release();
                    position = (position + 1) % listdoa.size();
                    data.getPlayDoa();
//                    player = MediaPlayer.create(getApplicationContext(), data.getPlayDoa());
                    player.start();

                    if (putar == 0) {
                        putar = 2;
                        play.setBackgroundResource(R.drawable.pause);

                    }

                    Toast.makeText(getApplicationContext(), data.getNama(),
                            Toast.LENGTH_SHORT).show();

                }
            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search, menu);
        // Retrieve the SearchView and plug it into SearchManager

        MenuItem mSearch = menu.findItem(R.id.action_search);

        SearchView mSearchView = (SearchView) mSearch.getActionView();
        mSearchView.setQueryHint("Cari doa");

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                if (TextUtils.isEmpty(newText)) {

                    adapter.filter("");
                    list.clearTextFilter();
                } else {

                    adapter.filter(newText);
                }

                return true;

            }
        });

        return true;
    }


}
