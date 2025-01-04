package com.example.cashregister;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements ProductBaseAdapter.ProductClickListener {

    ProductsServiceClass serviceClass;
    ListView myList;
    TextView productType;
    TextView total;
    TextView quantity;
    Integer p;
    Integer q;
    Button buy;
    Button manager;
    Integer t;
    SharedPreferences sharedPref;
    ProductBaseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        quantity = findViewById(R.id.quantity);
        productType = findViewById(R.id.productType);
        total = findViewById(R.id.total);
        buy = findViewById(R.id.buy);
        buy.setOnClickListener(v -> buyProduct());
        manager = findViewById(R.id.manager);
        manager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tosecond = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(tosecond);
            }
        });
        sharedPref = this.getSharedPreferences("allproducts", Context.MODE_PRIVATE);
        readFromSharedPreferences();

        findViewById(R.id.button0).setOnClickListener(v -> appendNumber("0"));
        findViewById(R.id.button1).setOnClickListener(v -> appendNumber("1"));
        findViewById(R.id.button2).setOnClickListener(v -> appendNumber("2"));
        findViewById(R.id.button3).setOnClickListener(v -> appendNumber("3"));
        findViewById(R.id.button4).setOnClickListener(v -> appendNumber("4"));
        findViewById(R.id.button5).setOnClickListener(v -> appendNumber("5"));
        findViewById(R.id.button6).setOnClickListener(v -> appendNumber("6"));
        findViewById(R.id.button7).setOnClickListener(v -> appendNumber("7"));
        findViewById(R.id.button8).setOnClickListener(v -> appendNumber("8"));
        findViewById(R.id.button9).setOnClickListener(v -> appendNumber("9"));
        findViewById(R.id.buttonC).setOnClickListener(v -> {quantity.setText("Quantity");total.setText("Total");updateBuy();});

        serviceClass = ((MyApp)getApplication()).myService;
        myList = findViewById(R.id.products);

        adapter = new ProductBaseAdapter(serviceClass.productsList, this);
        myList.setAdapter(adapter);

        adapter.productClickListener = this;
    }

    public void buyProduct(){
        if(buy.isEnabled()){
            for(int i =0 ;i<serviceClass.productsList.size();i++){
                if(productType.getText() == serviceClass.productsList.get(i).productType){
                    System.out.println(String.valueOf(Integer.valueOf(serviceClass.productsList.get(i).quantity) - Integer.valueOf((String) quantity.getText())));
                    ((MyApp)getApplication()).myService.productsList.get(i).quantity = String.valueOf(Integer.valueOf(serviceClass.productsList.get(i).quantity) - Integer.valueOf((String) quantity.getText()));
                }
            }
            updateTheListInStorage();
            adapter.notifyDataSetChanged();
            System.out.println(((MyApp)getApplication()).myService.productsList.get(0).productType);
            System.out.println(((MyApp)getApplication()).myService.productsList.get(0).quantity);
            String message = "Thank you for your purchase! Your order is " + quantity.getText() + " " + productType.getText() + " for " + total.getText();
            ((MyApp) getApplication()).historyService.addNewHistory(new History(
                    ((MyApp)getApplication()).myService.productsList.get(0).productType,
                    (String) total.getText(),
                    (String) quantity.getText(),
                    new Date()

            ));
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        }
    }

    public void updateTheListInStorage(){
        SharedPreferences.Editor editor = sharedPref.edit();
        Gson gson = new Gson();

        String json = gson.toJson( ((MyApp)getApplication()).myService.productsList);
        editor.putString("products",json);
        editor.apply();
    }
    void readFromSharedPreferences() {
        String jsonfromPreferences = sharedPref.getString("products", "");
        Gson gson = new Gson();
        // decoding
        ArrayList<Products> list = gson.fromJson(jsonfromPreferences, new TypeToken<ArrayList<Products>>() {
        }.getType());
        ((MyApp) getApplication()).myService.productsList = list;
        if (list == null) {
            ((MyApp) getApplication()).myService.productsList = new ArrayList<>(0);
        } else {
            ((MyApp) getApplication()).myService.productsList = list;
        }

    }
    public void updateBuy(){
        buy.setEnabled(!total.getText().equals("Total"));
    }

    public void appendNumber(String num){
        if(quantity.getText().equals("Quantity")){
            t = Integer.valueOf(num);
            quantity.setText(num);
        }else{
            t = Integer.valueOf(quantity.getText() + num);
            quantity.setText(quantity.getText() + num);
        }
        setTotal(t, p);
        if(q != null){
            if(t < q){
                updateBuy();
            }else{
                buy.setEnabled(false);
                Toast.makeText(this, "Not enough quantity in the stock!", Toast.LENGTH_LONG).show();
            }
        }
    }

    void setTotal(Integer t, Integer p){
        if(!productType.getText().equals("Product Type")){
            total.setText(String.valueOf(t*p));
        }
    }
    public void productSelected(String product, String price, String quantity){
        p = Integer.valueOf(price);
        q = Integer.valueOf(quantity);
        Toast.makeText(this, product, Toast.LENGTH_LONG).show();
        productType.setText(product);
        if(!this.quantity.getText().equals("Quantity")){
            setTotal(t, p);
        }
        updateBuy();
    }
}