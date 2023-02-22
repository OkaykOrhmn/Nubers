package com.example.nubers.adapter;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nubers.R;
import com.example.nubers.activitys.MainActivity;
import com.example.nubers.activitys.PlatformsActivity;
import com.example.nubers.databinding.CountryListBinding;
import com.example.nubers.models.CountryModel;
import com.example.nubers.utils.ApiEndPoint;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.ArrayList;

public class CountrysAdapter extends RecyclerView.Adapter<CountrysAdapter.ViewHolder > {

    private static final String TAG = "CountrysAdapter----> ";
    private Context context;
    private ArrayList<CountryModel> countryModelArrayList;
    private int deletePosition = -1;

    public CountrysAdapter(ArrayList<CountryModel> countryModelArrayList, Context context) {
        this.countryModelArrayList = countryModelArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public CountrysAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        CountryListBinding v = CountryListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CountrysAdapter.ViewHolder(v);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onBindViewHolder(@NonNull CountrysAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        CountryModel item = countryModelArrayList.get(position);

        holder.binding.countryName.setText(item.getName());

        Picasso.get().load(ApiEndPoint.BASE_URL_IMAGES+item.getImage()).into(holder.binding.countryImage);

        holder.binding.countryImage.setOnClickListener(view -> {
            Intent intent = new Intent(context, PlatformsActivity.class);
            ActivityOptions options =
                    ActivityOptions.makeCustomAnimation(context, R.anim.slide_to_down, R.anim.slide_to_up);
            context.startActivity(intent, options.toBundle());
        });




    }

    @Override
    public int getItemCount() {
        return countryModelArrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {


        public CountryListBinding binding;

        public ViewHolder(@NonNull CountryListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
    }