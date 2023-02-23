package com.example.nubers.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.nubers.R;
import com.example.nubers.databinding.ActivityNumbersBinding;
import com.example.nubers.databinding.ActivityOperatorBinding;
import com.example.nubers.utils.Data;

public class OperatorActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityOperatorBinding binding;
    private String server;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityOperatorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.serverOneButton.setOnClickListener(this::onClick);
        binding.serverTwoButton.setOnClickListener(this::onClick);
        binding.serverThreeButton.setOnClickListener(this::onClick);
        binding.serverFourButton.setOnClickListener(this::onClick);
        binding.minButton.setOnClickListener(this::onClick);
        binding.anyButton.setOnClickListener(this::onClick);


    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.server_one_button:
                server = "1";
                break;

            case R.id.server_two_button:
                server = "2";

                break;

            case R.id.server_three_button:
                server = "3";

                break;

            case R.id.server_four_button:
                server = "4";

                break;

            case R.id.any_button:
                server = "any";

                break;

            case R.id.min_button:
                server = "min";

                break;
        }

        intentTo(server);

    }

    private void intentTo(String server){
        Data.setServer(server, this);
        Intent intent = new Intent(this, NumbersActivity.class);
        ActivityOptions options =
                ActivityOptions.makeCustomAnimation(this, R.anim.slide_to_down, R.anim.slide_to_up);
        startActivity(intent, options.toBundle());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_from_down, R.anim.slide_from_up);

    }
}