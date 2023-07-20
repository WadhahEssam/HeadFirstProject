package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


public class LifeCycleActivity extends Activity {
    private Integer seconds = 0;
    private TextView secondsTextView;
    private Boolean running = false;
    private Boolean isWasRunningWhenActivityPaused = false;

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);

        System.out.println("Value of saved instance is null");
        System.out.println(savedInstance == null);
        if (savedInstance != null) {
            this.running = savedInstance.getBoolean("running");
            this.seconds = savedInstance.getInt("seconds");
            this.isWasRunningWhenActivityPaused = savedInstance.getBoolean("isWasRunningWhenActivityPaused");
        }

        LinearLayout.LayoutParams mainLayoutParams =
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        LinearLayout mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);

        LinearLayout wrapperLayout = new LinearLayout(this);
        wrapperLayout.setOrientation(LinearLayout.VERTICAL);
        wrapperLayout.setGravity(Gravity.CENTER);
        wrapperLayout.setLayoutParams(mainLayoutParams);
        mainLayout.addView(wrapperLayout);

        TextView secondsTextView = new TextView(this);
        this.secondsTextView = secondsTextView;
        secondsTextView.setText(seconds + "");
        secondsTextView.setGravity(Gravity.CENTER);
        secondsTextView.setTextSize(30f);
        secondsTextView.setTypeface(null, Typeface.BOLD);
        secondsTextView.setLayoutParams(
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        );
        wrapperLayout.addView(secondsTextView);

        View startButton = Generator.generateButtonWithWrapper(this, "Start", view -> {
            running = true;
        });
        wrapperLayout.addView(startButton);

        View pauseButton = Generator.generateButtonWithWrapper(this, "Pause", view -> {
            running = false;
        });
        wrapperLayout.addView(pauseButton);

        View resetButton = Generator.generateButtonWithWrapper(this, "Reset", view -> {
            running = false;
            seconds = 0;
        });
        wrapperLayout.addView(resetButton);

        View implicitIntent = Generator.generateButtonWithWrapper(this, "Share intent", view -> {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("mime/text");
            startActivity(intent);
        });
        wrapperLayout.addView(implicitIntent);

        runTimer();

        setContentView(mainLayout);
    }

    // This function gets called before the onDestroy function is called.
    @Override
    protected void onSaveInstanceState(Bundle savedInstanceBundle) {
        savedInstanceBundle.putInt("seconds", this.seconds);
        savedInstanceBundle.putBoolean("running", this.running);
        savedInstanceBundle.putBoolean("isWasRunningWhenActivityPaused", this.isWasRunningWhenActivityPaused);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // will always run before the activity being visible,
        // so it will run with the app launches and if app is opened after its been invisible
        System.out.println("onStart");
        System.out.println(isWasRunningWhenActivityPaused);
        running = isWasRunningWhenActivityPaused;
    }

    @Override
    protected void onStop() {
        super.onStop();
        // will run when app becomes invisible (another screen above it)
        // or gets called before the app is destroyed (it gets called after onSaveInstance)
        System.out.println("onStop");
        isWasRunningWhenActivityPaused = running;
        running = false;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        // will run when app becomes visible after being invisible
        System.out.println("onRestart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("onPause");
    }


    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("onResume");
    }

    void runTimer() {
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (running) {
                    seconds += 1;
                    secondsTextView.setTextColor(Color.BLACK);
                } else {
                    secondsTextView.setTextColor(Color.RED);
                }
                secondsTextView.setText(seconds + "");
                System.out.println("Running");
                handler.postDelayed(this, 1000);
            }
        });
    }
}
