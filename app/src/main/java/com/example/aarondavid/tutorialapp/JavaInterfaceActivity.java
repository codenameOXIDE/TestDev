package com.example.aarondavid.tutorialapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.Button;
import android.graphics.Color;

public class JavaInterfaceActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_java_interface);

        RelativeLayout interactiveLayout = new RelativeLayout(this);
        Button aButton = new Button(this);

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams
        (
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );

        layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        layoutParams.addRule(RelativeLayout.CENTER_VERTICAL);

        interactiveLayout.setBackgroundColor(Color.BLACK);
        aButton.setBackgroundColor(getResources().getColor(R.color.orange));
        aButton.setText("Click Me");

        interactiveLayout.addView(aButton, layoutParams);
        setContentView(interactiveLayout);
    }
}
