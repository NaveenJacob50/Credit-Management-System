package com.example.creditmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Report extends AppCompatActivity {
    DBHelper3 DB;
    String id,name,phone;
    TextView nameTitle_txt;
    EditText amt_txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        nameTitle_txt=(TextView)findViewById(R.id.nameTitle_txt);
        amt_txt=(EditText)findViewById(R.id.amt_txt);
        Button gaveBtn=(Button)findViewById(R.id.gave_Btn);
        Button gotBtn=(Button)findViewById(R.id.gotBtn);
        Date dateAndTime=Calendar.getInstance().getTime();
        SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        SimpleDateFormat timeFormat=new SimpleDateFormat("hh:mm a", Locale.getDefault());
        String date=dateFormat.format(dateAndTime);
        String time=timeFormat.format(dateAndTime);
        String dateTime=date + " " + time;
        Toast.makeText(Report.this,dateTime,Toast.LENGTH_SHORT).show();

        DB=new DBHelper3(Report.this);

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
                }
            }
        });

        getAndSetIntentData();
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
}