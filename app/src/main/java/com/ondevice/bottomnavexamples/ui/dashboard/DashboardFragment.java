package com.ondevice.bottomnavexamples.ui.dashboard;

import static android.app.Activity.RESULT_OK;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.arch.lifecycle.ViewModelProvider;

import com.ondevice.bottomnavexamples.MyListAdapter;
import com.ondevice.bottomnavexamples.databinding.FragmentDashboardBinding;
import com.ondevice.bottomnavmodule.DashboardViewModel;
import com.ondevice.bottomnavmodule.MyListData;

import java.util.List;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    TextView text_dashboard;
    RecyclerView rcvMyData;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        //text_dashboard = root.findViewById(R.id.text_dashboard);
        //((AppCompatActivity) getContext()).getSupportActionBar().setTitle("Dashboard");

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        //ViewGroup root = container;
        View root = binding.getRoot();

        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory())
                        .get(DashboardViewModel.class);

        //binding = FragmentDashboardBinding.inflate(inflater, container, false);
        //View root = binding.getRoot();
        //final TextView textView = binding.textDashboard;

        //dashboardViewModel.getText().observe(getViewLifecycleOwner(), text_dashboard::setText);
        /*text_dashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("upi://pay?pa=navanathhadapad@oksbi&pn=Naganath%20Hadapad&" +
                        "tn=Testing%20by%20Navnath&am=1&cu=INR&url=https://pay2all.in");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivityForResult(intent, 1421);
            }
        });*/

        //rcvMyData = root.findViewById(R.id.rcvMyData);
        binding.rcvMyData.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        //HeroViewModel model = ViewModelProviders.of(this).get(HeroViewModel.class);
        dashboardViewModel.getMyData().observe(getActivity(), new Observer<List<MyListData>>() {
            @Override
            public void onChanged(@Nullable List<MyListData> heroList) {
                MyListAdapter basicInfoAdapter = new MyListAdapter(heroList);
                binding.rcvMyData.setAdapter(basicInfoAdapter);
            }
        });


        return root;
    }

    /*@Override
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
    }*/

}