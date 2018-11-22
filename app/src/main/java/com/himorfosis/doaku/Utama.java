package com.himorfosis.doaku;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.util.Linkify;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class Utama extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.utama);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        displaySelectedScreen(R.id.beranda);

    }

    private void displaySelectedScreen(int itemId) {

        fragment = null;

        switch (itemId) {

            case R.id.beranda:
                beranda();
                break;

            case R.id.pembaruan:
                pembaruan();
                break;

            case R.id.rating :
                rating();
                break;

            case R.id.aplikasilain :
                aplikasilain();
                break;

            case R.id.bagikan :
                bagikan();
                break;

            case R.id.informasi :
                tentangaplikasi();
                break;

        }

        if (fragment == null) {
            fragment = new UtamaTabHome() ;
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frame, fragment);
            ft.commit();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        displaySelectedScreen(item.getItemId());

        return true;
    }

    boolean doubleBackToExitPressedOnce = false;


    public void beranda() {

        fragment = new UtamaTabHome() ;
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame, fragment);
        ft.commit();
    }

    public void pembaruan() {

        Utilities.ShowToast(getApplicationContext(), "pembaruan");
    }



    public void rating() {

        Utilities.ShowToast(getApplicationContext(), "rating");

    }

    public void aplikasilain() {

        Utilities.ShowToast(getApplicationContext(), "aplikasi lain");

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://play.google.com/store/apps/dev?id=7269907156091290232"));
        startActivity(intent);

    }

    public void bagikan() {

        Utilities.ShowToast(getApplicationContext(), "bagikan");

        Intent intent = new Intent(Intent.ACTION_SEND_MULTIPLE);

        //memilih type file yang akan di kirim
        intent.setType("text/plain");

        intent.putExtra(Intent.EXTRA_SUBJECT, "Berbagi Doaku");
        intent.putExtra(Intent.EXTRA_TEXT, "Yuk berdoa bersama dengan aplikasi Doaku\n\n"+ "https://play.google.com/store/apps/dev?id=7269907156091290232");

        startActivity(Intent.createChooser(intent, "Bagikan"));

    }

    public void tentangaplikasi() {

        Utilities.ShowToast(getApplicationContext(), "tentang aplikasi");
        Intent intent = new Intent(getApplicationContext(), TentangAplikasi.class);
        startActivity(intent);

    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Press again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}
