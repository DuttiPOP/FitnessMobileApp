package com.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class LoginActivity extends AppCompatActivity {
    EditText etEmail, etPassword;
    Button loginButton, registrationButton, asGuestButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPreferences = getSharedPreferences("user_preferences", Context.MODE_PRIVATE);
        boolean isLoggedIn = sharedPreferences.getBoolean("is_logged_in", false);

        if (isLoggedIn){
            Intent intent = new Intent(this, ProfileActivity.class);
            startActivity(intent);
            finish();
        }

        setContentView(R.layout.activity_login);


        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        loginButton = findViewById(R.id.loginButton);
        registrationButton = findViewById(R.id.registrationButton);
        asGuestButton = findViewById(R.id.asGuestButton);


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
                saveUserData("Пользователь", 0, "", "guest");
                Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void saveUserData(String name, int age, String email, String type) {
        SharedPreferences sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("name", name);
        editor.putInt("age", age);
        //editor.putString("email", email);
        editor.putString("type", type);
        editor.putBoolean("is_logged_in", true);
        editor.apply();
    }

}
