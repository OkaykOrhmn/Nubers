package com.example.nubers.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.nubers.R;
import com.example.nubers.api.ApiCallInterface;
import com.example.nubers.databinding.ActivityMainBinding;
import com.example.nubers.databinding.ActivityNumbersBinding;
import com.example.nubers.models.CountryModel;
import com.example.nubers.utils.ApiEndPoint;
import com.example.nubers.utils.Data;
import com.example.nubers.utils.SharpSvg;
import com.example.nubers.utils.Tools;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

public class NumbersActivity extends AppCompatActivity implements ApiCallInterface {

    private ActivityNumbersBinding binding;
    private String server;

    private String idCountry;
    private String nameCountry;
    private String imageCountry;
    private String enCountry;

    private String idPlatform;
    private String namePlatform;
    private String imagePlatform;
    private String enPlatform;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNumbersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        server = Data.getServer(this);

        idCountry = Data.getIdCountry(this);
        nameCountry = Data.getNameCountry(this);
        enCountry = Data.getNameEnCountry(this);
        imageCountry = Data.getImageCountry(this);

        idPlatform = Data.getIdPlatform(this);
        namePlatform = Data.getNamePlatform(this);
        enPlatform = Data.getNameEnPlatform(this);
        imagePlatform = Data.getImagePlatform(this);

        binding.countryName.setText(nameCountry);
        Picasso.get().load(ApiEndPoint.BASE_URL_IMAGES+imageCountry).into(binding.countryImage);

        binding.platformName.setText(namePlatform);
        SharpSvg.fetchSvg(this, ApiEndPoint.BASE_URL_IMAGES+ imagePlatform, binding.platformImage);




    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_from_down, R.anim.slide_from_up);

    }

    @Override
    public void onResponse(JSONObject jsonObject) {
        Tools.invisibleProgressBar(binding.spinKit);
        try {
            switch (jsonObject.getString("request")) {
                case ApiEndPoint.GET_ALL_COUNTRY:


                    if (jsonObject.getBoolean("isSuccess")) {
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

//                            countryModelArrayList.add(countryModel);
                        }

//                        recyclerCountry(countryModelArrayList);
//                        binding.a.setVisibility(View.VISIBLE);
                        binding.connectionError.getRoot().setVisibility(View.GONE);



                    } else {

                    }
                    break;
            }
        } catch (Exception e) {

        }

    }
}