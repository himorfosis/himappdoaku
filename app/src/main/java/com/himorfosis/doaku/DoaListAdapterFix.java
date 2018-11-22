package com.himorfosis.doaku;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.himorfosis.doaku.databinding.DaftardoaBinding;

/**
 * Created by him on 5/23/2018.
 */

public class DoaListAdapterFix extends  BaseAdapter {

    Context context;
    ArrayList<DoaClassFix> arraydoa;
    List<DoaClassFix> listfix;
    LayoutInflater inflater;
    SharedPref sharedPref;

    public DoaListAdapterFix(Context context, List<DoaClassFix> object ) {

        this.context = context;
        this.listfix = object;
        inflater = LayoutInflater.from(context);
        this.arraydoa = new ArrayList<DoaClassFix>();
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

                        sharedPref.addFavorite(context, listfix.get(position));

                        Utilities.ShowLog("pos fav ", ""+listfix.get(position));

                        holder.fav.setTag("red");
                        holder.fav.setImageResource(R.drawable.favchoose);

                        Log.e("simpan fav", "" +listfix.get(position));

                    } else {

                        sharedPref.removeFavorite(context, listfix.get(position));

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

        DoaClassFix doa = (DoaClassFix) getItem(position);

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

    public boolean checkFavItem (DoaClassFix checkDoa) {

        boolean check = false;
        List<DoaClassFix> fav = sharedPref.getFavorites(context);

        if(fav != null) {
            for(DoaClassFix doaClassFix : fav) {
                if (doaClassFix.equals(checkDoa)) {
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

            for (DoaClassFix data : arraydoa) {

                if (data.getNama().toLowerCase(Locale.getDefault()).contains(charText)) {

                    listfix.add(data);
                }
            }
        }

        notifyDataSetChanged();
    }


}
