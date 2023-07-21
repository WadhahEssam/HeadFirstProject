package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public TextView resultTextView;
    public Button submitButton;
    public Spinner itemsSpinner;
    public List<String> spinnerItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout wrapperLayout = new LinearLayout(this);
        wrapperLayout.setOrientation(LinearLayout.VERTICAL);
        wrapperLayout.setGravity(Gravity.CENTER);

        Spinner itemsSpinner = new Spinner(this);
        this.itemsSpinner = itemsSpinner;
        itemsSpinner.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        List<String> data = new ArrayList<String>();
        data.add("Option 1");
        data.add("Option 2");
        this.spinnerItems = data;
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, this.spinnerItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        itemsSpinner.setAdapter(adapter);
        wrapperLayout.addView(itemsSpinner);

        Button submitButton = new Button(this);
        this.submitButton = submitButton;
        submitButton.setText(R.string.another_string);
        handleOnSubmit();
        submitButton.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        LinearLayout buttonWrapper = new LinearLayout(this);
        buttonWrapper.setPadding(0,20, 0, 20);
        buttonWrapper.addView(submitButton);
        buttonWrapper.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        wrapperLayout.addView(buttonWrapper);

        TextView resultTextView = new TextView(this);
        this.resultTextView = resultTextView;
        resultTextView.setText(R.string.default_something);
        resultTextView.setGravity(Gravity.CENTER);
        resultTextView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        wrapperLayout.addView(resultTextView);

        Button goToNextScreenButton = new Button(this);
        goToNextScreenButton.setOnClickListener(view -> {
            Intent goToNextScreenIntent = new Intent(this, AnotherActivity.class);
            goToNextScreenIntent.putExtra("text", "Hello In the Another Screen");
            startActivity(goToNextScreenIntent);
        });
        goToNextScreenButton.setText("Go To Next Screen");
        goToNextScreenButton.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        LinearLayout goToNextScreenButtonWrapper = new LinearLayout(this);
        goToNextScreenButtonWrapper.setPadding(0,20, 0, 20);
        goToNextScreenButtonWrapper.addView(goToNextScreenButton);
        goToNextScreenButtonWrapper.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        wrapperLayout.addView(goToNextScreenButtonWrapper);

        Button implicitIntentButton = new Button(this);
        implicitIntentButton.setOnClickListener(view -> {
            Intent implicitIntent = new Intent(Intent.ACTION_SEND);
            implicitIntent.setType("mime/text");
            startActivity(implicitIntent);
        });
        implicitIntentButton.setText("Launch Implicit Intent");
        implicitIntentButton.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        LinearLayout implicitIntentButtonWrapper = new LinearLayout(this);
        implicitIntentButtonWrapper.setPadding(0,20, 0, 20);
        implicitIntentButtonWrapper.addView(implicitIntentButton);
        implicitIntentButtonWrapper.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        wrapperLayout.addView(implicitIntentButtonWrapper);



        Button goToLifeCycleActivityButton = new Button(this);
        goToLifeCycleActivityButton.setOnClickListener(view -> {
            startActivity(new Intent(this, LifeCycleActivity.class));
        });
        goToLifeCycleActivityButton.setText("Launch LifeCycle Activity");
        goToLifeCycleActivityButton.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        LinearLayout lifeCycleButtonWrapper = new LinearLayout(this);
        lifeCycleButtonWrapper.setPadding(0,20, 0, 20);
        lifeCycleButtonWrapper.addView(goToLifeCycleActivityButton);
        lifeCycleButtonWrapper.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        wrapperLayout.addView(lifeCycleButtonWrapper);

        View buttonForWeightLayout = Generator.generateButtonWithWrapper(this, "Weight Activity", view -> {
            Intent intent = new Intent(this, WeightActivity.class);
            startActivity(intent);
        });
        wrapperLayout.addView(buttonForWeightLayout);

        setContentView(wrapperLayout);
    }

    public void handleOnSubmit() {
        this.submitButton.setOnClickListener(view -> {
            System.out.println("Hello there");
            this.spinnerItems.add("New Option");
            this.resultTextView.setText((String) itemsSpinner.getSelectedItem());
        });
    }
}

