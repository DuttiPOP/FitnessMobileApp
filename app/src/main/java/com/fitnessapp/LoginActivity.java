package com.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fitnessapp.tools.DatabaseHelper;
import com.fitnessapp.tools.SharedPreferencesHelper;
import com.fitnessapp.user.User;


public class LoginActivity extends AppCompatActivity {
    EditText etEmail, etPassword;
    Button loginButton, registrationButton, asGuestButton;
    DatabaseHelper databaseHelper;
    SQLiteDatabase db;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        loginButton = findViewById(R.id.loginButton);
        registrationButton = findViewById(R.id.registrationButton);
        asGuestButton = findViewById(R.id.asGuestButton);

        databaseHelper = new DatabaseHelper(this);

        if (SharedPreferencesHelper.getLogged(this)){
            User user = databaseHelper.getUser(SharedPreferencesHelper.getId(this));
            Intent intent = new Intent(this, ProfileActivity.class);
            intent.putExtra(User.class.getSimpleName(), user);
            startActivity(intent);
            finish();
        }


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String emailString = etEmail.getText().toString();
                String passwordString = etPassword.getText().toString();



                /*
                AuthRequestBody authbody = new AuthRequestBody();
                authbody.setEmail(emailString);
                authbody.setPassword(passwordString);


                Call<ResponseBody> response = RetrofitClient
                        .auth("http://localhost:8080/")
                        .create(ApiService.class)
                        .auth(authbody);

                response.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            String jsonResponse = response.body().toString();
                            Gson gson = new Gson();
                            JsonElement jsonElement = new JsonParser().parse(jsonResponse);
                            if (jsonElement.getAsJsonObject().has("email")) {
                                User user = gson.fromJson(jsonResponse, User.class);
                                saveUserData(user.getName(), user.getAge(), user.getEmail());
                                Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }
                        else {
                            Toast.makeText(LoginActivity.this, "Неверный логин или пароль", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                    }

                });*/

            }
        });

        asGuestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                int id = databaseHelper.saveUser(user);

                if (id >= 0){
                    SharedPreferencesHelper.setLogged((Activity)v.getContext(), true);
                    SharedPreferencesHelper.setId((Activity)v.getContext(), id);
                    Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);
                    intent.putExtra(User.class.getSimpleName(), user);
                    startActivity(intent);
                    finish();
                }
                else{
                    Toast.makeText(LoginActivity.this, "Ошибка авторизации гостя!", Toast.LENGTH_LONG).show();
                }


            }
        });
    }


}
