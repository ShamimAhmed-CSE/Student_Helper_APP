package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import de.hdodenhof.circleimageview.CircleImageView;

public class RegisterActivity extends AppCompatActivity {
    Button signin, register;
    CircleImageView profile_image;
    EditText reg_name, reg_email, reg_pass, reg_cpass;
    FirebaseAuth auth;
    FirebaseDatabase database;
    FirebaseStorage storage;
    String imgURI;
    Uri imageUri;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();
        signin = findViewById(R.id.signin);
        reg_name = findViewById(R.id.reg_name);
        reg_pass = findViewById(R.id.reg_pass);
        reg_email = findViewById(R.id.reg_email);
        reg_cpass = findViewById(R.id.reg_Cpass);
        register = findViewById(R.id.registration);
        profile_image = findViewById(R.id.profile_image);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = reg_name.getText().toString();
                String email = reg_email.getText().toString();
                String pass = reg_pass.getText().toString();
                String cpass = reg_cpass.getText().toString();
                String status = "Hey there I'm using this";
                progressDialog.show();
                if(TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(cpass)) {
                    progressDialog.dismiss();
                    Toast.makeText(RegisterActivity.this, "Please Enter All Data", Toast.LENGTH_SHORT).show();
                }
                else if (!email.matches(emailPattern)) {
                    progressDialog.dismiss();
                    reg_email.setError("Invalid Email");
                    Toast.makeText(RegisterActivity.this, "Invalid Email", Toast.LENGTH_SHORT).show();
                }
                else if (!pass.equals(cpass) ) {
                    progressDialog.dismiss();
                    Toast.makeText(RegisterActivity.this, "Password Doesn't Match", Toast.LENGTH_SHORT).show();
                }
                else if ( pass.length()<6) {
                    progressDialog.dismiss();
                    Toast.makeText(RegisterActivity.this, "Password must be 6 character", Toast.LENGTH_SHORT).show();
                }
                else {
                    auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                DatabaseReference reference = database.getReference().child("user").child(auth.getUid());
                                StorageReference storageReference = storage.getReference().child("upload").child(auth.getUid());
                                if(imageUri != null) {
                                    storageReference.putFile(imageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                                        @Override
                                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                                            if (task.isSuccessful()) {
                                                storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                    @Override
                                                    public void onSuccess(Uri uri) {
                                                        imgURI =  uri.toString();
                                                        Users users = new Users(auth.getUid(), name, email, imgURI, status);
                                                        reference.setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<Void> task) {
                                                                if (task.isSuccessful()) {
                                                                    progressDialog.dismiss();
                                                                    Intent i = new Intent(getApplicationContext(), ChatMain.class);
                                                                    startActivity(i);
                                                                }
                                                                else {
                                                                    Toast.makeText(RegisterActivity.this, "Error in user Creating", Toast.LENGTH_SHORT).show();
                                                                }
                                                            }
                                                        });
                                                    }
                                                });
                                            }
                                        }
                                    });
                                }
                                else {
                                    String status = "Hey there I'm using this";
                                    imgURI =  "https://firebasestorage.googleapis.com/v0/b/checking-3732e.appspot.com/o/image_2023-05-29_082317047.png?alt=media&token=6df5759c-7578-4a3c-ba4c-c81be8dec500";
                                    Users users = new Users(auth.getUid(), name, email, imgURI, status);
                                    reference.setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Intent i = new Intent(getApplicationContext(), ChatMain.class);
                                                startActivity(i);
                                            }
                                            else {
                                                progressDialog.dismiss();
                                                Toast.makeText(RegisterActivity.this, "Error in user Creating", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                                }
                            }
                            else {
                                Toast.makeText(RegisterActivity.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
                }



            }
        });

        profile_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 10);
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);
                Toast.makeText(getApplicationContext(), "Login Here", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10) {
            if(data!= null) {
                imageUri = data.getData();
                profile_image.setImageURI(imageUri);
            }
        }
    }
}