package com.himorfosis.doaku;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class Pengaturan extends AppCompatActivity {

    LinearLayout fontarabic, fontlatin;
    TextView isifontarab, isifontlatin;


    //selected font size
    AlertDialog alertDialog;
    RadioButton rb12, rb14, rb16, rb18, rb20, rb22, rb24;
    RadioGroup radioGroup;
    int selectedFont;

    String[] select={"12 px","14 px","16 px","18 px","20 px","22 px","24 px"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pengaturan);


        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.toolbar);

        TextView toolbartext = (TextView) getSupportActionBar().getCustomView().findViewById(R.id.toolbartext);

        Button kembali = (Button)getSupportActionBar().getCustomView().findViewById(R.id.kembali);
        kembali.setVisibility(View.VISIBLE);

        toolbartext.setText("Pengaturan");

        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });

        int ukuranfontlatin = Utilities.getIntPref("ukuranfontlatin", "font", getApplicationContext());
        int ukuranfontarab = Utilities.getIntPref("ukuranfont", "font", getApplicationContext());

        fontarabic = (LinearLayout) findViewById(R.id.fontarabic);
        fontlatin = (LinearLayout) findViewById(R.id.fontlatin);
        isifontarab = (TextView) findViewById(R.id.isifontarabic);
        isifontlatin = (TextView) findViewById(R.id.isifontlatin);

        for ( int i = 0; i < select.length; i++ ) {

            if (ukuranfontarab == i) {

                isifontarab.setText(select[i]);

            }

            if (ukuranfontlatin == i) {

                isifontlatin.setText(select[i]);

            }
        }

        fontarabic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ukuranfont();

            }
        });

        fontlatin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ukuranFontLatin();

            }
        });

    }

    private void ukuranfont() {

        final String[] select={"12 px","14 px","16 px","18 px","20 px","22 px","24 px"};

        int intCheck = Utilities.getIntPref("ukuranfont", "font", getApplicationContext());

        AlertDialog dialog=new AlertDialog.Builder(Pengaturan.this)

                .setTitle("Pilih ukuran font")
                .setSingleChoiceItems(select, intCheck, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        selectedFont = which;
                    }
                })


                .setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();

                    }
                })

                .setPositiveButton("Pilih", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

//                        Utilities.ShowToast(getApplicationContext(), select[selectedFont]);

                        Utilities.saveIntPref("ukuranfont", "font", selectedFont, getApplicationContext());

                        Utilities.ShowToast(getApplicationContext(), "Ukuran font berhasil diubah");

                    }
                })

                .create();
                dialog.show();

    }

    private void ukuranFontLatin() {

        final String[] select={"12 px","14 px","16 px","18 px","20 px","22 px","24 px"};

        int intCheck = Utilities.getIntPref("ukuranfontlatin", "font", getApplicationContext());

        AlertDialog dialog=new AlertDialog.Builder(Pengaturan.this)

                .setTitle("Pilih ukuran font")
                .setSingleChoiceItems(select, intCheck, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        selectedFont = which;
                    }
                })


                .setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();

                    }
                })

                .setPositiveButton("Pilih", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

//                        Utilities.ShowToast(getApplicationContext(), select[selectedFont]);

                        Utilities.saveIntPref("ukuranfontlatin", "font", selectedFont, getApplicationContext());

                        Utilities.ShowToast(getApplicationContext(), "Ukuran font berhasil diubah");
                    }
                })


                .create();
        dialog.show();


    }

}
