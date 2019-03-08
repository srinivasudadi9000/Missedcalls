package com.srinivas.emergencymail.Activities;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.srinivas.emergencymail.R;

public class MailContent extends Activity {
    Button submit_btn;
    EditText email_et, mail_et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mail_content);
        mail_et = findViewById(R.id.mail_et);
        email_et = findViewById(R.id.email_et);


        submit_btn = findViewById(R.id.submit_btn);
        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor mail = getSharedPreferences("Email", MODE_PRIVATE).edit();
                mail.putString("email", mail_et.getText().toString());
                mail.putString("emailcontent", email_et.getText().toString());
                mail.commit();
                Toast.makeText(getBaseContext(), "Thankyou succefully set mail content", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
