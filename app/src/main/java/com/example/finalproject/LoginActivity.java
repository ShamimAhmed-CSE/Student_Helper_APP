package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
Button singup, signin;
EditText login_email, login_password;
FirebaseAuth auth;
    ProgressDialog progressDialog;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        singup = findViewById(R.id.signup);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);
signin = findViewById(R.id.signin);
login_email = findViewById(R.id.login_email);
login_password = findViewById(R.id.login_password);
auth = FirebaseAuth.getInstance();
signin.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        String email = login_email.getText().toString();
        String password = login_password.getText().toString();
        progressDialog.show();
        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            progressDialog.dismiss();
            Toast.makeText(LoginActivity.this, "Fill Data", Toast.LENGTH_SHORT).show();
        }
        else if (!email.matches(emailPattern)) {
            progressDialog.dismiss();
            login_email.setError("Invalid Email");
            Toast.makeText(LoginActivity.this, "Invalid Email", Toast.LENGTH_SHORT).show();
        }
        else if (password.length()<6) {
            progressDialog.dismiss();
            login_password.setError("Invalid Password");
            Toast.makeText(LoginActivity.this, "Invalid Password", Toast.LENGTH_SHORT).show();
        }
        else
        {
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()) {
                        progressDialog.dismiss();
                        Intent i = new Intent(getApplicationContext(), ChatMain.class);
                        startActivity(i);
                    }
                    else {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), "Log In Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }
});
        singup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(i);
                Toast.makeText(getApplicationContext(), "Register Here", Toast.LENGTH_SHORT).show();
            }
        });
    }
}