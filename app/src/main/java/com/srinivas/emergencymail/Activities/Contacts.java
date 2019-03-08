package com.srinivas.emergencymail.Activities;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.srinivas.emergencymail.Adapters.CheckinAdapter;
import com.srinivas.emergencymail.R;

import java.util.ArrayList;
import java.util.HashSet;

import static android.content.ContentValues.TAG;

public class Contacts extends Activity {
    ArrayList<com.srinivas.emergencymail.Contacts.Contacts> contacts;
    RecyclerView phones_rv;
    CheckinAdapter checkinAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CALL_LOG,
                    Manifest.permission.READ_CONTACTS, Manifest.permission.READ_PHONE_STATE,
                    Manifest.permission.READ_PHONE_NUMBERS,
                    android.Manifest.permission.ACCESS_NETWORK_STATE}, 0);
        }
        contacts = new ArrayList<com.srinivas.emergencymail.Contacts.Contacts>();
        contacts.add(new com.srinivas.emergencymail.Contacts.Contacts("22", "e3",false));
        getContactList();
        phones_rv = findViewById(R.id.phones_rv);
        phones_rv.setLayoutManager(new LinearLayoutManager(this));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                HashSet<com.srinivas.emergencymail.Contacts.Contacts> hashSet = new HashSet<com.srinivas.emergencymail.Contacts.Contacts>();
                hashSet.addAll(contacts);
                contacts.clear();
                contacts.addAll(hashSet);

                checkinAdapter = new CheckinAdapter(contacts, R.layout.contactssingle, Contacts.this);
                phones_rv.setAdapter(checkinAdapter);
            }
        }, 6000);


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Log.v(TAG, "Permission: " + permissions[0] + "was " + grantResults[0]);
            //resume tasks needing this permission
            getContactList();

        }
    }

    public void getContactList() {
        ContentResolver cr = getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);

        if ((cur != null ? cur.getCount() : 0) > 0) {
            while (cur != null && cur.moveToNext()) {
                String id = cur.getString(
                        cur.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cur.getString(cur.getColumnIndex(
                        ContactsContract.Contacts.DISPLAY_NAME));

                if (cur.getInt(cur.getColumnIndex(
                        ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {
                    Cursor pCur = cr.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                            new String[]{id}, null);
                    while (pCur.moveToNext()) {
                        String phoneNo = pCur.getString(pCur.getColumnIndex(
                                ContactsContract.CommonDataKinds.Phone.NUMBER));
                      /*  Log.i(TAG, "Name: " + name);
                        Log.i(TAG, "Phone Number: " + phoneNo);*/
                        contacts.add(new com.srinivas.emergencymail.Contacts.Contacts(name, phoneNo,false));
                    }
                    pCur.close();


                }
            }

        }
        if (cur != null) {
            cur.close();
        }
    }


 }


