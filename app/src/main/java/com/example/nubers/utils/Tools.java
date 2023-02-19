package com.example.nubers.utils;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Tools {

    private static final String TAG = "Kia----Tools----> ";

    public static int removePrice(String a){
           a= a.replace("ت","");
           a= a.replace("و","");
           a= a.replace("م","");
           a= a.replace("ا","");
           a= a.replace("ن","");
           a= a.replace(" ","");
           a= a.replace(",","");

        Log.d(TAG, "removePrice: " + a);
        return  Integer.parseInt(a);
    }

    public static String createPrice(int a){

        NumberFormat formatter = new DecimalFormat("#,###");
        String formattedNumber = formatter.format(a);
        String result = formattedNumber+" تومان";

        return result ;
    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        if (inputMethodManager.isAcceptingText()) {
            inputMethodManager.hideSoftInputFromWindow(activity.getWindow().getDecorView().getRootView().getWindowToken(), 0);
        }
    }

    //ProgressBar
    public static void visibleProgressBar(View view) {
        view.setVisibility(View.VISIBLE);
    }

    public static void invisibleProgressBar(View view) {
        view.setVisibility(View.INVISIBLE);
    }
    //ProgressBar


}
