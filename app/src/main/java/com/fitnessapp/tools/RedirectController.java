package com.fitnessapp.tools;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;

public class RedirectController{

    public static void switchToAnotherActivity(Context context, Class<?> cls){
        Intent intent = new Intent(context, cls);
        context.startActivity(intent);
    }

    public static void switchToAnotherActivity(Context context, Class<?> cls, Boolean finishCurrentActivity){
        Intent intent = new Intent(context, cls);
        context.startActivity(intent);
        if (finishCurrentActivity) {
            ((Activity) context).finish();
        }
    }

    public static void switchToAnotherActivity(Context context, Class<?> cls,  Parcelable parcelable){
        Intent intent = new Intent(context, cls);
        if (parcelable != null){
            intent.putExtra(parcelable.getClass().getSimpleName(), parcelable);
        }
        context.startActivity(intent);
    }


    public static void switchToAnotherActivity(Context context, Class<?> cls, Boolean finishCurrentActivity, Parcelable parcelable){
        Intent intent = new Intent(context, cls);
        if (parcelable != null){
            intent.putExtra(parcelable.getClass().getSimpleName(), parcelable);
        }
        context.startActivity(intent);
        if (finishCurrentActivity) {
            ((Activity) context).finish();
        }
    }


}
