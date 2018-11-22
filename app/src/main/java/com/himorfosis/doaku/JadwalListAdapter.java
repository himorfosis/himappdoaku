package com.himorfosis.doaku;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by him on 7/5/2018.
 */

public class JadwalListAdapter extends ArrayAdapter<JadwalClassData> {
    private Context context;
    private List<JadwalClassData> list;
    Database db;

    public JadwalListAdapter(Context context, List<JadwalClassData> list) {
        super(context, R.layout.rowjadwal, list);
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
            v = vi.inflate(R.layout.rowjadwal, null);

        }

        JadwalClassData p = list.get(position);

        db = new Database(getContext());

        if (p != null) {

            ImageView gambar = (ImageView) v.findViewById(R.id.gambar);
            TextView kegiatan = (TextView) v.findViewById(R.id.kegiatan);
            TextView doa = (TextView) v.findViewById(R.id.doa);
            TextView jam = (TextView) v.findViewById(R.id.jam);


            kegiatan.setText(p.getkegiatan());
            doa.setText(p.getdoa());
            jam.setText(p.getwaktu());

        }
        return v;

    }
}
