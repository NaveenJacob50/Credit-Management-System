package com.example.creditmanagement;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ReportAdapter extends RecyclerView.Adapter<ReportAdapter.MyViewHolder>{

    private Context context;
    private ArrayList tran_id,tran_date,tran_gave,tran_got;
    private Activity activity;

    ReportAdapter(Activity activity,Context context,ArrayList tran_id, ArrayList tran_date,ArrayList tran_gave,ArrayList tran_got){
        this.activity=activity;
        this.context = context;
        this.tran_id=tran_id;
        this.tran_date=tran_date;
        this.tran_gave=tran_gave;
        this.tran_got=tran_got;
    }

    @NonNull
    @Override
    public ReportAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.tran_row, parent, false);
        return new ReportAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReportAdapter.MyViewHolder holder, int position) {
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
