package com.fitnessapp.tools;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.fitnessapp.user.User;

import java.lang.annotation.Target;

public class DatabaseHelper extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "GymBuddy.db";
    private static final int DATABASE_VERSION = 3;
    private static final String TABLE_USERS = "users";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_FIRSTNAME = "firstName";
    private static final String COLUMN_LASTNAME = "lastName";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_AGE = "age";
    private static final String COLUMN_GENDER = "gender";
    private static final String COLUMN_WEIGHT = "weight";
    private static final String COLUMN_HEIGHT = "height";
    private static final String COLUMN_EXPERIENCE = "experience";
    private static final String COLUMN_GUEST = "guest";



    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_FIRSTNAME + " TEXT,"
                + COLUMN_LASTNAME + " TEXT,"
                + COLUMN_EMAIL + " TEXT,"
                + COLUMN_AGE + " INTEGER,"
                + COLUMN_GENDER + " TEXT,"
                + COLUMN_WEIGHT + " REAL,"
                + COLUMN_HEIGHT + " REAL,"
                + COLUMN_EXPERIENCE + " INTEGER,"
                + COLUMN_GUEST + " INTEGER"
                + ")";
        db.execSQL(CREATE_USERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_USERS);
        onCreate(db);
    }

    public int saveUser(User user){
        ContentValues values = new ContentValues();
        values.put(COLUMN_FIRSTNAME, user.getFirstName());
        values.put(COLUMN_LASTNAME, user.getLastName());
        values.put(COLUMN_EMAIL, user.getEmail());
        values.put(COLUMN_AGE, user.getAge());
        values.put(COLUMN_GENDER, user.getGender());
        values.put(COLUMN_WEIGHT, user.getWeight());
        values.put(COLUMN_HEIGHT, user.getHeight());
        values.put(COLUMN_EXPERIENCE, user.getExperience());
        values.put(COLUMN_GUEST, user.isGuest() ? 1 : 0);
        return (int) this.getWritableDatabase().insert(TABLE_USERS, null, values);

    }

    public User getUser(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_USERS + " where " + COLUMN_ID + "="+ id, null);
        User user = new User();
        if (cursor.moveToFirst()) {
            user.setFirstName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FIRSTNAME)));
            user.setLastName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_LASTNAME)));
            user.setEmail(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EMAIL)));
            user.setAge(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_AGE)));
            user.setGender(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_GENDER)));
            user.setWeight(cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_WEIGHT)));
            user.setHeight(cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_HEIGHT)));
            user.setExperience(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_EXPERIENCE)));
            user.setGuest(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_GUEST)) == 1);
            db.close();
            cursor.close();
        }
        return user;
    }




}
