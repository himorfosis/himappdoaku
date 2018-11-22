package com.himorfosis.doaku;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);

        new Handler().postDelayed(new Thread() {
            @Override
            public void run() {
                Intent mainMenu = new Intent(SplashScreen.this, Utama.class);
                SplashScreen.this.startActivity(mainMenu);
                SplashScreen.this.finish();

            }

        }, 500);
    }
}
