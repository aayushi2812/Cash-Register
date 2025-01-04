package com.example.cashregister;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ProductBaseAdapter extends BaseAdapter {

    ArrayList<Products> list;
    Context context;

    interface ProductClickListener {
        void productSelected(String name, String price, String quantity);
    }

    ProductClickListener productClickListener;
    ProductBaseAdapter(ArrayList<Products> l, Context c){
        list = l;
        context = c;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        View v = LayoutInflater.from(context).inflate(R.layout.row_layout, viewGroup,false);
        TextView productNameInRow = v.findViewById(R.id.productNameInRow);
        TextView quantityInRow = v.findViewById(R.id.quantityInRow);
        TextView priceInRow = v.findViewById(R.id.priceInRow);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("onclick");
                System.out.println(i + list.get(i).productType);
                productClickListener.productSelected(list.get(i).productType, list.get(i).price, list.get(i).quantity);
            }
        });

        productNameInRow.setText(list.get(i).productType);
        quantityInRow.setText(list.get(i).quantity);
        priceInRow.setText(list.get(i).price + "$");

        return v;
    }
}
