package com.ondevice.bottomnavmodule;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class LoginViewModel extends ViewModel {

    public MutableLiveData<String> email = new MutableLiveData<>();
    public MutableLiveData<String> password = new MutableLiveData<>();

    public MutableLiveData<String> Downloadmsg = new MutableLiveData<>();

    private MutableLiveData<User> userMutableLiveData;


    public LiveData<User> getUser() {

        if (userMutableLiveData == null) {
            userMutableLiveData = new MutableLiveData<>();
            //onClick();
        }

        //User user = new User("navnath@gmail.com", "navanath");
        //User user = new User(email.getValue(), password.getValue());
        //userMutableLiveData.setValue(user);

        return userMutableLiveData;
    }

    public LiveData<String> getShowDownl() {

        if (Downloadmsg == null) {
            Downloadmsg = new MutableLiveData<>();
            //onClick();
            Downloadmsg.setValue("Downloading...");
        }

        //User user = new User("navnath@gmail.com", "navanath");
        //User user = new User(email.getValue(), password.getValue());
        //userMutableLiveData.setValue(user);

        return Downloadmsg;
    }

    public void onClickkk() {

        //User user = new User("navnath@gmail.com", "navanath");
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
