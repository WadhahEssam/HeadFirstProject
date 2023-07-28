package com.example.myapplication.Workout;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainer;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.ListFragment;

import com.example.myapplication.Generator;


public class WorkoutMainActivity extends AppCompatActivity implements WorkoutListFragment.ListFragmentClickListener {
    private WorkoutDetailFragment workoutDetailFragment;
    private LinearLayout workoutDetailView;

    public boolean isTablet() {
        int screenSize = this.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK;

        return screenSize >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.HORIZONTAL);
        ll.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        LinearLayout listFCView = new LinearLayout(this);
        LinearLayout.LayoutParams listFCViewLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        listFCViewLayoutParams.weight = 1;
        listFCViewLayoutParams.width = 0;
        listFCView.setLayoutParams(listFCViewLayoutParams);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            listFCView.setId(View.generateViewId());
        }
        WorkoutListFragment workoutListFragment = new WorkoutListFragment();
        ll.addView(listFCView);

        if (isTablet()) {
            LinearLayout workoutDetailView = new LinearLayout(this);
            this.workoutDetailView = workoutDetailView;
            LinearLayout.LayoutParams workoutDetailLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            workoutDetailLayoutParams.weight = 2;
            workoutDetailLayoutParams.width = 0;
            workoutDetailView.setLayoutParams(workoutDetailLayoutParams);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                workoutDetailView.setId(View.generateViewId());
            }
            WorkoutDetailFragment workoutDetailFragment = new WorkoutDetailFragment();
            this.workoutDetailFragment = workoutDetailFragment;
            ll.addView(workoutDetailView);
        }

        FragmentManager fragManager = getSupportFragmentManager();
        FragmentTransaction fragTrans = fragManager.beginTransaction();
        fragTrans.add(listFCView.getId(), workoutListFragment);
        if (isTablet()) {
            fragTrans.add(workoutDetailView.getId(), workoutDetailFragment);
        }
        fragTrans.commit();

        setContentView(ll);
    }

    @Override
    public void onListItemClicked(int position) {
        if (isTablet()) {
            FragmentManager fragManager = getSupportFragmentManager();
            FragmentTransaction fragTrans = fragManager.beginTransaction();
            WorkoutDetailFragment newWorkoutDetailFragment = new WorkoutDetailFragment();
            newWorkoutDetailFragment.setWorkoutID(position);
            fragTrans.replace(workoutDetailView.getId(), newWorkoutDetailFragment);
            fragTrans.commit();
        } else {
            Intent i = new Intent(this, WorkoutDetailActivity.class);
            i.putExtra("workoutID", position);
            startActivity(i);
        }
    }
}
