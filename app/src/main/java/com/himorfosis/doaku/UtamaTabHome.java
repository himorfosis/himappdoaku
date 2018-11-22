package com.himorfosis.doaku;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

/**
 * Created by him on 5/15/2018.
 */

public class UtamaTabHome extends Fragment {

    SliderLayout sliderLayout;
    AlertDialog alertDialog;
    HashMap<String, String> Hash_file_maps;

    private static ViewPager mPager;
    private static int currentPage = 0;
    //    private static final Integer[] imagedata= {R.drawable.doa, R.drawable.logodoaku, R.drawable.favchoose};
    private static final String[] textdata = {IsiDoa.TerjAlfalaq, IsiDoa.TerjAlIkhlash, IsiDoa.TerjAnNass};

    private ArrayList<String> imagearray = new ArrayList<String>();

    String imgvalue, textvalue;
    Integer posimg;

    TextView textshare;
    ImageView imgshare;

    String[] isigambar = {
            "http://indonesiaone.org/wp-content/uploads/2017/06/Masjid-Bernama-Mary-Mother-of-Jesus-ada-di-Abu-Dhabi.jpg",
            "http://www.tentik.com/wp-content/uploads/2016/07/masjidterindahindo7.jpg",
            "http://images.blog.reservasi.com/2016/09/Masjid-Kristal.jpg"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        return inflater.inflate(R.layout.utamatabhome, container, false);

    }

    public void onViewCreated(final View view, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onViewCreated(view, savedInstanceState);
        getActivity().invalidateOptionsMenu();

        Hash_file_maps = new HashMap<String, String>();

        CardView daftardoa = (CardView) view.findViewById(R.id.satu);
        CardView favorit = (CardView) view.findViewById(R.id.dua);
        CardView riwayat = (CardView) view.findViewById(R.id.tiga);
        CardView jadwal = (CardView) view.findViewById(R.id.empat);
        CardView bantuan = (CardView) view.findViewById(R.id.lima);
        CardView pengaturan = (CardView) view.findViewById(R.id.enam);
        CardView share = (CardView) view.findViewById(R.id.share);
        sliderLayout = (SliderLayout) view.findViewById(R.id.pager);

        HashMap<String, String> url_maps = new HashMap<String, String>();
        url_maps.put("Masjid", "http://indonesiaone.org/wp-content/uploads/2017/06/Masjid-Bernama-Mary-Mother-of-Jesus-ada-di-Abu-Dhabi.jpg");
        url_maps.put("Big Bang Theory", "http://www.tentik.com/wp-content/uploads/2016/07/masjidterindahindo7.jpg");
        url_maps.put("House of Cards", "http://images.blog.reservasi.com/2016/09/Masjid-Kristal.jpg");


//        init();

        daftardoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), DaftarDoaStream.class);

                startActivity(intent);

            }
        });

        favorit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), Favorit.class);

                startActivity(intent);
            }
        });

        riwayat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in = new Intent(getContext(), Riwayat.class);
                startActivity(in);
            }
        });

        jadwal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in = new Intent(getContext(), Jadwal.class);
                startActivity(in);
            }
        });

        bantuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), Bantuan.class);
                startActivity(intent);

            }
        });

        pengaturan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), Pengaturan.class);
                startActivity(intent);

            }
        });

// ======================== percobaan ===============

        for (int i = 0; i < isigambar.length; i++) {

            //bug ada di sekitar sini

            imagearray.add(isigambar[i]);

            TextSliderView textSliderView = new TextSliderView(getContext());

            textSliderView

                    .description(textdata[i])
                    .image(isigambar[i])
                    .setScaleType(BaseSliderView.ScaleType.Fit);

            sliderLayout.addSlider(textSliderView);

            Log.e("text slider", "" + textSliderView);

            final Handler handler = new Handler();
            final Runnable Update = new Runnable() {
                public void run() {

                    if (currentPage == isigambar.length) {
                        currentPage = 0;

                    }

                    sliderLayout.setCurrentPosition(currentPage++, true);
                    Log.e("current page loop", "" + currentPage);

                }

            };

//                Log.e("current page loop", "" +currentPage++);

            sliderLayout.setPresetTransformer(SliderLayout.Transformer.Accordion);
            sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
            sliderLayout.setCustomAnimation(new DescriptionAnimation());
//            sliderLayout.setDuration(4000);

            Timer swipeTimer = new Timer();
            swipeTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(Update);
                }
            }, 4000);

            Log.e("current page last", "" + currentPage);

        }



        // ================== batas percobaan ===============

