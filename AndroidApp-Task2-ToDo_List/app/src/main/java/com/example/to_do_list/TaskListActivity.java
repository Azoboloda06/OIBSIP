package com.example.to_do_list;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TaskListActivity extends AppCompatActivity implements TaskAdapter.OnTaskActionListener {

    private RecyclerView rvTasks;
    private TextView tvGreeting, tvEmptyState;
    private Button btnAddTask, btnLogout;
    private DatabaseHelper dbHelper;
    private TaskAdapter adapter;
    private List<Task> taskList;
    private int userId;
    private ActivityResultLauncher<Intent> addTaskLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);


        userId = getIntent().getIntExtra("userId", -1);
        if (userId == -1) {

            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return;
        }

        rvTasks = findViewById(R.id.rvTasks);
        tvGreeting = findViewById(R.id.tvGreeting);
        tvEmptyState = findViewById(R.id.tvEmptyState);
        btnAddTask = findViewById(R.id.btnAddTask);
        btnLogout = findViewById(R.id.btnLogout);

        dbHelper = new DatabaseHelper(this);

        String userName = dbHelper.getUserName(userId);
        tvGreeting.setText("Hello, " + userName + "!");

        taskList = new ArrayList<>();
        adapter = new TaskAdapter(taskList, this);

        rvTasks.setLayoutManager(new LinearLayoutManager(this));
        rvTasks.setAdapter(adapter);

        loadTasks();

        addTaskLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        loadTasks();
                    }
                }
        );

        btnAddTask.setOnClickListener(v -> {
            Intent intent = new Intent(TaskListActivity.this, AddTask.class);
            intent.putExtra("userId", userId);
            addTaskLauncher.launch(intent);
        });

        btnLogout.setOnClickListener(v -> logout());
    }
    private void loadTasks() {
        taskList.clear();

        Cursor cursor = dbHelper.getTasks(userId);
        while (cursor.moveToNext()) {
            int taskId = cursor.getInt(cursor.getColumnIndexOrThrow("taskId"));
            String taskName = cursor.getString(cursor.getColumnIndexOrThrow("taskName"));
            String notes = cursor.getString(cursor.getColumnIndexOrThrow("notes"));
            boolean completed = cursor.getInt(cursor.getColumnIndexOrThrow("completed")) == 1;

            taskList.add(new Task(taskId, userId, taskName, notes, completed));
        }
        cursor.close();

        adapter.notifyDataSetChanged();
        toggleEmptyState();
    }
    private void toggleEmptyState() {
        if (taskList.isEmpty()) {
            tvEmptyState.setVisibility(View.VISIBLE);
            rvTasks.setVisibility(View.GONE);
        } else {
            tvEmptyState.setVisibility(View.GONE);
            rvTasks.setVisibility(View.VISIBLE);
        }
    }
    @Override
    public void onDeleteClicked(Task task) {
        new AlertDialog.Builder(this)
                .setTitle("Delete Task")
                .setMessage("Delete \"" + task.getTaskName() + "\"?")
                .setPositiveButton("Delete", (dialog, which) -> {
                    dbHelper.deleteTask(task.getTaskId());
                    loadTasks();
                })
                .setNegativeButton("Cancel", null)
                .show();
    }
    @Override
    public void onCompletedToggled(Task task, boolean isChecked) {
        dbHelper.updateTask(task.getTaskId(), isChecked);
    }
    private void logout() {
        SharedPreferences prefs = getSharedPreferences("session", MODE_PRIVATE);
        prefs.edit().clear().apply();

        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}