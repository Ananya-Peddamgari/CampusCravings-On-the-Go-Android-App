package com.example.campuscravingsonmobile.ui.profile;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.campuscravingsonmobile.R;
import com.example.campuscravingsonmobile.database.DatabaseHelper;

public class ProfileFragment extends Fragment {

    private TextView tvFullName;
    private TextView tvEmail;
    private TextView tvStudentNumber;
    private DatabaseHelper databaseHelper;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Initialize DatabaseHelper
        databaseHelper = new DatabaseHelper(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // Initialize UI components
        tvFullName = view.findViewById(R.id.tvFullName);
        tvEmail = view.findViewById(R.id.tvEmail);
        tvStudentNumber = view.findViewById(R.id.tvStudentNumber);

        // Load user data from the arguments
        loadUserProfile();

        return view;
    }

    private void loadUserProfile() {
        if (getArguments() != null) {
            String fullName = getArguments().getString("FULL_NAME", "User not found");
            String email = getArguments().getString("EMAIL", "Email not found");
//            String studentNumber = getArguments().getString("STUDENT_NUMBER", "Student number not found");
            // Set user full name and email
            tvFullName.setText("Full Name: " + fullName);
            tvEmail.setText("Email: " + email);
//            tvStudentNumber.setText("Student Number: " + studentNumber);
//
//
            // Fetch student number from the database using the email
            String studentNumber = getStudentNumberFromDatabase(email);
            tvStudentNumber.setText("Student Number: " + (studentNumber != null ? studentNumber : "Not found"));
        } else {
            tvFullName.setText("User not found");
            tvEmail.setText("Email not found");
            tvStudentNumber.setText("Student number not found");
        }
    }

    // Method to fetch student number from the database
    private String getStudentNumberFromDatabase(String email) {
        String studentNumber = null;
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        String[] projection = { "student_number" }; // Ensure this is the correct column name
        String selection = "email = ?";
        String[] selectionArgs = { email };
        Cursor cursor = null;

        try {
            cursor = db.query("users", projection, selection, selectionArgs, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                studentNumber = cursor.getString(cursor.getColumnIndexOrThrow("student_number")); // Ensure this is the correct column name
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log any errors
        } finally {
            if (cursor != null) {
                cursor.close(); // Ensure the cursor is closed to prevent memory leaks
            }
        }
        return studentNumber;
    }
}
