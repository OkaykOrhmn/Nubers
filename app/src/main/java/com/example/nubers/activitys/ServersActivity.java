package com.example.nubers.activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;

import com.example.nubers.R;
import com.example.nubers.adapter.ServerAdapter;
import com.example.nubers.adapter.ServicesAdapter;
import com.example.nubers.api.ApiCall;
import com.example.nubers.api.ApiCallInterface;
import com.example.nubers.databinding.ActivityNumbersBinding;
import com.example.nubers.databinding.ActivityServersBinding;
import com.example.nubers.models.Numbers;
import com.example.nubers.models.ServicesModel;
import com.example.nubers.utils.ApiEndPoint;
import com.example.nubers.utils.Connectivity;
import com.example.nubers.utils.Data;
import com.example.nubers.utils.Tools;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ServersActivity extends AppCompatActivity implements ApiCallInterface {

    private ActivityServersBinding binding;
    private ArrayList<Numbers> numbersArrayList = new ArrayList<>();
    private ServerAdapter adapter;
    private String platform;
    private String country;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityServersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        platform = Data.getIdPlatform(this);
        country = Data.getIdCountry(this);
        Log.e("KIA---->", "onCreate: "+ platform+" - "+ country);


        apiCall();


    }

    @Override
    public void onResponse(JSONObject jsonObject) {
//        Tools.invisibleProgressBar(binding.spinKit);
        try {
            switch (jsonObject.getString("request")) {
                case ApiEndPoint.GET_ALL_NUMBERS:


                    if (jsonObject.getBoolean("isSuccess")) {
                        Log.d("KIA----> ", "onResponse: "+jsonObject.getJSONArray("result"));
                        JSONArray res = jsonObject.getJSONArray("result");

                        for (int i =0 ; i<res.length(); i++){
                            JSONObject data = res.getJSONObject(i);

                            Numbers numbers = new Numbers();
                            numbers.setAmount(data.getString("amount"));
                            numbers.setOperator(data.getString("operator"));

                            if(!data.getString("count").equals("0")){

                                numbersArrayList.add(numbers);
                            }


                        }

                        recyclerCountry();
                        binding.serversRecycler.setVisibility(View.VISIBLE);
                        binding.connectionError.getRoot().setVisibility(View.GONE);
                        binding.spinKit.setVisibility(View.GONE);


                    } else {

                    }
                    break;
            }
        } catch (Exception e) {

        }
    }

    private void recyclerCountry() {

        if(numbersArrayList.isEmpty()){
            binding.serversRecycler.setVisibility(View.INVISIBLE);
            binding.errorTv.setVisibility(View.VISIBLE);
            binding.errorTv.setText("شماره ای موجود نیست لطفا مدتی دیگر تلاش کنید.");
        }else {
            LinearLayoutManager verticalLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
            binding.serversRecycler.setLayoutManager(verticalLayoutManager);

            adapter = new ServerAdapter(numbersArrayList, this);
            binding.serversRecycler.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }



    }


    private void apiCall(){
        if (Connectivity.isConnected(this)) {
            new ApiCall(this, this).getAllNumbers(Data.getIdCountry(this), Data.getIdPlatform(this));
        } else {

            binding.serversRecycler.setVisibility(View.GONE);
            binding.connectionError.getRoot().setVisibility(View.VISIBLE);


        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_from_down, R.anim.slide_from_up);

    }
}