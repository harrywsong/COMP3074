package com.example.assignment1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText workedHours;
    private EditText hourRate;
    private Button calcButton;
    private TextView calcResults;
    public static ArrayList<Payment> paymentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        workedHours = findViewById(R.id.workedHours);
        hourRate = findViewById(R.id.hourRate);
        calcButton = findViewById(R.id.calcButton);
        calcResults = findViewById(R.id.calcResults);

        calcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculatePay();
            }
        });
    }

    private void calculatePay() {
        String hoursStr = workedHours.getText().toString().trim();
        String rateStr = hourRate.getText().toString().trim();

        // Validate empty fields
        if (hoursStr.isEmpty() || rateStr.isEmpty()) {
            Toast.makeText(this, "Error: Both fields must be filled.", Toast.LENGTH_SHORT).show();
            return;
        }

        double hours;
        double rate;

        // Validate numeric input
        try {
            hours = Double.parseDouble(hoursStr);
            rate = Double.parseDouble(rateStr);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Error: Please enter valid numbers.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validate positive numbers
        if (hours < 0 || rate < 0) {
            Toast.makeText(this, "Error: Please input positive numbers only.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validate reasonable values
        if (hours == 0) {
            Toast.makeText(this, "Error: Hours worked cannot be zero.", Toast.LENGTH_SHORT).show();
            return;
        }

        double pay;
        double overtimePay = 0;

        // Calculate pay with overtime
        if (hours <= 40) {
            pay = hours * rate;
        } else {
            overtimePay = (hours - 40) * rate * 1.5;
            pay = 40 * rate;
        }

        double totalPay = pay + overtimePay;
        double tax = totalPay * 0.18;
        double finalPay = totalPay - tax;

        // Display results
        String results =
                "Regular Pay: $" + String.format("%.2f", pay) + "\n" +
                        "Overtime Pay: $" + String.format("%.2f", overtimePay) + "\n" +
                        "Total Pay: $" + String.format("%.2f", totalPay) + "\n" +
                        "Tax (18%): $" + String.format("%.2f", tax) + "\n" +
                        "Final Pay: $" + String.format("%.2f", finalPay);

        calcResults.setText(results);

        // Add to payment list
        Payment payment = new Payment(hours, rate, pay, overtimePay, totalPay, tax);
        paymentList.add(payment);

        // Show success message
        Toast.makeText(this, "Success: Payment calculated and saved!", Toast.LENGTH_SHORT).show();
    }
}