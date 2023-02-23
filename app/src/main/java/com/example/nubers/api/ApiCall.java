package com.example.nubers.api;

import android.content.Context;
import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.nubers.utils.ApiEndPoint;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

public class ApiCall {

    private static final String TAG = "KIA----ApiCall----> ";
    Context context;
    ApiCallInterface apiCallInterface;


    public ApiCall(Context context, ApiCallInterface apiCallInterface) {

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();
        AndroidNetworking.initialize(context, okHttpClient);

        this.context = context;
        this.apiCallInterface = apiCallInterface;

    }


    public JSONObject apiResponseJsonCreator(String requestUrl, boolean isSuccess, JSONArray response) {

        JSONObject jsonObject = new JSONObject();
        try {

            jsonObject.put("request", requestUrl);
            jsonObject.put("isSuccess", isSuccess);
            jsonObject.put("result", response);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }


    public void getAllCountry() {

        JSONObject userData = new JSONObject();
        try {
            userData.put("apikey", "d07296d17874ba9c2cf76ec605db021d");
            userData.put("method", "getcountry");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        AndroidNetworking.get("https://api.numberland.ir/v2.php?apikey=d07296d17874ba9c2cf76ec605db021d&method=getcountry")
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            Log.d(TAG, "onResponse: ");
                            apiCallInterface.onResponse(
                                    apiResponseJsonCreator(
                                            ApiEndPoint.GET_ALL_COUNTRY,
                                            true,
                                            response
                                    ));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.d(TAG, "onError: "+ anError.getErrorBody());
                        // JSONObject error = new JSONObject(anError.getErrorBody());
//                            apiCallInterface.onResponse(
//                                    apiResponseJsonCreator(
//                                            ApiEndPoint.GET_ALL_COUNTRY,
//                                            false,
//                                            error.getInt("status"),
//                                            null
//                                    ));
                    }
                });
    }

    public void getAllServices() {

        JSONObject userData = new JSONObject();
        try {
            userData.put("apikey", "d07296d17874ba9c2cf76ec605db021d");
            userData.put("method", "getcountry");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        AndroidNetworking.get("https://api.numberland.ir/v2.php/?apikey=d07296d17874ba9c2cf76ec605db021d&method=getservice")
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            Log.d(TAG, "onResponse: ");
                            apiCallInterface.onResponse(
                                    apiResponseJsonCreator(
                                            ApiEndPoint.GET_ALL_SERVICES,
                                            true,
                                            response
                                    ));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.d(TAG, "onError: "+ anError.getErrorBody());
                        // JSONObject error = new JSONObject(anError.getErrorBody());
//                            apiCallInterface.onResponse(
//                                    apiResponseJsonCreator(
//                                            ApiEndPoint.GET_ALL_COUNTRY,
//                                            false,
//                                            error.getInt("status"),
//                                            null
//                                    ));
                    }
                });
    }

    public void getNumbers(String country, String service) {

        JSONObject userData = new JSONObject();
        try {
            userData.put("service", service);
            userData.put("country", country);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        AndroidNetworking.get("https://api.numberland.ir/v2.php/?apikey=d07296d17874ba9c2cf76ec605db021d&method=getinfo")
                .addQueryParameter(userData)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            Log.d(TAG, "onResponse: ");
                            apiCallInterface.onResponse(
                                    apiResponseJsonCreator(
                                            ApiEndPoint.GET_NUMBERS,
                                            true,
                                            response
                                    ));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.d(TAG, "onError: "+ anError.getErrorBody());
                        // JSONObject error = new JSONObject(anError.getErrorBody());
//                            apiCallInterface.onResponse(
//                                    apiResponseJsonCreator(
//                                            ApiEndPoint.GET_ALL_COUNTRY,
//                                            false,
//                                            error.getInt("status"),
//                                            null
//                                    ));
                    }
                });
    }




}
