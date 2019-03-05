package com.surya432.apis.Helpers;

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
    private static final String KEY_ID_USER = "key";
    private static final String KEY_PID = "pid";
    public static final String KEY_NAME = "name";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_ROLE = "role";
    public static final String KEY_TOKEN_GCM = "token_gcm";

    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setLogin(boolean isLoggedIn, String pid, String name) {

        editor.putBoolean(IS_LOGGEDIN, isLoggedIn);
        editor.putString(KEY_PID, pid);
        editor.putString(KEY_NAME, name);
        // commit changes
        editor.apply();

        Log.d(TAG, "User login session modified!");
    }
    public void setJumlah(String jml){
        editor.putString(KEY_NAME, jml);
        editor.commit();
    }


    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGGEDIN, false);
    }

    public String getName() {
        return pref.getString(KEY_NAME, "null");
    }
    public String getPID() {
        return pref.getString(KEY_PID, "null");
    }

    public String getKey() {
        return pref.getString(KEY_ID_USER, "null");
    }

    public String getEmail() {
        return pref.getString(KEY_EMAIL, "null");
    }

    public String getUsername() {
        return pref.getString(KEY_USERNAME, "null");
    }

    public String getRole() {
        return pref.getString(KEY_ROLE, "null");
    }

    public void destroySession(){
        editor.putString(KEY_PID, "");
        editor.putString(KEY_NAME, "");
        editor.putBoolean(IS_LOGGEDIN, false);
        //editor.clear();
        editor.apply();

        Log.d(TAG, "User login session destroyed!");
    }

    public void updateData(String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }

    public String getData(String key) {
        return pref.getString(key, "");
    }
}
