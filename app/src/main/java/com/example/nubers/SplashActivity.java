package com.example.nubers;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;

import com.example.nubers.databinding.ActivityMainBinding;
import com.example.nubers.databinding.ActivitySplashBinding;

public class SplashActivity extends AppCompatActivity {

    private ActivitySplashBinding binding;
    private int per = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        // result varible define as a public


        new CountDownTimer(6000, 450) {

            public void onTick(long millisUntilFinished) {
                binding.textPer.setText(per+ "%");

                if(millisUntilFinished/1000 <= 1){
                    binding.textPer.setText("خوش آمدید");


                }else{
                    per+=10;
                }
                // logic to set the EditText could go here
            }

            public void onFinish() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                ActivityOptions options =
                        ActivityOptions.makeCustomAnimation(SplashActivity.this, R.anim.fade_in, R.anim.fade_out);
                startActivity(intent, options.toBundle());
                finish();
            }

        }.start();


    }
}