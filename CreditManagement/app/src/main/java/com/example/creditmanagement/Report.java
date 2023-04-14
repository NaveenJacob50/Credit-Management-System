package com.example.creditmanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.Manifest;

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
    String msg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        DB=new DBHelper3(this);


        nameTitle_txt=(TextView)findViewById(R.id.nameTitle_txt);
        amt_txt=(EditText)findViewById(R.id.amt_txt);
        TextView gave_amt_txt=(TextView)findViewById(R.id.gave_amt_txt);
        TextView got_amt_txt=(TextView)findViewById(R.id.got_amt_txt);


        Button gaveBtn=(Button)findViewById(R.id.gave_Btn);
        Button gotBtn=(Button)findViewById(R.id.gotBtn);

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
                    if(ContextCompat.checkSelfPermission(Report.this, Manifest.permission.SEND_SMS)
                            ==PackageManager.PERMISSION_GRANTED){
                        msg="You gave : Rs" +amt;
                        sendSMS(msg);
                    }
                    else{
                        ActivityCompat.requestPermissions(Report.this,new String[]{Manifest.permission.SEND_SMS},100);
                    }
                    Intent i=new Intent(Report.this,HomePage.class);
                    startActivity(i);
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
                    if(ContextCompat.checkSelfPermission(Report.this, Manifest.permission.SEND_SMS)
                            ==PackageManager.PERMISSION_GRANTED){
                        msg="You got : Rs" +amt;
                        sendSMS(msg);
                    }
                    else{
                        ActivityCompat.requestPermissions(Report.this,new String[]{Manifest.permission.SEND_SMS},100);
                    }
                    Intent i=new Intent(Report.this,HomePage.class);
                    startActivity(i);
                }
            }
        });

        getAndSetIntentData();
        storeDataInArrays2();
        gave_amt_txt.setText(String.format(DB.gaveSum(id)));
        got_amt_txt.setText(String.format(DB.gotSum(id)));


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


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,@NonNull int[] grantResults){
        super.onRequestPermissionsResult(requestCode,permissions,grantResults);
        if(requestCode==100 && grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                sendSMS(msg);
        }
        else{
            Toast.makeText(this,"Permission Denied",Toast.LENGTH_SHORT).show();
        }
    }

    private void sendSMS(String msg){
        SmsManager smsManager=SmsManager.getDefault();
        smsManager.sendTextMessage(phone,null,msg,null,null);
        Toast.makeText(this,"Message is Sent",Toast.LENGTH_SHORT).show();
    }
}