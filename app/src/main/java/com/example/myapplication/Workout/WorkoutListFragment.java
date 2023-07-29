package com.example.myapplication.Workout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.ListFragment;

import com.example.myapplication.R;

public class WorkoutListFragment extends ListFragment {
    interface ListFragmentClickListener {
        void onListItemClicked(int position);
    }

    private ListFragmentClickListener listener;
    private ArrayAdapter<Workout> adapter;
    private int selectedWorkout = -1;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listener = (ListFragmentClickListener) context;
    }

    @Override
    public void onStart() {
        super.onStart();
        ArrayAdapter<Workout> adapter = new ArrayAdapter(getContext(), 0, Workout.workouts) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                LinearLayout ll = new LinearLayout(getContext());
                ll.setPadding(20, 20, 20, 20);
                TextView tv = new TextView(getContext());
                tv.setText(Workout.workouts[position].getName());
                tv.setTextSize(20);
                if (selectedWorkout == position) {
                    ll.setBackgroundColor(getResources().getColor(R.color.kindaBlue));
                    int white = Color.parseColor("#FFFFFF");
                    tv.setTextColor(white);
                }
                ll.addView(tv);

                return ll;
            }
        };
        this.adapter = adapter;
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        // this is the easier way
        //        Activity callerActivity = getActivity();
        //        Intent i = new Intent(getContext(), WorkoutDetailActivity.class);
        //        i.putExtra("workoutID", position);
        //        callerActivity.startActivity(i);

        // this is the harder way, but it shows you how you can do better communication
        if (listener != null) {
            listener.onListItemClicked(position);
        }
    }

    public void setSelectedWorkout(int position) {
        System.out.println("set selected workout" + position);
        this.selectedWorkout = position;
        adapter.notifyDataSetChanged();
    }
}
