package kz.baqshamninonimi.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class UserConfig {

    public static final String PREFS_NAME = "App_PREFS";

    private SharedPreferences getPrefences(Context context){
        return context.getSharedPreferences(PREFS_NAME,0);
    }

    public void saveUser(Context context){

    }

}
