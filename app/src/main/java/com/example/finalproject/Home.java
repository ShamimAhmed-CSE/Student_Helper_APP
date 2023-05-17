package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Home extends AppCompatActivity {
    ImageView img, fee;
    ImageView cgpa, chat;

    @SuppressLint({ "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().hide();
       img = findViewById(R.id.portal);
       cgpa = findViewById(R.id.cgpa);
       fee = findViewById(R.id.fees);
       chat = findViewById(R.id.chat);
       img.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent i = new Intent(getApplicationContext(), PortalActivity.class);
               startActivity(i);
               Toast.makeText(getApplicationContext(), "Opening Portal", Toast.LENGTH_SHORT).show();
           }
       });
       cgpa.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent i = new Intent(getApplicationContext(), Cgpa1.class);
               startActivity(i);
               Toast.makeText(getApplicationContext(), "CGPA Calculation", Toast.LENGTH_SHORT).show();
           }
       });
        fee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), TuitionFees.class);
                startActivity(i);
                Toast.makeText(getApplicationContext(), "Tuition Fee Payment", Toast.LENGTH_SHORT).show();
            }
        });
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), ChatMain.class);
                startActivity(i);
                Toast.makeText(getApplicationContext(), "Students Chatting", Toast.LENGTH_SHORT).show();
            }
        });
    }

}