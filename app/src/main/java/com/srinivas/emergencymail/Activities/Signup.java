package com.srinivas.emergencymail.Activities;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.srinivas.emergencymail.Models.Details;
import com.srinivas.emergencymail.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Signup extends Activity implements View.OnClickListener {
    FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference myRef;

    EditText firstname;
    Button login_btn;
    EditText name_et, email_et, phoneno_et, password_et;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        name_et = (EditText) findViewById(R.id.firstname);
        email_et = (EditText) findViewById(R.id.emailid);
        phoneno_et = (EditText) findViewById(R.id.phone);
        password_et = (EditText) findViewById(R.id.password);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("details");
        login_btn = findViewById(R.id.login_btn);
        login_btn.setOnClickListener(this);
        progressDialog = new ProgressDialog(Signup.this);
        String id = myRef.push().getKey();
        Details details = new Details("dadi123", "dadi@gmail.com", "12345678", "asdfasdf");
        myRef.child(id).setValue(details);

       /* login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callme();
            }
        });*/


    }

    void callme() {
        mAuth.createUserWithEmailAndPassword("dadi@gmail.com", "dadi@123")
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            // progressDialog.dismiss();
                            //finish();
                            System.out.println("hellow " + task.isSuccessful());
                            System.out.println("hellowxy " + task.getResult());
                            System.out.println("successyyy " + task.getResult().getUser().getEmail() + "Phone " +
                                    task.getResult().getUser().getPhoneNumber());
                            SharedPreferences.Editor users = getSharedPreferences("Details", MODE_PRIVATE).edit();
                            users.putString("email", task.getResult().getUser().getEmail());
                            users.commit();

                        } else {
                            Intent login = new Intent(Signup.this, Login.class);
                            startActivity(login);
                            //System.out.println("hellow " + task.getResult());
                            //System.out.println("hellowaskdflsadf " + task.getResult().getUser());
                          /*  progressDialog.dismiss();
                            // If sign in fails, display a message to the user.
                            showDialog(Signup.this, "Sorry Email Already Exists !!", "no");*/
                            // Toast.makeText(RegisterActivity.this, "Email Already Exists.",
                            //        Toast.LENGTH_SHORT).show();

                        }


                    }
                });

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.login_btn:
                if (name_et.getText().toString().length() == 0) {
                    Toast.makeText(Signup.this, "Please Enter Name ", Toast.LENGTH_SHORT).show();
                } else if (email_et.getText().toString().length() == 0) {
                    Toast.makeText(Signup.this, "Please Enter Valid Email ", Toast.LENGTH_SHORT).show();
                } else if (!isValidEmail(email_et.getText().toString())) {
                    Toast.makeText(Signup.this, "Please Enter Valid Email Address ", Toast.LENGTH_SHORT).show();
                } else if (phoneno_et.getText().toString().length() == 0) {
                    Toast.makeText(Signup.this, "Please Enter Valid Phone Number ", Toast.LENGTH_SHORT).show();
                } else if (password_et.getText().toString().length() == 0) {
                    Toast.makeText(Signup.this, "Please Enter Password ", Toast.LENGTH_SHORT).show();
                } else {

                    String id = myRef.push().getKey();
                    Details details = new Details(name_et.getText().toString(), email_et.getText().toString(), phoneno_et.getText().toString(), password_et.getText().toString());
                    myRef.child(id).setValue(details);
                    progressDialog.setMessage("Please Wait");
                    progressDialog.show();

                    mAuth.createUserWithEmailAndPassword(email_et.getText().toString(), password_et.getText().toString())
                            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    if (task.isSuccessful()) {
                                        progressDialog.dismiss();
                                        // progressDialog.dismiss();
                                        //finish();
                                        System.out.println("hellow " + task.isSuccessful());
                                        System.out.println("hellowxy " + task.getResult());
                                        System.out.println("successyyy " + task.getResult().getUser().getEmail() + "Phone " +
                                                task.getResult().getUser().getPhoneNumber());
                                        SharedPreferences.Editor users = getSharedPreferences("Details", MODE_PRIVATE).edit();
                                        users.putString("email", task.getResult().getUser().getEmail());
                                        users.commit();
                                        Intent login = new Intent(Signup.this, Login.class);
                                        startActivity(login);
                                    } else {
                                        progressDialog.dismiss();
                                        Toast.makeText(getBaseContext(), "Email Already Existed ", Toast.LENGTH_SHORT).show();

                                        //System.out.println("hellow " + task.getResult());
                                        //System.out.println("hellowaskdflsadf " + task.getResult().getUser());
                          /*  progressDialog.dismiss();
                            // If sign in fails, display a message to the user.
                            showDialog(Signup.this, "Sorry Email Already Exists !!", "no");*/
                                        // Toast.makeText(RegisterActivity.this, "Email Already Exists.",
                                        //        Toast.LENGTH_SHORT).show();

                                    }


                                }
                            });


                }
                break;
        }
    }


    private boolean isValidEmail(String Emailid) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(Emailid);
        return matcher.matches();
    }
}
