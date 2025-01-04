package com.example.cashregister;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HistoryDetails extends AppCompatActivity {

    String name;
    String quan;
    String date;
    String amt;

    TextView one;
    TextView two;
    TextView three;
    TextView four;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_history_details);
        name = getIntent().getStringExtra("name");
        quan = getIntent().getStringExtra("quan");
        date = getIntent().getStringExtra("date");
        amt = getIntent().getStringExtra("amt");
        System.out.println(date);
        one = findViewById(R.id.textViewProduct);
        two = findViewById(R.id.textViewAmount);
        three = findViewById(R.id.textViewDate);
        four = findViewById(R.id.textViewQuantity);

        one.setText(name);
        two.setText(amt);
        three.setText(date);
        four.setText(quan);
    }
}