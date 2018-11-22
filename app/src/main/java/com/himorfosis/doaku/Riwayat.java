package com.himorfosis.doaku;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Riwayat extends AppCompatActivity {

    List<String> riwayat;
    SharedPref sharedPref;
    ListView list;
    List<String> riwayatadapter = new ArrayList<>();

    RiwayatListAdapter riwayatListAdapter;
    DoaListAdapterFix doalistadapter;
    List<DoaClassData> listRiwayat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.riwayat);

        sharedPref = new SharedPref();

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.toolbar);

        TextView judul = (TextView) getSupportActionBar().getCustomView().findViewById(R.id.toolbartext);
        judul.setText("Riwayat");

        Button back = (Button)getSupportActionBar().getCustomView().findViewById(R.id.kembali);
        back.setVisibility(View.VISIBLE);

        list = (ListView) findViewById(R.id.list);

        riwayat = sharedPref.getRiwayat(getApplicationContext());

        Log.e("data riwayt", "" +riwayat);

        if (riwayat == null) {

        } else {

            if(riwayat.size() == 0) {

                Log.e("Riwayat size", "" +riwayat.size());

            }
        }

        if(riwayat != null) {

//            Collections.reverse(riwayat);
//            {
//                @Override
//                public int compare(Integer o1, Integer o2) {
//                    return 0;
//                }
//            });

            for(int i = 0; i < riwayat.size(); i++) {

//                Log.e("lopping ke", "" +i);

//                DoaClassFix data = ListDoa.listdoa().get(riwayat.get(i));
//                riwayat.get(i);

                riwayatadapter.add(riwayat.get(i));

                Log.e("looping ke ","" +i);
//                Log.e("List doa get i", "" +ListDoa.listdoa().get(i));

            }

            riwayatListAdapter = new RiwayatListAdapter(getApplicationContext(), riwayatadapter);
//            doalistadapter = new DoaListAdapterFix(getApplicationContext(), riwayat);

            Utilities.ShowLog("Riwayat", "" +riwayat);

        }

            list.setAdapter(riwayatListAdapter);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

    }
}
