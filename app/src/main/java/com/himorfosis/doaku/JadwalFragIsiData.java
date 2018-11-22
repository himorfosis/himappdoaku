package com.himorfosis.doaku;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.app.SearchManager;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.TimePicker;
import android.widget.Toast;

import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidListener;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Future;

/**
 * Created by him on 7/4/2018.
 */

public class JadwalFragIsiData extends Fragment  {

    //toolbar
    Button kembali;

    Fragment fragment;
    TextView pilihdoa, waktu;
    Button simpan;
    EditText kegiatan;

    Database db;

    //builder layout
    AlertDialog.Builder builder;
    LayoutInflater inflater;
    View dialogView;
    AlertDialog alertDialog;

    //SearchView search;
    BuilderListAdapter adapter;

    //setting waktu
    int intJam, intMenit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.jadwalfragisidata, container, false);

    }

    public void onViewCreated(final View view, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onViewCreated(view, savedInstanceState);
        getActivity().invalidateOptionsMenu();
        savedInstanceState = getArguments();

        db = new Database(getContext());

        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();

        kembali = (Button) actionBar.getCustomView().findViewById(R.id.kembali);
        kembali.setVisibility(View.VISIBLE);

        pilihdoa = (TextView) view.findViewById(R.id.doa);
        waktu = (TextView) view.findViewById(R.id.waktu);
        simpan = (Button) view.findViewById(R.id.simpan);
        kegiatan = (EditText) view.findViewById(R.id.kegiatan);
//        tempat = (EditText) view.findViewById(R.id.tempat);


        pilihdoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                builder = new AlertDialog.Builder(getContext());
                inflater = getActivity().getLayoutInflater();

                dialogView = inflater.inflate(R.layout.builderlayout, null);

                final ListView listBuilder = (ListView) dialogView.findViewById(R.id.listbuilder);
//                SearchView search = (SearchView) dialogView.findViewById(R.id.search);

                adapter = new BuilderListAdapter(getContext(), android.R.layout.simple_list_item_1, ListDoa.liststreaming());
                listBuilder.setAdapter(adapter);

                listBuilder.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        DoaClassData data = ListDoa.liststreaming().get(position);
                        pilihdoa.setText(data.getNama());
                        alertDialog.dismiss();

                    }
                });


//                https://www.youtube.com/watch?v=rHhpK3JQ1So

                builder.setTitle("Pilih doa\n");
                builder.setView(dialogView);

                alertDialog = builder.create();
                alertDialog.show();

            }
        });

        waktu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Calendar calendar = Calendar.getInstance();
                intJam = calendar.get(Calendar.HOUR_OF_DAY);
                intMenit = calendar.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        String strjam = Integer.toString(hourOfDay);
                        String strmenit = Integer.toString(minute);

                        waktu.setText(strjam + ":" + strmenit);

                    }
                }, intJam, intMenit, false);

                timePickerDialog.show();

            }
        });

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (waktu.getText().toString().equals("") || pilihdoa.getText().toString().equals("") || pilihdoa.getText().toString().equals("")
                        || kegiatan.getText().toString().equals("")) {

                    Toast.makeText(getContext(), "Harap isi secara lengkap", Toast.LENGTH_SHORT).show();

                } else  {

                    db.insertjadwal(new JadwalClassData(null, kegiatan.getText().toString(), pilihdoa.getText().toString(), waktu.getText().toString() ));

                    fragment = new JadwalFragHome();
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.replace(R.id.frame, fragment);
                    ft.commit();

                }

            }
        });


        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragment = new JadwalFragHome();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.frame, fragment);
                ft.commit();

            }
        });

    }


}
