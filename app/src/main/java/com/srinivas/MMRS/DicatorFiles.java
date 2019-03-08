package com.srinivas.MMRS;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import com.srinivas.emergencymail.R;

public class DicatorFiles extends Activity {
    CardView mycardview;
    FloatingActionButton floatingActionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dicator_files);

        mycardview = findViewById(R.id.mycardview);
        mycardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mycardview = new Intent(DicatorFiles.this, DictatorForm.class);
                startActivity(mycardview);
            }
        });
        floatingActionButton = findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mycardview = new Intent(DicatorFiles.this, Recording.class);
                startActivity(mycardview);
            }
        });
    }
}
