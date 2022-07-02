package com.example.amazonapplication.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.amazonapplication.R;
import com.example.amazonapplication.accountcreation.LoginActivity;
import com.example.amazonapplication.accountcreation.RegistrationActivity;

public class IntroActivity extends AppCompatActivity {

    TextView signIn, signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        //Attempt to invoke virtual method 'void android.widget.TextView.setOnClickListener(android.view.View$OnClickListener)' on a null object reference
        //        at com.example.amazonapplication.View.IntroActivity.onCreate(IntroActivity.java:25)
        //when you dont define findViewByid

        signIn=findViewById(R.id.signInTextView);
        signUp=findViewById(R.id.signUpTextView);





        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IntroActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IntroActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });


    }
}