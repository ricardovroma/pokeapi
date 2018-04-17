package com.example.roma.android_challenge.core;

import android.app.Application;
import android.content.Context;

//import com.activeandroid.ActiveAndroid;
//import com.activeandroid.Configuration;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Configuration;
import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.core.CrashlyticsCore;
import com.example.roma.android_challenge.BuildConfig;

import io.fabric.sdk.android.Fabric;

public class CustomApplication extends Application {
    private static CustomApplication instance;

    public static CustomApplication getInstance() {
        return instance;
    }

    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Crashlytics crashlyticsKit = new Crashlytics.Builder()
                .core(new CrashlyticsCore.Builder().disabled(BuildConfig.DEBUG).build())
                .build();
        // Initialize Fabric with the debug-disabled crashlytics.
        Fabric.with(this, crashlyticsKit);

        Configuration dbConfiguration = new Configuration.Builder(this)
                .setDatabaseName("MyDb.db")
                .setDatabaseVersion(1)
                .addModelClass(Favorite.class)
                .create();

        ActiveAndroid.initialize(dbConfiguration);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();

    }
}
