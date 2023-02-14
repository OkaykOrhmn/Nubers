package com.example.nubers.activitys;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;

import com.example.nubers.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nubers.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private boolean isExit = false;

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
}