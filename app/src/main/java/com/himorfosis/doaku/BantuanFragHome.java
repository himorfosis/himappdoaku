package com.himorfosis.doaku;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

/**
 * Created by him on 7/1/2018.
 */

public class BantuanFragHome extends Fragment {

    ListView list;

    Bundle bundle = new Bundle();
    Fragment fragment;

    String[] isibantuan = { "Apa itu Doaku ?", "Cara memutar doa", "Cara download audio",
            "Cara bagikan aplikasi"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Returning the layout file after inflating
        //Change R.layout.tab1 in you classes
        return inflater.inflate(R.layout.bantuanfraghome, container, false);

    }

    public void onViewCreated(final View view, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onViewCreated(view, savedInstanceState);
        getActivity().invalidateOptionsMenu();

        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();

        final Button kembali = (Button) actionBar.getCustomView().findViewById(R.id.kembali);
        kembali.setVisibility(View.VISIBLE);

        list = (ListView) view.findViewById(R.id.listbantuan);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), R.layout.rowtextview, R.id.isitext, isibantuan);

        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Utilities.ShowToast(getContext(), "pos :" + position);

                bundle.putInt("key", position);

                fragment = new Penjelasan();
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
