package com.example.creditmanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Report extends AppCompatActivity {
    DBHelper3 DB;
    RecyclerView recyclerView2;
    ArrayList<String> tran_id,tran_date,tran_gave,tran_got;
    String id,name,phone;
    TextView nameTitle_txt;
    EditText amt_txt;
    TransactionAdapter transactionAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        nameTitle_txt=(TextView)findViewById(R.id.nameTitle_txt);
        amt_txt=(EditText)findViewById(R.id.amt_txt);

        Button gaveBtn=(Button)findViewById(R.id.gave_Btn);
        Button gotBtn=(Button)findViewById(R.id.gotBtn);
        DB=new DBHelper3(Report.this);
        recyclerView2=(RecyclerView)findViewById(R.id.recyclerView2);

        //Date and Time
        Date dateAndTime=Calendar.getInstance().getTime();
        SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        SimpleDateFormat timeFormat=new SimpleDateFormat("hh:mm a", Locale.getDefault());
        String date=dateFormat.format(dateAndTime);
        String time=timeFormat.format(dateAndTime);
        String dateTime=date + " " + time;



        tran_id=new ArrayList<>();
        tran_date=new ArrayList<>();
        tran_gave=new ArrayList<>();
        tran_got=new ArrayList<>();
        transactionAdapter=new TransactionAdapter(Report.this,this,tran_id,tran_date,tran_gave,tran_got);
        recyclerView2.setAdapter(transactionAdapter);
        recyclerView2.setLayoutManager(new LinearLayoutManager(Report.this));

        gaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String amt=amt_txt.getText().toString().trim();
                String got="0";

                if(TextUtils.isEmpty(amt)){
                    Toast.makeText(Report.this,"Enter an amount",Toast.LENGTH_SHORT).show();
                }
                else{
                    DB.addTransaction(id,dateTime,amt,got);
                    amt_txt.setText("");
                }
            }
        });

        gotBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String amt=amt_txt.getText().toString().trim();
                String gave="0";

                if(TextUtils.isEmpty(amt)){
                    Toast.makeText(Report.this,"Enter an amount",Toast.LENGTH_SHORT).show();
                }
                else{
                    DB.addTransaction(id,dateTime,gave,amt);
                    amt_txt.setText("");
                    Intent i=new Intent(Report.this,HomePage.class);
                    startActivity(i);
                }
            }
        });

        getAndSetIntentData();
        storeDataInArrays2();
    }
    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("name") &&
                getIntent().hasExtra("phone")) {
            //Getting Data from Intent
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            phone = getIntent().getStringExtra("phone");

            nameTitle_txt.setText(name);

        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    void storeDataInArrays2(){
        Cursor cursor=DB.readTransactions(id);
        if(cursor.getCount()==0){
            Toast.makeText(this,"No data",Toast.LENGTH_SHORT).show();
        }
        else{
            while (cursor.moveToNext()){
                tran_id.add(cursor.getString(0));
                tran_date.add(cursor.getString(1));
                tran_gave.add(cursor.getString(2));
                tran_got.add(cursor.getString(3));
            }
        }
    }
}