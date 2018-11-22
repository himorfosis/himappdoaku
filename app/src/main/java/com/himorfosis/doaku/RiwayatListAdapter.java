package com.himorfosis.doaku;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by him on 6/7/2018.
 */

public class RiwayatListAdapter extends BaseAdapter {

    Context context;
    ArrayList<String> arraydoa;
    List<String> listfix;
    LayoutInflater inflater;
    SharedPref sharedPref;

    int pilih = 0;

    public RiwayatListAdapter(Context context, List<String> object ) {

        this.context = context;
        this.listfix = object;
        inflater = LayoutInflater.from(context);
        this.arraydoa = new ArrayList<String>();
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
//        return  3;
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

        final RiwayatListAdapter.ViewHolder holder;

        if (convertView == null) {

            holder = new RiwayatListAdapter.ViewHolder();
            convertView = inflater.inflate(R.layout.rowdoa, null);

            holder.nama = convertView.findViewById(R.id.doa);
            holder.fav = convertView.findViewById(R.id.favorit);

            Log.e("pos", "" + position);

//            holder.fav.getContext();

//            holder.fav.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    String tag = holder.fav.getTag().toString();
//
//                    if (tag.equalsIgnoreCase("grey")) {
//
//                        holder.fav.setTag("red");
//                        holder.fav.setImageResource(R.drawable.favchoose);
//
//                    } else {
//
//                        holder.fav.setTag("grey");
//                        holder.fav.setImageResource(R.drawable.favorit);
//
//                    }
//
//                }
//            });

            convertView.setTag(holder);

        }

        else {
//
            holder = (RiwayatListAdapter.ViewHolder) convertView.getTag();
//        }
//
//            DoaClassFix doa = (DoaClassFix) getItem(position);
//
            holder.nama.setText(listfix.get(position));
//
//            if (checkFavItem(doa)) {
//
//                holder.fav.setImageResource(R.drawable.favchoose);
//                holder.fav.setTag("red");
//            } else {
//
//                holder.fav.setImageResource(R.drawable.favorit);
//                holder.fav.setTag("grey");
            }

            return convertView;

        }



    public boolean checkFavItem (DoaClassFix checkDoa) {

        boolean check = false;
        List<DoaClassFix> fav = sharedPref.getFavorites(context);

        if(fav != null) {
            for(DoaClassFix doaClassData : fav) {
                if (doaClassData.equals(checkDoa)) {
                    check = true;
                    break;
                }

            }
        }

        return  check;
    }


//    public void filter(String charText) {
//
//        charText = charText.toLowerCase(Locale.getDefault());
//        listfix.clear();
//
//        if (charText.length() == 0) {
//            listfix.addAll(arraydoa);
//
//        }
//        else {
//
//            for (DoaClassFix data : arraydoa) {
//
//                if (data.getNama().toLowerCase(Locale.getDefault()).contains(charText)) {
//
//                    listfix.add(data);
//                }
//            }
//        }
//
//        notifyDataSetChanged();
//    }
}
