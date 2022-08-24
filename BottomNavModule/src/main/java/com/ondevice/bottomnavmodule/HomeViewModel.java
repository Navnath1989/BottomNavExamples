package com.ondevice.bottomnavmodule;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.CountDownTimer;
import android.widget.Toast;

public class HomeViewModel extends ViewModel {

    //private final MutableLiveData<String> mText;

    private MutableLiveData<String> mText;

    private MutableLiveData<Long> strtcount;
    private MutableLiveData<Boolean> fhinish;
    Object abc;

    /*public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");

    }*/

    public LiveData<String> getText() {

        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");

        return mText;
    }

    public LiveData<Long> getstrt() {

        strtcount = new MutableLiveData<>();
        //strtcount.setValue("This is home fragment");

        return strtcount;
    }

    public LiveData<Boolean> getfinish() {

        fhinish = new MutableLiveData<>();
        //fhinish.setValue("This is home fragment");

        return fhinish;
    }

    public void starCount(){

        new CountDownTimer(5000, 100){

            @Override
            public void onTick(long millisUntilFinished) {
                Long abc = millisUntilFinished / 1000;
                strtcount.setValue(abc);
            }

            @Override
            public void onFinish() {
                fhinish.setValue(true);
            }
        }.start();
    }

}

