package com.example.nubers.fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.example.nubers.R;
import com.example.nubers.activitys.MainActivity;
import com.example.nubers.activitys.SplashActivity;
import com.example.nubers.databinding.FragmentEnterCodeBinding;
import com.example.nubers.utils.Tools;

public class EnterCodeFragment extends Fragment {
    private static final String TAG = "Kia----EnterCodeFragment----> ";
    private FragmentEnterCodeBinding binding;
    private boolean focus = false;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEnterCodeBinding.inflate(inflater, container, false);



        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    @SuppressLint("LongLogTag")
    @Override
    public void onViewCreated(@NonNull View view1, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view1, savedInstanceState);
        binding.nextButt.setVisibility(View.INVISIBLE);


        onBack();

        SharedPreferences sh = getActivity().getSharedPreferences("MySharedPref", getContext().MODE_PRIVATE);
        String number = sh.getString("number", "");

        Log.d(TAG, "onViewCreated: "+ number);
        binding.mobileNumber.setText(number);

        binding.editSearch.setOnFocusChangeListener((view, b) -> {
            focus = b;
            if(b){
                binding.editSearch.setHint("");
            }else{
                binding.editSearch.setHint("کد");

            }
        });

        binding.editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length() == 6){
                    binding.nextButt.setVisibility(View.VISIBLE);
                }else{
                    binding.nextButt.setVisibility(View.INVISIBLE);

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        binding.nextButt.setOnClickListener(view -> {
            Tools.hideSoftKeyboard(getActivity());

            Intent intent = new Intent(getActivity(), MainActivity.class);
            ActivityOptions options =
                    ActivityOptions.makeCustomAnimation(getActivity(), R.anim.fade_in, R.anim.fade_out);
            startActivity(intent, options.toBundle());
            getActivity().finish();
        });
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    private void onBack(){
        ////////////////////----------~~ <onBackPress> ~~----------////////////////////

        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                // Handle the back button event
                if(focus){
                    binding.editSearch.clearFocus();

                }else{
                    Navigation.findNavController(getView()).popBackStack();
                }


            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), callback);
//
//        ////////////////////----------~~ <onBackPress> ~~----------////////////////////

    }



}