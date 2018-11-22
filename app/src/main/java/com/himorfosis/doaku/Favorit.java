package com.himorfosis.doaku;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class Favorit extends AppCompatActivity {

    ListView list;
    List<DoaClassData> fav;
    SharedPref sharedPref;

    DoaListAdapter doalistadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorit);

        sharedPref = new SharedPref();

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.toolbar);

        TextView judul = (TextView) getSupportActionBar().getCustomView().findViewById(R.id.toolbartext);
        judul.setText("Favorit");

        Button back = (Button)getSupportActionBar().getCustomView().findViewById(R.id.kembali);
        back.setVisibility(View.VISIBLE);

        list = (ListView) findViewById(R.id.list);

//        fav = sharedPref.getFavorites(getApplicationContext());
        fav = sharedPref.getFavoritesStream(getApplicationContext());

        Log.e("get favorit", "" + fav);

        if (fav == null) {


        } else {

            if(fav.size() == 0) {


            }
        }

        if(fav != null) {

            doalistadapter = new DoaListAdapter(getApplicationContext(), fav);

            Collections.sort(ListDoa.listdoa(), new Comparator<DoaClassFix>() {
                @Override
                public int compare(DoaClassFix o1, DoaClassFix o2) {
                    return 0;
                }
            });

            list.setAdapter(doalistadapter);

            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    DoaClassFix data = ListDoa.listdoa().get(position);

                    String pos = Integer.toString(position);

//                    Utilities.putPrefName("PlayDoa", "posisi", pos, getApplicationContext());

                    Intent intent = new Intent(getApplicationContext(), PlayDoa.class);

                    intent.putExtra("key", pos);

                    startActivity(intent);

                    Log.e("di klik", "");
                }
            });

        }

//        adapter = new FavoritListAdapter(getApplicationContext(), ListDoa.listdoa());
//
//        list.setAdapter(adapter);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

    }

}
