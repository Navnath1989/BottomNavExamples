package com.ondevice.bottomnavexamples;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ondevice.bottomnavexamples.ui.ShowDataFragment;
import com.ondevice.bottomnavmodule.MyListData;

import java.util.List;

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder> {

    //private MyListData[] listdata;
    private List<MyListData> listdata;

    // RecyclerView recyclerView;
    /*public MyListAdapter(MyListData[] listdata) {
        this.listdata = listdata;
    }*/
    public MyListAdapter(List<MyListData> listdata) {
        this.listdata = listdata;
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @NonNull
    @Override
    public MyListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View listItem= inflater.inflate(R.layout.row_mydata, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyListAdapter.ViewHolder viewHolder, int position) {
        //final MyListData myListData = listdata[position];
        final MyListData myListData = listdata.get(position);

        viewHolder.text_dashboard.setText(myListData.getDescription());

        /*Glide.with(viewHolder.itemView)
                .load(viewHolder.imgIcon)
                .override(300, 200)
                .error(R.drawable.ic_dashboard_black_24dp).placeholder(R.drawable.ic_home_black_24dp)
                .into(myListData.getImgId());*/

        viewHolder.imgIcon.setImageResource(myListData.getImgId());

        viewHolder.imgIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), myListData.getDescription(), Toast.LENGTH_SHORT).show();
                Bundle args = new Bundle();
                args.putString("FName", myListData.getDescription());
                Fragment fragment;
                fragment = new ShowDataFragment();
                fragment.setArguments(args);
                FragmentManager fm = ((AppCompatActivity) v.getContext()).getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.frame_container, fragment);
                ft.attach(fragment);
                ft.commit();
            }
        });


    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgIcon;
        TextView text_dashboard;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgIcon = itemView.findViewById(R.id.imgIcon);
            text_dashboard = itemView.findViewById(R.id.text_dashboard);
        }
    }
}
