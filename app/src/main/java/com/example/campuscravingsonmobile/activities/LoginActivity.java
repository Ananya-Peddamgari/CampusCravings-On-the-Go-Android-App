package com.example.campuscravingsonmobile.activities;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import org.mindrot.jbcrypt.BCrypt;

import com.example.campuscravingsonmobile.MainActivity;
import com.example.campuscravingsonmobile.R;
import com.example.campuscravingsonmobile.database.DatabaseHelper;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextEmail, editTextPassword;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        Button loginButton = findViewById(R.id.loginButton);
        TextView textViewSignup = findViewById(R.id.textViewSignup);

        // Initialize DatabaseHelper
        databaseHelper = new DatabaseHelper(this);

        // Handle login button click
        loginButton.setOnClickListener(view -> {
            String inputEmail = editTextEmail.getText().toString().trim();
            String inputPassword = editTextPassword.getText().toString().trim();

            if (inputEmail.isEmpty() || inputPassword.isEmpty()) {
                Toast.makeText(this, "Please enter both email and password", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!isValidEmail(inputEmail)) {
                Toast.makeText(this, "Please enter a valid email address", Toast.LENGTH_SHORT).show();
                return;
            }

            // Check credentials in the database
            if (checkCredentials(inputEmail, inputPassword)) {
                String fullName = getFullName(inputEmail);

                if (fullName != null) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("FULL_NAME", fullName);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(this, "Error retrieving user details", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show();
            }
        });

        textViewSignup.setOnClickListener(view -> {
            Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(registerIntent);
        });
    }

    private boolean checkCredentials(String email, String password) {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        String[] projection = { "password" };
        String selection = "email = ?";
        String[] selectionArgs = { email };
        Cursor cursor = null;
        boolean isValid = false;

        try {
            cursor = db.query("users", projection, selection, selectionArgs, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                String storedHash = cursor.getString(cursor.getColumnIndexOrThrow("password"));
                isValid = BCrypt.checkpw(password, storedHash);
            }
        } catch (Exception e) {
            Toast.makeText(this, "Error checking credentials: " + e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return isValid;
    }

    private String getFullName(String email) {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        String[] projection = { "fullname" };
        String selection = "email = ?";
        String[] selectionArgs = { email };
        Cursor cursor = null;
        String fullName = null;

        try {
            cursor = db.query("users", projection, selection, selectionArgs, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                fullName = cursor.getString(cursor.getColumnIndexOrThrow("fullname"));
            }
        } catch (Exception e) {
            Toast.makeText(this, "Error retrieving full name: " + e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return fullName;
    }

    private boolean isValidEmail(String email) {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        return email.matches(emailPattern);
    }

    @Override
    protected void onDestroy() {
        if (databaseHelper != null) {
            databaseHelper.close();
        }
        super.onDestroy();
    }
}
