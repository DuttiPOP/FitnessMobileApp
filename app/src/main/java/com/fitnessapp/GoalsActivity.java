package com.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class GoalsActivity extends AppCompatActivity {

    EditText etHeight, etWeight, etGoal, etExperience;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goals);

        etHeight = findViewById(R.id.et_height);
        etWeight = findViewById(R.id.et_weight);
        etGoal = findViewById(R.id.et_goal);
        etExperience = findViewById(R.id.et_experience);
        btnSave = findViewById(R.id.btn_save);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveUserData();
                requestWorkoutPlan();
            }
        });
    }

    private void saveUserData() {
    }

    private void requestWorkoutPlan() {
    }
}
