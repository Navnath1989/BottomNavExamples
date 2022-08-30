package com.ondevice.bottomnavexamples.ui.notifications;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.arch.lifecycle.ViewModelProvider;
import android.widget.Toast;

import com.ondevice.bottomnavexamples.R;
import com.ondevice.bottomnavexamples.databinding.FragmentNotificationsBinding;
import com.ondevice.bottomnavmodule.LoginViewModel;
import com.ondevice.bottomnavmodule.NotificationsViewModel;
import com.ondevice.bottomnavmodule.User;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;
    TextView text_notifications;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        text_notifications = root.findViewById(R.id.text_notifications);

        //((AppCompatActivity) getContext()).getSupportActionBar().setTitle("Notifications");

        NotificationsViewModel notificationsViewModel =
                new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(NotificationsViewModel.class);

        //binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        //View root = binding.getRoot();
        //final TextView textView = binding.textNotifications;
        notificationsViewModel.getText().observe(getViewLifecycleOwner(), text_notifications::setText);
        //notificationsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        return root;
    }

    /*@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }*/
}