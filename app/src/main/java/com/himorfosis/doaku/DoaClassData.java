package com.himorfosis.doaku;

/**
 * Created by him on 6/27/2018.
 */

public class DoaClassData {

    private int id;
    private String namadoa;
    private String ayat;
    private String terjemahan;
    private String play;


    DoaClassData (int id, String namadoa, String ayat, String terjemahan, String playdoa) {

        super();
        this.id = id;
        this.namadoa = namadoa;
        this.ayat = ayat;
        this.terjemahan = terjemahan;
        this.play = playdoa;
    }

    public int getId() {
        return  id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public String getNama() {

        return namadoa;
    }

    public String getAyat() {
        return ayat;
    }

    public String getTerjemahan() {
        return terjemahan;
    }

    public String getPlayDoa() {

        return play;
    }

    public void setNama(String nama) {

        this.namadoa = nama;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return  true;

        if (obj == null)
            return  false;

        if(getClass() != obj.getClass())
            return false;

        DoaClassData doa = (DoaClassData) obj;

        if (id != doa.id)
            return  false;

        return true;

    }

}

