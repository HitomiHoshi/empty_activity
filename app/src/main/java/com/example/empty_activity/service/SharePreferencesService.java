package com.example.empty_activity.service;

import android.content.Context;
import android.content.SharedPreferences;

public class SharePreferencesService {

    private static Context context;

    public SharePreferencesService(Context context){
        this.context = context;
    }

    public final static String PREFS_NAME = "appname_prefs";

    public boolean sharedPreferenceExist(String key)
    {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        if(!prefs.contains(key)){
            return true;
        }
        else {
            return false;
        }
    }

    public static void setInt( String key, int value) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME,0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public static int getInt(String key) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        return prefs.getInt(key, 0);
    }

    public static void setStr(String key, String value) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME,0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String getStr(String key) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        return prefs.getString(key,"DNF");
    }

    public static void setBool(String key, boolean value) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME,0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static boolean getBool(String key) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        return prefs.getBoolean(key,false);
    }
}

//    CALL FROM ACTIVITY
//      To Save a value
//          SharePreferencesService dataProccessor = new SharePreferencesService(this);
//          dataProccessor.setStr("email","johndoe@mail.com");
//
//     To Retreive a value
//          dataProccessor.getStr("email");

//    CALL FROM FRAGMENT
//      To Save a value
//          SharePreferencesService dataProccessor = new SharePreferencesService(getActivity());
//          dataProccessor.setStr("email","johndoe@mail.com");
//
//      To Retreive a value
//          dataProccessor.getStr("email");

//    CALL FROM SERVICE
//      To Save a value
//          SharePreferencesService dataProccessor = new SharePreferencesService(getApplicationContext());
//          dataProccessor.setStr("email","johndoe@mail.com");
//
//      To Retreive a value
//          dataProccessor.getStr("email");
