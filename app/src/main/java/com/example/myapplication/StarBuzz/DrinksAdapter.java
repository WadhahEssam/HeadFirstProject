package com.example.myapplication.StarBuzz;

import android.content.Context;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;

import java.util.List;
import java.util.Map;

public class DrinksAdapter extends SimpleAdapter {
    public DrinksAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
        super(context, data, resource, from, to);
    }
}
