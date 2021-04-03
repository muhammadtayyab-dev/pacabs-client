package com.techlogix.pacaps.utility;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SharePrefData {
    private static final SharePrefData instance = new SharePrefData();
    private static final String LOGGED_IN = "logged_in";
    private static final String CUSTOMER_ID = "user_id";
    private static final String CUSTOMER_NAME = "user_name";
    private static final String CUSTOMER_MOBILE = "user_num";
    private static final String CUSTOMER_EMAIL = "user_email";

    private SharedPreferences sp;
    private SharedPreferences.Editor spEditor;

    public static synchronized SharePrefData getInstance() {
        return instance;
    }

    public void setContext(Context context) {
        this.sp = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public SharePrefData(Context context) {
        this.sp = PreferenceManager.getDefaultSharedPreferences(context);
    }

    private SharePrefData() {
    }

    public void setUserId(int userId) {
        spEditor = sp.edit();
        spEditor.putInt(CUSTOMER_ID, userId);
        spEditor.apply();
        spEditor.commit();
    }

    public int getUserId() {
        return sp.getInt(CUSTOMER_ID, -1);
    }


    public void setUserName(String name) {
        spEditor = sp.edit();
        spEditor.putString(CUSTOMER_NAME, name);
        spEditor.apply();
        spEditor.commit();
    }

    public String getUserName() {
        return sp.getString(CUSTOMER_NAME, "");
    }

    public void setUserNum(String num) {
        spEditor = sp.edit();
        spEditor.putString(CUSTOMER_MOBILE, num);
        spEditor.apply();
        spEditor.commit();
    }

    public String getUserNum() {
        return sp.getString(CUSTOMER_MOBILE, "");
    }


    @SuppressLint("CommitPrefEdits")
    public boolean destroyUserSession() {
        this.spEditor = this.sp.edit();
        this.spEditor.remove(LOGGED_IN);
        this.spEditor.apply();
        return true;
    }
}
