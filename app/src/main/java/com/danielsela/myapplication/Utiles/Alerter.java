package com.danielsela.myapplication.Utiles;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build.VERSION;
import android.widget.Toast;

public class Alerter {


    private static Alerter instance=null;
    private final Context context;

    private Alerter(Context context) {
        this.context=context;
    }

    public static void init(Context context) {
        if (instance == null) {
            instance = new Alerter(context);
        }
    }

    public static  Alerter getInstance() {
        return instance;
    }

    public void toast(String text, int length) {
        Toast.makeText(this.context, text, length).show();
    }
}
