package com.example.creditmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText name_txt=(EditText)findViewById(R.id.name_txt);
        EditText pass_txt=(EditText)findViewById(R.id.pass_txt);

        Button btn1=(Button) findViewById(R.id.save_btn);
        Button btn2=(Button) findViewById(R.id.createBtn);
        Button btn3=(Button)findViewById(R.id.phoneBtn);
        DB=new DBHelper(this);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=name_txt.getText().toString();
                String password=pass_txt.getText().toString();

                if(TextUtils.isEmpty(email)||TextUtils.isEmpty(password)){
                    Toast.makeText(MainActivity.this,"All Fields Required",Toast.LENGTH_SHORT).show();
                }
                else{
                    Boolean checkuserpass=DB.checkusernamepassword(email,password);
                    if(checkuserpass==true){
                        Toast.makeText(MainActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();
                        Intent i=new Intent(getApplicationContext(),HomePage.class);
                        startActivity(i);
                    }
                    else{
                        Toast.makeText(MainActivity.this,"Login Failed",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,RegisterPage.class);
                startActivity(i);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,PhoneLogin.class);
                startActivity(i);
            }
        });
    }
}