package com.example.creditmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterPage extends AppCompatActivity {
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        EditText name_txt=(EditText)findViewById(R.id.total_got_txt);
        EditText company_txt=(EditText)findViewById(R.id.cphone_txt);
        EditText phone_txt=(EditText)findViewById(R.id.phone_txt);
        EditText email_txt=(EditText)findViewById(R.id.email_txt);
        EditText password_txt=(EditText)findViewById(R.id.password_txt2);
        EditText cpassword_txt=(EditText)findViewById(R.id.cpassword_txt);

        Button btn1=(Button) findViewById(R.id.registerBtn);
        Button btn2=(Button) findViewById(R.id.emailBtn);
        Button btn3=(Button)findViewById(R.id.phoneBtn);
        DB=new DBHelper(this);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=name_txt.getText().toString();
                String company=company_txt.getText().toString();
                String phone=phone_txt.getText().toString();
                String email=email_txt.getText().toString();
                String password=password_txt.getText().toString();
                String cpassword=cpassword_txt.getText().toString();

                if(TextUtils.isEmpty(name)||TextUtils.isEmpty(company)||TextUtils.isEmpty(phone)||TextUtils.isEmpty(email)||TextUtils.isEmpty(password)||TextUtils.isEmpty(cpassword)){
                    Toast.makeText(RegisterPage.this,"All fields required",Toast.LENGTH_SHORT).show();
                }
                else{
                    if(name.length()>=5){
                        if(company.length()>=5){
                            if(phone.length()==10){
                                if(Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                                    if(password.length()>8){
                                        if(password.equals(cpassword)){
                                            Boolean checkuser=DB.checkusername(email);
                                            if(checkuser==false){
                                                Boolean insert=DB.createUser(name,company,phone,email,password,cpassword);
                                                if(insert==true){
                                                    Toast.makeText(RegisterPage.this,"Registration Successfully",Toast.LENGTH_SHORT).show();
                                                    Intent i=new Intent(getApplicationContext(),HomePage.class);
                                                    startActivity(i);
                                                }
                                                else{
                                                    Toast.makeText(RegisterPage.this,"Registration Failed",Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                            else{
                                                Toast.makeText(RegisterPage.this,"User already Exists",Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                        else{
                                            Toast.makeText(RegisterPage.this,"Passwords are not matching",Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                    else{
                                        Toast.makeText(RegisterPage.this,"Password length should be greater than 8",Toast.LENGTH_SHORT).show();
                                    }
                                }
                                else{
                                    Toast.makeText(RegisterPage.this,"Invalid email address",Toast.LENGTH_SHORT).show();
                                }
                            }
                            else{
                                Toast.makeText(RegisterPage.this,"Invalid phone number",Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(RegisterPage.this,"Company Name should be greater than 5 characters",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(RegisterPage.this,"Name should be greater than 5 characters",Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),PhoneLogin.class);
                startActivity(i);
            }
        });
    }
}