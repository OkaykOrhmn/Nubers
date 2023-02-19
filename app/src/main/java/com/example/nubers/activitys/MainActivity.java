package com.example.nubers.activitys;

import static android.view.View.VISIBLE;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.GridLayout;
import android.widget.Toast;

import com.example.nubers.R;
import com.example.nubers.adapter.CountrysAdapter;
import com.example.nubers.api.ApiCall;
import com.example.nubers.api.ApiCallInterface;
import com.example.nubers.models.CountryModel;
import com.example.nubers.utils.ApiEndPoint;
import com.example.nubers.utils.Connectivity;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nubers.databinding.ActivityMainBinding;
import com.example.nubers.utils.Tools;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ApiCallInterface {

    private static final String TAG = "KIA----MainActivity----> ";
    private ActivityMainBinding binding;
    private boolean isExit = false;
    private ArrayList<CountryModel> countryModelArrayList = new ArrayList<>();
    private CountrysAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        binding.menuOpen.setOnClickListener(view -> {
            binding.drawerLayout.open();
        });

        binding.profileButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ProfilesActivity.class);
            ActivityOptions options =
                    ActivityOptions.makeCustomAnimation(MainActivity.this, R.anim.slide_to_right, R.anim.slide_to_left);
            startActivity(intent, options.toBundle());
        });

        if (Connectivity.isConnected(this)) {
            new ApiCall(this, this).getAllCountry();
        } else {

        }




    }

    @Override
    public void onBackPressed() {
        if(!isExit) {
            if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                binding.drawerLayout.closeDrawer(GravityCompat.START);
            } else {
                isExit = true;
                Toast.makeText(this, R.string.exite_alart, Toast.LENGTH_SHORT).show();
                Handler h = new Handler();
                Runnable r = () -> {
                    isExit = false;
                };
                h.postDelayed(r, 2000);
            }
        }else{
            super.onBackPressed();
        }
    }

    @SuppressLint("LongLogTag")
    @Override
    public void onResponse(JSONObject jsonObject) {
        Tools.invisibleProgressBar(binding.spinKit);
        try {
            switch (jsonObject.getString("request")) {
                case ApiEndPoint.GET_ALL_COUNTRY:


                    if (jsonObject.getBoolean("isSuccess")) {
                        Log.d(TAG, "onResponse: "+jsonObject.getJSONArray("result"));
                        JSONArray res = jsonObject.getJSONArray("result");

                        for (int i =0 ; i<res.length(); i++){
                            JSONObject data = res.getJSONObject(i);

                            CountryModel countryModel = new CountryModel();

                            countryModel.setId(data.getString("id"));
                            countryModel.setName(data.getString("name"));
                            countryModel.setName_en(data.getString("name_en"));
                            countryModel.setAreacode(data.getString("areacode"));
                            countryModel.setEmoji(data.getString("emoji"));
                            countryModel.setImage(data.getString("image"));
                            countryModel.setActive(data.getString("active"));

                            countryModelArrayList.add(countryModel);
                        }

                        recyclerCountry();


                    } else {

                    }
                    break;
            }
        } catch (Exception e) {

        }

    }

    private void recyclerCountry() {

        LinearLayoutManager verticalLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        binding.recyclerCountry.setLayoutManager(gridLayoutManager);

        adapter = new CountrysAdapter(countryModelArrayList, this);
        binding.recyclerCountry.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
}