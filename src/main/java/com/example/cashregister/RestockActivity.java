package com.example.cashregister;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class RestockActivity extends AppCompatActivity implements ProductBaseAdapter.ProductClickListener {
    ProductsServiceClass serviceClass;
    ListView myList;

    Button okay;

    Button cancel;

    EditText inputText;

    String p;
    Integer pr;
    Integer q;

    ProductBaseAdapter adapter;
    SharedPreferences sharedPref;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_restock);

        myList = findViewById(R.id.restockList);
        okay = findViewById(R.id.okay);
        cancel = findViewById(R.id.cancel);
        inputText = findViewById(R.id.inputText);

        okay.setOnClickListener(v -> addingQuantity());
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tosecond = new Intent(RestockActivity.this, SecondActivity.class);
                startActivity(tosecond);
            }
        });

        sharedPref = this.getSharedPreferences("allproducts", Context.MODE_PRIVATE);
        readFromSharedPreferences();
        serviceClass = ((MyApp)getApplication()).myService;
        System.out.println(serviceClass.productsList);

        adapter = new ProductBaseAdapter(serviceClass.productsList, this);
        myList.setAdapter(adapter);

        adapter.productClickListener = this;
    }

    public void updateTheListInStorage(){
        SharedPreferences.Editor editor = sharedPref.edit();
        Gson gson = new Gson();

        String json = gson.toJson( ((MyApp)getApplication()).myService.productsList);
        editor.putString("products",json);
        editor.apply();
    }
    void readFromSharedPreferences() {
        // read one todo at a time (string, string, boolean)
        String jsonfromPreferences = sharedPref.getString("products", "");
        Gson gson = new Gson();
        // decoding
        ArrayList<Products> list = gson.fromJson(jsonfromPreferences, new TypeToken<ArrayList<Products>>() {
        }.getType());
        if (list == null) {
            ((MyApp) getApplication()).myService.productsList = new ArrayList<>(0);
        } else {
            ((MyApp) getApplication()).myService.productsList = list;
        }
    }

    void addingQuantity(){
        System.out.println("adding");
        if(p != null && inputText.getText() != null){
            System.out.println(inputText.getText());

            for(int i =0 ;i<serviceClass.productsList.size();i++){
                if(p == serviceClass.productsList.get(i).productType){
                    ((MyApp)getApplication()).myService.productsList.get(i).quantity = String.valueOf(Integer.valueOf(serviceClass.productsList.get(i).quantity) + Integer.valueOf(String.valueOf(inputText.getText())));
                }
            }
            updateTheListInStorage();
            adapter.notifyDataSetChanged();
        }else{
            Toast.makeText(this, "Add quantity and select product to update", Toast.LENGTH_LONG).show();
        }
    }

    public void productSelected(String product, String price, String quantity){
        p = product;
        pr = Integer.valueOf(price);
        q = Integer.valueOf(quantity);
        System.out.println(product);
    }
}