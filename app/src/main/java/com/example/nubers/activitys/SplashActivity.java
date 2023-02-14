package com.example.nubers.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

import com.example.nubers.R;
import com.example.nubers.databinding.ActivitySplashBinding;

public class SplashActivity extends AppCompatActivity {

    private ActivitySplashBinding binding;
    private int per = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        new CountDownTimer(4000, 200) {

            @SuppressLint("SetTextI18n")
            public void onTick(long millisUntilFinished) {
                binding.textPer.setText(per+ "%");

                if(millisUntilFinished/1000 <= 1){
                    binding.textPer.setText(R.string.wellcome);


                }else{
                    per+=10;
                }
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