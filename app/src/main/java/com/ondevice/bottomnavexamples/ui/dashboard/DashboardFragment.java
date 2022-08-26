package com.ondevice.bottomnavexamples.ui.dashboard;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.arch.lifecycle.ViewModelProvider;
import android.widget.Toast;

import com.ondevice.bottomnavexamples.R;
import com.ondevice.bottomnavexamples.databinding.FragmentDashboardBinding;
import com.ondevice.bottomnavmodule.DashboardViewModel;

public class DashboardFragment extends Fragment {

    //private FragmentDashboardBinding binding;
    TextView text_dashboard;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        text_dashboard = root.findViewById(R.id.text_dashboard);

        //((AppCompatActivity) getContext()).getSupportActionBar().setTitle("Dashboard");

        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory())
                        .get(DashboardViewModel.class);

        //binding = FragmentDashboardBinding.inflate(inflater, container, false);
        //View root = binding.getRoot();
        //final TextView textView = binding.textDashboard;
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), text_dashboard::setText);

        text_dashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("upi://pay?pa=navanathhadapad@oksbi&pn=Naganath%20Hadapad&" +
                        "tn=Testing%20by%20Navnath&am=1&cu=INR&url=https://pay2all.in");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivityForResult(intent, 1421);
            }
        });

        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==1421)
        {
            if (resultCode==RESULT_OK)
            {
                assert data != null;
                Log.e("data","response "+data.getStringExtra("response"));
                Toast.makeText(getContext(), "response : "+data.getStringExtra("response"), Toast.LENGTH_LONG).show();

            }
        }
    }

    /*@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }*/
}