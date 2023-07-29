package com.example.myapplication.StopWatchFragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.Generator;

public class StopWatchFragment extends Fragment {
    private Integer seconds = 0;
    private TextView secondsTextView;
    private Boolean running = false;
    private Boolean isWasRunningWhenActivityPaused = false;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Context context = getContext();

        System.out.println("Value of saved instance is null");
        System.out.println(savedInstanceState == null);
        if (savedInstanceState != null) {
            this.running = savedInstanceState.getBoolean("running");
            this.seconds = savedInstanceState.getInt("seconds");
            this.isWasRunningWhenActivityPaused = savedInstanceState.getBoolean("isWasRunningWhenActivityPaused");
        }

        LinearLayout wrapperLayout = new LinearLayout(context);
        wrapperLayout.setOrientation(LinearLayout.VERTICAL);
        wrapperLayout.setGravity(Gravity.CENTER);

        TextView secondsTextView = new TextView(context);
        this.secondsTextView = secondsTextView;
        secondsTextView.setText(seconds + "");
        secondsTextView.setGravity(Gravity.CENTER);
        secondsTextView.setTextSize(30f);
        secondsTextView.setTypeface(null, Typeface.BOLD);
        secondsTextView.setLayoutParams(
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        );
        wrapperLayout.addView(secondsTextView);

        View startButton = Generator.generateButtonWithWrapper(context, "Start", view -> {
            running = true;
        });
        wrapperLayout.addView(startButton);

        View pauseButton = Generator.generateButtonWithWrapper(context, "Pause", view -> {
            running = false;
        });
        wrapperLayout.addView(pauseButton);

        View resetButton = Generator.generateButtonWithWrapper(context, "Reset", view -> {
            running = false;
            seconds = 0;
        });
        wrapperLayout.addView(resetButton);

        View implicitIntent = Generator.generateButtonWithWrapper(context, "Share intent", view -> {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("mime/text");
            startActivity(intent);
        });
        wrapperLayout.addView(implicitIntent);

        runTimer();

        return wrapperLayout;
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("seconds", this.seconds);
        outState.putBoolean("running", this.running);
        outState.putBoolean("isWasRunningWhenActivityPaused", this.isWasRunningWhenActivityPaused);
    }

    @Override
    public void onStart() {
        super.onStart();
        // will always run before the activity being visible,
        // so it will run with the app launches and if app is opened after its been invisible
        running = isWasRunningWhenActivityPaused;
    }

    @Override
    public void onStop() {
        super.onStop();
        // will run when app becomes invisible (another screen above it)
        // or gets called before the app is destroyed (it gets called after onSaveInstance)
        isWasRunningWhenActivityPaused = running;
        running = false;
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
                handler.postDelayed(this, 1000);
            }
        });
    }
}
