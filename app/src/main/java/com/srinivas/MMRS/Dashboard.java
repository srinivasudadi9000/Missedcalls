package com.srinivas.MMRS;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.srinivas.emergencymail.R;

public class Dashboard extends Activity implements View.OnClickListener {
    LinearLayout recording, feedback, approval;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);
        recording = findViewById(R.id.linearLayout3);
        feedback = findViewById(R.id.linearLayout);
        approval = findViewById(R.id.linearLayout2);

        recording.setOnClickListener(this);
        feedback.setOnClickListener(this);
        approval.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linearLayout3:
                Intent recording = new Intent(Dashboard.this, DicatorFiles.class);
                startActivity(recording);
                break;
            case R.id.linearLayout:
                break;
            case R.id.linearLayout2:
                break;
        }

    }
}
