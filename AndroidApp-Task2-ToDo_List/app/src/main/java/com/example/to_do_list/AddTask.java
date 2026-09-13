package com.example.to_do_list;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddTask extends AppCompatActivity {

    private EditText etTaskName, etNotes;
    private Button btnSaveTask, btnCancel;
    private DatabaseHelper dbHelper;
    private int userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        userId = getIntent().getIntExtra("userId", -1);

        etTaskName = findViewById(R.id.etTaskName);
        etNotes = findViewById(R.id.etNotes);
        btnSaveTask = findViewById(R.id.btnSaveTask);
        btnCancel = findViewById(R.id.btnCancel);
        dbHelper = new DatabaseHelper(this);

        btnSaveTask.setOnClickListener(v -> saveTask());

        btnCancel.setOnClickListener(v -> {
            setResult(RESULT_CANCELED);
            finish();
        });

    }
    private void saveTask() {
        String taskName = etTaskName.getText().toString().trim();
        String notes = etNotes.getText().toString().trim();

        if (taskName.isEmpty()) {
            Toast.makeText(this, "Task name can't be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        boolean success = dbHelper.addTask(userId, taskName, notes);

        if (success) {
            setResult(RESULT_OK);
            finish();
        } else {
            Toast.makeText(this, "Failed to add task", Toast.LENGTH_SHORT).show();
        }
    }
}