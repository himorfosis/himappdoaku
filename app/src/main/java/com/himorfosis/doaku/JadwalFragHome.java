package com.himorfosis.doaku;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

/**
 * Created by him on 7/4/2018.
 */

public class JadwalFragHome extends Fragment {

    //toolbar
    Button kembali;

    //add jadwal
    FloatingActionButton add;
    ListView list;

    Fragment fragment;
    Database db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        return inflater.inflate(R.layout.jadwalfraghome, container, false);

    }

    public void onViewCreated(final View view, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onViewCreated(view, savedInstanceState);
        getActivity().invalidateOptionsMenu();

        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();

        kembali = (Button) actionBar.getCustomView().findViewById(R.id.kembali);
        kembali.setVisibility(View.VISIBLE);

        add = (FloatingActionButton) view.findViewById(R.id.add);
        list = (ListView) view.findViewById(R.id.list);

        String data[] = null;
        db = new Database(getContext());

        final List<JadwalClassData> datalist = db.getalljadwal();
        data = new String[datalist.size()];
        int i=0;   for (JadwalClassData d : datalist) {
            data [i] = d.getId_jadwal() + d.getkegiatan() + d.getkegiatan() + d.getwaktu();
            i++;

        }

        JadwalListAdapter adapter = new JadwalListAdapter(getContext(), datalist);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Utilities.ShowToast(getContext(), "" +position);

            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Utilities.ShowToast(getContext(), "tambah");
                Bundle bundle = new Bundle();

                fragment = new JadwalFragIsiData();

                fragment.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.frame, fragment).commit();

            }
        });

        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getActivity().finish();
            }
        });
    }
}
