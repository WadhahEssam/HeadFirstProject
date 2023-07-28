package com.example.myapplication.Workout;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;

public class WorkoutDetailFragment extends Fragment {
    private int workoutID;
    private TextView titleTextView;
    private TextView descriptionTextView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        System.out.println(container.getClass().toString());
        container.setPadding(20, 20, 20, 20);

        // you can go ahead and use the inflater if you want, but I want it the hard way
        //        View rootView = inflater.inflate(R.layout.workout_detail_fragment, container, false); // I suppose that will create duplicate children because we are adding it here and returning it later.
        //        return rootView;

        LinearLayout wrapper = new LinearLayout(getContext());
        wrapper.setOrientation(LinearLayout.VERTICAL);

        TextView title = new TextView(getContext());
        title.setTextSize(30);
        this.titleTextView = title;
        wrapper.addView(title);

        TextView description = new TextView(getContext());
        this.descriptionTextView = description;
        wrapper.addView(description);

        return wrapper;
    }

    @Override
    public void onStart() {
        super.onStart();
        Workout currentWorkout = Workout.workouts[workoutID];
        descriptionTextView.setText(currentWorkout.getDescription());
        titleTextView.setText(currentWorkout.getName());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("onCreate " + workoutID);
    }

    public void setWorkoutID(int workoutID) {
        this.workoutID = workoutID;
    }
}
