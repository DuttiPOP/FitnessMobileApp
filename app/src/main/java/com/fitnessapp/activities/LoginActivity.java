package com.fitnessapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.fitnessapp.R;
import com.fitnessapp.api.ApiService;
import com.fitnessapp.auth.AuthRequestBody;
import com.fitnessapp.tools.DatabaseHelper;
import com.fitnessapp.tools.RedirectController;
import com.fitnessapp.tools.SharedPreferencesHelper;
import com.fitnessapp.user.User;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {
    Context context = this;

    TextInputEditText fieldEmail, fieldPassword;

    Button loginButton, registrationButton, asGuestButton;
    DatabaseHelper databaseHelper;
    SQLiteDatabase db;
    Cursor cursor;
    ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        fieldEmail = findViewById(R.id.editEmailAddress);
        fieldPassword = findViewById(R.id.editPassword);
        loginButton = findViewById(R.id.loginButton);
        registrationButton = findViewById(R.id.registrationButton);
        asGuestButton = findViewById(R.id.asGuestButton);



        apiService = new ApiService(context);
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

                String emailString = fieldEmail.getText().toString();
                String passwordString = fieldPassword.getText().toString();


                if (emailString.isEmpty()){
                    fieldEmail.setError("Email field is empty!");
                    return;
                }

                if (passwordString.isEmpty()){
                    fieldPassword.setError("Password field is empty!");
                    return;
                }


                AuthRequestBody authbody = new AuthRequestBody(emailString, passwordString);



                apiService.loginUser(authbody).enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (response.isSuccessful()) {
                            User user = response.body();
                            int id = databaseHelper.saveUser(user);
                            SharedPreferencesHelper.setId((Activity)context, id);
                            SharedPreferencesHelper.setLogged((Activity) context, true);
                            RedirectController.switchToAnotherActivity(context, ProfileActivity.class, true, user);
                        }
                        else {
                            Toast.makeText(LoginActivity.this, "Неверный логин или пароль", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(LoginActivity.this, "Неверный логин или пароль", Toast.LENGTH_SHORT).show();
                    }

                });

            }
        });

        registrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
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
                    RedirectController.switchToAnotherActivity(context, ProfileActivity.class, true, user);
                }
                else{
                    Toast.makeText(LoginActivity.this, "Ошибка авторизации гостя!", Toast.LENGTH_LONG).show();
                }


            }
        });
    }


}
