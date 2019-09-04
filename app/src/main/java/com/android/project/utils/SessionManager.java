package com.android.project.utils;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;

public class SessionManager {

    private static final String APP_VERSION = "app_version";

    private static final String PREF_NAME = "book_shared_pref";
    private static SessionManager instance;
    private final SharedPreferences pref;
    private final SharedPreferences.Editor editor;

    @Inject
    public SessionManager(Context context) {
        pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = pref.edit();
        editor.apply();
    }

    public static SessionManager getInstance(Context context) {
        if (instance == null) {
            instance = new SessionManager(context);
        }
        return instance;
    }

    public String getAppVersion() {
        return pref.getString(APP_VERSION, Constants.INSTANCE.getEMPTY_STRING());
    }

    public void setAppVersion(String appVersion) {
        editor.putString(APP_VERSION, appVersion);
        editor.apply();
    }

    public void clear() {
        editor.remove(APP_VERSION);
        editor.apply();
    }

}
