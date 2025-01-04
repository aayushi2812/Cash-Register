package com.example.cashregister;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity implements RecyclerViewAdapter.RecyclerToDoClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_history);


        RecyclerView recyclerView = findViewById(R.id.recycler_list);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(
                ((MyApp)getApplication()).historyService.historyList, this  );
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        adapter.listener = (RecyclerViewAdapter.RecyclerToDoClickListener) this;
    }

    @Override
    public void historyDetailSelected(int position) {
        Intent todetails = new Intent(HistoryActivity.this, HistoryDetails.class);
        todetails.putExtra("name", ((MyApp)getApplication()).historyService.historyList.get(position).productType);
        todetails.putExtra("quan", ((MyApp)getApplication()).historyService.historyList.get(position).quantityPurchased);
        todetails.putExtra("date", String.valueOf(((MyApp)getApplication()).historyService.historyList.get(position).purchaseDate));
        todetails.putExtra("amt", ((MyApp)getApplication()).historyService.historyList.get(position).totalAmount);
        startActivity(todetails);
//        Toast.makeText(this,t,Toast.LENGTH_LONG).show();
    }

}