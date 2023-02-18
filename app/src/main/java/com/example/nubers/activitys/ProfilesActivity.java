package com.example.nubers.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.nubers.R;
import com.example.nubers.utils.Tools;
import com.example.nubers.databinding.ActivityProfilesBinding;
import com.google.android.material.button.MaterialButton;

public class ProfilesActivity extends AppCompatActivity implements View.OnClickListener {


    private ActivityProfilesBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityProfilesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        String number = sh.getString("number", "");

        binding.numberProfile.setText(number);

        binding.addOne.setOnClickListener(this::onClick);
        binding.addTwo.setOnClickListener(this::onClick);
        binding.addThree.setOnClickListener(this::onClick);
        binding.addFour.setOnClickListener(this::onClick);
        binding.addFive.setOnClickListener(this::onClick);
        binding.addSix.setOnClickListener(this::onClick);

        binding.plusPrice.setOnClickListener(view -> {
            plusPrice();
        });

        binding.minusPrice.setOnClickListener(view -> {
            minusPrice();
        });

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_from_left);

    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        binding.priceToPay.setText(button.getText());


    }


    private void plusPrice() {
        String price = binding.priceToPay.getText().toString();
        int noSymbol = Tools.removePrice(price);
        if (noSymbol<120000){
            noSymbol+=10000;

        }
        binding.priceToPay.setText(Tools.createPrice(noSymbol));
    }


    private void minusPrice() {
        String price = binding.priceToPay.getText().toString();
        int noSymbol = Tools.removePrice(price);
        if (10000<noSymbol){
            noSymbol-=10000;

        }
        binding.priceToPay.setText(Tools.createPrice(noSymbol));
    }
}