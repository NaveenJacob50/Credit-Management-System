package com.example.creditmanagement;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    //private Activity activity;
    private ArrayList c_id, c_name,c_phone;
    CustomAdapter(Context context, ArrayList c_id, ArrayList c_name,ArrayList c_phone){
       // this.activity=activity;
        this.context = context;
        this.c_id = c_id;
        this.c_name = c_name;
        this.c_phone=c_phone;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.customer_id_txt.setText(String.valueOf(c_id.get(position)));
        holder.customer_name_txt.setText(String.valueOf(c_name.get(position)));
        holder.customer_mobile_txt.setText(String.valueOf(c_phone.get(position)));
    }

    @Override
    public int getItemCount() {
        return c_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView customer_id_txt,  customer_name_txt,customer_mobile_txt;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            customer_id_txt = itemView.findViewById(R.id.customer_id_txt);
            customer_name_txt = itemView.findViewById(R.id.customer_name_txt);
            customer_mobile_txt=itemView.findViewById(R.id.customer_phone_txt);
        }

    }

}
