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

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    //private Activity activity;
    private ArrayList c_id, c_name,c_phone;
    private Activity activity;

    CustomAdapter(Activity activity,Context context, ArrayList c_id, ArrayList c_name,ArrayList c_phone){
        this.activity=activity;
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
    public void onBindViewHolder(@NonNull final MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.customer_id_txt.setText(String.valueOf(c_id.get(position)));
        holder.customer_name_txt.setText(String.valueOf(c_name.get(position)));
        holder.customer_mobile_txt.setText(String.valueOf(c_phone.get(position)));

        //Recyclerview onClickListener
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Report.class);
                intent.putExtra("id", String.valueOf(c_id.get(position)));
                intent.putExtra("name", String.valueOf(c_name.get(position)));
                intent.putExtra("phone", String.valueOf(c_phone.get(position)));;
                activity.startActivityForResult(intent, 1);

            }
        });
    }

    @Override
    public int getItemCount() {
        return c_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView customer_id_txt,  customer_name_txt,customer_mobile_txt;
        LinearLayout mainLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            customer_id_txt = itemView.findViewById(R.id.transaction_date_txt);
            customer_name_txt = itemView.findViewById(R.id.transaction_gave_txt);
            customer_mobile_txt=itemView.findViewById(R.id.transaction_got_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }


    }

}
