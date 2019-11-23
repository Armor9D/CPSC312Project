package com.example.contacts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Button saveContactButton = (Button) findViewById(R.id.saveContactButton);
        EditText nameEditText = (EditText) findViewById(R.id.nameEditText);
        EditText phoneNumberEditText = (EditText) findViewById(R.id.nameEditText);
        EditText eMailEditText = (EditText) findViewById(R.id.eMailEditText);

        // exits this Activity and returns the information entered/edited by the user to MainActivity
        saveContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
