package com.fitnessapp.tools;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.content.SharedPreferences;



public class SharedPreferencesHelper {
    private static final String Data_Name = "UserData";
    private static final String Id = "id";
    private static final String Logged = "logged";

    static private SharedPreferences getSharedPreferences(Activity activity){
        return activity.getApplicationContext().getSharedPreferences(Data_Name, MODE_PRIVATE);
    }
    static public void setValue(Activity activity, String key, String value){
        SharedPreferences.Editor editor = getSharedPreferences(activity).edit();
        editor.putString(key, value);
        editor.apply();
    }

    static public void setValue(Activity activity, String key, int value){
        SharedPreferences.Editor editor = getSharedPreferences(activity).edit();
        editor.putInt(key, value);
        editor.apply();
    }

    static public void setValue(Activity activity, String key, boolean value){
        SharedPreferences.Editor editor = getSharedPreferences(activity).edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    static public boolean getBoolean(Activity activity, String key){
        return getSharedPreferences(activity).getBoolean(key, false);
    }

    static public String getString(Activity activity, String key){
        return getSharedPreferences(activity).getString(key, "");
    }

    static public int getInt(Activity activity, String key){
        return getSharedPreferences(activity).getInt(key, -1);
    }


    public static int getId(Activity activity){
        return getInt(activity, Id);
    }

    public static void setId(Activity activity, int id){
        setValue(activity, Id, id);
    }
    public static boolean getLogged(Activity activity){
        return getBoolean(activity, Logged);
    }

    public static void setLogged(Activity activity, boolean logged){
        setValue(activity, Logged, logged);
    }




}
