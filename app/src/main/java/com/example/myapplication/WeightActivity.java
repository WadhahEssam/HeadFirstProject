package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;

// This is the most right way I did the components without xml

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

        Button sendButton = new Button(this);
        LinearLayout.LayoutParams sendButtonLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        sendButtonLayoutParams.gravity = Gravity.END;
        sendButtonLayoutParams.bottomMargin = 10;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            sendButtonLayoutParams.setMarginEnd(5);
        }
        sendButton.setLayoutParams(sendButtonLayoutParams);
        sendButton.setText("Send");
        sendButton.setOnClickListener(view -> {
            Toast.makeText(this, "Sending the message...", Toast.LENGTH_SHORT).show();
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        });
        wrapperLayout.addView(sendButton);

        setContentView(wrapperLayout);
    }
}
