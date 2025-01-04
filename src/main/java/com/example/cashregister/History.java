package com.example.cashregister;

import java.util.Date;

public class History {
    String productType;
    String totalAmount;
    String quantityPurchased;
    Date purchaseDate;

    public History(String productType, String totalAmount, String quantityPurchased, Date purchaseDate) {
        this.productType = productType;
        this.totalAmount = totalAmount;
        this.quantityPurchased = quantityPurchased;
        this.purchaseDate = purchaseDate;
    }
}
