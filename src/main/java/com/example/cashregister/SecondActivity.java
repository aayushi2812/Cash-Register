package com.example.cashregister;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SecondActivity extends AppCompatActivity {

    Button history;
    Button restock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);

        findViewById(R.id.history).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tohistory = new Intent(SecondActivity.this, HistoryActivity.class);
                startActivity(tohistory);
            }
        });

        findViewById(R.id.restock).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent torestock = new Intent(SecondActivity.this, RestockActivity.class);
                startActivity(torestock);
            }
        });
    }
}