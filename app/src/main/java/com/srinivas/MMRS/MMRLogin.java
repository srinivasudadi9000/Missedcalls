package com.srinivas.MMRS;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.srinivas.emergencymail.R;

public class MMRLogin extends Activity implements View.OnClickListener {
    Button login_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mmrlogin);

        login_btn = findViewById(R.id.login_btn);
        login_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_btn:
                Intent loging = new Intent(MMRLogin.this, Dashboard.class);
                startActivity(loging);
                break;
        }
    }
}
