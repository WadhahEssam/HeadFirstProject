package com.example.myapplication.Workout;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.R;
import com.example.myapplication.StopWatchFragment.StopWatchFragment;

public class WorkoutDetailFragment extends Fragment {
    private int workoutID = -1;
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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            wrapper.setId(View.generateViewId());
        }
        TextView title = new TextView(getContext());
        title.setTextSize(30);
        this.titleTextView = title;
        wrapper.addView(title);

        TextView description = new TextView(getContext());
        description.setPadding(0,0,0,50);
        this.descriptionTextView = description;
        wrapper.addView(description);

        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        StopWatchFragment stopWatchFragment = new StopWatchFragment();
        fragmentTransaction.add(wrapper.getId(), stopWatchFragment);
        fragmentTransaction.commit();

        return wrapper;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (workoutID != -1) {
            Workout currentWorkout = Workout.workouts[workoutID];
            descriptionTextView.setText(currentWorkout.getDescription());
            titleTextView.setText(currentWorkout.getName());
        }
    }

    public void setWorkoutID(int workoutID) {
        this.workoutID = workoutID;
    }
}
