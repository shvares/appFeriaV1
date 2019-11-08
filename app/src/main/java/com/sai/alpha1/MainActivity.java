package com.sai.alpha1;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;

import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.sai.alpha1.conciertos.*;
import com.sai.alpha1.events.*;
import com.sai.alpha1.patrocinadores.*;

import java.text.DateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity  {


Button btnconcert, btncultura, btnpatro;
ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        Drawable originalDrawable = getResources().getDrawable(R.drawable.cultura);
        Bitmap originalBitmap = ((BitmapDrawable) originalDrawable).getBitmap();
        RoundedBitmapDrawable roundedDrawable =
                RoundedBitmapDrawableFactory.create(getResources(), originalBitmap);
        roundedDrawable.setCornerRadius(originalBitmap.getHeight());

        btnconcert = (Button) findViewById(R.id.btnconcert);
        btncultura = (Button) findViewById(R.id.btncultura);
        btnpatro = (Button) findViewById(R.id.btnpatro);
        imageView = (ImageView) findViewById(R.id.imagenprueba);
        imageView.setImageDrawable(roundedDrawable);

        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());

        TextView textViewDate = findViewById(R.id.text_date12);
        textViewDate.setText(currentDate);


        btnconcert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, listaconcert.class);
                startActivity(intent);
            }
        });

        btncultura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, listaevents.class);
                startActivity(intent);
            }
        });

        btnpatro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, listapatrocin.class);
                startActivity(intent);
            }
        });




    }




}