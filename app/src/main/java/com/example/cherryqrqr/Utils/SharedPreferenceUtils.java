package com.example.cherryqrqr.Utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceUtils {

    SharedPreferences sharedPref;
    SharedPreferences.Editor sharedEditor;
    Context c;

    public SharedPreferenceUtils(Context c){
        sharedPref = c.getSharedPreferences("e4net_pref", Context.MODE_PRIVATE);
        sharedEditor = sharedPref.edit();
        this.c = c;
    }

    public void saveString(int keyID, String value){
        sharedEditor.putString(c.getResources().getString(keyID), value);
        sharedEditor.apply();
    }

    public void saveBool(int keyID, boolean value){
        sharedEditor.putBoolean(c.getResources().getString(keyID), value);
        sharedEditor.apply();
    }

    public String getString(int keyID, String defaultVal){
        return sharedPref.getString(c.getResources().getString(keyID), defaultVal);
    }

    public boolean getBool(int keyID, boolean defaultVal){
        return sharedPref.getBoolean(c.getResources().getString(keyID), defaultVal);
    }

}
