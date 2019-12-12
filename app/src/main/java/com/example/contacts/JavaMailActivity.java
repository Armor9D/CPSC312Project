package com.example.contacts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * This activity allows the user to write and send emails to a contact's email addrees from the GMail address of their cjoice
 */

public class JavaMailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java_mail);

        // add back button to action bar
        assert getSupportActionBar() != null;   // null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   // show back button

        String eMail = "";
        Intent intent = getIntent();
        if (intent != null) {
            eMail = intent.getStringExtra("sendEMail");
        }

        TextView recipientTextView = findViewById(R.id.recipientTextView);
        final EditText mailEditText = findViewById(R.id.mailEditText);
        final EditText passwordEditText = findViewById(R.id.passwordEditText);
        final EditText subjectEditText = findViewById(R.id.subjectEditText);
        final EditText messageEditText = findViewById(R.id.messageEditText);
        FloatingActionButton sendFloatingActionButton = findViewById(R.id.sendFloatingActionButton);

        recipientTextView.setText("Send to " + eMail);

        final String gmailAddress = eMail;
        sendFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mailEditText.getText().toString().trim().equals("") || passwordEditText.getText().toString().equals("")) {
                    Toast.makeText(JavaMailActivity.this, "Please enter your username and password.", Toast.LENGTH_SHORT).show();
                }
                else {
                    Utils.EMAIL = mailEditText.getText().toString().trim();
                    Utils.PASSWORD = passwordEditText.getText().toString();
                    String subject = subjectEditText.getText().toString().trim();
                    String message = messageEditText.getText().toString();

                    // send email
                    JavaMailAPI javaMailAPI = new JavaMailAPI(JavaMailActivity.this, gmailAddress, subject, message);
                    javaMailAPI.execute();
                }
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
