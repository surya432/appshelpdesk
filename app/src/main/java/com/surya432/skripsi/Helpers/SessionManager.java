package com.surya432.skripsi.Helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class SessionManager {
    private static String TAG = SessionManager.class.getSimpleName();

    // Shared Preferences
    SharedPreferences pref;

    SharedPreferences.Editor editor;
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    public static final String PREF_NAME = "simpleRunningAssistant";
    public static final String IS_LOGGEDIN = "isLoggedIn";
    private static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_ROLE = "role";
    public static final String KEY_TOKEN_FIREBASE = "token_gcm";
    public static final String KEY_AVATAR = "Avatar";
    public static final String KEY_TOKEN = "Token";

    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setLogin(boolean isLoggedIn, String pid, String name, String email,String role,String token,String avatar) {

        editor.putBoolean(IS_LOGGEDIN, isLoggedIn);
        editor.putString(KEY_ID, pid);
        editor.putString(KEY_NAME, name);
        editor.putString(KEY_ROLE, role);
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_TOKEN, token);
        editor.putString(KEY_AVATAR, avatar);
        // commit changes
        editor.apply();

        Log.d(TAG, "User login session modified!");
    }



    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGGEDIN, false);
    }

    public String getName() {
        return pref.getString(KEY_NAME, "null");
    }
    public String getId() {
        return pref.getString(KEY_ID, "null");
    }

    public String getRole() {
        return pref.getString(KEY_ROLE, "null");
    }

    public String getEmail() {
        return pref.getString(KEY_EMAIL, "null");
    }

    public String getToken() {
        return pref.getString(KEY_TOKEN, "null");
    }


    public String getAvatar() {
        return pref.getString(KEY_AVATAR, "null");
    }

    public void destroySession(){
        editor.putBoolean(IS_LOGGEDIN, false);
        editor.putString(KEY_ID, null);
        editor.putString(KEY_NAME, null);
        editor.putString(KEY_AVATAR, null);
        editor.putString(KEY_EMAIL, null);
        editor.putString(KEY_TOKEN, null);
        editor.putString(KEY_ROLE, null);
        //editor.clear();
        editor.apply();

        Log.d(TAG, "User login session destroyed!");
    }

    public void updateData(String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }

}
