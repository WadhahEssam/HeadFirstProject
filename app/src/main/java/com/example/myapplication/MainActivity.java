package com.example.myapplication;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Space;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import com.example.myapplication.StarBuzz.StarBuzzMainActivity;
import com.example.myapplication.StopWatchFragment.StopWatchActivity;
import com.example.myapplication.Workout.WorkoutMainActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.resources.TextAppearance;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public TextView resultTextView;
    public Button submitButton;
    public Spinner itemsSpinner;
    public List<String> spinnerItems;

    private String theme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        theme = savedInstanceState != null ? savedInstanceState.getString("theme") : theme;

        if (theme != null) {
            setTheme(theme == "light" ? com.google.android.material.R.style.Theme_Material3_Light : com.google.android.material.R.style.Theme_Material3_Dark);
        }
        ScrollView mainScrollview = new ScrollView(this);

        LinearLayout wrapperLayout = new LinearLayout(this);
        wrapperLayout.setOrientation(LinearLayout.VERTICAL);
        wrapperLayout.setGravity(Gravity.CENTER);
        mainScrollview.addView(wrapperLayout);

        Toolbar toolbar = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            toolbar = new Toolbar(this);
            toolbar.setTitle("Main Screen");
            toolbar.setBackgroundColor(Color.parseColor("#629FE0"));
            toolbar.setTitleTextColor(Color.WHITE);
            toolbar.inflateMenu(R.menu.main);
            if (theme == null) {
                wrapperLayout.addView(toolbar);
            }
        }


        View spaceView = new View(this);
        ViewGroup.LayoutParams spaceViewLP = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        spaceViewLP.height = 50;
        wrapperLayout.addView(spaceView, spaceViewLP);

        Spinner itemsSpinner = new AppCompatSpinner(this);
        this.itemsSpinner = itemsSpinner;
        itemsSpinner.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        List<String> data = new ArrayList<String>();
        data.add("Option 1");
        data.add("Option 2");
        this.spinnerItems = data;
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, this.spinnerItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        itemsSpinner.setAdapter(adapter);
        wrapperLayout.addView(itemsSpinner);

        Button submitButton = new MaterialButton(this);
        this.submitButton = submitButton;
        submitButton.setText(R.string.another_string);
        handleOnSubmit();
        submitButton.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        LinearLayout buttonWrapper = new LinearLayout(this);
        buttonWrapper.setPadding(0, 20, 0, 20);
        buttonWrapper.addView(submitButton);
        buttonWrapper.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        wrapperLayout.addView(buttonWrapper);

        TextView resultTextView = new TextView(this);
        this.resultTextView = resultTextView;
        resultTextView.setText(R.string.default_something);
        resultTextView.setGravity(Gravity.CENTER);
        resultTextView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        wrapperLayout.addView(resultTextView);

        Button goToNextScreenButton = new MaterialButton(this);
        goToNextScreenButton.setOnClickListener(view -> {
            Intent goToNextScreenIntent = new Intent(this, AnotherActivity.class);
            goToNextScreenIntent.putExtra("text", "Hello In the Another Screen");
            startActivity(goToNextScreenIntent);
        });
        goToNextScreenButton.setText("Go To Next Screen");
        goToNextScreenButton.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        LinearLayout goToNextScreenButtonWrapper = new LinearLayout(this);
        goToNextScreenButtonWrapper.setPadding(0, 20, 0, 20);
        goToNextScreenButtonWrapper.addView(goToNextScreenButton);
        goToNextScreenButtonWrapper.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        wrapperLayout.addView(goToNextScreenButtonWrapper);

        Button implicitIntentButton = new MaterialButton(this);
        implicitIntentButton.setOnClickListener(view -> {
            Intent implicitIntent = new Intent(Intent.ACTION_SEND);
            implicitIntent.setType("mime/text");
            startActivity(implicitIntent);
        });
        implicitIntentButton.setText("Launch Implicit Intent");
        implicitIntentButton.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        LinearLayout implicitIntentButtonWrapper = new LinearLayout(this);
        implicitIntentButtonWrapper.setPadding(0, 20, 0, 20);
        implicitIntentButtonWrapper.addView(implicitIntentButton);
        implicitIntentButtonWrapper.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        wrapperLayout.addView(implicitIntentButtonWrapper);


        Button goToLifeCycleActivityButton = new MaterialButton(this);
        goToLifeCycleActivityButton.setOnClickListener(view -> {
            startActivity(new Intent(this, LifeCycleActivity.class));
        });
        goToLifeCycleActivityButton.setText("Launch LifeCycle Activity");
        goToLifeCycleActivityButton.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        LinearLayout lifeCycleButtonWrapper = new LinearLayout(this);
        lifeCycleButtonWrapper.setPadding(0, 20, 0, 20);
        lifeCycleButtonWrapper.addView(goToLifeCycleActivityButton);
        lifeCycleButtonWrapper.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        wrapperLayout.addView(lifeCycleButtonWrapper);

        View buttonForWeightLayout = Generator.generateButtonWithWrapper(this, "Weight Activity", view -> {
            Intent intent = new Intent(this, WeightActivity.class);
            startActivity(intent);
        });
        wrapperLayout.addView(buttonForWeightLayout);

        View buttonForFrameLayoutActivity = Generator.generateButtonWithWrapper(this, "Frame Layout Activity", view -> {
            startActivity(new Intent(this, FrameLayoutActivity.class));
        });
        wrapperLayout.addView(buttonForFrameLayoutActivity);

        View buttonForConstraintLayoutActivity = Generator.generateButtonWithWrapper(this, "Constraint Layout Activity", view -> {
            startActivity(new Intent(this, ConstraintLayoutActivity.class));
        });
        wrapperLayout.addView(buttonForConstraintLayoutActivity);

        View buttonForDrinksApp = Generator.generateButtonWithWrapper(this, "ListView", view -> {
            startActivity(new Intent(this, StarBuzzMainActivity.class));
        });
        wrapperLayout.addView(buttonForDrinksApp);

        View changeThemeButton = Generator.generateButtonWithWrapper(this, "Change Theme", view -> {
            toggleTheme();
        });
        wrapperLayout.addView(changeThemeButton);

        View workoutButton = Generator.generateButtonWithWrapper(this, "Fragments", view -> {
            startActivity(new Intent(this, WorkoutMainActivity.class));
        });
        wrapperLayout.addView(workoutButton);

        View stopWatchFragmentButton = Generator.generateButtonWithWrapper(this, "Stop Watch Fragment", view -> {
            startActivity(new Intent(this, StopWatchActivity.class));
        });
        wrapperLayout.addView(stopWatchFragmentButton);

        View viewDialogButton = Generator.generateButtonWithWrapper(this, "View Dialog", view -> {
            DialogFragment dialogFragment = new DialogFragment();
            dialogFragment.show(getSupportFragmentManager(), "myDialog");
        });

        wrapperLayout.addView(viewDialogButton);

        setContentView(mainScrollview);
    }

    private void toggleTheme() {
        System.out.println(theme);
        theme = theme == "light" ? "dark" : "light";
        recreate(); // Recreate the activity to apply the new theme
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceBundle) {
        super.onSaveInstanceState(savedInstanceBundle);
        savedInstanceBundle.putString("theme", theme);
    }

    public void handleOnSubmit() {
        this.submitButton.setOnClickListener(view -> {
            System.out.println("Hello there");
            this.spinnerItems.add("New Option");
            this.resultTextView.setText((String) itemsSpinner.getSelectedItem());
        });
    }
}

