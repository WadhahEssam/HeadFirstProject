package com.example.myapplication.StarBuzz;

import android.app.Activity;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class StarBuzzMainActivity extends AppCompatActivity {
    private ArrayList<String> entries = new ArrayList<String>(List.of("Drinks", "Food", "Something Else"));

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout wrapper = new LinearLayout(this);
        LinearLayout.LayoutParams wrapperLP = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        wrapper.setOrientation(LinearLayout.VERTICAL);
        wrapper.setLayoutParams(wrapperLP);

        entries.add("Drinks");
        entries.add("Food");
        entries.add("Something Else");

        ListView drinksListView = new ListView(this);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, 0, entries) {
            // this is the way to xo it if you want to write the code for the view your self
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                if (convertView == null) {
                    System.out.println("New");
                    LinearLayout listItem;
                    listItem = new LinearLayout(StarBuzzMainActivity.this);
                    listItem.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    listItem.setPadding(30, 30, 30, 30);
                    TextView title = new TextView(StarBuzzMainActivity.this);
                    title.setText(entries.get(position));
                    title.setTextSize(20);
                    listItem.addView(title, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    return listItem;
                } else {
                    System.out.println("Converted");
                    convertView = (LinearLayout) convertView;
                    TextView title = (TextView) ((LinearLayout) convertView).getChildAt(0);
                    title.setText(entries.get(position));

                    return convertView;
                }
            }
        };
        drinksListView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        drinksListView.setAdapter(adapter);
        drinksListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(StarBuzzMainActivity.this, entries.get(i), Toast.LENGTH_SHORT).show();
            }
        });
        wrapper.addView(drinksListView);

        LinearLayout footer = new LinearLayout(this);
        footer.setOrientation(LinearLayout.VERTICAL);

        Button addNewItemButton = new AppCompatButton(this);
        addNewItemButton.setText("Add New Item");
        addNewItemButton.setOnClickListener(view -> {
            entries.add("New Stuff");
            adapter.notifyDataSetChanged();
        });
        footer.addView(addNewItemButton);

        Button editItemButton = new Button(this);
        editItemButton.setText("Edit Item");
        editItemButton.setOnClickListener(view -> {
            entries.set(new Random().nextInt(entries.size()), "Edited Item ****");
            adapter.notifyDataSetChanged();
        });
        footer.addView(editItemButton);

        drinksListView.addFooterView(footer);

        setContentView(wrapper);
    }
}
