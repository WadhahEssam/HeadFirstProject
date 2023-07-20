package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.reflect.Field;

public class AnotherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Class mainActivityClass = MainActivity.class;
        Field[] mainActivityClassFields = mainActivityClass.getDeclaredFields();
        String nameOfTheFirstField = mainActivityClassFields[0].getName();
        System.out.println(nameOfTheFirstField);

        LinearLayout.LayoutParams mainLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        LinearLayout mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);

        LinearLayout wrapperLayout = new LinearLayout(this);
        wrapperLayout.setOrientation(LinearLayout.VERTICAL);
        wrapperLayout.setGravity(Gravity.CENTER);
        wrapperLayout.setLayoutParams(mainLayoutParams);
        mainLayout.addView(wrapperLayout);

        Intent callingIntent = getIntent();
        String text = callingIntent.getStringExtra("text");
        TextView resultTextView = new TextView(this);
        resultTextView.setText(text);
        resultTextView.setGravity(Gravity.CENTER);
        resultTextView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        wrapperLayout.addView(resultTextView);

        CustomView customView = new CustomView(this);
        customView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));        resultTextView.setGravity(Gravity.CENTER);
        customView.setOnClickListener(view -> {
            System.out.println("Clicked");
        });
        wrapperLayout.addView(customView);

        setContentView(mainLayout);
    }
}