package com.example.to_do_list;

import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {

    EditText email, password;
    TextView signUp;
    Button btn;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = findViewById(R.id.enterEmail);
        password = findViewById(R.id.enterPass);
        signUp = findViewById(R.id.signUp);
        btn = findViewById(R.id.login);
        db = new DatabaseHelper(this);

        btn.setOnClickListener(v -> {
            String Email = email.getText().toString().trim();
            String Password = password.getText().toString().trim();

            if(Email.isEmpty() || Password.isEmpty()){
                Toast.makeText(this, "Please fill in both fields", Toast.LENGTH_SHORT).show();
            }

            int userId = db.checkUser(Email,Password);

            if (userId != -1) {

                SharedPreferences prefs = getSharedPreferences("session", MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putInt("userId", userId);
                editor.putBoolean("isLoggedIn", true);
                editor.apply();

                Intent intent = new Intent(LoginActivity.this, TaskListActivity.class);
                intent.putExtra("userId", userId);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show();
            }
        });

        signUp.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
            startActivity(intent);
        });

    }

}