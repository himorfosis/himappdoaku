package com.himorfosis.doaku;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.himorfosis.doaku.databinding.DaftardoaBinding;

import java.nio.file.LinkOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DaftarDoa extends AppCompatActivity {

    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;
    public static final String Broadcast_PLAY_NEW_AUDIO = "com.valdioveliu.valdio.audioplayer.PlayNewAudio";

    ArrayList<DoaClassFix> listdoa = new ArrayList<>();
    List<DoaClassFix> listriwayat;

    DoaListAdapterFix adapter;
    ListView list;
    private MediaPlayer player;
    int putar = 0;
    int position;
    int klikposition;
    SharedPref sharedPref;

    Toolbar toolbar;


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

        ListDoa.listdoa();

        play.setBackgroundResource(R.drawable.play);

        Log.e("Jumlah list doa", "" + ListDoa.listdoa());

        adapter = new DoaListAdapterFix(getApplicationContext(), ListDoa.listdoa());

        Collections.sort(ListDoa.listdoa(), new Comparator<DoaClassFix>() {
            @Override
            public int compare(DoaClassFix o1, DoaClassFix o2) {
                return 0;
            }
        });

//        ListDoa.listdoa().addAll(listfix);

        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                klikposition = position;

                DoaClassFix data = ListDoa.listdoa().get(position);

                Log.e("posisi", "" + position);

//                List<String> datariwayat = sharedPref.getRiwayat(getApplicationContext());
//
//                Log.e("size riwayat", "" + datariwayat);
//
////                List<String> newdatariwayat = sharedPref.getRiwayat(getApplicationContext());
//
//
//                if (datariwayat != null) {
//
//                    for (int i = 0; i < datariwayat.size(); i++) {
//
//                        datariwayat = sharedPref.getRiwayat(getApplicationContext());
//
//                        Log.e("data get nama", "" + data.getNama());
//                        Log.e("data riwayat", "" + datariwayat.get(i));
//                        Log.e("looping ke", "" +i);
////                        Log.e("data riwayat.get(i)", "" + newdatariwayat.get(i));
//
//                        if (datariwayat.get(i).equals(data.getNama())) {
//
//                            sharedPref.removeRiwayat(getApplicationContext(), data.getNama());
//                            Utilities.ShowLog("data riwayat hapus", "" + position);
//
//                            sharedPref.addRiwayat(getApplicationContext(), data.getNama());
//                            Utilities.ShowLog("data riwayat diperbarui", "" + position);
//
//                        } else {
//
//                            sharedPref.addRiwayat(getApplicationContext(), data.getNama());
//                            Utilities.ShowLog("data riwayat simpan", "" + position);
//
//                        }
//
//                        datariwayat = sharedPref.getRiwayat(getApplicationContext());
//
//                        Utilities.ShowLog("loop new data riwayat", "" + datariwayat);
//
//                    }
//
//                } else {
//
//                    Utilities.ShowLog("data null riwayat simpan", "" + position);
//                    sharedPref.addRiwayat(getApplicationContext(), data.getNama());
//
//                }

//                datariwayat = sharedPref.getRiwayat(getApplicationContext());
//
//                Utilities.ShowToast(getApplicationContext(), "" + datariwayat);

                String pos = Integer.toString(position);

                Utilities.putPrefName("PlayDoa", "posisi", pos, getApplicationContext());
//
////              startService(new Intent(DaftarDoa.this, PlayerService.class));
//
                Intent intent = new Intent(getApplicationContext(), PlayDoa.class);

                startActivity(intent);

            }
        });


        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (position == 0) {

                    Utilities.putPrefName("PlayDoa", "posisi", "0", getApplicationContext());

                    Intent intent = new Intent(getApplicationContext(), PlayDoa.class);
                    startActivity(intent);
                } else {

                    if (putar == 2) {

                        player.pause();
                        putar = 0;
                        play.setBackgroundResource(R.drawable.play);
                    } else if (putar == 0) {

                        player.start();
                        putar = 2;
                        play.setBackgroundResource(R.drawable.pause);
                    }

                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (position == 0) {

                    Toast.makeText(getApplicationContext(), "silahkan pilih doa",
                            Toast.LENGTH_SHORT).show();
                } else {

                    DoaClassFix data = listdoa.get(position);
                    player.stop();
                    player.release();
                    position = (position + 1) % listdoa.size();
                    data.getPlayDoa();
                    player = MediaPlayer.create(getApplicationContext(), data.getPlayDoa());
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

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (position == 0) {

                    Toast.makeText(getApplicationContext(), "silahkan pilih doa",
                            Toast.LENGTH_SHORT).show();
                } else {

                    DoaClassFix data = listdoa.get(position);
                    player.stop();
                    player.release();
                    position = (position + 1) % listdoa.size();
                    data.getPlayDoa();
                    player = MediaPlayer.create(getApplicationContext(), data.getPlayDoa());
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

    private void cekriwayat() {

        List<String> newdatariwayat = sharedPref.getRiwayat(getApplicationContext());

        Utilities.ShowLog("hapus data riwayat", "" + newdatariwayat.get(klikposition));
        sharedPref.removeRiwayat(getApplicationContext(), newdatariwayat.get(klikposition));

    }

}
