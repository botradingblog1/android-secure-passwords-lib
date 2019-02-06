package com.justmobiledev.securepasswordslib;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class SecurePasswordContainer {

    private static SecurePasswordContainer container = null;
    private static SharedPreferences securePreferences;

    // Constants
    private static final String SECURE_PREFS_FILE_NAME = "secure_preferences_file";

    // private constructor restricted to this class itself
    private SecurePasswordContainer(Application app)
    {
        // Setup secure preferences
        securePreferences = new SecurePrefsBuilder()
                .setApplication(app)
                .obfuscateValue(true)
                .obfuscateKey(true)
                .setSharePrefFileName(SECURE_PREFS_FILE_NAME)
                .createSharedPrefs(app);
    }

    // static method to create instance of Singleton class
    public static SecurePasswordContainer create(Application app)
    {
        if (container == null)
            container = new SecurePasswordContainer(app);

        return container;
    }

    public static SecurePasswordContainer SecurePasswordsContainer() throws Exception {
        if (container == null)
            throw new Exception("SecurePasswordContainer needs to be initialized using 'create' with Application object");

        return container;
    }

    public static void set(final String key, final String value){
        SharedPreferences.Editor editor = securePreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }


    public static String get(final String key, final String defaultValue){
        return securePreferences.getString(key, defaultValue);
    }

}
