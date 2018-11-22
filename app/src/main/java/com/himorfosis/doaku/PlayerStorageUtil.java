package com.himorfosis.doaku;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by him on 6/22/2018.
 */

public class PlayerStorageUtil {

    private final String STORAGE = " com.himorfosis.doaku.STORAGE";
    private SharedPreferences preferences;
    private Context context;

    public PlayerStorageUtil(Context context) {
        this.context = context;

    }

    public void storeDoa (ArrayList<DoaClassFix> arrayList) {

        preferences = context.getSharedPreferences(STORAGE, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(arrayList);
        editor.putString("doaArraylist", json);
        editor.apply();
    }

    public ArrayList<DoaClassFix> loadDoa() {

        preferences = context.getSharedPreferences(STORAGE, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = preferences.getString("doaArraylist", null);
        Type type = new TypeToken<ArrayList<DoaClassFix>>() {
        }.getType();
        return gson.fromJson(json, type);
    }

    public void storeDoaIndex(int index) {

        preferences = context.getSharedPreferences(STORAGE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("doaIndex", index);
        editor.apply();
    }

    public int loadDoaIndex() {

        preferences = context.getSharedPreferences(STORAGE, Context.MODE_PRIVATE);
        return preferences.getInt("doaIndex", -1);//return -1 if no data found
    }

    public void clearCachedAudioPlaylist() {
        preferences = context.getSharedPreferences(STORAGE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.commit();
    }
}
