package com.himorfosis.doaku;

/**
 * Created by him on 5/23/2018.
 */

public class DoaClassFix {

    private int id;
    private String namadoa;
    private String ayat;
    private String terjemahan;
    private Integer play;


    DoaClassFix (int id, String namadoa, String ayat, String terjemahan, Integer playdoa) {

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

    public Integer getPlayDoa() {

        return  play;
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

        DoaClassFix doa = (DoaClassFix) obj;

        if (id != doa.id)
            return  false;

        return true;

    }

}
