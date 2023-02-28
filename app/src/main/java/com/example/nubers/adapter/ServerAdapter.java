package com.example.nubers.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nubers.R;
import com.example.nubers.activitys.NumbersActivity;
import com.example.nubers.activitys.PlatformsActivity;
import com.example.nubers.databinding.ListRowBinding;
import com.example.nubers.models.Numbers;
import com.example.nubers.utils.ApiEndPoint;
import com.example.nubers.utils.Data;
import com.example.nubers.utils.Tools;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ServerAdapter extends RecyclerView.Adapter<ServerAdapter.ViewHolder > {

    private static final String TAG = "ServerAdapter----> ";
    private Context context;
    private ArrayList<Numbers> numbersArrayList;
    private int deletePosition = -1;

    public ServerAdapter(ArrayList<Numbers> numbersArrayList, Context context) {
        this.numbersArrayList = numbersArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ServerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        ListRowBinding v = ListRowBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ServerAdapter.ViewHolder(v);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onBindViewHolder(@NonNull ServerAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Numbers item = numbersArrayList.get(position);

        holder.binding.name.setText("شماره سرور "+item.getOperator());
        int price = Integer.parseInt(item.getAmount());
        Log.e(TAG, "onBindViewHolder: "+price );
        price += 20000;
        holder.binding.priceButton.setText(Tools.createPrice(price));

        holder.binding.layout.setOnClickListener(view -> {
            Intent intent = new Intent(context, NumbersActivity.class);
            Data.setServer(item.getOperator(), context);
            context.startActivity(intent);
            ((Activity)context).overridePendingTransition(R.anim.slide_to_down, R.anim.slide_to_up);
        });

        holder.binding.priceButton.setOnClickListener(view -> {
            Intent intent = new Intent(context, NumbersActivity.class);
            Data.setServer(item.getOperator(), context);
            context.startActivity(intent);
            ((Activity)context).overridePendingTransition(R.anim.slide_to_down, R.anim.slide_to_up);
        });






    }

    @Override
    public int getItemCount() {
        return numbersArrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {


        public ListRowBinding binding;

        public ViewHolder(@NonNull ListRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}