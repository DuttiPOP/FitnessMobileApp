package com.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.fitnessapp.tools.DatabaseHelper;
import com.fitnessapp.user.User;

import java.lang.annotation.Target;

public class ProfileActivity extends AppCompatActivity {
    TextView tvUsername;
    Button btnViewWorkoutPlan, btnViewWorkoutLog, btnLogout;

    DatabaseHelper databaseHelper;
    SQLiteDatabase db;
    Cursor cursor;
    Bundle arguments;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        databaseHelper = new DatabaseHelper(this);
        db = databaseHelper.getReadableDatabase();

        arguments = getIntent().getExtras();
        user = arguments.getParcelable(User.class.getSimpleName());


        tvUsername = findViewById(R.id.tv_username);
        btnViewWorkoutPlan = findViewById(R.id.btn_view_workout_plan);
        btnViewWorkoutLog = findViewById(R.id.btn_view_workout_log);
        btnLogout = findViewById(R.id.btn_logout);

        tvUsername.setText("Привет, " + user.getFirstName() + "!");

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