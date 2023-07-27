package com.example.myapplication.Workout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Generator;



public class WorkoutMainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout wrapper = new LinearLayout(this);
        wrapper.setPadding(20, 20, 20, 20);
        wrapper.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        View button = Generator.generateButtonWithWrapper(this, "Show Details", view -> {
            startActivity(new Intent(this, WorkoutDetailActivity.class));
        });
        wrapper.addView(button);

        setContentView(wrapper);
    }
}
