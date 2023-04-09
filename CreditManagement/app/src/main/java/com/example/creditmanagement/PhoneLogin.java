package com.example.creditmanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class PhoneLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_login);

        EditText phone=(EditText)findViewById(R.id.phoneno_txt);
        final ProgressBar progressBar=(ProgressBar)findViewById(R.id.progressbar_sending_otp);

        final Button btn1=(Button) findViewById(R.id.loginBtn);
        Button btn2=(Button) findViewById(R.id.createBtn);
        Button btn3=(Button)findViewById(R.id.emailBtn);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!phone.getText().toString().trim().isEmpty()){
                    if((phone.getText().toString().trim().length()==10)){

                        progressBar.setVisibility(View.VISIBLE);
                        btn1.setVisibility(View.INVISIBLE);

                        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                                "+91" + phone.getText().toString(),
                                60,
                                TimeUnit.SECONDS,
                                PhoneLogin.this,
                                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                    @Override
                                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                        progressBar.setVisibility(View.GONE);
                                        btn1.setVisibility(View.VISIBLE);
                                    }

                                    @Override
                                    public void onVerificationFailed(@NonNull FirebaseException e) {
                                        progressBar.setVisibility(View.GONE);
                                        btn1.setVisibility(View.VISIBLE);
                                        Toast.makeText(PhoneLogin.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onCodeSent(@NonNull String backendotp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                        progressBar.setVisibility(View.GONE);
                                        btn1.setVisibility(View.VISIBLE);
                                        Intent i=new Intent(getApplicationContext(),otp.class);
                                        i.putExtra("mobile",phone.getText().toString());
                                        i.putExtra("backendotp",backendotp);
                                        startActivity(i);
                                    }
                                }
                        );
                    }
                    else{
                        Toast.makeText(PhoneLogin.this,"Please enter correct number",Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(PhoneLogin.this,"Enter mobile number",Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(PhoneLogin.this,RegisterPage.class);
                startActivity(i);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(PhoneLogin.this,MainActivity.class);
                startActivity(i);
            }
        });
    }
}