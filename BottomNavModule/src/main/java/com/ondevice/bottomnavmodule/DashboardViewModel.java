package com.ondevice.bottomnavmodule;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class DashboardViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    private MutableLiveData<List<MyListData>> listdata;
    private ArrayList<MyListData> dataSet = new ArrayList<>();

    public DashboardViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Dashboard");
    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<List<MyListData>> getMyData() {
        //if the list is null
        if (listdata == null) {
            listdata = new MutableLiveData<List<MyListData>>();
            //we will load it asynchronously from server in this method
            //loadMyData();
            loadMyData();
            listdata.setValue(dataSet);
        }

        //finally we will return the list
        return listdata;
    }

    public void loadMyData() {

        /*MyListData[] myListData = new MyListData[] {
                new MyListData("Email", android.R.drawable.ic_dialog_email),
                new MyListData("Info", android.R.drawable.ic_dialog_info),
                new MyListData("Delete", android.R.drawable.ic_delete),
                new MyListData("Dialer", android.R.drawable.ic_dialog_dialer),
                new MyListData("Alert", android.R.drawable.ic_dialog_alert),
                new MyListData("Map", android.R.drawable.ic_dialog_map),
                new MyListData("Email", android.R.drawable.ic_dialog_email),
                new MyListData("Info", android.R.drawable.ic_dialog_info),
                new MyListData("Delete", android.R.drawable.ic_delete),
                new MyListData("Dialer", android.R.drawable.ic_dialog_dialer),
                new MyListData("Alert", android.R.drawable.ic_dialog_alert),
                new MyListData("Map", android.R.drawable.ic_dialog_map),
        };*/

        dataSet.add(
                new MyListData("Email", android.R.drawable.ic_dialog_email)
        );
        dataSet.add(
                new MyListData("Info", android.R.drawable.ic_dialog_info)
        );
        dataSet.add(
                new MyListData("Delete", android.R.drawable.ic_delete)
        );
        dataSet.add(
                new MyListData("Dialer", android.R.drawable.ic_dialog_dialer)
        );
        dataSet.add(
                new MyListData("Alert", android.R.drawable.ic_dialog_alert)
        );
        dataSet.add(
                new MyListData("Map", android.R.drawable.ic_dialog_map)
        );
        dataSet.add(
                new MyListData("Email", android.R.drawable.ic_dialog_email)
        );
        dataSet.add(
                new MyListData("Delete", android.R.drawable.ic_delete)
        );

    }
}