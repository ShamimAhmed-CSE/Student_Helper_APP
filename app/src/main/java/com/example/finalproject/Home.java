package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

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

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;

public class Home extends AppCompatActivity {
    ImageView img, fee, library;
    ImageView gpa, chat, noticeboard;
    TextView textView;



    @SuppressLint({ "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //getSupportActionBar().hide();
       img = findViewById(R.id.portal);
       gpa = findViewById(R.id.cgpa);
       fee = findViewById(R.id.fees);
        textView = findViewById(R.id.textView);
       chat = findViewById(R.id.chat);
        textView.setSelected(true);
       library = findViewById(R.id.elibrary);
       noticeboard = findViewById(R.id.notice);

        ArrayList<SlideModel> imageList = new ArrayList<>(); // Create image list

// imageList.add(new SlideModel("String Url" or R.drawable));
// imageList.add(new SlideModel("String Url" or R.drawable, "title")); // You can add title

        imageList.add(new SlideModel(R.drawable.slide1,  ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(R.drawable.slide2, "Organized Cricket Cup",  ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(R.drawable.slide3, "Graduation Day Batch-191", ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(R.drawable.slide4, "ICPC Asia Region Contest 2022", ScaleTypes.CENTER_CROP));

        ImageSlider imageSlider = findViewById(R.id.image_slider);
        imageSlider.setImageList(imageList);



        FirebaseMessaging.getInstance().subscribeToTopic("News")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "Done";
                        if (!task.isSuccessful()) {
                            msg = "Failed";
                        }

                    }
                });
       img.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent i = new Intent(getApplicationContext(), PortalActivity.class);
               startActivity(i);
               Toast.makeText(getApplicationContext(), "Opening Portal", Toast.LENGTH_SHORT).show();
           }
       });
        noticeboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), NoticeBoard.class);
                startActivity(i);
                Toast.makeText(getApplicationContext(), "Notice Board", Toast.LENGTH_SHORT).show();
            }
        });
        library.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Contact.class);
                startActivity(i);
                Toast.makeText(getApplicationContext(), "Contact Helpline", Toast.LENGTH_SHORT).show();
            }
        });
       gpa.setOnClickListener(new View.OnClickListener() {
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