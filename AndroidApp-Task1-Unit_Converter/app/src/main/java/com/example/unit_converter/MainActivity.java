package com.example.unit_converter;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    Spinner categorySpinner, fromSpinner, toSpinner;
    EditText value;
    Button btn;
    TextView answer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        categorySpinner = findViewById(R.id.categorySpinner);
        fromSpinner = findViewById(R.id.fromSpinner);
        toSpinner = findViewById(R.id.toSpinner);
        value = findViewById(R.id.enteredValue);
        btn = findViewById(R.id.convertButton);
        answer = findViewById(R.id.resultTextView);

        String[] categories = {
                "Category",
                "Length",
                "Weight",
                "Temperature"
        };
        String[] lengths = {
                "Unit",
                "cm",
                "m"
        };
        String[] weight = {
                "Unit",
                "kg",
                "g"
        };
       String[] temperature = {
               "Unit",
               "Celsius",
               "Fahrenheit"
       };

        ArrayAdapter<String> categoryAdapter =
                new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_spinner_item,
                        categories
                );
        categoryAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );
        categorySpinner.setAdapter(categoryAdapter);

        ArrayAdapter<String> lengthAdapter =
                new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_spinner_item,
                        lengths
                );
        lengthAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );

        ArrayAdapter<String> weightAdapter =
                new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_spinner_item,
                        weight
                );
        weightAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );

        ArrayAdapter<String> temperatureAdapter =
                new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_spinner_item,
                        temperature
                );
        temperatureAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );

        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 1) {
                    fromSpinner.setAdapter(lengthAdapter);
                    toSpinner.setAdapter(lengthAdapter);
                }
                else if (position == 2) {
                    fromSpinner.setAdapter(weightAdapter);
                    toSpinner.setAdapter(weightAdapter);
                }
                else if (position == 3) {
                    fromSpinner.setAdapter(temperatureAdapter);
                    toSpinner.setAdapter(temperatureAdapter);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btn.setOnClickListener(v -> {

            String input = value.getText().toString().trim();

            if (input.isEmpty()) {
                Toast.makeText(this, "Please enter a value", Toast.LENGTH_SHORT).show();
                return;
            }

            double number;

            try {
                number = Double.parseDouble(input);
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Please enter a valid number", Toast.LENGTH_SHORT).show();
                return;
            }

            String category = categorySpinner.getSelectedItem().toString();
            String from = fromSpinner.getSelectedItem().toString();
            String to = toSpinner.getSelectedItem().toString();

            double result = 0;

            switch (category) {
                case "Length":
                    result = convertLength(number, from, to);
                    break;
                case "Weight":
                    result = convertWeight(number, from, to);
                    break;
                case "Temperature":
                    result = convertTemperature(number, from, to);
                    break;
            }

            answer.setText(number + " " + from + " = " + result + " " + to);

        });
    }
    private double convertLength(double value, String from, String to) {

        if (from.equals("cm") && to.equals("m")) {
            return value / 100;
        }

        if (from.equals("m") && to.equals("cm")) {
            return value * 100;
        }

        return value;
    }
    private double convertWeight(double value, String from, String to) {

        if (from.equals("kg") && to.equals("g")) {
            return value * 1000;
        }

        if (from.equals("g") && to.equals("kg")) {
            return value / 1000;
        }

        return value;
    }
    private double convertTemperature(double value, String from, String to) {

        if (from.equals("Celsius") && to.equals("Fahrenheit")) {
            return (value * 9 / 5) + 32;
        }

        if (from.equals("Fahrenheit") && to.equals("Celsius")) {
            return (value - 32) * 5 / 9;
        }

        return value;
    }
}