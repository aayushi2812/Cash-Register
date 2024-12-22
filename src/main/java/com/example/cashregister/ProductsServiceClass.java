package com.example.cashregister;

import java.util.ArrayList;

public class ProductsServiceClass {

    ArrayList<Products> productsList = new ArrayList<>(0);

    ProductsServiceClass(){
        productsList.add(new Products("Pants","20","10"));
        productsList.add(new Products("Shirts","10","20"));
    }
    void addNewProduct(Products product){
        productsList.add(product);
    }
}
