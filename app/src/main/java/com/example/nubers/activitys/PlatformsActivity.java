package com.example.nubers.activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.nubers.R;
import com.example.nubers.adapter.CountrysAdapter;
import com.example.nubers.adapter.ServicesAdapter;
import com.example.nubers.api.ApiCall;
import com.example.nubers.api.ApiCallInterface;
import com.example.nubers.databinding.ActivityMainBinding;
import com.example.nubers.databinding.ActivityPlatformsBinding;
import com.example.nubers.models.CountryModel;
import com.example.nubers.models.ServicesModel;
import com.example.nubers.utils.ApiEndPoint;
import com.example.nubers.utils.Connectivity;
import com.example.nubers.utils.MyActionDialog;
import com.example.nubers.utils.Tools;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class PlatformsActivity extends AppCompatActivity implements ApiCallInterface {

    private static final String TAG = "Kia----PlatformsActivity---->";
    private ActivityPlatformsBinding binding;
    private ServicesAdapter adapter;
    private ArrayList<ServicesModel> servicesModelArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPlatformsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());




        apiCall();

        MyActionDialog.dialogButtons(this, binding.connectionError.again,binding.connectionError.exit );



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_from_down, R.anim.slide_from_up);

    }

    @SuppressLint("LongLogTag")
    @Override
    public void onResponse(JSONObject jsonObject) {

        Tools.invisibleProgressBar(binding.spinKit);
        try {
            switch (jsonObject.getString("request")) {
                case ApiEndPoint.GET_ALL_SERVICES:


                    if (jsonObject.getBoolean("isSuccess")) {
                        Log.d(TAG, "onResponse: "+jsonObject.getJSONArray("result"));
                        JSONArray res = jsonObject.getJSONArray("result");

                        for (int i =0 ; i<res.length(); i++){
                            JSONObject data = res.getJSONObject(i);

                            ServicesModel servicesModel = new ServicesModel();

                            servicesModel.setId(data.getString("id"));
                            servicesModel.setName(data.getString("name"));
                            servicesModel.setName_en(data.getString("name_en"));
                            servicesModel.setDescription(data.getString("description"));
                            servicesModel.setImage(data.getString("image"));
                            servicesModel.setActive(data.getString("active"));
                            servicesModel.setEmoji(data.getString("emoji"));

                            servicesModelArrayList.add(servicesModel);
                        }

                        recyclerCountry();
                        binding.recyclerServices.setVisibility(View.VISIBLE);
                        binding.connectionError.getRoot().setVisibility(View.GONE);


                    } else {

                    }
                    break;
            }
        } catch (Exception e) {

        }
    }

    private void recyclerCountry() {

        LinearLayoutManager verticalLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        binding.recyclerServices.setLayoutManager(verticalLayoutManager);

        adapter = new ServicesAdapter(servicesModelArrayList, this);
        binding.recyclerServices.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    private void apiCall(){
        if (Connectivity.isConnected(this)) {
            new ApiCall(this, this).getAllServices();
        } else {

            binding.recyclerServices.setVisibility(View.GONE);
            binding.connectionError.getRoot().setVisibility(View.VISIBLE);


        }
    }


}