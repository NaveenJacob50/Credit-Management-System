package com.example.creditmanagement.MyAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.creditmanagement.CustomAdapter;
import com.example.creditmanagement.Model.RowModel;
import com.example.creditmanagement.R;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyHolder> {

    Context context;
    List<RowModel> modelList;

    public Adapter(Context context,List<RowModel> modelList){
        this.context=context;
        this.modelList=modelList;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.my_row,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        int id=modelList.get(position).getId();
        String name=modelList.get(position).getName();
        String phone=modelList.get(position).getPhone();

        holder.customer_id_txt.setText(id);
        holder.customer_name_txt.setText(name);
        holder.customer_mobile_txt.setText(phone);
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{
        TextView customer_id_txt,  customer_name_txt,customer_mobile_txt;
        public MyHolder(@NonNull View itemView) {
            super(itemView);

            customer_id_txt = itemView.findViewById(R.id.customer_id_txt);
            customer_name_txt = itemView.findViewById(R.id.customer_name_txt);
            customer_mobile_txt=itemView.findViewById(R.id.customer_phone_txt);
        }
    }
}
