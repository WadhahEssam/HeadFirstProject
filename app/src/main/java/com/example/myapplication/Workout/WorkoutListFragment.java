package com.example.myapplication.Workout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.ListFragment;

public class WorkoutListFragment extends ListFragment {
    interface ListFragmentClickListener {
        void onListItemClicked(int position);
    }

    private ListFragmentClickListener listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listener = (ListFragmentClickListener) context;
    }

    @Override
    public void onStart() {
        super.onStart();
        setListAdapter(new ArrayAdapter(getContext(), 0, Workout.workouts) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                LinearLayout ll = new LinearLayout(getContext());
                ll.setPadding(20, 20, 20, 20);
                TextView tv = new TextView(getContext());
                tv.setText(Workout.workouts[position].getName());
                tv.setTextSize(20);
                ll.addView(tv);

                return ll;
            }
        });
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
}
