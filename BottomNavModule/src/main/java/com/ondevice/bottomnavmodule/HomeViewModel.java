package com.ondevice.bottomnavmodule;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.widget.Toast;

public class HomeViewModel extends ViewModel {

    //private final MutableLiveData<String> mText;

    private MutableLiveData<String> mText;

    /*public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");

    }*/

    public LiveData<String> getText() {

        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");

        return mText;
    }
}