package com.example.buzzapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class CreateAccountActivity extends AppCompatActivity {
    EditText emailId, pass, inputname, inputsurname;
    Button createaccountbutton, yesaccount;
    FirebaseAuth mFirebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account);

        mFirebaseAuth = FirebaseAuth.getInstance();
        emailId = findViewById(R.id.emailId);
        pass = findViewById(R.id.pass);
        createaccountbutton = findViewById(R.id.createaccountbutton);
        yesaccount = findViewById(R.id.yesaccount);
        createaccountbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailId.getText().toString();
                String pwd = pass.getText().toString();
                if (email.isEmpty()){
                    emailId.setError("Please enter email id");
                    emailId.requestFocus();
                }
                else if(pwd.isEmpty()){
                    pass.setError("Please enter your password");
                    pass.requestFocus();
                }
                else if(email.isEmpty() && pwd.isEmpty()){
                    Toast.makeText(CreateAccountActivity.this,"Fields Are Empty!",Toast.LENGTH_SHORT);
                }
                else if(!(email.isEmpty() && pwd.isEmpty())){
                    mFirebaseAuth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(CreateAccountActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(CreateAccountActivity.this,"SignUp Unsuccessful, Please Try Again",Toast.LENGTH_SHORT);
                            }
                            else{
                                startActivity(new Intent(CreateAccountActivity.this, LoginActivity.class));
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(CreateAccountActivity.this,"Error Occurred!",Toast.LENGTH_SHORT);
                }
            }
        });

        yesaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CreateAccountActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });
    }
}
