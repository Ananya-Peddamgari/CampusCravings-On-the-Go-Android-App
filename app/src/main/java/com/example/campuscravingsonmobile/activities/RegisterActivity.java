package com.example.campuscravingsonmobile.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import org.mindrot.jbcrypt.BCrypt;

import com.example.campuscravingsonmobile.R;
import com.example.campuscravingsonmobile.database.DatabaseHelper;

public class RegisterActivity extends AppCompatActivity {

    private EditText etFullname, editTextEmail, editTextPassword, editTextConfirmPassword;
    private Button registerButton;
    private TextView textViewLogin;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        // Initialize UI components
        etFullname = findViewById(R.id.etFullname);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextConfirmPassword = findViewById(R.id.editTextConfirmPassword);
        registerButton = findViewById(R.id.registerButton);
        textViewLogin = findViewById(R.id.textViewLogin);

        // Initialize DatabaseHelper
        databaseHelper = new DatabaseHelper(this);

        // Handle registration logic
        registerButton.setOnClickListener(view -> {
            String fullName = etFullname.getText().toString().trim();
            String email = editTextEmail.getText().toString().trim();
            String password = editTextPassword.getText().toString().trim();
            String confirmPassword = editTextConfirmPassword.getText().toString().trim();

            // Validate input
            if (!validateInput(fullName, email, password, confirmPassword)) {
                return;
            }

            // Hash the password using BCrypt
            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

            // Save the user data to SQLite database
            boolean isInserted = databaseHelper.addUser(fullName, email, hashedPassword);
            if (isInserted) {
                Toast.makeText(RegisterActivity.this, "Registration successful!", Toast.LENGTH_SHORT).show();

                // Proceed to LoginActivity after registration
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish(); // Finish the RegisterActivity
            } else {
                Toast.makeText(RegisterActivity.this, "Registration failed!", Toast.LENGTH_SHORT).show();
            }
        });

        // Redirect to LoginActivity
        textViewLogin.setOnClickListener(view -> {
            Intent signinIntent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(signinIntent);
            finish(); // Optional: Finish RegisterActivity to remove it from the back stack
        });
    }

    private boolean validateInput(String fullName, String email, String password, String confirmPassword) {
        if (fullName.isEmpty()) {
            Toast.makeText(this, "Full Name is required", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (fullName.length() < 3) {
            Toast.makeText(this, "Full Name should be at least 3 characters", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (email.isEmpty()) {
            Toast.makeText(this, "Email is required", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Please enter a valid email", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (password.isEmpty()) {
            Toast.makeText(this, "Password is required", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (password.length() < 6) {
            Toast.makeText(this, "Password should be at least 6 characters", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}
