package com.himorfosis.doaku;

/**
 * Created by him on 7/4/2018.
 */

public class JadwalClassData {

    public static final String TABLE_NAME = "tablejadwal";
    public static final String COLUMN_ID = "id_jadwal";
    public static final String COLUMN_KEGIATAN = "kegiatan";
    public static final String COLUMN_DOA= "doa";
    public static final String COLUMN_WAKTU = "waktu";
    public static final String COLUMN_TEMPAT = "tempat";
//    public static final String COLUMN_PENGINGAT = "pengingat";

    private Integer id_jadwal;
    private String kegiatan;
    private String doa;
    private String waktu;
//    private String tempat;
//    private String pengingat;


//    public JadwalClassData(String pengingat) {
//        this.pengingat = pengingat;
//    }

    // Create table SQL query
//    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
//                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
//                    + COLUMN_KEGIATAN + " TEXT,"
//                    + COLUMN_DOA + " TEXT,"
//                    + COLUMN_WAKTU + " DATETIME DEFAULT CURRENT_TIMESTAMP"
//                    + COLUMN_TEMPAT + " TEXT,"
//                    + COLUMN_PENGINGAT + " TEXT,"
//                    + ")";

    public JadwalClassData (Integer id_jadwal, String kegiatan, String doa, String waktu ) {

        this.id_jadwal = id_jadwal;
        this.kegiatan = kegiatan;
        this.doa = doa;
        this.waktu = waktu;
//        this.tempat = tempat;
//        this.pengingat = pengingat;
    }

    public Integer getId_jadwal() {
        return id_jadwal ;  }

    public void setId_jadwal(Integer id_jadwal) {
        this.id_jadwal = id_jadwal;  }

    public String getkegiatan() {
        return kegiatan ;  }

    public void setkegiatan(String kegiatan) {
        this.kegiatan = kegiatan;  }

    public String getdoa() {
        return doa ;  }

    public void setdoa(String doa) {
        this.doa = doa;  }


    public String getwaktu() {
        return waktu ;  }

    public void setwaktu(String waktu) {
        this.waktu = waktu; }

//    public String gettempat() {
//        return tempat ;  }
//
//    public void settempat(String tempat) {
//        this.tempat = tempat; }

//    public String getpengingat() {
//        return tempat ;  }
//
//    public void setpengingat(String pengingat) {
//        this.pengingat = pengingat; }

}
