package com.example.flowerpower;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class signup extends AppCompatActivity {
        EditText username, password, repassword;
        Button signup, login;
        DBHelper DB;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_signup);

            username = (EditText) findViewById(R.id.username);
            password = (EditText) findViewById(R.id.password);
            repassword = (EditText) findViewById(R.id.repassword);
            signup = (Button) findViewById(R.id.btnsignup1);
            DB = new DBHelper(this);

            signup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String user = username.getText().toString();
                    String pass = password.getText().toString();
                    String repass = repassword.getText().toString();

                    if(user.equals("")||pass.equals("")||repass.equals(""))
                        Toast.makeText(signup.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                    else{
                        if(pass.equals(repass)){
                            Boolean checkuser = DB.checkusername(user);
                            if(checkuser==false){
                                Boolean insert = DB.insertData(user, pass);
                                if(insert==true){
                                    Toast.makeText(signup.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                    startActivity(intent);
                                }else{
                                    Toast.makeText(signup.this, "Registration failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else{
                                Toast.makeText(signup.this, "User already exists! please sign in", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(signup.this, "Passwords not matching", Toast.LENGTH_SHORT).show();
                        }
                    } }
            });
        }
    }