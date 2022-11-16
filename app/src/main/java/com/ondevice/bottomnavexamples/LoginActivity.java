package com.ondevice.bottomnavexamples;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ondevice.bottomnavexamples.databinding.ActivityLoginBinding;
import com.ondevice.bottomnavexamples.ui.home.HomeFragment;
import com.ondevice.bottomnavmodule.LoginViewModel;
import com.ondevice.bottomnavmodule.User;

import java.util.Objects;
import java.util.Observer;

public class LoginActivity extends TestingActivity {

    Button btnLogin;
    LoginViewModel loginViewModel;
    TextView tvTitle;
    EditText edEmail, edPassw;

    ActivityLoginBinding binding;
    //ViewDataBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setContentView(R.layout.activity_login);

        binding = DataBindingUtil.setContentView(LoginActivity.this, R.layout.activity_login);

        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        binding.setLoginViewModel(loginViewModel);
        binding.setLifecycleOwner(this);
        binding.executePendingBindings();

        /*btnLogin = findViewById(R.id.btnLogin);
        tvTitle = findViewById(R.id.tvTitle);
        edEmail = findViewById(R.id.edEmail);
        edPassw = findViewById(R.id.edPassw);*/

        //loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        //loginViewModel = new ViewModelProvider(this,
         //       new ViewModelProvider.NewInstanceFactory()).get(LoginViewModel.class);

        /*loginViewModel.getUser().observe(LoginActivity.this, new Observer<User>() {
            @Override
            public void onChanged(@Nullable User user) {
                if (user.getEmail() != null || user.getPassword() != null){
                    *//*if (progressBarLayout != null) {
                        setProgressVisible(View.VISIBLE);
                        progressTextMessage.setText(user.getEmail());
                    }*//*
                    Toast.makeText(getApplicationContext(), "email : " + user.getEmail() + " password "
                            + user.getPassword(), Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    i.putExtra("testN", user.getEmail());
                    startActivity(i);
                }else {
                    Toast.makeText(LoginActivity.this, "Enter Valid Details!", Toast.LENGTH_SHORT).show();
                }
            }
        });*/
    }
}