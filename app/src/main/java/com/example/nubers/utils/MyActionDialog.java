package com.example.nubers.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.google.android.material.button.MaterialButton;

public class MyActionDialog {

    public static void dialogButtons(Activity activity, MaterialButton again, MaterialButton exit){
        again.setClickable(true);

        again.setOnClickListener(view -> {
            again.setClickable(false);
            activity.recreate();
        });

        exit.setOnClickListener(view -> {
            activity.finish();
        });
    }
}
