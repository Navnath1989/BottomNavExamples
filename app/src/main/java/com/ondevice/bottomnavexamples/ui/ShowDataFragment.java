package com.ondevice.bottomnavexamples.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ondevice.bottomnavexamples.R;

public class ShowDataFragment extends Fragment {

    TextView txtFname;
    //public static final String Fnme = "FName";
    String nameF;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_show_data, container, false);

        Bundle args = getArguments();
        nameF = args.getString("FName");


        txtFname = view.findViewById(R.id.txtFname);
        txtFname.setText(nameF);



        return view;
    }
}