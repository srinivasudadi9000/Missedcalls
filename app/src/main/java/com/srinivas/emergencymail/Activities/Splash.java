package com.srinivas.emergencymail.Activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.srinivas.emergencymail.R;

public class Splash extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                SharedPreferences login = getSharedPreferences("Login", MODE_PRIVATE);
                if (login.getString("login", "").equals("sucess")) {
                    Intent l = new Intent(Splash.this, Menu.class);
                    startActivity(l);

                } else {
                    Intent d = new Intent(Splash.this, Login.class);
                    startActivity(d);
                }


            }
        }, 2000);
    }
}
