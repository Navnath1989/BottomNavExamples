package com.ondevice.bottomnavmodule;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.Handler;
import android.view.View;

public class LoginViewModel extends ViewModel {

    private MutableLiveData<String> errorPassword = new MutableLiveData<>();
    private MutableLiveData<String> errorEmail = new MutableLiveData<>();

    private MutableLiveData<String> email = new MutableLiveData<>();
    private MutableLiveData<String> password = new MutableLiveData<>();

    private MutableLiveData<Integer> busy;
    public MutableLiveData<Integer> getBusy() {

        if (busy == null) {
            busy = new MutableLiveData<>();
            busy.setValue(8);
        }

        return busy;
    }

    private MutableLiveData<User> userMutableLiveData;

    public LiveData<User> getUser() {
        /*if (userMutableLiveData == null) {
            userMutableLiveData = new MutableLiveData<>();
            onLoginClicked();
        }*/

        userMutableLiveData = new MutableLiveData<>();
        //onLoginClicked();

        //User user = new User(email.getValue(), password.getValue());
        //userMutableLiveData.setValue(user);

        return userMutableLiveData;
    }

    public void onClick(View view) {

        User user = new User(email.getValue(), password.getValue());
        userMutableLiveData.setValue(user);

    }

    /*public void onLoginClicked() {

        getBusy().setValue(0); //View.VISIBLE
        User user = new User(email.getValue(), password.getValue());

        if (!user.isEmailValid()) {
            errorEmail.setValue("Enter a valid email address");
        } else {
            errorEmail.setValue(null);
        }

        if (!user.isPasswordLengthGreaterThan5())
            errorPassword.setValue("Password Length should be greater than 5");
        else {
            errorPassword.setValue(null);
        }

        userMutableLiveData.setValue(user);
        busy.setValue(8); //8 == View.GONE
    }*/


}
