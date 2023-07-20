package com.example.myapplication;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class WeightActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout wrapperLayout = Generator.generateActivityWrapper(this, "column");
        wrapperLayout.setPadding(10, 10, 10, 10);

        EditText senderField = new EditText(this);
        senderField.setHint("Sender");
        wrapperLayout.addView(senderField, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        EditText messageField = new EditText(this);
        messageField.setHint("Message");
        messageField.setGravity(Gravity.TOP);
        LinearLayout.LayoutParams messageFieldLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        messageFieldLayoutParams.weight = 1;
        wrapperLayout.addView(messageField, messageFieldLayoutParams);

        LinearLayout sendButtonWrapper = new LinearLayout(this);
        sendButtonWrapper.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        sendButtonWrapper.setGravity(Gravity.END);
        Button sendButton = new Button(this);
        sendButton.setText("Send");
        sendButton.setWidth(80);
        sendButton.setOnClickListener(view -> {
            Toast.makeText(this, "Sending the message...", Toast.LENGTH_SHORT).show();
        });
        sendButtonWrapper.addView(sendButton, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        wrapperLayout.addView(sendButtonWrapper);

        setContentView(wrapperLayout);
    }
}
