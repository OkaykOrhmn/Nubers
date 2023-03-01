package com.example.nubers.activitys;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.nubers.R;
import com.example.nubers.adapter.CountrysAdapter;
import com.example.nubers.adapter.SliderAdapterExample;
import com.example.nubers.adapter.SliderItem;
import com.example.nubers.api.ApiCall;
import com.example.nubers.api.ApiCallInterface;
import com.example.nubers.models.CountryModel;
import com.example.nubers.utils.ApiEndPoint;
import com.example.nubers.utils.Connectivity;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nubers.databinding.ActivityMainBinding;
import com.example.nubers.utils.MyActionDialog;
import com.example.nubers.utils.Tools;
import com.google.android.material.navigation.NavigationView;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ApiCallInterface {

    private static final String TAG = "KIA----MainActivity----> ";
    private ActivityMainBinding binding;
    private NavController navController;
    private boolean isExit = false;
    private ArrayList<CountryModel> countryModelArrayList = new ArrayList<>();
    private ArrayList<SliderItem> sliderItemArrayList = new ArrayList<>();
    private ArrayList<CountryModel> resultArrayList;
    private CountrysAdapter adapter;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        SliderItem one = new SliderItem();
        SliderItem two = new SliderItem();
        SliderItem three = new SliderItem();
        SliderItem four = new SliderItem();

        one.setImageUrl(R.drawable.baner);
        two.setImageUrl(R.drawable.flgs);
        three.setImageUrl(R.drawable.baner);
        four.setImageUrl(R.drawable.insta);

        sliderItemArrayList.add(one);
        sliderItemArrayList.add(two);
        sliderItemArrayList.add(three);
        sliderItemArrayList.add(four);

        SliderView sliderView = findViewById(R.id.imageSlider);

        SliderAdapterExample adapter = new SliderAdapterExample(sliderItemArrayList,this);

        sliderView.setSliderAdapter(adapter);

        sliderView.setIndicatorAnimation(IndicatorAnimationType.DROP); //set indicator animation by using IndicatorAnimationType. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(4); //set scroll delay in seconds :
        sliderView.startAutoCycle();

        binding.a.setVisibility(View.GONE);

        NavigationView navView = binding.navView;
        navView.setNavigationItemSelectedListener(item -> {
            Intent intent = new Intent(this, DrawersActivity.class);
            ActivityOptions options =
                    ActivityOptions.makeCustomAnimation(MainActivity.this, R.anim.slide_from_right, R.anim.slide_from_left);
            switch (item.getItemId()) {
                case R.id.nav_home:
                    intent.putExtra("page", 0);
                    break;

                case R.id.nav_gallery:
                    intent.putExtra("page", 1);

                    break;

                case R.id.nav_slideshow:
                    intent.putExtra("page", 2);


                    break;

                case R.id.nav_home2:
                    intent.putExtra("page", 3);


                    break;
            }
            startActivity(intent, options.toBundle());
            return false;
        });
        apiCall();

        binding.editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @SuppressLint("LongLogTag")
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                resultArrayList = Tools.searchResult(countryModelArrayList, charSequence.toString());
                Log.d(TAG, "onTextChanged: " + resultArrayList);
                if (charSequence.length() == 0) {
                    recyclerCountry(countryModelArrayList);
                } else {
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


        MyActionDialog.dialogButtons(this, binding.connectionError.again, binding.connectionError.exit);


    }

    @Override
    public void onBackPressed() {
        if (!isExit) {
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
        } else {
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
                        Log.d(TAG, "onResponse: " + jsonObject.getJSONArray("result"));
                        JSONArray res = jsonObject.getJSONArray("result");

                        for (int i = 0; i < res.length(); i++) {
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

    private void apiCall() {
        if (Connectivity.isConnected(this)) {

            new ApiCall(this, this).getAllCountry();
        } else {

            binding.a.setVisibility(View.GONE);
            binding.connectionError.getRoot().setVisibility(View.VISIBLE);


        }
    }
}