package com.sai.alpha1;


import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;

import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.sai.alpha1.conciertos.*;
import com.sai.alpha1.events.*;
import com.sai.alpha1.patrocinadores.*;

import java.text.DateFormat;
import java.util.Calendar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity  {


Button btnconcert, btncultura, btnpatro;
ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        Drawable originalDrawable = getResources().getDrawable(R.drawable.mulinik);
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

        basicReadWrite();

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
<<<<<<< HEAD
    public static class Dinosaur {

        public int height;
        public int weight;

        public Dinosaur() {
            // ...
        }


    }


        public void basicReadWrite() {
        // [START write_message]
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("/ferias/ejemplo/img1");

       // myRef.setValue("Hello, World!"); //Commented because we won't writing operation on the database
        // [END write_message]

        // [START read_message]
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d("Img", "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Error", "Failed to read value.", error.toException());
            }



        });
            final DatabaseReference dinosaursRef = database.getReference("dinosaurs");
            dinosaursRef.orderByChild("height").addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                    Dinosaur dinosaur = dataSnapshot.getValue(Dinosaur.class);
                    System.out.println(dataSnapshot.getKey() + " was " + dinosaur.height + " meters tall.");
                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }

                // ...
            });
        // [END read_message]
    }


}