package com.himorfosis.doaku;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by him on 7/4/2018.
 */

public class BuilderListAdapter extends ArrayAdapter<DoaClassData> {

    ArrayList<DoaClassData> arrayisi = new ArrayList<>();
    List<DoaClassData> listisi;

    public BuilderListAdapter(Context context, int textViewResourceId, ArrayList<DoaClassData> objects) {
        super(context, textViewResourceId, objects);
        arrayisi = objects;
//        this.arrayisi.addAll(listisi);

    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(android.R.layout.simple_list_item_1, null);
        TextView isisenyap = (TextView) v.findViewById(android.R.id.text1);

        isisenyap.setText(arrayisi.get(position).getNama());

        return v;

    }

    public void filter(String charText) {

        charText = charText.toLowerCase(Locale.getDefault());
        arrayisi.clear();

        if (charText.length() == 0) {
            arrayisi.addAll(listisi);

        }
        else {

            for (DoaClassData data : listisi) {

                if (data.getNama().toLowerCase(Locale.getDefault()).contains(charText)) {

                    arrayisi.add(data);
                }
            }
        }

        notifyDataSetChanged();
    }
}
