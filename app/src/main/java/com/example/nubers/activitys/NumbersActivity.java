package com.example.nubers.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.nubers.R;
import com.example.nubers.api.ApiCall;
import com.example.nubers.api.ApiCallInterface;
import com.example.nubers.databinding.ActivityMainBinding;
import com.example.nubers.databinding.ActivityNumbersBinding;
import com.example.nubers.models.CountryModel;
import com.example.nubers.utils.ApiEndPoint;
import com.example.nubers.utils.Connectivity;
import com.example.nubers.utils.Data;
import com.example.nubers.utils.SharpSvg;
import com.example.nubers.utils.Tools;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

public class NumbersActivity extends AppCompatActivity implements ApiCallInterface {

    private ActivityNumbersBinding binding;
    private String server;
    private String serverName;

    private String idCountry;
    private String nameCountry;
    private String imageCountry;
    private String enCountry;

    private String idPlatform;
    private String namePlatform;
    private String imagePlatform;
    private String enPlatform;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNumbersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        server = Data.getServer(this);
        serverName = Data.getServerName(this);

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

        binding.serverTv.setText("سرور "+ serverName);

        apiCall();








    }

    private void apiCall(){
        if (Connectivity.isConnected(this)) {
            new ApiCall(this, this).getNumbers(idCountry, idPlatform, server);
        } else {

            binding.allLay.setVisibility(View.INVISIBLE);
            binding.connectionError.getRoot().setVisibility(View.VISIBLE);


        }
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
                case ApiEndPoint.GET_NUMBERS:


                    if (jsonObject.getBoolean("isSuccess")) {
                        JSONArray res = jsonObject.getJSONArray("result");

                        for (int i =0 ; i<res.length(); i++){
                            JSONObject data = res.getJSONObject(i);

                            String rep = data.getString("repeat");
                            String repeat;

                            if(rep.equals("0")){
                                repeat = "ندارد";
                            }else{
                                repeat = "دارد";

                            }

                            int price = Integer.parseInt(data.getString("amount"));
                            price += 20000;

                            binding.timeTv.setText(data.getString("time"));
                            binding.countTv.setText(data.getString("count"));
                            binding.priceTv.setText(Tools.createPrice(price));
                            binding.repeatTv.setText(repeat);



//                            countryModelArrayList.add(countryModel);
                        }

//                        recyclerCountry(countryModelArrayList);
//                        binding.a.setVisibility(View.VISIBLE);
//                        binding.connectionError.getRoot().setVisibility(View.GONE);

                        binding.allLay.setVisibility(View.VISIBLE);



                    } else {
                        binding.errorTv.setVisibility(View.VISIBLE);


                    }
                    break;
            }
        } catch (Exception e) {

        }

    }
}