package com.example.nubers.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.nubers.R;
import com.example.nubers.databinding.ActivityDrawersBinding;
import com.example.nubers.databinding.ActivityMainBinding;

public class DrawersActivity extends AppCompatActivity {

    private ActivityDrawersBinding binding;

    private int page = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDrawersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                page = 0;
            } else {
                page= extras.getInt("page");

            }
        } else {
            page= (int) savedInstanceState.getSerializable("page");
        }

        switch (page){
            case 0:
                supportPage();
                break;
            case 1:
                hintPage();
                break;
            case 2:
                othersAppPage();
                break;
            case 3:
                infoPage();
                break;


        }


    }

    private void supportPage(){
        binding.supportPage.getRoot().setVisibility(View.VISIBLE);

    }

    private void hintPage(){
        binding.hintPage.getRoot().setVisibility(View.VISIBLE);


    }

    private void othersAppPage(){
        binding.otherAppPage.getRoot().setVisibility(View.VISIBLE);


    }

    private void infoPage(){
        binding.aboutUsPage.getRoot().setVisibility(View.VISIBLE);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_to_right, R.anim.slide_to_left);

    }
}