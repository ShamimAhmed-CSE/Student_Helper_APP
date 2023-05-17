package com.example.finalproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Cgpa1 extends AppCompatActivity {
    private Button cgpaCalc;
    private Button directCgpaCalc;
    private AlertDialog.Builder aleartDialog;
   /* private Button cgpaCalc;
    private  Button directCgpaCalc;
    private AlertDialog.Builder aleartDialog; */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cgpa1);
        //getSupportActionBar().hide();
        cgpaCalc = findViewById(R.id.cgpaId);
        directCgpaCalc = findViewById(R.id.directcgpaId);


        cgpaCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Cgpa2.class);
                startActivity(i);
            }
        });

        directCgpaCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Cgpa2.class);
                startActivity(i);
            }
        });
    }

   /* @Override
    public void onBackPressed() {
        aleartDialog = new AlertDialog.Builder(getApplicationContext());
        aleartDialog.setTitle("Do you want to exit?");
        aleartDialog.setCancelable(false);
        aleartDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        aleartDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        aleartDialog.show(); */


}
