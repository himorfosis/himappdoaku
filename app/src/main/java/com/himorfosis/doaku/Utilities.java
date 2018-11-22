package com.himorfosis.doaku;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.TabLayout;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Utilities {

    private final String STORAGE = "com.himorfosis.doaku.raw";
    public static final String NAME = "DoaKu";
    public static final String FAV = "Fav";

    public static void saveIntPref(String DBNAME, String Tablekey, int value , Context context){

        SharedPreferences settings;
        SharedPreferences.Editor editor;
        settings = context.getSharedPreferences(DBNAME, Context.MODE_PRIVATE);
        editor = settings.edit();
        editor.putInt(Tablekey, value);
        editor.commit();

    }

    public static int getIntPref (String DBNAME, String Tablekey, Context context){

        SharedPreferences settings;

        settings = context.getSharedPreferences(DBNAME, Context.MODE_PRIVATE);
        int value = settings.getInt(Tablekey, 2);

        return value;

    }

    public static void putPrefName (String DBNAME, String Tablekey, String value, Context context){

        SharedPreferences settings;
        SharedPreferences.Editor editor;
        settings = context.getSharedPreferences(DBNAME, Context.MODE_PRIVATE);
        editor = settings.edit();
        editor.putString(Tablekey, value);
        editor.commit();
    }

    public void putPrefArray (String DBNAME, ArrayList<DoaClassFix> arrayList, Context context) {

        SharedPreferences preferences = context.getSharedPreferences(DBNAME, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(arrayList);
        editor.putString("listdoa", json);
        editor.apply();

    }

    public static String getValueName (String DBNAME, String Tablekey, Context context){

        SharedPreferences settings;
        String text;
        settings = context.getSharedPreferences(DBNAME, Context.MODE_PRIVATE);
        text = settings.getString(Tablekey, null);
        return text;
    }


    public static void clearSharedPreferance (String DBNAME, Context context){

        SharedPreferences settings;
        SharedPreferences.Editor editor;
        settings = context.getSharedPreferences(DBNAME, Context.MODE_PRIVATE);
        editor = settings.edit();
        editor.clear();
        editor.commit();
    }


    public static void ShowLog(String message, String value) {
        Log.e("", message + " : " + value);
    }

    public static void ShowToast(Activity activity, String message) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();

    }

    public static void ShowToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();

    }

//    public static boolean cek_status(Activity cek) {
//        ConnectivityManager cm = (ConnectivityManager) cek.getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo info = cm.getActiveNetworkInfo();
//        if (info != null && info.isConnected()) {
//            return true;
//        } else {
//            ShowToast(cek, "Please check your network connection!");
//            return false;
//        }
//    }

//    public static boolean network_status(Activity cek) {
//        ConnectivityManager cm = (ConnectivityManager) cek.getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo info = cm.getActiveNetworkInfo();
//        if (info != null && info.isConnected()) {
//            return true;
//        } else {
//            //ShowToast(cek,"Please check your network connection!");
//            return false;
//        }
//    }
}

