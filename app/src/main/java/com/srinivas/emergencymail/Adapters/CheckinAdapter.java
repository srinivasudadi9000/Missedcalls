package com.srinivas.emergencymail.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.srinivas.emergencymail.Activities.Login;
import com.srinivas.emergencymail.Activities.Signup;
import com.srinivas.emergencymail.Contacts.Contacts;
import com.srinivas.emergencymail.R;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;


public class CheckinAdapter extends RecyclerView.Adapter<CheckinAdapter.CheckIn> {
    ArrayList<Contacts> contacts;
    int Rowlayout;
    Context context;
    String phonestring = "+919848372261,+918074021304";

    public CheckinAdapter(ArrayList<Contacts> contacts, int check_single, Context applicationContext) {
        this.context = applicationContext;
        this.Rowlayout = check_single;
        this.contacts = contacts;
    }

    @Override
    public CheckIn onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(Rowlayout, parent, false);
        return new CheckIn(view);
    }

    @SuppressLint({"NewApi", "ResourceAsColor"})
    @Override
    public void onBindViewHolder(final CheckIn holder, final int position) {


        holder.contactname.setText(contacts.get(position).getName());
        holder.contactnumber.setText(contacts.get(position).getPhone());
       /* holder.mycheckbox.setTag(contacts.get(position));
        holder.mycheckbox.setOnCheckedChangeListener(null);
        holder.myll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                *//*Intent i = new Intent(context, Signup.class);
                context.startActivity(i);*//*
                if (holder.mycheckbox.isChecked()) {
                    holder.mycheckbox.setChecked(false);
                } else {
                    holder.mycheckbox.setChecked(true);
                }

            }
        });*/

        holder.mycheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (holder.mycheckbox.isChecked())
                    contacts.get(position).setChecked(true);
                else
                    contacts.get(position).setChecked(false);
            }
        });

        if (contacts.get(position).getChecked())
            holder.mycheckbox.setChecked(true);
        else
            holder.mycheckbox.setChecked(false);
        holder.myll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.mycheckbox.isChecked()) {
                    holder.mycheckbox.setChecked(false);
                } else {
                    holder.mycheckbox.setChecked(true);
                    phonestring = phonestring + contacts.get(position).getPhone() + ",";

                    SharedPreferences.Editor phones = context.getSharedPreferences("PHones", MODE_PRIVATE).edit();
                    phones.putString("phones", phonestring.toString());
                    phones.commit();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public class CheckIn extends RecyclerView.ViewHolder {
        TextView contactname, contactnumber;
        CheckBox mycheckbox;
        LinearLayout myll;

        public CheckIn(View itemView) {
            super(itemView);
            contactname = itemView.findViewById(R.id.contactname);
            contactnumber = itemView.findViewById(R.id.contactnumber);
            mycheckbox = itemView.findViewById(R.id.mycheckbox);
            myll = itemView.findViewById(R.id.myll);
            //this.setIsRecyclable(false);
        }


    }
}
