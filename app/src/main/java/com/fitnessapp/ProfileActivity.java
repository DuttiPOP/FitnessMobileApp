package com.fitnessapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.fitnessapp.tools.DatabaseHelper;
import com.fitnessapp.tools.SharedPreferencesHelper;
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

        tvUsername.setText(String.format("Привет, %s!", user.getFirstName()));

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
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setMessage("Вы действительно хотите выйти из аккаунта?")
                        .setCancelable(false)
                        .setPositiveButton("Выход", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                SharedPreferencesHelper.setLogged((Activity) view.getContext(), false);
                                SharedPreferencesHelper.setId((Activity) view.getContext(), -1);
                                Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();

            }
        });
    }
}