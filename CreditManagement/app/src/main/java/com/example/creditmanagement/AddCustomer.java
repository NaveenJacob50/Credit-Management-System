package com.example.creditmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddCustomer extends AppCompatActivity {
    EditText name,phone;
    Button saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);
        name=(EditText) findViewById(R.id.name_txt);
        phone=(EditText) findViewById(R.id.cphone_txt);
        saveBtn=(Button) findViewById(R.id.save_btn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper2 DB=new DBHelper2(AddCustomer.this);
                String cusname=name.getText().toString().trim();
                String cuspassword=phone.getText().toString().trim();
                if(TextUtils.isEmpty(cusname)||TextUtils.isEmpty(cuspassword)){
                    Toast.makeText(AddCustomer.this,"All Fields Required",Toast.LENGTH_SHORT).show();
                }
                else{
                    Boolean checknp=DB.checknamephone(cusname,cuspassword);
                    if(checknp==false) {
                        DB.addUser(cusname, cuspassword);
                        name.setText("");
                        phone.setText("");
                        Intent i = new Intent(getApplicationContext(), HomePage.class);
                        startActivity(i);
                    }
                    else{
                        Toast.makeText(AddCustomer.this,"Customer Already Exist",Toast.LENGTH_SHORT).show();
                    }


                }
            }
        });
    }
}