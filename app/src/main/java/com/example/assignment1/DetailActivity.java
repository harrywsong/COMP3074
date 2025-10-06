package com.example.assignment1;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    private ListView paymentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        paymentList = findViewById(R.id.paymentList);

        ArrayAdapter<Payment> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                MainActivity.paymentList
        );

        paymentList.setAdapter(adapter);
    }
}