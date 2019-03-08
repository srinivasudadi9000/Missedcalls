package com.srinivas.emergencymail.Service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import com.srinivas.Mail.SendMailTask;

import java.util.Arrays;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class IncommingCallReceiver extends BroadcastReceiver {

    static boolean ring = false;
    static boolean callReceived = false;
    String callerPhoneNumber;
    int count = 0;

    @Override
    public void onReceive(Context mContext, Intent intent) {

        // Get the current Phone State
        String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
        String number = intent.getExtras().getString(TelephonyManager.EXTRA_INCOMING_NUMBER);

        if (state == null)
            return;

        // If phone state "Rininging"
        if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
            ring = true;
            // Get the Caller's Phone Number
            Bundle bundle = intent.getExtras();
            //   callerPhoneNumber = bundle.getString("incoming_number");
            callerPhoneNumber = bundle.toString();
        }


        // If incoming call is received
        if (state.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)) {
            callReceived = true;
        }


        // If phone is Idle
        if (state.equals(TelephonyManager.EXTRA_STATE_IDLE)) {

            SharedPreferences mail = mContext.getSharedPreferences("Email", MODE_PRIVATE);

            System.out.println("seeeeeee " + mail.getString("email", "").toString() + " n0 " + number);
            SharedPreferences phones = mContext.getSharedPreferences("PHones", MODE_PRIVATE);
            // If phone was ringing(ring=true) and not received(callReceived=false) , then it is a missed call
            if (ring == true && callReceived == false) {
                if (number.contains(phones.getString("phones", ""))) {
                    List<String> toEmailList = Arrays.asList(mail.getString("email", "").toString().trim().split("\\s*,\\s*"));
                    new SendMailTask(mContext).execute("emergencymail045@gmail.com",
                            "wadahell@123", toEmailList,
                            "Emergency Mail Sender", "<!DOCTYPE html>\n" +
                                    "<html>\n" +
                                    "<body style=\"color:#000000\">\n" +
                                    " \n" +
                                    "<p ><b>Dear  Applicant  ,</b></p>\n" +
                                    " \n" + mail.getString("emailcontent", "").toString() + "From Number :" + number +
                                    " \n" +
                                    "</body>\n" +
                                    "</html>\n"
                    );
                    Toast.makeText(mContext, "It was A MISSED CALL from vidyanadht : " + number, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(mContext, "It was A MISSED CALL from : " + number, Toast.LENGTH_LONG).show();

                    SharedPreferences a = mContext.getSharedPreferences("Others", MODE_PRIVATE);


                    SharedPreferences.Editor otherno = mContext.getSharedPreferences("Others", MODE_PRIVATE).edit();
                    otherno.putString("otheno", a.getString("otheno", "") + "," + number);
                    otherno.putString("count", String.valueOf(count++));
                    otherno.commit();


                }
            }
        }

    }
}