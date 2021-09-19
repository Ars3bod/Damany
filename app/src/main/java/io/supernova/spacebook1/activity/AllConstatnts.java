package io.supernova.spacebook1.activity;

import android.content.Context;
import android.content.SharedPreferences;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class AllConstatnts {

    public static String USER_EMAIL="user_email";


    public static void setString(Context context,String key,String savedData)
    {
        SharedPreferences sharedpreferences = context.getSharedPreferences("my_preference",
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(key, savedData);
        editor.commit();
    }

    public static String getString(Context context,String key)
    {
        SharedPreferences  sharedpreferences = context.getSharedPreferences("my_preference",
                Context.MODE_PRIVATE);
        return sharedpreferences.getString(key, "");
    }
}
