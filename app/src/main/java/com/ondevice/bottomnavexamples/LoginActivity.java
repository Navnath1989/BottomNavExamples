package com.ondevice.bottomnavexamples;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ondevice.bottomnavexamples.databinding.ActivityLoginBinding;
import com.ondevice.bottomnavmodule.LoginViewModel;
import com.ondevice.bottomnavmodule.User;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin;
    LoginViewModel loginViewModel;
    TextView tvTitle;
    EditText edEmail, edPassw;

    //ActivityLoginBinding binding;
    ViewDataBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);

        //binding = DataBindingUtil.setContentView(LoginActivity.this, R.layout.activity_login);
        //binding.setLifecycleOwner(this);


        btnLogin = findViewById(R.id.btnLogin);
        tvTitle = findViewById(R.id.tvTitle);

        edEmail = findViewById(R.id.edEmail);
        edPassw = findViewById(R.id.edPassw);

        //loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        //loginViewModel = new ViewModelProvider(this,
         //       new ViewModelProvider.NewInstanceFactory()).get(LoginViewModel.class);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Intent i = new Intent(LoginActivity.this, MainActivity.class);
                //startActivity(i);

             loginViewModel.getUser().observe(LoginActivity.this, new Observer<User>() {
                    @Override
                    public void onChanged(@Nullable User user) {

                        String inemail = user.getmEmail();
                        //inemail = edEmail.getText().toString();
                        edEmail.requestFocus();

                        String inpass = user.getmPassword();
                        //inpass = edPassw.getText().toString();
                        edPassw.requestFocus();

                        if (inemail.length() > 0 || inpass.length() > 0)
                            Toast.makeText(getApplicationContext(), "email : " + inemail + " password " +
                                    inpass, Toast.LENGTH_SHORT).show();

                        tvTitle.setText(inemail);

                        //Toast.makeText(LoginActivity.this, "Email :"+user.getmEmail(), Toast.LENGTH_SHORT).show();
                        //Toast.makeText(LoginActivity.this, "Password :"+user.getmPassword(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}