package com.srinivas.emergencymail.Activities;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.srinivas.emergencymail.R;
import com.srinivas.emergencymail.Spin.LoadingSpin;

import static android.content.ContentValues.TAG;

public class Login extends Activity implements View.OnClickListener {
    TextView registraion_tv;
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener authStateListener;
    ProgressDialog progressDialog;
    FirebaseDatabase database;
    DatabaseReference myRef;

    Button login_btn;

    EditText password, emailid;
    LoadingSpin loadingSpin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        registraion_tv = findViewById(R.id.registraion_tv);
        registraion_tv.setOnClickListener(this);
        loadingSpin = findViewById(R.id.spinn);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("details");
        emailid = findViewById(R.id.emailid);
        login_btn = findViewById(R.id.login_btn);
        password = findViewById(R.id.password);

        progressDialog = new ProgressDialog(Login.this);
        progressDialog.setTitle("Authentication");
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // progressDialog.show();
                loadingSpin.setVisibility(View.VISIBLE);
                callmedadi();
            }
        });
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CALL_LOG,
                    Manifest.permission.READ_CONTACTS, Manifest.permission.READ_PHONE_STATE,
                    Manifest.permission.READ_PHONE_NUMBERS,
                    android.Manifest.permission.ACCESS_NETWORK_STATE}, 0);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Log.v(TAG, "Permission: " + permissions[0] + "was " + grantResults[0]);
            //resume tasks needing this permission


        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.registraion_tv:
                Intent registraion_tv = new Intent(Login.this, Signup.class);
                startActivity(registraion_tv);
                break;
        }
    }

    void callmedadi() {

        mAuth.signInWithEmailAndPassword(emailid.getText().toString(), password.getText().toString())
                .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            loadingSpin.setVisibility(View.GONE);
                            //progressDialog.dismiss();
                            // there was an error
                            Toast.makeText(getBaseContext(), "You are not authorized user", Toast.LENGTH_SHORT).show();

                        } else {
                            // progressDialog.dismiss();
                            loadingSpin.setVisibility(View.GONE);
                            SharedPreferences.Editor login = getSharedPreferences("Login", MODE_PRIVATE).edit();
                            login.putString("login", "sucess");
                            login.commit();

                            Intent intent = new Intent(Login.this, Menu.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                });


    }
}
