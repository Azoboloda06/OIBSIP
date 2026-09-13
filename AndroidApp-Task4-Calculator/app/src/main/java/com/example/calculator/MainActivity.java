package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView inputText;
    private StringBuilder displayExpression = new StringBuilder();
    private StringBuilder currentInput = new StringBuilder();
    private boolean resultShown = false;
    private double firstNumber = 0;
    private String currentOperator = "";
    private boolean operatorPending = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputText = findViewById(R.id.inputText);

        AppCompatButton zeroBtn = findViewById(R.id.zeroBtn);
        AppCompatButton oneBtn = findViewById(R.id.oneBtn);
        AppCompatButton twoBtn = findViewById(R.id.twoBtn);
        AppCompatButton threeBtn = findViewById(R.id.threeBtn);
        AppCompatButton fourBtn = findViewById(R.id.fourBtn);
        AppCompatButton fiveBtn = findViewById(R.id.fiveBtn);
        AppCompatButton sixBtn = findViewById(R.id.sixBtn);
        AppCompatButton sevenBtn = findViewById(R.id.sevenBtn);
        AppCompatButton eightBtn = findViewById(R.id.eightBtn);
        AppCompatButton nineBtn = findViewById(R.id.nineBtn);
        AppCompatButton commaBtn = findViewById(R.id.commaBtn);

        AppCompatButton addBtn = findViewById(R.id.addBtn);
        AppCompatButton subtractBtn = findViewById(R.id.subtractBtn);
        AppCompatButton multiplyBtn = findViewById(R.id.multiplyBtn);
        AppCompatButton divideBtn = findViewById(R.id.divideBtn);

        AppCompatButton equalBtn = findViewById(R.id.equalBtn);
        AppCompatButton eraseBtn = findViewById(R.id.eraseBtn);
        AppCompatButton deleteBtn = findViewById(R.id.deleteBtn);

        View.OnClickListener digitClickListener = v -> {
            AppCompatButton btn = (AppCompatButton) v;
            onDigitPressed(btn.getText().toString());
        };

        zeroBtn.setOnClickListener(digitClickListener);
        oneBtn.setOnClickListener(digitClickListener);
        twoBtn.setOnClickListener(digitClickListener);
        threeBtn.setOnClickListener(digitClickListener);
        fourBtn.setOnClickListener(digitClickListener);
        fiveBtn.setOnClickListener(digitClickListener);
        sixBtn.setOnClickListener(digitClickListener);
        sevenBtn.setOnClickListener(digitClickListener);
        eightBtn.setOnClickListener(digitClickListener);
        nineBtn.setOnClickListener(digitClickListener);
        commaBtn.setOnClickListener(digitClickListener);

        addBtn.setOnClickListener(v -> onOperatorPressed("+"));
        subtractBtn.setOnClickListener(v -> onOperatorPressed("−"));
        multiplyBtn.setOnClickListener(v -> onOperatorPressed("×"));
        divideBtn.setOnClickListener(v -> onOperatorPressed("÷"));

        equalBtn.setOnClickListener(v -> onEqualsPressed());

        eraseBtn.setOnClickListener(v -> clearAll());

        deleteBtn.setOnClickListener(v -> onBackspacePressed());
    }

    private void onDigitPressed(String digit) {
        if (digit.equals(".") && currentInput.toString().contains(".")) {
            return;
        }

        if (resultShown) {
            displayExpression.setLength(0);
            currentInput.setLength(0);
            resultShown = false;
        }

        currentInput.append(digit);
        inputText.setText(displayExpression.toString() + currentInput.toString());
    }

    private void onOperatorPressed(String operator) {
        if (currentInput.length() == 0) {
            return;
        }

        if (resultShown) {
            displayExpression.setLength(0);
            resultShown = false;
        }

        firstNumber = Double.parseDouble(currentInput.toString());
        currentOperator = operator;
        operatorPending = true;

        displayExpression.append(currentInput).append(" ").append(operator).append(" ");
        currentInput.setLength(0);

        inputText.setText(displayExpression.toString());
    }

    private void onEqualsPressed() {
        if (!operatorPending || currentInput.length() == 0) {
            return;
        }

        double secondNumber = Double.parseDouble(currentInput.toString());
        double result;

        switch (currentOperator) {
            case "+":
                result = firstNumber + secondNumber;
                break;
            case "−":
                result = firstNumber - secondNumber;
                break;
            case "×":
                result = firstNumber * secondNumber;
                break;
            case "÷":
                if (secondNumber == 0) {
                    inputText.setText("Error");
                    resetStateKeepingDisplay();
                    displayExpression.setLength(0);
                    resultShown = true;
                    return;
                }
                result = firstNumber / secondNumber;
                break;
            default:
                return;
        }

        String resultStr = formatResult(result);

        displayExpression.append(currentInput).append(" = ").append(resultStr);
        inputText.setText(displayExpression.toString());

        currentInput.setLength(0);
        currentInput.append(resultStr);
        operatorPending = false;
        currentOperator = "";
        resultShown = true;
    }

    private void onBackspacePressed() {
        if (currentInput.length() > 0) {
            currentInput.deleteCharAt(currentInput.length() - 1);
            inputText.setText(currentInput.length() == 0 ? "0" : currentInput.toString());
        }
    }

    private void clearAll() {
        currentInput.setLength(0);
        displayExpression.setLength(0);
        firstNumber = 0;
        currentOperator = "";
        operatorPending = false;
        inputText.setText("0");
    }

    private void resetStateKeepingDisplay() {
        currentInput.setLength(0);
        firstNumber = 0;
        currentOperator = "";
        operatorPending = false;
    }

    private String formatResult(double result) {
        if (result == Math.floor(result) && !Double.isInfinite(result)) {
            return String.valueOf((long) result);
        }
        return String.valueOf(result);
    }
}