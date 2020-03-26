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
import com.google.firebase.auth.FirebaseUser;

//in this java file we log the user in to his account using firebase database services which was created on CreateAccountActivity.
//also when user enters wrong credentials it asks him to re enter them
public class LoginActivity extends AppCompatActivity {
    EditText emailId, pass, inputname, inputsurname;
    Button loginbutton, noaccount;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        mFirebaseAuth = FirebaseAuth.getInstance();
        emailId = findViewById(R.id.emailId);
        pass = findViewById(R.id.pass);
        loginbutton = findViewById(R.id.loginbutton);
        noaccount = findViewById(R.id.noaccount);

        //Toast message asking user to login or telling user that he logged in
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
                if (mFirebaseUser != null) {
                    Toast.makeText(LoginActivity.this, "You are logged in", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(LoginActivity.this, MapsActivity.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(LoginActivity.this, "Please Login", Toast.LENGTH_SHORT).show();
                }
            }
        };

        //login button click listener instructions asking user to enter password or email in case he left the boxes empty or in case he entered them wrong
        loginbutton.setOnClickListener(new View.OnClickListener() {
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
                    Toast.makeText(LoginActivity.this,"Fields Are Empty!",Toast.LENGTH_SHORT);
                }
                else if(!(email.isEmpty() && pwd.isEmpty())){
                    mFirebaseAuth.signInWithEmailAndPassword(email, pwd).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(LoginActivity.this,"Login Error, Please Login Again!",Toast.LENGTH_SHORT);
                            }
                            else{
                                Intent intToHome = new Intent(LoginActivity.this, MapsActivity.class);
                                startActivity(intToHome);
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(LoginActivity.this,"Error Occurred!",Toast.LENGTH_SHORT);
                }
            }
        });

        noaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intSignUp = new Intent(LoginActivity.this, CreateAccountActivity.class);
                startActivity(intSignUp);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }
}
