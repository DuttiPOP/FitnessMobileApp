package com.fitnessapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.fitnessapp.R;
import com.fitnessapp.tools.DatabaseHelper;
import com.fitnessapp.tools.SharedPreferencesHelper;
import com.fitnessapp.user.User;
import com.google.android.material.textfield.TextInputEditText;

public class RegisterActivity extends AppCompatActivity {
    TextInputEditText editEmailAddress, editPassword, editFirstName, editLastName;
    Button btnRegister;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editEmailAddress = findViewById(R.id.fieldEmail);
        editPassword = findViewById(R.id.fieldPassword);
        editFirstName = findViewById(R.id.fieldFirstName);
        editLastName = findViewById(R.id.fieldLastName);
        btnRegister = findViewById(R.id.btn_register);

        databaseHelper = new DatabaseHelper(this);
        editPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0 && s.length() < 6){
                    editPassword.setError("Пароль не должен быть короче 6 символов");
                }
                else{
                    editPassword.setError(null);
                }
            }
        });


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User(editFirstName.getText().toString(), editLastName.getText().toString(), editEmailAddress.getText().toString());
                int id = databaseHelper.saveUser(user);

                if (id >= 0){
                    SharedPreferencesHelper.setLogged((Activity)view.getContext(), true);
                    SharedPreferencesHelper.setId((Activity)view.getContext(), id);
                    Intent intent = new Intent(RegisterActivity.this, ProfileActivity.class);
                    intent.putExtra(User.class.getSimpleName(), user);
                    startActivity(intent);
                    finish();
                }
                else{
                    Toast.makeText(RegisterActivity.this, "Ошибка регистрации пользователя!", Toast.LENGTH_LONG).show();
                }


            }
        });
    }
}
