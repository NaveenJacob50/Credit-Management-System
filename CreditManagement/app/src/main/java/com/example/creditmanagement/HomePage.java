package com.example.creditmanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.SearchEvent;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    DBHelper2 DB;
    RecyclerView recyclerView;
    ArrayList<String> c_id,c_name,c_phone;
    CustomAdapter customAdapter;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        searchView=findViewById(R.id.searchView);
        searchView.clearFocus();

        recyclerView=findViewById(R.id.recyclerView);
        DB=new DBHelper2(HomePage.this);
        c_id=new ArrayList<>();
        c_name=new ArrayList<>();
        c_phone=new ArrayList<>();
        customAdapter=new CustomAdapter(HomePage.this,c_id,c_name,c_phone);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(HomePage.this));

        Button btn1=(Button) findViewById(R.id.hiBtn);
        Button btn2=(Button) findViewById(R.id.viewReportBtn);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(HomePage.this,Report.class);
                startActivity(i);
            }
        });
        storeDataInArrays();

    }



    void storeDataInArrays(){
        Cursor cursor=DB.readAllData();
        if(cursor.getCount()==0){
            Toast.makeText(this,"No data",Toast.LENGTH_SHORT).show();
        }
        else{
            while (cursor.moveToNext()){
                c_id.add(cursor.getString(0));
                c_name.add(cursor.getString(1));
                c_phone.add(cursor.getString(2));
            }
        }
    }
    public void showPopup(View v){
        PopupMenu popup=new PopupMenu(this,v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.popup_menu);
        popup.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch(item.getItemId()){
            case R.id.item1:
                Intent i=new Intent(HomePage.this,AddCustomer.class);
                startActivity(i);
                return true;
            case R.id.item2:
                Intent j=new Intent(HomePage.this,MainActivity.class);
                startActivity(j);
                return true;
            default:
                return false;
        }
    }


}