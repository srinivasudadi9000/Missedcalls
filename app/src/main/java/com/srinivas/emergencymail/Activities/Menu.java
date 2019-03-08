package com.srinivas.emergencymail.Activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import com.srinivas.emergencymail.R;

public class Menu extends Activity implements View.OnClickListener {
    CardView mailcontent, contacts_rv, timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        timer = findViewById(R.id.timer);
        mailcontent = findViewById(R.id.mailcontent);
        contacts_rv = findViewById(R.id.contacts_rv);

        timer.setOnClickListener(this);
        mailcontent.setOnClickListener(this);
        contacts_rv.setOnClickListener(this);
        SharedPreferences a = getSharedPreferences("Others", MODE_PRIVATE);
        a.getString("otheno", "");
    }


    @Override
    public void onClick(View v) {
       switch (v.getId()){
           case R.id.timer:
               Intent timer= new Intent(Menu.this, Timer.class);
               startActivity(timer);
               return;
           case R.id.mailcontent:
               Intent mailcontent= new Intent(Menu.this, MailContent.class);
               startActivity(mailcontent);
               break;
           case R.id.contacts_rv:
               Intent contacts= new Intent(Menu.this, Contacts.class);
               startActivity(contacts);
               break;
       }
    }
}
