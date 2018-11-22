package com.himorfosis.doaku;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by him on 7/1/2018.
 */

public class BantuanListAdapter extends ArrayAdapter<String> {

    Context context;
    List<String> list;

    public BantuanListAdapter(Context context, List<String> list) {

        super(context, R.layout.rowisipenjelasan, list);
        this.context = context;
        this.list = list;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        View v = view;
        if (v == null) {

            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.rowisipenjelasan, null);

        }

        Log.e("posisi", "" +position);

        Integer pos = position +1;

        String strposisi = String.valueOf(pos);

        String data = list.get(position);

        if (data != null) {

            TextView nomor = (TextView) v.findViewById(R.id.nomorurut);
            TextView penjelasan = (TextView) v.findViewById(R.id.isipenjelasan);

            nomor.setText(strposisi +".");
            penjelasan.setText(data);

        }

        return v;

    }

}
