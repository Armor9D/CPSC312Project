package com.example.contacts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        String name = "placeholder name";
        String phoneNumber = "###-###-####";
        String eMail = "examplename@example.com";

        Intent intent = getIntent();
        if (intent != null) {
            name = intent.getStringExtra("sendName");
            phoneNumber = intent.getStringExtra("sendPhoneNumber");
            eMail = intent.getStringExtra("sendEMail");
        }

        TextView displayNameTextView = (TextView) findViewById(R.id.displayNameTextView);
        TextView displayPhoneNumberTextView = (TextView) findViewById(R.id.displayPhoneNumberTextView);
        TextView displayEMailTextView = (TextView) findViewById(R.id.displayEMailTextView);
        Button callButton = (Button) findViewById(R.id.callButton);
        Button eMailButton = (Button) findViewById(R.id.eMailButton);

        displayNameTextView.setText(name);
        displayPhoneNumberTextView.setText(phoneNumber);
        displayEMailTextView.setText(eMail);

        // uses an Intent to make a call with the phone number
        final String dial = "tel:" + phoneNumber;
        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(dial);
                Intent dialIntent = new Intent(Intent.ACTION_DIAL, uri);
                startActivity(dialIntent);
                // this will open the dialer, but ACTION_CALL would need to be used and the CALL_PHONE permission granted to make the call
            }
        });

        // starts JavaMailActivity and loads it with the contact's email address
        final String sendEMail = eMail;
        eMailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent javaMailActivityIntent = new Intent(ContactActivity.this, JavaMailActivity.class);

                javaMailActivityIntent.putExtra("sendEMail", sendEMail);

                startActivity(javaMailActivityIntent);
            }
        });
    }
}
