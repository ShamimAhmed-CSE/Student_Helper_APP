package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Contact extends AppCompatActivity {
TextView admission, textView;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        admission = findViewById(R.id.ao);
        textView = findViewById(R.id.textView);
        textView.setSelected(true);
        admission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), AdmissionContact.class);
                startActivity(i);
                Toast.makeText(getApplicationContext(), "Admission Office Info", Toast.LENGTH_SHORT).show();
            }
        });

      //  Intent callIntent = new Intent(Intent.ACTION_CALL);
      //  callIntent.setData(Uri.parse("tel:123456789"));
      //  startActivity(callIntent);
    }
}