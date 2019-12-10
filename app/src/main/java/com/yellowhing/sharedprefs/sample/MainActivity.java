package com.yellowhing.sharedprefs.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import de.yellowhing.sharedprefs.Refine;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppConfig config = Refine.create(AppConfig.class, this);
        Log.d(TAG, "onCreate: "+config.password().getValue());
        config.password().setValue("qwer1234");
        Log.d(TAG, "onCreate: "+config.password().getValue());

        Log.d(TAG, "onCreate: "+config.info());
        config.info("info");
        Log.d(TAG, "onCreate: "+config.info());

    }
}
