package com.ondevice.bottomnavmodule;

import android.util.Patterns;

public class User {

    private String mEmail;
    private String mPassword;

    public User(String email, String mPass){
        mEmail = email;
        mPassword = mPass;
    }

    public String getmEmail() {
        if (mEmail == null) {
            return "";
        }
        return mEmail;
    }

    public String getmPassword() {
        if (mPassword == null) {
            return "";
        }
        return mPassword;
    }
    public boolean isEmailValid() {
        return Patterns.EMAIL_ADDRESS.matcher(getmEmail()).matches();
    }

    public boolean isPasswordLengthGreaterThan5() {
        return getmPassword().length() > 5;
    }
}
