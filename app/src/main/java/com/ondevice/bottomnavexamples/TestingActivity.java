package com.ondevice.bottomnavexamples;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ondevice.bottomnavmodule.LoginViewModel;
import com.ondevice.bottomnavmodule.User;

public class TestingActivity extends AppCompatActivity {

    public FrameLayout progressBarLayout;
    public TextView progressTextMessage;
    protected LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_login);

        /*loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        loginViewModel.getShowDownl().observe(TestingActivity.this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                Toast.makeText(TestingActivity.this, s, Toast.LENGTH_SHORT).show();
                progressTextMessage.setText(s);
            }
        });*/

    }

    public void setProgressVisible(int progressVisibility) {

        if (progressVisibility == 0) {
            progressBarLayout.setVisibility(View.VISIBLE);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        } else {
            progressBarLayout.setVisibility(View.GONE);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (progressBarLayout == null) {
            progressBarLayout = findViewById(R.id.progressBarOverlay);
            //progressBar = (ProgressBar) findViewById(R.id.progressBar);
            progressTextMessage = findViewById(R.id.progressMsg);
            if (progressBarLayout != null)
                //setProgressVisible(View.VISIBLE);

            setProgressVisible(View.GONE);
        }
    }
}