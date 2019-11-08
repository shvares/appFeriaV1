package com.sai.alpha1;


import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import com.sai.alpha1.conciertos.*;
import com.sai.alpha1.events.*;
import com.sai.alpha1.patrocinadores.*;

public class MainActivity extends AppCompatActivity  {


Button btnconcert, btncultura, btnpatro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);
        btnconcert = (Button) findViewById(R.id.btnconcert);
        btncultura = (Button) findViewById(R.id.btncultura);
        btnpatro = (Button) findViewById(R.id.btnpatro);
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