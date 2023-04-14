package com.example.creditmanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewReport extends AppCompatActivity {
    DBHelper3 DB;
    RecyclerView recyclerView2;
    ArrayList<String> tran_id,tran_date,tran_gave,tran_got;
    ReportAdapter reportAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_report);
        DB=new DBHelper3(this);
        recyclerView2=(RecyclerView)findViewById(R.id.recyclerView2);

        tran_id=new ArrayList<>();
        tran_date=new ArrayList<>();
        tran_gave=new ArrayList<>();
        tran_got=new ArrayList<>();
        reportAdapter=new ReportAdapter(ViewReport.this,this,tran_id,tran_date,tran_gave,tran_got);
        recyclerView2.setAdapter(reportAdapter);
        recyclerView2.setLayoutManager(new LinearLayoutManager(ViewReport.this));

        storeDataInArrays2();
    }

    void storeDataInArrays2(){
        Cursor cursor=DB.readReport();
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