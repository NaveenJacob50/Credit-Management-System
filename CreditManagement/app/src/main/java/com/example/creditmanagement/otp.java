package com.example.creditmanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class otp extends AppCompatActivity {
    EditText inputnumber1,inputnumber2,inputnumber3,inputnumber4,inputnumber5,inputnumber6;
    String getotpbackend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        inputnumber1=(EditText)findViewById(R.id.inputotp1);
        inputnumber2=(EditText)findViewById(R.id.inputotp2);
        inputnumber3=(EditText)findViewById(R.id.inputotp3);
        inputnumber4=(EditText)findViewById(R.id.inputotp4);
        inputnumber5=(EditText)findViewById(R.id.inputotp5);
        inputnumber6=(EditText)findViewById(R.id.inputotp6);
        TextView textView=(TextView)findViewById(R.id.textmobile);
        textView.setText(String.format(
                "+91-%s", getIntent().getStringExtra("mobile")
                ));

        getotpbackend=getIntent().getStringExtra("backendotp");
        final ProgressBar progressBarverifyotp=findViewById(R.id.progressbar_verify_otp);

        final Button btn1=(Button)findViewById(R.id.save_btn);
        Button btn2=(Button)findViewById(R.id.resendBtn);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number1=inputnumber1.getText().toString().trim();
                String number2=inputnumber2.getText().toString().trim();
                String number3=inputnumber3.getText().toString().trim();
                String number4=inputnumber4.getText().toString().trim();
                String number5=inputnumber5.getText().toString().trim();
                String number6=inputnumber6.getText().toString().trim();

                if(!number1.isEmpty() && !number2.isEmpty() && !number3.isEmpty() && !number4.isEmpty() && !number5.isEmpty() && !number6.isEmpty()){
                    String entercodeotp=inputnumber1.getText().toString()+
                            inputnumber2.getText().toString() +
                            inputnumber3.getText().toString() +
                            inputnumber4.getText().toString() +
                            inputnumber5.getText().toString() +
                            inputnumber6.getText().toString();

                    if(getotpbackend!=null){
                        progressBarverifyotp.setVisibility(View.VISIBLE);
                        btn1.setVisibility(View.INVISIBLE);

                        PhoneAuthCredential phoneAuthCredential=PhoneAuthProvider.getCredential(
                                getotpbackend,entercodeotp
                        );
                        FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        progressBarverifyotp.setVisibility(View.GONE);
                                        btn1.setVisibility(View.VISIBLE);

                                        if(task.isSuccessful()){
                                            Intent i=new Intent(getApplicationContext(),HomePage.class);
                                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                            startActivity(i);
                                            Toast.makeText(otp.this,"OTP Verified",Toast.LENGTH_SHORT).show();
                                        }
                                        else{
                                            Toast.makeText(otp.this,"Enter the correct OTP",Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    }
                    else{
                        Toast.makeText(otp.this,"Please check internet connection",Toast.LENGTH_SHORT).show();
                    }

                    //Toast.makeText(otp.this,"OTP Verified",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(otp.this,"Please enter all numbers",Toast.LENGTH_SHORT).show();
                }

                //Intent i=new Intent(otp.this,HomePage.class);
                //startActivity(i);
            }
        });
        numberotpmove();

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+91" + getIntent().getStringExtra("mobile"),
                        60,
                        TimeUnit.SECONDS,
                        otp.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {

                                Toast.makeText(otp.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String newbackendotp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                getotpbackend=newbackendotp;

                                Toast.makeText(otp.this,"Otp sended Successfully",Toast.LENGTH_SHORT).show();
                            }
                        }
                );
            }
        });
    }

    private void numberotpmove(){
        inputnumber1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    boolean checkFocus=inputnumber2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        inputnumber2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    boolean checkFocus=inputnumber3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        inputnumber3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    boolean checkFocus=inputnumber4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        inputnumber4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    boolean checkFocus=inputnumber5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        inputnumber5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    boolean checkFocus=inputnumber6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }


}