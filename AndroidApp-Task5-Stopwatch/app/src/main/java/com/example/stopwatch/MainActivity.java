package com.example.stopwatch;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView timeDisplay;
    private AppCompatButton startBtn, pauseBtn, resetBtn;
    private Handler handler = new Handler(Looper.getMainLooper());
    private long startTime = 0L;
    private long elapsedTime = 0L;
    private boolean isRunning = false;

    private Runnable timerRunnable = new Runnable() {
        @Override
        public void run() {
            long currentElapsed = elapsedTime + (SystemClock.elapsedRealtime() - startTime);
            updateDisplay(currentElapsed);
            handler.postDelayed(this, 1000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timeDisplay = findViewById(R.id.timeDisplay);
        startBtn = findViewById(R.id.startBtn);
        pauseBtn = findViewById(R.id.pauseBtn);
        resetBtn = findViewById(R.id.resetBtn);

        startBtn.setOnClickListener(v -> startTimer());
        pauseBtn.setOnClickListener(v -> pauseTimer());
        resetBtn.setOnClickListener(v -> resetTimer());
    }

    private void startTimer() {
        if (isRunning) return;

        startTime = SystemClock.elapsedRealtime();
        handler.post(timerRunnable);
        isRunning = true;

        startBtn.setEnabled(false);
        pauseBtn.setEnabled(true);
    }

    private void pauseTimer() {
        if (!isRunning) return;

        elapsedTime += SystemClock.elapsedRealtime() - startTime;
        handler.removeCallbacks(timerRunnable);
        isRunning = false;

        startBtn.setEnabled(true);
        pauseBtn.setEnabled(false);
    }

    private void resetTimer() {
        handler.removeCallbacks(timerRunnable);
        isRunning = false;
        elapsedTime = 0L;
        startTime = 0L;

        updateDisplay(0L);

        startBtn.setEnabled(true);
        pauseBtn.setEnabled(false);
    }

    private void updateDisplay(long elapsedMillis) {
        int totalSeconds = (int) (elapsedMillis / 1000);
        int hours = totalSeconds / 3600;
        int minutes = (totalSeconds % 3600) / 60;
        int seconds = totalSeconds % 60;

        String formatted = String.format(Locale.getDefault(), "%02d:%02d:%02d", hours, minutes, seconds);
        timeDisplay.setText(formatted);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isRunning) {
            handler.post(timerRunnable);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(timerRunnable);
    }
}