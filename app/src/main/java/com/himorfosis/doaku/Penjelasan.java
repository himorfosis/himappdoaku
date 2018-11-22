package com.himorfosis.doaku;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Penjelasan extends Fragment {

    TextView judul, keterangan;
    ListView listpenjelasan;

    int getdata;
    int position;
    BantuanListAdapter adapter;

    ArrayList<String> isipenjelasan = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Returning the layout file after inflating
        //Change R.layout.tab1 in you classes
        return inflater.inflate(R.layout.penjelasan, container, false);

    }

    public void onViewCreated(final View view, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onViewCreated(view, savedInstanceState);
        getActivity().invalidateOptionsMenu();

        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();

        final Button kembali = (Button) actionBar.getCustomView().findViewById(R.id.kembali);
        kembali.setVisibility(View.VISIBLE);

        judul = (TextView) view.findViewById(R.id.judul);
        keterangan = (TextView) view.findViewById(R.id.keterangan);
        listpenjelasan = (ListView) view.findViewById(R.id.listpenjelasan);

        savedInstanceState = getArguments();

        if(savedInstanceState==null){

        } else {

            getdata = savedInstanceState.getInt("key");

        }

        if (getdata == 0) {

            judul.setText("Apa itu Doaku ?");
            keterangan.setText("DoaKu adalah aplikasi pemutar doa doa dalam agama islam dengan terjemahan bahasa Indonesia. " +
                    "Aplikasi DoaKu cocok untuk orang yang belajar dan mengahafal doa. Aplikasi ini berjalan dengan online maupun offline. " +
                    "Pengguna dapat menggunakan fitur offline dengan mendownload file audio terlebih dahulu. " +
                    "Aplikasi Doaku sangat ringan dan cocok digunakan dalam jenis android minimal Jelly Bean.\n");

//            dynamiclist();

        } else if (getdata == 1) {

            judul.setText("Cara memutar doa");
            keterangan.setText("Untuk memutar audio di aplikasi Doaku ikuti langkah - langkah sebagai berikut :");

            isipenjelasan.add(new String( "Pada menu utama pilih daftar doa"));
            isipenjelasan.add(new String( "Pastikan anda terhubung dengan koneksi internet agar bisa memutar doa"));
            isipenjelasan.add(new String( "Setelah masuk ke daftar doa, pilih salah satu doa yang ingin diputar"));
            isipenjelasan.add(new String( "Selamat anda berhasil memutar audio"));


            dynamiclist();

        } else if (getdata == 2) {

            judul.setText("Cara mendownload audio");
            keterangan.setText("Untuk dapat memutar audio di aplikasi Doaku tanpa harus memiliki koneksi internet," +
                    "anda harus mendownload audio doa terlebih dahulu. Cara mendownload audio doa di aplikasi ini adalah sebagai berikut :")
            ;

            isipenjelasan.add(new String( "Pada menu utama pilih daftar doa"));
            isipenjelasan.add(new String( "Pastikan anda terhubung dengan koneksi internet agar bisa memutar doa"));
            isipenjelasan.add(new String( "Setelah masuk ke daftar doa, pilih salah satu doa yang ingin diputar"));
            isipenjelasan.add(new String( "Pada menu pemutar audio doa, pilih icon download berwarna kucing"));
            isipenjelasan.add(new String( "Tunggu proses download selesai"));
            isipenjelasan.add(new String( "Setelah proses donwload selesai, anda dapat memutar audio yang telah anda download" +
                    "tanpa perlu terhubung dengan koneksi internet lagi"));


            dynamiclist();

        } else if (getdata == 3) {

            judul.setText("Cara membagikan aplikasi");
            keterangan.setText("Untuk dapat membagikan aplikasi Doaku ke publik adalah sebagai berikut :");

            isipenjelasan.add(new String( "Pada menu utama pilih icon di pojok kiri atas"));
            isipenjelasan.add(new String( "Kemudian pilih icon bagikan aplikasi"));
            isipenjelasan.add(new String( "Pilih salah satu aplikasi yang ingin anda gunakan untuk membagikan aplikasi"));
            isipenjelasan.add(new String( "Terima kasih telah membagikan aplikasi Doaku"));


            dynamiclist();

        }


        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment fragment = new BantuanFragHome();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.frame, fragment);
                ft.commit();
            }
        });

    }

    public void dynamiclist() {

        adapter = new BantuanListAdapter(getContext(), isipenjelasan);

        listpenjelasan.setAdapter(adapter);

    }
}
