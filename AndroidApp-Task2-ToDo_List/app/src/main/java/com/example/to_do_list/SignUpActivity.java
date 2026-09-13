package com.example.to_do_list;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SignUpActivity extends AppCompatActivity {

    EditText name, email, password, pass2;
    Button btn;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);

        name = findViewById(R.id.enterName);
        email = findViewById(R.id.enterEmail);
        password = findViewById(R.id.enterPass);
        pass2 = findViewById(R.id.enterPass2);
        btn = findViewById(R.id.login);
        dbHelper = new DatabaseHelper(this);

        btn.setOnClickListener(v -> {
            registerUser();
        });

    }
    private void registerUser() {
        String nameStr = name.getText().toString().trim();
        String emailStr = email.getText().toString().trim();
        String passStr = password.getText().toString().trim();
        String pass2Str = pass2.getText().toString().trim();

        if (nameStr.isEmpty() || emailStr.isEmpty() || passStr.isEmpty() || pass2Str.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!passStr.equals(pass2Str)) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(emailStr).matches()) {
            Toast.makeText(this, "Please enter a valid email", Toast.LENGTH_SHORT).show();
            return;
        }

        boolean success = dbHelper.registerUser(nameStr, emailStr, passStr);

        if (success) {
            Toast.makeText(this, "Registration successful! Please log in.", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Registration failed — email may already be in use", Toast.LENGTH_SHORT).show();
        }

    }
}