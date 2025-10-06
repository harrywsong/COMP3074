package com.example.assignment1;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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
        String hoursStr = workedHours.getText().toString();
        String rateStr = hourRate.getText().toString();

        if (hoursStr.isEmpty() || rateStr.isEmpty()) {
            Toast.makeText(this, "Both fields must be filled.", Toast.LENGTH_SHORT).show();
            return;
        }

        double hours = Double.parseDouble(hoursStr);
        double rate = Double.parseDouble(rateStr);

        if (hours < 0 || rate < 0) {
            Toast.makeText(this, "Please input positive numbers only.", Toast.LENGTH_SHORT).show();
            return;
        }

        double pay;
        double overtimePay = 0;

        if (hours <= 40) {
            pay = hours * rate;
        } else {
            overtimePay = (hours - 40) * rate * 1.5;
            pay = 40 * rate;
        }

        double totalPay = pay + overtimePay;
        double tax = totalPay * 0.18;

        String results =
                "Regular Pay: $" + String.format("%.2f", pay) + "\n" +
                        "Overtime Pay: $" + String.format("%.2f", overtimePay) + "\n" +
                        "Total Pay: $" + String.format("%.2f", totalPay) + "\n" +
                        "Tax (18%): $" + String.format("%.2f", tax) + "\n" +
                        "Final Pay: $" + String.format("%.2f", totalPay-tax);

        calcResults.setText(results);

        Payment payment = new Payment(hours, rate, pay, overtimePay, totalPay, tax);
        paymentList.add(payment);

        Toast.makeText(this, "Payment calculated successfully, view in Details page.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.paymentMenu) {
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}