package com.bruang.bookingruang.Util;

import android.content.Context;
import android.content.SharedPreferences;

public class UserPreference {

    private String KEY_USER_ID = "user_id";
    private String KEY_USER_TOKEN = "user_token";
    private SharedPreferences preferences;

    public UserPreference(Context context){
        String PREFS_NAME = "UserPref";
        preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public void setUserId(String user_id) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_USER_ID, user_id);
        editor.apply();
    }

    public String getUserId() {
        return preferences.getString(KEY_USER_ID, null);
    }

    public void setUserToken(String token) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_USER_TOKEN, token);
        editor.apply();
    }

    public String getUserToken() {
        return preferences.getString(KEY_USER_TOKEN, null);
    }

    public void removeData() {
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(KEY_USER_ID).commit();
        editor.remove(KEY_USER_TOKEN).commit();
    }

    public String checkData() {
        String stringMember = preferences.getString(KEY_USER_ID, "");
        return stringMember;
    }
}
