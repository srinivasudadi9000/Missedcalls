package com.srinivas.MMRS;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.srinivas.emergencymail.R;

public class Recording extends Activity implements View.OnClickListener {

    TextView textView4;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recording);

        textView4 = findViewById(R.id.textView4);
        textView4.setOnClickListener(this);

        imageView = findViewById(R.id.imageView);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.textView4:
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.voiceover));

                break;
        }
    }
}
