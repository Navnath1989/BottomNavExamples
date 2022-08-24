package com.ondevice.bottomnavexamples.ui.home;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.arch.lifecycle.ViewModelProvider;
import android.widget.Toast;

import com.ondevice.bottomnavexamples.R;
import com.ondevice.bottomnavexamples.databinding.FragmentHomeBinding;
import com.ondevice.bottomnavmodule.HomeViewModel;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    TextView text_home;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container,false);

        text_home = root.findViewById(R.id.text_home);

        ((AppCompatActivity) getContext()).getSupportActionBar().setTitle("Home");

        HomeViewModel homeViewModel =
                new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory())
                        .get(HomeViewModel.class);

        //binding = FragmentHomeBinding.inflate(inflater, container, false);
        //View root = binding.getRoot();
        //final TextView textView = binding.textHome;
        //homeViewModel.getText().observe(getViewLifecycleOwner(), text_home::setText);

        homeViewModel.getText().observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
                text_home.setText(s);
            }
        });

        return root;
    }

    /*@Override
    public void onDestroyView() {
        super.onDestroyView();
        //binding = null;

    }*/
}