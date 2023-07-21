package com.example.myapplication;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.Nullable;

public class FrameLayoutActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FrameLayout mainFrameLayout = new FrameLayout(this);
        mainFrameLayout.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        FrameLayout wrapperFrameLayout = new FrameLayout(this);
        wrapperFrameLayout.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        ImageView testImage = new ImageView(this);
        testImage.setImageResource(R.drawable.test_image);
        testImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
        wrapperFrameLayout.addView(testImage, new FrameLayout.LayoutParams(500, 500));

        TextView testImageDescription = new TextView(this);
        testImageDescription.setText("OS Wallpaper");
        testImageDescription.setTextColor(Color.WHITE);
        testImageDescription.setTextSize(20);
        FrameLayout.LayoutParams testImageDescriptionLayoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        testImageDescriptionLayoutParams.gravity = Gravity.BOTTOM | Gravity.END; // really cool
        wrapperFrameLayout.addView(testImageDescription, testImageDescriptionLayoutParams);

        mainFrameLayout.addView(wrapperFrameLayout);
        setContentView(mainFrameLayout);
    }
}
