package com.example.nubers.utils;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.example.nubers.models.CountryModel;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

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

    public static ArrayList<CountryModel> searchResult(ArrayList<CountryModel> arrayList, String search){
        Log.d(TAG, "search: "+search);
        ArrayList<CountryModel> result = new ArrayList<>();

        for(int i = 0; i<arrayList.size(); i++){
            Log.d(TAG, "arrayList: "+arrayList.get(i).getName());

            if( arrayList.get(i).getName().contains(search)){
                result.add(arrayList.get(i));
                Log.d(TAG, "arrayList: "+arrayList.get(i).getName());
            }

        }

        return result;

    }

}
