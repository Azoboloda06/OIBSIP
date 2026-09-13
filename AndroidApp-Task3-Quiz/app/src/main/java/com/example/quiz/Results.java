package com.example.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Results extends AppCompatActivity {

    Button restartButton;
    TextView correct, incorrect, score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        score = findViewById(R.id.scoreTextView);
        correct = findViewById(R.id.correctTextView);
        incorrect = findViewById(R.id.incorrectTextView);
        restartButton = findViewById(R.id.restartBtn);

        int scoreNum = getIntent().getIntExtra("correctScore",0);
        int inc = getIntent().getIntExtra("incorrectScore",0);

        score.setText("Score: " + scoreNum + " out of 10");
        correct.setText("Correct: " + scoreNum);
        incorrect.setText("Incorrect: " + inc);;

        restartButton.setOnClickListener(v -> {
            Intent intent = new Intent(Results.this, QUIZ.class);
            startActivity(intent);
            finish();
        });

    }
}