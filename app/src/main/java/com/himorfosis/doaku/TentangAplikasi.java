package com.himorfosis.doaku;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.util.Linkify;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TentangAplikasi extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tentangaplikasi);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.toolbar);

        TextView judul = (TextView) getSupportActionBar().getCustomView().findViewById(R.id.toolbartext);
        judul.setText("Tentang Aplikasi");

        TextView tentangapp = (TextView) findViewById(R.id.tentangaplikasi);
        TextView email = (TextView) findViewById(R.id.email);
        TextView web = (TextView) findViewById(R.id.web);
        TextView fb = (TextView) findViewById(R.id.fb);
        TextView twit = (TextView) findViewById(R.id.twitter);
        TextView ig = (TextView) findViewById(R.id.ig);

        Button kembali = (Button)getSupportActionBar().getCustomView().findViewById(R.id.kembali);
        kembali.setVisibility(View.VISIBLE);

        tentangapp.setText("DoaKu adalah aplikasi pemutar doa doa dalam agama islam dengan terjemahan bahasa Indonesia.  " +
                "Aplikasi DoaKu cocok untuk orang yang belajar dan mengahafal doa. Aplikasi ini berjalan dengan online maupun offline. " +
                "Pengguna dapat menggunakan fitur offline dengan mendownload file audio terlebih dahulu. " +
                "Aplikasi Doaku sangat ringan dan cocok digunakan dalam jenis android miniman Jelly Bean.\n"
                );

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {

                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www/google.com")));
            }
        };

        email.setClickable(true);
        email.setText("Email : " + Html.fromHtml("cs@gohajj.id"));
        Linkify.addLinks(email, Linkify.EMAIL_ADDRESSES);

//        email.setMovementMethod(LinkMovementMethod.getInstance());


        web.setClickable(true);
        String weblink = "https://gohajj.id";
        web.setText("Website : " + weblink);
        Linkify.addLinks(web, Linkify.WEB_URLS);
//        web.setMovementMethod(LinkMovementMethod.getInstance());


        fb.setClickable(true);
        String fblink = "https://www.facebook.com/goHajjIndonesia";
        fb.setText("Facebook : " + fblink);
        Linkify.addLinks(fb, Linkify.WEB_URLS);
//        fb.setMovementMethod(LinkMovementMethod.getInstance());


        twit.setClickable(true);
        String twitlink = "https://twitter.com/gohajjindonesia";
        twit.setText("Twitter : " + twitlink);
        Linkify.addLinks(twit, Linkify.WEB_URLS);

//        twit.setMovementMethod(LinkMovementMethod.getInstance());


        ig.setClickable(true);
        String iglink = "https://www.instagram.com/gohajj/";
        ig.setText("Instagram : " + iglink);
        Linkify.addLinks(ig, Linkify.WEB_URLS);
//        ig.setMovementMethod(LinkMovementMethod.getInstance());


        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });
    }
}
