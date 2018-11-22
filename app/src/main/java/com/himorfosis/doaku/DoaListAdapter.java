package com.himorfosis.doaku;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.himorfosis.doaku.databinding.RowdoaBinding;

import java.io.FilterReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


/**
 * Created by him on 6/4/2018.
 */

public class DoaListAdapter extends BaseAdapter {

    Context context;
    ArrayList<DoaClassData> arraydoa;
    List<DoaClassData> listfix;
    LayoutInflater inflater;
    SharedPref sharedPref;

    public DoaListAdapter(Context context, List<DoaClassData> object ) {

        this.context = context;
        this.listfix = object;
        inflater = LayoutInflater.from(context);
        this.arraydoa = new ArrayList<DoaClassData>();
        this.arraydoa.addAll(listfix);
        sharedPref = new SharedPref();

    }

    public class ViewHolder {
        TextView nama;
        ImageView fav;

    }

    @Override
    public  int getCount() {
        return  listfix.size();
    }

    @Override
    public Object getItem(int i) {
        return listfix.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView (final int position, View convertView, ViewGroup parent) {

        final ViewHolder holder;

        if (convertView == null) {

            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.rowdoa, null);

            holder.nama = convertView.findViewById(R.id.doa);
            holder.fav = convertView.findViewById(R.id.favorit);

            Log.e("pos", ""+position);

            holder.fav.getContext();

            holder.fav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String tag = holder.fav.getTag().toString();

                    if(tag.equalsIgnoreCase("grey")) {

                        sharedPref.addFavoritStream(context, listfix.get(position));

                        Utilities.ShowLog("pos fav ", ""+listfix.get(position));

                        holder.fav.setTag("red");
                        holder.fav.setImageResource(R.drawable.favchoose);

                        Log.e("simpan fav", "" +listfix.get(position));

                    } else {

                        sharedPref.removeFavoritStream(context, listfix.get(position));

                        holder.fav.setTag("grey");
                        holder.fav.setImageResource(R.drawable.favorit);

                        Log.e("hapus fav", "" +listfix.get(position));


                    }

                }
            });

            convertView.setTag(holder);

        }

        else {

            holder = (ViewHolder) convertView.getTag();
        }

        DoaClassData doa = (DoaClassData) getItem(position);

        holder.nama.setText(listfix.get(position).getNama());

        if (checkFavItem(doa)) {

            holder.fav.setImageResource(R.drawable.favchoose);
            holder.fav.setTag("red");
        } else {

            holder.fav.setImageResource(R.drawable.favorit);
            holder.fav.setTag("grey");
        }

        return convertView;

    }

    public boolean checkFavItem (DoaClassData checkDoa) {

        boolean check = false;
        List<DoaClassData> fav = sharedPref.getFavoritesStream(context);

        if(fav != null) {
            for(DoaClassData doaClassData : fav) {
                if (doaClassData.equals(checkDoa)) {
                    check = true;
                    break;
                }

            }
        }

        return  check;
    }


    public void filter(String charText) {

        charText = charText.toLowerCase(Locale.getDefault());
        listfix.clear();

        if (charText.length() == 0) {
            listfix.addAll(arraydoa);

        }
        else {

            for (DoaClassData data : arraydoa) {

                if (data.getNama().toLowerCase(Locale.getDefault()).contains(charText)) {

                    listfix.add(data);
                }
            }
        }

        notifyDataSetChanged();
    }


}