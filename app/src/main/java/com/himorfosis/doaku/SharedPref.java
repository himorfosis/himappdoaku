package com.himorfosis.doaku;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by him on 6/6/2018.
 */

public class SharedPref {

    public static final String PREFS_NAME = "PRODUCT_APP";
    public static final String FAVORITES = "Product_Favorite";
    public static final String RIWAYAT = "Product_Riwayat";

    public SharedPref() {
        super();
    }

    // This four methods are used for maintaining favorites.

    public void saveFavorites(Context context, List<DoaClassFix> favorites) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
        editor = settings.edit();

        Gson gson = new Gson();
        String jsonFavorites = gson.toJson(favorites);

        editor.putString(FAVORITES, jsonFavorites);

        editor.commit();
    }

    public void addFavorite(Context context, DoaClassFix product) {
        List<DoaClassFix> favorites = getFavorites(context);
        if (favorites == null)
            favorites = new ArrayList<DoaClassFix>();
        favorites.add(product);
        saveFavorites(context, favorites);
    }

    public void removeFavorite(Context context, DoaClassFix product) {
        ArrayList<DoaClassFix> favorites = getFavorites(context);
        if (favorites != null) {
            favorites.remove(product);
            saveFavorites(context, favorites);
        }
    }

    public ArrayList<DoaClassFix> getFavorites(Context context) {
        SharedPreferences settings;
        List<DoaClassFix> favorites;

        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        if (settings.contains(FAVORITES)) {
            String jsonFavorites = settings.getString(FAVORITES, null);
            Gson gson = new Gson();
            DoaClassFix[] favoriteItems = gson.fromJson(jsonFavorites,
                    DoaClassFix[].class);

            favorites = Arrays.asList(favoriteItems);
            favorites = new ArrayList<DoaClassFix>(favorites);
        } else
            return null;

        return (ArrayList<DoaClassFix>) favorites;
    }


    // ============================ FAVORITES STREAM ==========================

    public void saveFavoritStream(Context context, List<DoaClassData> favorites) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
        editor = settings.edit();

        Gson gson = new Gson();
        String jsonFavorites = gson.toJson(favorites);

        editor.putString(FAVORITES, jsonFavorites);

        editor.commit();
    }

    public void addFavoritStream(Context context, DoaClassData product) {
        List<DoaClassData> favorites = getFavoritesStream(context);
        if (favorites == null)
            favorites = new ArrayList<DoaClassData>();
        favorites.add(product);
        saveFavoritStream(context, favorites);
    }

    public void removeFavoritStream (Context context, DoaClassData product) {
        ArrayList<DoaClassData> favorites = getFavoritesStream(context);
        if (favorites != null) {
            favorites.remove(product);
            saveFavoritStream(context, favorites);
        }
    }

    public ArrayList<DoaClassData> getFavoritesStream (Context context) {
        SharedPreferences settings;
        List<DoaClassData> favorites;

        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        if (settings.contains(FAVORITES)) {
            String jsonFavorites = settings.getString(FAVORITES, null);
            Gson gson = new Gson();
            DoaClassData[] favoriteItems = gson.fromJson(jsonFavorites,
                    DoaClassData[].class);

            favorites = Arrays.asList(favoriteItems);
            favorites = new ArrayList<DoaClassData>(favorites);
        } else
            return null;

        return (ArrayList<DoaClassData>) favorites;
    }


    // ========================= R i w a y a t ================


    public void saveRiwayat(Context context, List<String> riwayat) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();

        Gson gson = new Gson();
        String jsonRiwayat = gson.toJson(riwayat);

        editor.putString(RIWAYAT, jsonRiwayat);

        editor.commit();
    }

    public void addRiwayat(Context context, String product) {
        List<String> riwayat = getRiwayat(context);
        if (riwayat == null)
            riwayat = new ArrayList<String>();
        riwayat.add(product);
        saveRiwayat(context, riwayat);
    }

    public void removeRiwayat(Context context, String product) {
        ArrayList<String> riwayat = getRiwayat(context);
        if (riwayat != null) {
            riwayat.remove(product);
            saveRiwayat(context, riwayat);
        }
    }

    public ArrayList<String> getRiwayat(Context context) {
        SharedPreferences settings;
        List<String> riwayat;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);

        if (settings.contains(RIWAYAT)) {
            String jsonRiwayat = settings.getString(RIWAYAT, null);
            Gson gson = new Gson();
            String[] favoriteItems = gson.fromJson(jsonRiwayat,
                    String[].class);

            riwayat = Arrays.asList(favoriteItems);
            riwayat = new ArrayList<String>(riwayat);
        } else
            return null;

        return (ArrayList<String>) riwayat;
    }

}
