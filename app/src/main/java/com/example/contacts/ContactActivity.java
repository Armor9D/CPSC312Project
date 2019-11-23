package com.example.contacts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        TextView displayNameTextView = (TextView) findViewById(R.id.displayNameTextView);
        TextView displayPhoneNumberTextView = (TextView) findViewById(R.id.displayPhoneNumberTextView);
        TextView displayEMailTextView = (TextView) findViewById(R.id.displayEMailTextView);
        Button callButton = (Button) findViewById(R.id.callButton);
        Button eMailButton = (Button) findViewById(R.id.eMailButton);

        // uses an Intent to make a call with the phone number
        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        // starts JavaMailActivity and loads it with the contact's email address
        eMailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
