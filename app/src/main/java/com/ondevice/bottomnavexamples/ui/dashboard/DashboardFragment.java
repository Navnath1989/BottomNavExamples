package com.ondevice.bottomnavexamples.ui.dashboard;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.arch.lifecycle.ViewModelProvider;

import com.ondevice.bottomnavexamples.R;
import com.ondevice.bottomnavexamples.databinding.FragmentDashboardBinding;
import com.ondevice.bottomnavmodule.DashboardViewModel;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    TextView text_dashboard;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        text_dashboard = root.findViewById(R.id.text_dashboard);

        ((AppCompatActivity) getContext()).getSupportActionBar().setTitle("Dashboard");

        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory())
                        .get(DashboardViewModel.class);

        //binding = FragmentDashboardBinding.inflate(inflater, container, false);
        //View root = binding.getRoot();
        //final TextView textView = binding.textDashboard;
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), text_dashboard::setText);

        return root;
    }

    /*@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }*/
}