package com.srinivas.emergencymail.Activities;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.srinivas.emergencymail.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Timer extends Activity implements View.OnClickListener {
    EditText starttime, endtime;
    Button submit_btn;


    public static final String inputFormat = "HH:mm";


    private String compareStringOne = "";
    private String compareStringTwo = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timer);
        starttime = findViewById(R.id.starttime);
        endtime = findViewById(R.id.endtime);
        submit_btn = findViewById(R.id.submit_btn);
        submit_btn.setOnClickListener(this);
        endtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(Timer.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                       /* if (selectedHour < 12) {
                            compareStringOne = String.valueOf(selectedHour);
                            endtime.setText(selectedHour + ":" + selectedMinute );
                        } else {
                            compareStringOne = String.valueOf(selectedHour - 12);
                            endtime.setText(selectedHour - 12 + ":" + selectedMinute + " " + "PM");
                        }*/
                        compareStringTwo = String.valueOf(selectedHour);
                        endtime.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, false);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });
        starttime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(Timer.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                      /*  if (selectedHour < 12) {
                            compareStringTwo = String.valueOf(selectedHour);
                            starttime.setText(selectedHour + ":" + selectedMinute + " " + "AM");
                        } else {
                            compareStringTwo = String.valueOf(selectedHour - 12);
                            starttime.setText(selectedHour - 12 + ":" + selectedMinute + " " + "PM");
                        }*/
                        compareStringOne = String.valueOf(selectedHour);
                        starttime.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, false);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.submit_btn:
                if (compareStringOne.equals("") || compareStringTwo.equals("")) {
                    Toast.makeText(getBaseContext(), "Please give timer correct values", Toast.LENGTH_SHORT).show();
                } else {
                    SharedPreferences.Editor timer = getSharedPreferences("Timer", MODE_PRIVATE).edit();
                    timer.putString("starttime", starttime.getText().toString());
                    timer.putString("endtime", endtime.getText().toString());
                    Toast.makeText(getBaseContext(), "Succesfully set timer thanks ", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }


}
