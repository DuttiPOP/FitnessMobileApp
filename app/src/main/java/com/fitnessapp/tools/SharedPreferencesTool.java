package com.fitnessapp.tools;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;



public class SharedPreferencesTool {



    static public void setSharedPreferences(Activity activity, String key, String value){
        SharedPreferences sharedPreferences = activity.getApplicationContext().getSharedPreferences("UserData", MODE_PRIVATE);
    }


}
