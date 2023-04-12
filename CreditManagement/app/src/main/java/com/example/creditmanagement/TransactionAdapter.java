package com.example.creditmanagement;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.MyViewHolder> {

    private Context context;
    private ArrayList tran_id, tran_date,tran_gave,tran_got;
    private Activity activity;

    TransactionAdapter(Activity activity,Context context, ArrayList tran_id, ArrayList tran_date,ArrayList tran_gave,ArrayList tran_got){
        this.activity=activity;
        this.context = context;
        this.tran_id=tran_id;
        this.tran_date=tran_date;
        this.tran_gave=tran_gave;
        this.tran_got=tran_got;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.tran_row, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.transaction_date_txt.setText(String.valueOf(tran_date.get(position)));
        holder.transaction_gave_txt.setText(String.valueOf(tran_gave.get(position)));
        holder.transaction_got_txt.setText(String.valueOf(tran_got.get(position)));
    }

    @Override
    public int getItemCount() {
        return tran_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView transaction_date_txt,  transaction_gave_txt,transaction_got_txt;
        LinearLayout mainLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            transaction_date_txt = itemView.findViewById(R.id.transaction_date_txt);
            transaction_gave_txt = itemView.findViewById(R.id.transaction_gave_txt);
            transaction_got_txt=itemView.findViewById(R.id.transaction_got_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }


    }

}
