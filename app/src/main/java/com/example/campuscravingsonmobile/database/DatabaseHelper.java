package com.example.campuscravingsonmobile.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "CampusCravings.db";
    private static final int DATABASE_VERSION = 1; // Increment if you change the schema
    private static final String TABLE_USERS = "users";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_FULLNAME = "fullname";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORD = "password";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_FULLNAME + " TEXT NOT NULL, "
                + COLUMN_EMAIL + " TEXT UNIQUE NOT NULL, "  // Ensure email is unique and not null
                + COLUMN_PASSWORD + " TEXT NOT NULL)"; // Password should not be null
        db.execSQL(CREATE_USERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    // Add user to the database
    public boolean addUser(String fullname, String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_FULLNAME, fullname);
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_PASSWORD, password); // Store hashed password for security

        long result = db.insert(TABLE_USERS, null, values);
        db.close();
        return result != -1;
    }

    // Method to get full name based on email and password
    public String getFullName(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS, new String[]{COLUMN_PASSWORD, COLUMN_FULLNAME},
                COLUMN_EMAIL + "=?", new String[]{email}, null, null, null);

        String fullName = null; // Default value

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                String storedPassword = cursor.getString(0);
                if (password.equals(storedPassword)) { // Directly matching password (use hashing instead)
                    fullName = cursor.getString(1);
                }
            }
            cursor.close(); // Close cursor to prevent memory leak
        }
        db.close(); // Close database connection
        return fullName;
    }

    // Fetch user details by email
    public Cursor getUserByEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_USERS, null, COLUMN_EMAIL + "=?", new String[]{email}, null, null, null);
    }
}
