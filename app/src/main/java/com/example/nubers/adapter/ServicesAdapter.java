package com.example.nubers.adapter;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nubers.R;
import com.example.nubers.activitys.NumbersActivity;
import com.example.nubers.activitys.OperatorActivity;
import com.example.nubers.activitys.PlatformsActivity;
import com.example.nubers.activitys.ServersActivity;
import com.example.nubers.databinding.ServicesListBinding;
import com.example.nubers.databinding.ServicesListBinding;
import com.example.nubers.models.ServicesModel;
import com.example.nubers.models.ServicesModel;
import com.example.nubers.utils.ApiEndPoint;
import com.example.nubers.utils.Data;
import com.example.nubers.utils.SharpSvg;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ServicesAdapter extends RecyclerView.Adapter<ServicesAdapter.ViewHolder > {

    private static final String TAG = "ServicesAdapter----> ";
    private Context context;
    private ArrayList<ServicesModel> servicesModelArrayList;

    public ServicesAdapter(ArrayList<ServicesModel> servicesModelArrayList, Context context) {
        this.servicesModelArrayList = servicesModelArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ServicesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        ServicesListBinding v = ServicesListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ServicesAdapter.ViewHolder(v);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onBindViewHolder(@NonNull ServicesAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ServicesModel item = servicesModelArrayList.get(position);

        SharpSvg.fetchSvg(context, ApiEndPoint.BASE_URL_IMAGES+ item.getImage(), holder.binding.iconService);
        holder.binding.titleService.setText(item.getName());
        holder.binding.layout.setOnClickListener(view -> {
            Intent intent = new Intent(context, ServersActivity.class);
            Data.setIdPlatform(item.getId(),context);
            Data.setImagePlatform(item.getImage(),context);
            Data.setNamePlatform(item.getName(),context);
            Data.setNameEnPlatform(item.getName_en(),context);
            ActivityOptions options =
                    ActivityOptions.makeCustomAnimation(context, R.anim.slide_to_down, R.anim.slide_to_up);
            context.startActivity(intent, options.toBundle());
        });





    }

    @Override
    public int getItemCount() {
        return servicesModelArrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {


        public ServicesListBinding binding;

        public ViewHolder(@NonNull ServicesListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}