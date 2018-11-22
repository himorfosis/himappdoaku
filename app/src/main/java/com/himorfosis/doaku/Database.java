package com.himorfosis.doaku;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by him on 5/13/2018.
 */

public class Database extends SQLiteOpenHelper {

    private static final String DatabaseName = "Doaku";
    private static final int DatabaseVersion = 1;

    public Database(Context context) {
        super(context, DatabaseName, null, DatabaseVersion);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE tabeljadwal ( id_jadwal INTEGER PRIMARY KEY AUTOINCREMENT, kegiatan TEXT NOT NULL, doa TEXT NOT NULL, waktu TEXT NOT NULL ); ");
    }

    @Override

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS tabeljadwal");

        onCreate(db);
    }

    public void insertjadwal(JadwalClassData classData) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("id_jadwal", classData.getId_jadwal());
        cv.put("kegiatan", classData.getkegiatan());
        cv.put("doa", classData.getdoa());
        cv.put("waktu", classData.getwaktu());
//        cv.put("tempat", classData.gettempat());
//        cv.put("pengingat", classData.getpengingat());

        db.insert("tabeljadwal", null,cv);
        db.close();

    }

    public void deletejadwal(String id) {

        SQLiteDatabase db = this.getWritableDatabase();
        String[] args = {id};
        ContentValues cv = new ContentValues();

        db.delete("tabeljadwal", "id_jadwal=?", args);
        db.close();
    }

    public List<JadwalClassData> getalljadwal() {
        List<JadwalClassData> dataArray = new ArrayList<JadwalClassData>();
        String query = "SELECT * FROM tabeljadwal";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                JadwalClassData datalist = new JadwalClassData(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
                dataArray.add(datalist);

            } while (cursor.moveToNext());
        }
        return dataArray;
    }


    public void updatejadwal(String id, String kegiatan, String doa, String waktu, String tempat) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] args={id};
        ContentValues cv = new ContentValues();
        cv.put("kegiatan", kegiatan);
        cv.put("doa", doa);
        cv.put("waktu", waktu);
        cv.put("tempat", tempat);
//        cv.put("pengingat", pengingat);

        db.update("tabeljadwal", cv, "id_jadwal=?", args);
        db.close();

    }

}
