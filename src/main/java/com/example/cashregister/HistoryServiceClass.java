package com.example.cashregister;

import java.util.ArrayList;
import java.util.Date;

public class HistoryServiceClass {

    ArrayList<History> historyList = new ArrayList<>(0);

    HistoryServiceClass(){
        historyList.add(new History("Pants","100","5", new Date()));
        historyList.add(new History("Shirts","200","2", new Date()));
    }
    void addNewHistory(History history){
        historyList.add(history);
    }
}
