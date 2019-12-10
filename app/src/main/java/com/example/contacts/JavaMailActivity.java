package com.example.contacts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class JavaMailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java_mail);

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
                Utils.EMAIL = mailEditText.getText().toString().trim();
                Utils.PASSWORD = passwordEditText.getText().toString();
                String subject = subjectEditText.getText().toString().trim();
                String message = messageEditText.getText().toString();

                // send email
                JavaMailAPI javaMailAPI = new JavaMailAPI(JavaMailActivity.this, gmailAddress, subject, message);
                javaMailAPI.execute();
            }
        });
    }
}
