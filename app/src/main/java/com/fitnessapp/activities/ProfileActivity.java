package com.fitnessapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.fitnessapp.R;
import com.fitnessapp.tools.DatabaseHelper;
import com.fitnessapp.tools.RedirectController;
import com.fitnessapp.tools.SharedPreferencesHelper;
import com.fitnessapp.user.User;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

public class ProfileActivity extends AppCompatActivity {

    Context context = this;
    TextView tvUsername;
    Button toWorkoutPlanButton, toWorkoutLogButton, logoutButton, toPersonalDataButton;
    MaterialCalendarView calendarView;

    DatabaseHelper databaseHelper;
    SQLiteDatabase db;
    Cursor cursor;
    Bundle arguments;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        arguments = getIntent().getExtras();
        user = arguments.getParcelable(User.class.getSimpleName());


        tvUsername = findViewById(R.id.tv_username);
        toPersonalDataButton = findViewById(R.id.toPersonalDataButton);
        toWorkoutPlanButton = findViewById(R.id.toWorkoutPlanButton);
        toWorkoutLogButton = findViewById(R.id.toWorkoutLogButton);
        logoutButton = findViewById(R.id.logoutButton);
        calendarView = findViewById(R.id.workoutCalendar);

        CalendarDay dateToDecorate = CalendarDay.today();

        int colorToUse = ContextCompat.getColor(this, R.color.purple_700);


        calendarView.addDecorator(new DayViewDecorator() {
            @Override
            public boolean shouldDecorate(CalendarDay day) {
                return false;
            }

            @Override
            public void decorate(DayViewFacade view) {

            }
        });


        calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                RedirectController.switchToAnotherActivity(context, WorkoutLogActivity.class);
            }
        });

        tvUsername.setText(String.format("Привет, %s!", user.getFirstName()));

        toWorkoutPlanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RedirectController.switchToAnotherActivity(context, WorkoutPlanActivity.class);
            }
        });

        toWorkoutLogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RedirectController.switchToAnotherActivity(context, WorkoutLogActivity.class);
            }
        });

        toPersonalDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RedirectController.switchToAnotherActivity(context, PersonalDataActivity.class, user);
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setMessage("Вы действительно хотите выйти из аккаунта?")
                        .setCancelable(false)
                        .setPositiveButton("Выход", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                SharedPreferencesHelper.setLogged((Activity) view.getContext(), false);
                                SharedPreferencesHelper.setId((Activity) view.getContext(), -1);
                                Intent intent = new Intent(context, LoginActivity.class);
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