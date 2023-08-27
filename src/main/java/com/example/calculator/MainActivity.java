package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView resultTextView;
    private StringBuilder inputBuffer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTextView = findViewById(R.id.resultTextView);
        inputBuffer = new StringBuilder();
        setButtonClickListeners();
    }

    private void setButtonClickListeners() {
        // Get references to digit and operator buttons
        Button[] digitButtons = new Button[10];
        for (int i = 0; i < 10; i++) {
            String buttonID = "button" + i;
            int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
            digitButtons[i] = findViewById(resID);
            final int digit = i;
            digitButtons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    inputBuffer.append(digit);
                    updateResult();
                }
            });
        }

        // Define operator buttons and set their click listeners
        Button addButton = findViewById(R.id.buttonAdd);
        Button subtractButton = findViewById(R.id.buttonsub);
        Button multiplyButton = findViewById(R.id.buttonMul);
        Button divideButton = findViewById(R.id.buttonDiv);
        Button clearButton = findViewById(R.id.buttonClear);
        Button equalsButton = findViewById(R.id.buttonEquals);

        // Implement the click listeners for operator buttons
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputBuffer.append("+");
                updateResult();
            }
        });

        subtractButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputBuffer.append("-");
                updateResult();
            }
        });

        multiplyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputBuffer.append("*");
                updateResult();
            }
        });

        divideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputBuffer.append("/");
                updateResult();
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputBuffer.setLength(0);
                updateResult();
            }
        });

        equalsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    // Parse inputBuffer and perform the calculation
                    String input = inputBuffer.toString();
                    double result = evaluateExpression(input);
                    inputBuffer.setLength(0);
                    inputBuffer.append(result);
                    updateResult();
                } catch (Exception e) {
                    // Handle invalid expressions or calculations
                    inputBuffer.setLength(0);
                    inputBuffer.append("Error");
                    updateResult();
                }
            }
        });
    }

    private double evaluateExpression(String expression) {
        String[] parts = expression.split("[+\\-*/]");
        double operand1 = Double.parseDouble(parts[0]);
        double operand2 = Double.parseDouble(parts[1]);
        if (expression.contains("+")) {
            return operand1 + operand2;
        } else if (expression.contains("-")) {
            return operand1 - operand2;
        } else if (expression.contains("*")) {
            return operand1 * operand2;
        } else if (expression.contains("/")) {
            return operand1 / operand2;
        } else {
            return 0;
        }
    }
    private void updateResult() {
        resultTextView.setText(inputBuffer.toString());
    }
}