//        Log.e("current page", "" +currentPage);
//        Log.e("current page", "" );
//
//        for(int i=0; i<isigambar.length; i++)
////        for(int i=0; i<spaceImage.length; i++)
//
//            imagearray.add( isigambar[i] );
//
//        mPager = (ViewPager) getActivity().findViewById(R.id.pager);
//        mPager.setAdapter(new ImageAdapter(getContext(), imagearray));
//        final CircleIndicator indicator = (CircleIndicator) getActivity().findViewById(R.id.indicator);
//        indicator.setViewPager(mPager);
//
//        Log.e("imagedata", "" +isigambar);
//        Log.e("image array", "" +imagearray);
//        Log.e("pager", ""+mPager);
//
//        // Auto start of viewpager
//        final Handler handler = new Handler();
//        final Runnable Update = new Runnable() {
//            public void run() {
//
//                if (currentPage == isigambar.length) {
//                    currentPage = 0;
//
////                    Log.e("current page 1", ""+currentPage);
//                }
//                mPager.setCurrentItem(currentPage++, true);
//
////                Log.e("current page 2", ""+currentPage);
//            }
//        };
//
//        Timer swipeTimer = new Timer();
//        swipeTimer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                handler.post(Update);
//            }
//        }, 4000, 4000);
//
//        Log.e("current page 3", ""+currentPage);

        // batas view slider

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LayoutInflater inflater = getActivity().getLayoutInflater();
                View dialogview = inflater.inflate(R.layout.shareinformasi, null);

                imgshare = (ImageView) dialogview.findViewById(R.id.sharegambar);
                textshare = (TextView) dialogview.findViewById(R.id.sharetext);
                Button btnshare = (Button) dialogview.findViewById(R.id.sharebtn);

                Log.e("current page klik", "" +currentPage);


                posisigambar();

                btnshare.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

//                        Utilities.ShowToast(getContext(), "berhasil di share");

                        Intent intent = new Intent(Intent.ACTION_SEND_MULTIPLE);

                        //memilih type file yang akan di kirim

                        int textvalue = currentPage - 1;
                        int gambarvalue = currentPage - 1;

                        intent.setType("text/plain");
                        intent.setType("text/plain");
                        String sharebody = textdata[textvalue];
                        String imagevalue = isigambar[gambarvalue];

                        Glide.with(getContext())
                                .load(imagevalue);

                        intent.putExtra(Intent.EXTRA_SUBJECT, "Berbagi Doaku");
                        intent.putExtra(Intent.EXTRA_TEXT, sharebody + "\n\n" + imagevalue);
                        startActivity(Intent.createChooser(intent, "Bagikan"));

                    }
                });

                AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());

                dialog.setView(dialogview);

                alertDialog = dialog.create();
                alertDialog.show();

            }
        });

    }

    private void posisigambar() {

        if (currentPage == 1) {

            valueCurrentPage();

//            Glide.with(getContext())
//                    .load(isigambar[0])
//                    .into(imgshare);
//
//            textshare.setText(textdata[0]);
//            textvalue =  textdata[0];

        } else if (currentPage == 2) {

            valueCurrentPage();

//            Glide.with(getContext())
//                    .load(isigambar[1])
//                    .into(imgshare);
//
//            textshare.setText(textdata[1]);
//            textvalue =  textdata[1];

        } else if (currentPage == 3) {

            valueCurrentPage();

//            Glide.with(getContext())
//                    .load(isigambar[2])
//                    .into(imgshare);
//
//            textshare.setText(textdata[2]);
//            textvalue =  textdata[2];

        }

    }

    private void valueCurrentPage () {

        int valuepage = currentPage -1;

        Glide.with(getContext())
                .load(isigambar[valuepage])
                .into(imgshare);

        textshare.setText(textdata[valuepage]);
        textvalue =  textdata[valuepage];
    }

}
