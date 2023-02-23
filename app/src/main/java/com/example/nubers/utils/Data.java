package com.example.nubers.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class Data {

    //Country
    public static String getIdCountry(Context context) {
        String result;
        SharedPreferences sharedPreferences = context.getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        result = sharedPreferences.getString(Constants.ID_C, "");
        return result;
    }

    public static void setIdCountry(String accessToken, Context context) {
        SharedPreferences.Editor editor = context.getSharedPreferences("myPrefs", context.MODE_PRIVATE).edit();
        editor.putString(Constants.ID_C, accessToken);
        editor.apply();
    }

    public static String getImageCountry(Context context) {
        String result;
        SharedPreferences sharedPreferences = context.getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        result = sharedPreferences.getString(Constants.IMAGE_C, "");
        return result;
    }

    public static void setImageCountry(String accessToken, Context context) {
        SharedPreferences.Editor editor = context.getSharedPreferences("myPrefs", context.MODE_PRIVATE).edit();
        editor.putString(Constants.IMAGE_C, accessToken);
        editor.apply();
    }


    public static String getNameCountry(Context context) {
        String result;
        SharedPreferences sharedPreferences = context.getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        result = sharedPreferences.getString(Constants.NAME_C, "");
        return result;
    }

    public static void setNameCountry(String accessToken, Context context) {
        SharedPreferences.Editor editor = context.getSharedPreferences("myPrefs", context.MODE_PRIVATE).edit();
        editor.putString(Constants.NAME_C, accessToken);
        editor.apply();
    }

    public static String getNameEnCountry(Context context) {
        String result;
        SharedPreferences sharedPreferences = context.getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        result = sharedPreferences.getString(Constants.NAME_EN_C, "");
        return result;
    }

    public static void setNameEnCountry(String accessToken, Context context) {
        SharedPreferences.Editor editor = context.getSharedPreferences("myPrefs", context.MODE_PRIVATE).edit();
        editor.putString(Constants.NAME_EN_C, accessToken);
        editor.apply();
    }


    //Platform
    public static String getIdPlatform(Context context) {
        String result;
        SharedPreferences sharedPreferences = context.getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        result = sharedPreferences.getString(Constants.ID_P, "");
        return result;
    }

    public static void setIdPlatform(String accessToken, Context context) {
        SharedPreferences.Editor editor = context.getSharedPreferences("myPrefs", context.MODE_PRIVATE).edit();
        editor.putString(Constants.ID_P, accessToken);
        editor.apply();
    }

    public static String getImagePlatform(Context context) {
        String result;
        SharedPreferences sharedPreferences = context.getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        result = sharedPreferences.getString(Constants.IMAGE_P, "");
        return result;
    }

    public static void setImagePlatform(String accessToken, Context context) {
        SharedPreferences.Editor editor = context.getSharedPreferences("myPrefs", context.MODE_PRIVATE).edit();
        editor.putString(Constants.IMAGE_P, accessToken);
        editor.apply();
    }


    public static String getNamePlatform(Context context) {
        String result;
        SharedPreferences sharedPreferences = context.getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        result = sharedPreferences.getString(Constants.NAME_P, "");
        return result;
    }

    public static void setNamePlatform(String accessToken, Context context) {
        SharedPreferences.Editor editor = context.getSharedPreferences("myPrefs", context.MODE_PRIVATE).edit();
        editor.putString(Constants.NAME_P, accessToken);
        editor.apply();
    }

    public static String getNameEnPlatform(Context context) {
        String result;
        SharedPreferences sharedPreferences = context.getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        result = sharedPreferences.getString(Constants.NAME_EN_P, "");
        return result;
    }

    public static void setNameEnPlatform(String accessToken, Context context) {
        SharedPreferences.Editor editor = context.getSharedPreferences("myPrefs", context.MODE_PRIVATE).edit();
        editor.putString(Constants.NAME_EN_P, accessToken);
        editor.apply();
    }

    //Server
    public static String getServer(Context context) {
        String result;
        SharedPreferences sharedPreferences = context.getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        result = sharedPreferences.getString(Constants.SERVER, "");
        return result;
    }

    public static void setServer(String accessToken, Context context) {
        SharedPreferences.Editor editor = context.getSharedPreferences("myPrefs", context.MODE_PRIVATE).edit();
        editor.putString(Constants.SERVER, accessToken);
        editor.apply();
    }

}
