package com.example.cashregister;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.HistoryViewHolder> {

    interface RecyclerToDoClickListener {
        void historyDetailSelected(int position);
    }

    ArrayList<History> historyList;
    Context context;
    RecyclerToDoClickListener listener;

    RecyclerViewAdapter(ArrayList<History> l, Context c){
        historyList = l;
        context = c;
    }
    @NonNull
    @Override
    public RecyclerViewAdapter.HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                                 int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.recycler_row_layout,parent,false);
        return new HistoryViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.HistoryViewHolder holder,
                                 int position) {
        holder.taskText.setText(historyList.get(position).productType);
        holder.totalAmount.setText(historyList.get(position).totalAmount + "$");
        holder.quantityPurchased.setText(historyList.get(position).quantityPurchased);
    }

    @Override
    public int getItemCount() {
        return historyList.size();
    }

    class HistoryViewHolder extends RecyclerView.ViewHolder{

        TextView taskText;
        TextView totalAmount;
        TextView quantityPurchased;


        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            taskText =  itemView.findViewById(R.id.productNameInRecRow);
            totalAmount = itemView.findViewById(R.id.totalAmountInRecRow);
            quantityPurchased = itemView.findViewById(R.id.quantityPurchasedInRecRow);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // do nothing here
                    // just notify the activity
                    listener.historyDetailSelected(getAdapterPosition());
                }
            });
        }
    }
}
