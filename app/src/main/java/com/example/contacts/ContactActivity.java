package com.example.contacts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Handles the interaction with an established Contact and allows the user to use the calling, email, and location functions regarding that contact
 */

public class ContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        // add back button to action bar
        assert getSupportActionBar() != null;   // null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   // show back button

        String name = "placeholder name";
        String phoneNumber = "###-###-####";
        String eMail = "examplename@example.com";
        String address = "placeholder address";

        Intent intent = getIntent();
        if (intent != null) {
            name = intent.getStringExtra("sendName");
            phoneNumber = intent.getStringExtra("sendPhoneNumber");
            eMail = intent.getStringExtra("sendEMail");
            address = intent.getStringExtra("sendAddress");
        }

        TextView displayNameTextView = (TextView) findViewById(R.id.displayNameTextView);
        TextView displayPhoneNumberTextView = (TextView) findViewById(R.id.displayPhoneNumberTextView);
        TextView displayEMailTextView = (TextView) findViewById(R.id.displayEMailTextView);
        TextView displayAddressTextView = (TextView) findViewById(R.id.displayAddressTextView);
        Button callButton = (Button) findViewById(R.id.callButton);
        Button eMailButton = (Button) findViewById(R.id.eMailButton);
        Button addressButton = (Button) findViewById(R.id.addressButton);

        displayNameTextView.setText(name);
        displayPhoneNumberTextView.setText(phoneNumber);
        displayEMailTextView.setText(eMail);
        displayAddressTextView.setText(address);

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

        // uses an Intent to find the address on Google Maps
        final String findAddress = address;
        addressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri mapUri = Uri.parse("geo:0,0?q=" + Uri.encode(findAddress));
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, mapUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });
    }

    // back button returns user to previous activity
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
