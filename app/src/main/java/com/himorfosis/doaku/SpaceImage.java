package com.himorfosis.doaku;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by him on 6/8/2018.
 */

public class SpaceImage implements Parcelable {

    private String mUrl;
    private String mTitle;

    public SpaceImage(String url, String title) {
        mUrl = url;
        mTitle = title;
    }

    protected SpaceImage(Parcel in) {
        mUrl = in.readString();
        mTitle = in.readString();
    }

    public static final Creator<SpaceImage> CREATOR = new Creator<SpaceImage>() {
        @Override
        public SpaceImage createFromParcel(Parcel in) {
            return new SpaceImage(in);
        }

        @Override
        public SpaceImage[] newArray(int size) {
            return new SpaceImage[size];
        }
    };

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public static  SpaceImage[] getSpaceImage() {

        return new SpaceImage[]{
                new SpaceImage("http://indonesiaone.org/wp-content/uploads/2017/06/Masjid-Bernama-Mary-Mother-of-Jesus-ada-di-Abu-Dhabi.jpg", "Masjid 1"),
                new SpaceImage("http://www.tentik.com/wp-content/uploads/2016/07/masjidterindahindo7.jpg", "Masjid 2"),
                new SpaceImage("http://islamislife.org/wp-content/uploads/2010/01/hajj-panorama-kaba.jpg", "Kabbah"),

        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mUrl);
        parcel.writeString(mTitle);
    }
}
