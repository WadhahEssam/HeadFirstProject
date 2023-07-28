package com.example.myapplication.Workout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.ListFragment;

import com.example.myapplication.Generator;



public class WorkoutMainActivity extends AppCompatActivity implements WorkoutListFragment.ListFragmentClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        LinearLayout wrapper = new LinearLayout(this);
//        wrapper.setPadding(20, 20, 20, 20);
//        wrapper.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//        View button = Generator.generateButtonWithWrapper(this, "Show Details", view -> {
//            startActivity(new Intent(this, WorkoutDetailActivity.class));
//        });
//        wrapper.addView(button);
//
//        setContentView(wrapper);

        FragmentManager fragManager = getSupportFragmentManager();
        FragmentTransaction fragTrans = fragManager.beginTransaction();
        WorkoutListFragment workoutListFragment = new WorkoutListFragment();
        fragTrans.add(android.R.id.content, workoutListFragment);
        fragTrans.commit();

    }

    @Override
    public void onListItemClicked(int position) {
        Intent i = new Intent(this, WorkoutDetailActivity.class);
        i.putExtra("workoutID", position);
        startActivity(i);
    }
}
