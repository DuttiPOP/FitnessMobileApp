package com.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class PersonalDataActivity extends AppCompatActivity {

    Context context = this;

    TextInputEditText edFirstName, edLastName, edEmail, edGender, edHeight, edWeight, edExperience, edAge;
    Button btChangeAndSave;

    TextInputEditText edPassword, edNewPassword, edConfirmNewPassword;
    Button btChangePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_data);

        edFirstName = findViewById(R.id.edFirstName);
        edLastName = findViewById(R.id.edLastName);
        edEmail = findViewById(R.id.edEmail);
        edGender = findViewById(R.id.edGender);
        edHeight = findViewById(R.id.edHeight);
        edWeight = findViewById(R.id.edWeight);
        edExperience = findViewById(R.id.edExperience);
        edAge = findViewById(R.id.edAge);
        btChangeAndSave = findViewById(R.id.btChangeAndSave);

        edPassword = findViewById(R.id.edPassword);
        edNewPassword = findViewById(R.id.edNewPassword);
        edConfirmNewPassword = findViewById(R.id.edConfirmNewPassword);
        btChangePassword = findViewById(R.id.btChangePassword);


        btChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Ошибка авторизации гостя!", Toast.LENGTH_LONG).show();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}