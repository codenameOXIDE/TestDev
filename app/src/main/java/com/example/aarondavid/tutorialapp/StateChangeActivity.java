package com.example.aarondavid.tutorialapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class StateChangeActivity extends AppCompatActivity
{
    private static final String TAG = "AaronLog";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state_change);

        Log.i(TAG, "onCreate");
    }
}
