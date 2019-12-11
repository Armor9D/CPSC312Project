package com.example.contacts;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity {
    static final String TAG = "MainActivityTag";
    static final int NEW_CONTACT_LOGIN_REQUEST_CODE = 1; // use this as the login request code when adding a new contact
    static final int EDIT_CONTACT_LOGIN_REQUEST_CODE = 2; // use this as the login request code qhen editing an existing contact

    ContactOpenHelper contactOpenHelper;
    SimpleCursorAdapter cursorAdapter;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contactOpenHelper = new ContactOpenHelper(this);
        //Contact testContact = new Contact("Daniel Weaver", "408-220-3141", "dcweaver510@gmail.com", "1461 Bedford Avenue, Sunnyvale CA");
        //contactOpenHelper.insertContact(testContact);
        cursor = contactOpenHelper.getSelectAllContactsCursor();

        Button addNewContactButton = (Button) findViewById(R.id.addNewContactButton);
        ListView contactListView = (ListView) findViewById(R.id.contactListView);

        // SimpleCursorAdapter to link cursor with noteListView
        cursorAdapter = new SimpleCursorAdapter(
                this,
                android.R.layout.simple_list_item_1,
                cursor,
                new String[] {ContactOpenHelper.NAME},
                new int[] {android.R.id.text1},
                0
        );
        contactListView.setAdapter(cursorAdapter);

        // starts EditActivity with an empty template
        addNewContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EditActivity.class);

                intent.putExtra("name", "");
                intent.putExtra("phoneNumber", "");
                intent.putExtra("emailAddress", "");
                intent.putExtra("address", "");
                intent.putExtra("id", -1);

                startActivityForResult(intent, NEW_CONTACT_LOGIN_REQUEST_CODE);
            }
        });

        // starts ContactActivity loaded with the info from the selected Contact
        contactListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Contact contact = contactOpenHelper.getSelectContactById(id);

                Intent intent = new Intent(MainActivity.this, ContactActivity.class);
                intent.putExtra("sendName", contact.getName());
                intent.putExtra("sendPhoneNumber", contact.getPhoneNumber());
                intent.putExtra("sendEMail", contact.geteMail());
                intent.putExtra("sendAddress", contact.getAddress());
                startActivity(intent);
            }
        });

        registerForContextMenu(contactListView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        getMenuInflater().inflate(R.menu.floating_context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final long id = info.id; // this should be the id of the selected Contact from contactListView
        Contact contact = contactOpenHelper.getSelectContactById(id);

        switch (item.getItemId()) {
            case R.id.edit:
                // open EditActivity with the info of the selected Contact
                Intent intent = new Intent(MainActivity.this, EditActivity.class);

                intent.putExtra("name", contact.getName());
                intent.putExtra("phoneNumber", contact.getPhoneNumber());
                intent.putExtra("emailAddress", contact.geteMail());
                intent.putExtra("address", contact.getAddress());
                intent.putExtra("id", id);

                startActivityForResult(intent, EDIT_CONTACT_LOGIN_REQUEST_CODE);
                return true;
            case R.id.delete:
                // confirm deletion with AlertDialog
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
                alertBuilder.setTitle("Delete this contact?")
                        .setMessage("Are you sure you want to delete '" + contact.getName() + "' from your list of contacts?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // delete contact
                                contactOpenHelper.deleteContactById(id);
                                Cursor newCursor2 = contactOpenHelper.getSelectAllContactsCursor();
                                cursorAdapter.changeCursor(newCursor2);
                            }
                        })
                        .setNegativeButton("No", null);
                alertBuilder.show();
                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }

    // handles data returned from EditActivity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult: ");

        if(requestCode == NEW_CONTACT_LOGIN_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            String name = data.getStringExtra("returnName");
            String phoneNumber = data.getStringExtra("returnPhoneNumber");
            String eMail = data.getStringExtra("returnEMail");
            String address = data.getStringExtra("returnAddress");
            long id = data.getLongExtra("returnId", -1);
            Contact contact = new Contact(name, phoneNumber, eMail, address);

            contactOpenHelper.insertContact(contact);
            Cursor newCursor = contactOpenHelper.getSelectAllContactsCursor();
            cursorAdapter.changeCursor(newCursor);
        }
        else if (requestCode == EDIT_CONTACT_LOGIN_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            String name = data.getStringExtra("returnName");
            String phoneNumber = data.getStringExtra("returnPhoneNumber");
            String eMail = data.getStringExtra("returnEMail");
            String address = data.getStringExtra("returnAddress");
            long id = data.getLongExtra("returnId", -1);
            Contact contact = new Contact(name, phoneNumber, eMail, address);

            contactOpenHelper.updateContactById(id, contact);
            Cursor newCursor = contactOpenHelper.getSelectAllContactsCursor();
            cursorAdapter.changeCursor(newCursor);
        }
    }
}
