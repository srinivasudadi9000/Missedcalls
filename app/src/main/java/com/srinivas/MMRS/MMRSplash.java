package com.srinivas.MMRS;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.srinivas.emergencymail.R;

public class MMRSplash extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mmrsplash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent loging = new Intent(MMRSplash.this,MMRLogin.class);
                startActivity(loging);
            }
        },2000);
    }
}
