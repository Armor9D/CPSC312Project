package com.example.contacts;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Setes up the SQLite table that stores the Contact list, allowing for persistent storage between app uses
 */

public class ContactOpenHelper extends SQLiteOpenHelper {
    static final String TAG = "ContactOpenHelperTag";

    static final String DATABASE_NAME = "contactsDatabase";
    static final int DATABASE_VERSION = 1;

    static final String TABLE_CONTACTS = "tableContacts";
    static final String ID = "_id";
    static final String NAME = "name";
    static final String PHONE_NUMBER = "phoneNumber";
    static final String EMAIL = "eMail";
    static final String ADDRESS = "address";

    /**
     * DVC for the ContactOpenHelper class
     *
     * @param context - context in which the ContactOpenHelper is being run in
     */
    public ContactOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreate = "CREATE TABLE " + TABLE_CONTACTS +
                "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NAME + " TEXT, " +
                PHONE_NUMBER + " TEXT, " +
                EMAIL + " TEXT," +
                ADDRESS + " TEXT" + ")";
        Log.d(TAG, "onCreate: " + sqlCreate);


        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /**
     * Inserts a contact into the SQLite table
     *
     * @param contact - contact to insert into table
     */
    public void insertContact(Contact contact) {
        String sqlInsert = "INSERT INTO " + TABLE_CONTACTS + " VALUES(null, '" +
                contact.getName() + "', '" +
                contact.getPhoneNumber() + "', '" +
                contact.geteMail() + "', '" +
                contact.getAddress() + "')";
        Log.d(TAG, "insertContact: " + sqlInsert);


        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sqlInsert);
        db.close();
    }

    /**
     * Gets the cursor which selects a contact from the SQLite table
     *
     * @return cursor used by the database
     */
    public Cursor getSelectAllContactsCursor() {
        String sqlSelect = "SELECT * FROM " + TABLE_CONTACTS;
        Log.d(TAG, "getSelectAllContactCursor: " + sqlSelect);


        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sqlSelect, null);
        return cursor;
    }

    /**
     * Gets the contact from the table corresponding to the id
     *
     * @param id - id used to identify a Contact in the SQL table
     * @return Contact corresponding to the sent id
     */
    public Contact getSelectContactById(long id) {
        String sqlSelect = "SELECT * FROM " + TABLE_CONTACTS +
                " WHERE " + ID + "=" + id;
        Log.d(TAG, "selectContactById: " + sqlSelect);

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sqlSelect, null);

        cursor.moveToNext();
        String name = cursor.getString(1);
        String phoneNumber = cursor.getString(2);
        String eMail = cursor.getString(3);
        String address = cursor.getString(4);
        Contact returnContact = new Contact(name, phoneNumber, eMail, address);

        db.close();
        return returnContact;
    }

    /**
     * Updates the Contact within the SQLite table, when the Contact is edited
     *
     * @param id - id number used to identify the Contact to be updated
     * @param contact - new Contact information to replace the older information
     */
    public void updateContactById(long id, Contact contact) {
        String sqlUpdate = "UPDATE " + TABLE_CONTACTS + " SET " +
                NAME + "='" + contact.getName() + "', " +
                PHONE_NUMBER + "='" + contact.getPhoneNumber() + "', " +
                EMAIL + "='" + contact.geteMail() + "', " +
                ADDRESS + "='" + contact.getAddress() + "' WHERE " +
                ID + "=" + id;
        Log.d(TAG, "updateContactById: " + sqlUpdate);


        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sqlUpdate);
        db.close();
    }

    /**
     * Deletes all contacts from the SQLite table
     */
    public void deleteAllContacts() {
        String sqlDelete = "DELETE FROM " + TABLE_CONTACTS;
        Log.d(TAG, "deleteAllContacts: " + sqlDelete);


        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sqlDelete);
        db.close();
    }

    /**
     * Deletes a specific contact from the SQLite table based on an identifier
     *
     * @param id - identifier for which contact to delete from the table
     */
    public void deleteContactById(long id) {
        String sqlDelete = "DELETE FROM " + TABLE_CONTACTS + " WHERE " +
                ID + "=" + id;
        Log.d(TAG, "deleteContactById: " + sqlDelete);


        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sqlDelete);
        db.close();
    }
}

