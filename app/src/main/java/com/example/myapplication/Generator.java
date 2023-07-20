package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public class Generator {
    static public View generateButtonWithWrapper(Context context, String label, View.OnClickListener clickListener) {
        Button button = new Button(context);
        button.setOnClickListener(clickListener);

        button.setText(label);
        button.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        LinearLayout buttonWrapper = new LinearLayout(context);
        buttonWrapper.setPadding(0,20, 0, 20);
        buttonWrapper.addView(button);
        buttonWrapper.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        return buttonWrapper;
    }

    static public LinearLayout generateActivityWrapper(Context context, String orientation) {
        LinearLayout wrapperLayout = new LinearLayout(context);
        wrapperLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        wrapperLayout.setOrientation(orientation == "row" ? LinearLayout.HORIZONTAL : LinearLayout.VERTICAL);
        return wrapperLayout;
    }
}
