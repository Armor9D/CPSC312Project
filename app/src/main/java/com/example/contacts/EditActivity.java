package com.example.contacts;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Allows the user to create or edit a Contact, and saves the edit or new csontact to the main Contact list
 */

public class EditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        // add back button to action bar
        assert getSupportActionBar() != null;   // null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   // show back button

        Button saveContactButton = (Button) findViewById(R.id.saveContactButton);
        final EditText nameEditText = (EditText) findViewById(R.id.nameEditText);
        final EditText phoneNumberEditText = (EditText) findViewById(R.id.phoneNumberEditText);
        final EditText eMailEditText = (EditText) findViewById(R.id.eMailEditText);
        final EditText addressEditText = (EditText) findViewById(R.id.addressEditText);
        long id = -1;

        if(getIntent() != null){
            nameEditText.setText(getIntent().getStringExtra("name"));
            phoneNumberEditText.setText(getIntent().getStringExtra("phoneNumber"));
            eMailEditText.setText(getIntent().getStringExtra("emailAddress"));
            addressEditText.setText(getIntent().getStringExtra("address"));
            id = getIntent().getLongExtra("id", -1);
        }

        // exits this Activity and returns the information entered/edited by the user to MainActivity
        final long returnId = id;
        saveContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nameEditText.getText().toString().equals("") || phoneNumberEditText.getText().toString().equals("") || eMailEditText.getText().toString().equals("") || addressEditText.getText().toString().equals("")){
                    Toast.makeText(EditActivity.this, "Please fill all fields.", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent();

                    intent.putExtra("returnName", nameEditText.getText().toString());
                    intent.putExtra("returnPhoneNumber", phoneNumberEditText.getText().toString());
                    intent.putExtra("returnEMail", eMailEditText.getText().toString());
                    intent.putExtra("returnAddress", addressEditText.getText().toString());
                    intent.putExtra("returnId", returnId);

                    // return data to MainActivity
                    setResult(Activity.RESULT_OK, intent);
                    finish();
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
