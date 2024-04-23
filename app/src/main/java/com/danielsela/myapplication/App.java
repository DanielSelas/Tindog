package com.danielsela.myapplication;

import android.app.Application;
import android.app.Application;

import com.danielsela.myapplication.Utiles.Alerter;
import com.google.firebase.FirebaseApp;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // Initialize Alerter
        Alerter.init(this);

        // Initialize Firebase
        FirebaseApp.initializeApp(this);

        // Initialize DataManager if needed
        // DataManager.init(this);
    }
}
