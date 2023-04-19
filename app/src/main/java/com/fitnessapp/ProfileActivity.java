package com.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {
    TextView tvUsername;
    Button btnViewWorkoutPlan, btnViewWorkoutLog, btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        SharedPreferences sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE);

        tvUsername = findViewById(R.id.tv_username);
        btnViewWorkoutPlan = findViewById(R.id.btn_view_workout_plan);
        btnViewWorkoutLog = findViewById(R.id.btn_view_workout_log);
        btnLogout = findViewById(R.id.btn_logout);

        tvUsername.setText("Привет! "+ sharedPreferences.getString("name", "") + ".");

        btnViewWorkoutPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        btnViewWorkoutLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();
                Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}