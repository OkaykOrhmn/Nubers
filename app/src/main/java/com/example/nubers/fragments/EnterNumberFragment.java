package com.example.nubers.fragments;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.nubers.R;
import com.example.nubers.databinding.FragmentEnterNumberBinding;
import com.example.nubers.utils.Tools;
import com.google.android.material.snackbar.Snackbar;


public class EnterNumberFragment extends Fragment {

    private static final String TAG = "Kia----EnterNumberFragment----> ";
    private FragmentEnterNumberBinding binding;

    @SuppressLint("LongLogTag")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentEnterNumberBinding.inflate(inflater, container, false);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MySharedPref",MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();





        binding.nextButt.setOnClickListener(view -> {
            Tools.hideSoftKeyboard(getActivity());

            if(binding.editSearch.getText().length() == 11){
                myEdit.putString("number", binding.editSearch.getText().toString());
                myEdit.commit();
                Navigation.findNavController(requireView()).navigate(R.id.action_to_enterCodeFragment);

            }else{
                Snackbar.make(requireView(),"شماره اشتباه است", Toast.LENGTH_SHORT ).show();
            }
        });
        // Inflate the layout for this fragment
        return binding.getRoot();
    }
}