package com.example.myapplication.Workout;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.Generator;

public class WorkoutDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // How to attach the fragment to the activity directly (shorter way)
        //        FragmentManager fragmentManager = getSupportFragmentManager();
        //        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //        Fragment workoutDetailFragment = new WorkoutDetailFragment();
        //        fragmentTransaction.add(android.R.id.content, workoutDetailFragment);
        //        fragmentTransaction.commit();


        // How to attach the fragment to the activity wrapped by a container (ViewGroup of your choice)
        LinearLayout activityWrapper = new LinearLayout(this);
        activityWrapper.setOrientation(LinearLayout.VERTICAL);
        activityWrapper.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            activityWrapper.setId(View.generateViewId());
        }

        // Adding a button just for fun
        //        View button = Generator.generateButtonWithWrapper(this, "Testing Button", view -> {
        //            // do something
        //        });
        //        activityWrapper.addView(button);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        WorkoutDetailFragment workoutDetailFragment = new WorkoutDetailFragment();
        int workoutID =  getIntent().getIntExtra("workoutID", 0);
        workoutDetailFragment.setWorkoutID(workoutID);
        fragmentTransaction.add(activityWrapper.getId(), workoutDetailFragment);
        fragmentTransaction.commit();

        setContentView(activityWrapper);
    }
}
