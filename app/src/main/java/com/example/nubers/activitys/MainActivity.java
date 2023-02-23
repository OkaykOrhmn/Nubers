package com.example.nubers.activitys;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
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
import com.example.nubers.utils.MyActionDialog;
import com.example.nubers.utils.Tools;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ApiCallInterface {

    private static final String TAG = "KIA----MainActivity----> ";
    private ActivityMainBinding binding;
    private boolean isExit = false;
    private ArrayList<CountryModel> countryModelArrayList = new ArrayList<>();
    private ArrayList<CountryModel> resultArrayList ;
    private CountrysAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.a.setVisibility(View.GONE);

        apiCall();

        binding.editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @SuppressLint("LongLogTag")
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                resultArrayList = Tools.searchResult(countryModelArrayList, charSequence.toString());
                Log.d(TAG, "onTextChanged: "+resultArrayList);
                if(charSequence.length() == 0){
                    recyclerCountry(countryModelArrayList);
                }else{
                    recyclerCountry(resultArrayList);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        binding.menuOpen.setOnClickListener(view -> {
            binding.drawerLayout.open();
        });

        binding.profileButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ProfilesActivity.class);
            ActivityOptions options =
                    ActivityOptions.makeCustomAnimation(MainActivity.this, R.anim.slide_to_right, R.anim.slide_to_left);
            startActivity(intent, options.toBundle());
        });


        MyActionDialog.dialogButtons(this, binding.connectionError.again,binding.connectionError.exit );



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

                        recyclerCountry(countryModelArrayList);
                        binding.a.setVisibility(View.VISIBLE);
                        binding.connectionError.getRoot().setVisibility(View.GONE);



                    } else {

                    }
                    break;
            }
        } catch (Exception e) {

        }

    }

    private void recyclerCountry(ArrayList<CountryModel> arrayList) {

        LinearLayoutManager verticalLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        binding.recyclerCountry.setLayoutManager(gridLayoutManager);

        adapter = new CountrysAdapter(arrayList, this);
        binding.recyclerCountry.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    private void apiCall(){
        if (Connectivity.isConnected(this)) {

            new ApiCall(this, this).getAllCountry();
        } else {

            binding.a.setVisibility(View.GONE);
            binding.connectionError.getRoot().setVisibility(View.VISIBLE);




        }
    }
}