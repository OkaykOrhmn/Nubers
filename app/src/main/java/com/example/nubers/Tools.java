package com.example.nubers;

import android.util.Log;

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


}
