package com.himorfosis.doaku;

import java.util.ArrayList;

/**
 * Created by him on 5/24/2018.
 */

public class ListDoa {

    public static ArrayList<DoaClassFix> listdoa() {

        ArrayList<DoaClassFix> listdoa = new ArrayList<>();

        listdoa.add(new DoaClassFix(1, IsiDoa.Alfalaq, IsiDoa.AyatAlfalaq, IsiDoa.TerjAlfalaq, R.raw.al_falaq));
        listdoa.add(new DoaClassFix(2, IsiDoa.AlIkhlash, IsiDoa.AyatAlikhlash, IsiDoa.TerjAlIkhlash, R.raw.al_ikhlas));
        listdoa.add(new DoaClassFix(3, IsiDoa.AnNaas, IsiDoa.AyatAnNass, IsiDoa.TerjAnNass, R.raw.an_naas));
//        listdoa.add(new DoaClassFix(3, IsiDoa.AnNaas, IsiDoa.AyatAnNass, IsiDoa.TerjAnNass, R.raw.an_naas));

        return listdoa;
    }

    public static ArrayList<DoaClassData> liststreaming() {


        ArrayList<DoaClassData> liststrem = new ArrayList<>();

//        liststrem.add(new DoaClassData(1, IsiDoa.Alfalaq, IsiDoa.AyatAlfalaq, IsiDoa.TerjAlfalaq, "http://192.168.1.60/audio/Doa%20Agar%20Allah%20Menurunkan%20AdzabNya%20Kepada%20Kaum%20Fakir.mp3"));
//        liststrem.add(new DoaClassData(2, IsiDoa.AlIkhlash, IsiDoa.AyatAlikhlash, IsiDoa.TerjAlIkhlash, "http://192.168.1.60/audio/Doa%20Agar%20Diri%20dan%20Keluarga%20Menjadi%20Orang=orang%20Yang%20Tunduk%20Patuh%20Kepada%20Allah.mp3"));
//        liststrem.add(new DoaClassData(3, IsiDoa.AnNaas, IsiDoa.AyatAnNass, IsiDoa.TerjAnNass, "http://192.168.1.60/audio/Doa%20Agar%20Terbebas%20Dari%20Kesukaran%20Yang%20Berat.mp3"));

        // 1 - 10
        liststrem.add(new DoaClassData(1, "Doa Agar Allah Menurunkan AdzabNya Kepada Kaum Fakir", IsiDoa.AyatAlfalaq, IsiDoa.TerjAlfalaq, "http://192.168.1.36/audio/Doa%20Agar%20Allah%20Menurunkan%20AdzabNya%20Kepada%20Kaum%20Fakir.mp3"));
        liststrem.add(new DoaClassData(2, "Doa Agar Diri dan Keluarga Menjadi Orang 0rang Yang Tunduk Patuh Kepada Allah", IsiDoa.AyatAlikhlash, IsiDoa.TerjAlIkhlash, "http://192.168.1.36/audio/Doa%20Agar%20Diri%20dan%20Keluarga%20Menjadi%20Orang=orang%20Yang%20Tunduk%20Patuh%20Kepada%20Allah.mp3"));
        liststrem.add(new DoaClassData(3, "Doa Agar Terbebas Dari Kesukaran Yang Berat", IsiDoa.AyatAnNass, IsiDoa.TerjAnNass, "http://192.168.1.36/audio/Doa%20Agar%20Terbebas%20Dari%20Kesukaran%20Yang%20Berat.mp3"));

        liststrem.add(new DoaClassData(4, "Doa Disaat Melihat Keajaiban Alam", "", "", ""));
        liststrem.add(new DoaClassData(5, "Doa Ketika Naik Kendaraan", "", "", ""));
        liststrem.add(new DoaClassData(6, "Doa Malikat Untuk Orang Ynag Beriman", "", "", ""));
        liststrem.add(new DoaClassData(7, "Doa Memohon Ampun dan Tetap Teguh Dalam Perjuangan", "", "", ""));
        liststrem.add(new DoaClassData(8, "Doa Memohon Ampun dan Tetap Teguh Dalam Perjuangan", "", "", ""));
        liststrem.add(new DoaClassData(9, "Doa Memohon Daerah Lingkungan Yang Baik", "", "", ""));
        liststrem.add(new DoaClassData(10, "Doa Memohon Dihindarkan Dari Neraka", "", "", ""));

        // 11 - 20

        liststrem.add(new DoaClassData(11, "Doa Mohon Kebaikan", "", "", ""));
        liststrem.add(new DoaClassData(12, "Doa Muslim Terbaik", "", "", ""));
        liststrem.add(new DoaClassData(13, "Doa Pengekuh Keyakinan", "", "", ""));
        liststrem.add(new DoaClassData(14, "Doa Penghuni Surga", "", "", ""));
        liststrem.add(new DoaClassData(15, "Doa Penyesalan", "", "", ""));
        liststrem.add(new DoaClassData(16, "Doa Syukur Atas Karunia Allah", "", "", ""));
        liststrem.add(new DoaClassData(17, "Doa Syukur Atas Nikmnat Allah", "", "", ""));
        liststrem.add(new DoaClassData(18, "Doa Taubat dan Mohon Ampun Setelah Menghadapi Kegagalan Berdakwah", "", "", ""));
        liststrem.add(new DoaClassData(19, "Doa Untuk Kedua Orang Tua", "", "", ""));
        liststrem.add(new DoaClassData(20, "Doa Mohon Kesempurnaan Nur Iman Dan Ampunan", "", "", ""));

        // 21 - 30

        liststrem.add(new DoaClassData(21, "Doa Mohon Agar Amal Diterima Allah", "", "", ""));
        liststrem.add(new DoaClassData(22, "Doa Minta Ampunan", "", "", ""));
        liststrem.add(new DoaClassData(23, "Doa Mensyukuri NIkmat Allah Dan Dapat Berbuat Kebaikan", "", "", ""));
        liststrem.add(new DoaClassData(24, "Doa Memohon Rizki Yang Baik", "", "", ""));
        liststrem.add(new DoaClassData(25, "Doa Memohon Pengetahuan dan Ampunan Allah", "", "", ""));
        liststrem.add(new DoaClassData(26, "Doa Memohon Ketetapan Dalam Islam", "", "", ""));
        liststrem.add(new DoaClassData(27, "Doa Memohon Keselamatan", "", "", ""));
        liststrem.add(new DoaClassData(28, "Doa Memohon Keputusan Keadilan Allah", "", "", ""));
        liststrem.add(new DoaClassData(29, "Doa Memohon Dipisahkan Dari Kaum Fasik", "", "", ""));
        liststrem.add(new DoaClassData(30, "Doa Memohon Dijauhkan Dari Neraka", "", "", ""));

        // 31 - 40

        liststrem.add(new DoaClassData(31, "Doa Mohon Anak Yang Saleh", "", "", ""));
        liststrem.add(new DoaClassData(32, "Doa Mohon Diberi HIkmah Dan Dimasukkan Dalam Golongan Orang-Orang", "", "", ""));
        liststrem.add(new DoaClassData(33, "Doa Mohon Diberi Keluarga Yang Diberkahi Allah", "", "", ""));
        liststrem.add(new DoaClassData(34, "Doa Mohon Diberi Kemudahan Untuk Dapat Beribadah", "", "", ""));
        liststrem.add(new DoaClassData(35, "Doa Mohon Dihindarkan Dari Kesesatan", "", "", ""));
        liststrem.add(new DoaClassData(36, "Doa mohon dimasukkan dalam golongan kaum muslimin", "", "", ""));
        liststrem.add(new DoaClassData(37, "Doa Mohon Keadilan Allah", "", "", ""));
        liststrem.add(new DoaClassData(38, "Doa Mohon Kebinasaan Orang Zalim", "", "", ""));
        liststrem.add(new DoaClassData(39, "Doa Mohon Kekuatan Iman", "", "", ""));
        liststrem.add(new DoaClassData(40, "Doa Mohon Kelapangan Hati", "", "", ""));

        // 41 - 50

        liststrem.add(new DoaClassData(41, "Doa Mohon Kemuliaan", "", "", ""));
        liststrem.add(new DoaClassData(42, "Doa Mohon Keridhaan Allah", "", "", ""));
        liststrem.add(new DoaClassData(43, "Doa Mohon Kesabaran", "", "", ""));
        liststrem.add(new DoaClassData(44, "Doa Mohon Keselamatan Dalam Perjalanan", "", "", ""));
        liststrem.add(new DoaClassData(45, "Doa Mohon Keselamatan Diri Dan Keluarga", "", "", ""));
        liststrem.add(new DoaClassData(46, "Doa Mohon Keselamatan", "", "", ""));
        liststrem.add(new DoaClassData(47, "Doa Mohon Kesembuhan", "", "", ""));
        liststrem.add(new DoaClassData(48, "Doa Mohon Ketabahan Disaat Menghadapi Musuh", "", "", ""));
        liststrem.add(new DoaClassData(49, "Doa Mohon Lindungan Allah Dari Godaan Setan", "", "", ""));
        liststrem.add(new DoaClassData(50, "Doa Mohon Negri Aman Sentosa", "", "", ""));

        // 51 - 60

        liststrem.add(new DoaClassData(51, "Doa Mohon Negri Yang Aman Dan Menjadi Orang Yang Ikhlas MenyembahNya", "", "", ""));
        liststrem.add(new DoaClassData(52, "Doa Mohon Perlindungan Dari Segala Kejahatan", "", "", ""));
        liststrem.add(new DoaClassData(53, "Doa Mohon Petunjuk Agar Memperoleh Jalan Ynag Lurus", "", "", ""));
        liststrem.add(new DoaClassData(54, "Doa mohon Rumah Indah Dalam Syurga", "", "", ""));
        liststrem.add(new DoaClassData(55, "Doa Mohon Tambahan Ilmu Pengetahuan", "", "", ""));
        liststrem.add(new DoaClassData(56, "Doa Mohon Tempat Mulia", "", "", ""));
        liststrem.add(new DoaClassData(57, "Doa Mohon Tempat Yang Baik", "", "", ""));
        liststrem.add(new DoaClassData(58, "Doa Mohon Terhindar Dari Kedengkian", "", "", ""));
        liststrem.add(new DoaClassData(59, "Doa Mohon Tidak Hidup Bersama Golongan Orang Zalim", "", "", ""));
        liststrem.add(new DoaClassData(60, "Doa Mohon Kebaikan", "", "", ""));

        // 60 - 70

        liststrem.add(new DoaClassData(61, "Doa Muslim Terbaik", "", "", ""));
        liststrem.add(new DoaClassData(62, "Doa Pengekuh Keyakinan", "", "", ""));
        liststrem.add(new DoaClassData(63, "Doa Penghuni Surga", "", "", ""));
        liststrem.add(new DoaClassData(64, "Doa Penyesalan", "", "", ""));
        liststrem.add(new DoaClassData(65, "Doa Syukur Atas Karunia Allah", "", "", ""));
        liststrem.add(new DoaClassData(66, "Doa Syukur Atas Nikmnat Allah", "", "", ""));
        liststrem.add(new DoaClassData(67, "Doa Taubat dan Mohon Ampun Setelah Menghadapi Kegagalan Berdakwah", "", "", ""));
        liststrem.add(new DoaClassData(68, "Doa Tawakal, Taubat dan Mohon Terhindar Dari Fitnah", "", "", ""));
        liststrem.add(new DoaClassData(69, "Doa Untuk Kedua Orang Tua", "", "", ""));
        liststrem.add(new DoaClassData(70, "DoaMohon Kesempurnaan Nur Iman Dan Ampunan", "", "", ""));

        return liststrem;
    }



//    public static ArrayList<DoaClassData> listdoa() {
//
//        ArrayList<DoaClassData> listdoa = new ArrayList<>();
//
//        listdoa.add(new DoaClassData(1, "Nisa sabyan", "Ya Habibal Qolbi", "Ya Habibal Qolbi","http://programmerguru.com/android-tutorial/wp-content/uploads/2013/04/hosannatelugu.mp3"));
//        listdoa.add(new DoaClassData(2, "Si Bad", "Lagi Syantik", "Lagi Syantik", "https://s01.solidfilesusercontent.com/MmY3MzAxMWIwMTNjM2JmZmRlMGM5OGE2NzZmMmIzODczNjM0OTY4YToxZllPblY6czE4N3lwd1hORFB2Qld4QXp6cEVCY1ptOGEw/eRrvaW3v3MzXL/Doa_Agar_Diri_dan_Keluarga_Menjadi_Orang%3Dorang_Yang_Tunduk_Patuh_Kepada_Allah.mp3"));
////        listdoa.add(new DoaClassData(3, IsiDoa.AnNaas, IsiDoa.AyatAnNass, IsiDoa.TerjAnNass, R.raw.an_naas));
////        listdoa.add(new DoaClassData(3, IsiDoa.AnNaas, IsiDoa.AyatAnNass, IsiDoa.TerjAnNass, R.raw.an_naas));
//
//        return listdoa;
//    }

}
