package com.ondevice.bottomnavexamples;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ondevice.bottomnavmodule.ContactList;

import java.util.ArrayList;

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ViewHolder> {

    //private MyListData[] listdata;
    private ArrayList<ContactList> listdata;

    // RecyclerView recyclerView;
    /*public MyListAdapter(MyListData[] listdata) {
        this.listdata = listdata;
    }*/
    public ContactListAdapter(ArrayList<ContactList> listdata) {
        this.listdata = listdata;
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @NonNull
    @Override
    public ContactListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View listItem= inflater.inflate(R.layout.raw_contactlist, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ContactListAdapter.ViewHolder viewHolder, int position) {
        //final MyListData myListData = listdata[position];
        ContactList myListData = listdata.get(position);

        viewHolder.txtName.setText(myListData.getFname()+"\n\n "+ myListData.getSalry());

        /*byte[] image = c.getBlob(0);
        Bitmap bmp= BitmapFactory.decodeByteArray(image, 0 , image.length);
        imageView.setImageBitmap(bmp);*/

        Glide.with(viewHolder.itemView).load(myListData.getuImage()).into(viewHolder.imgUSeImg);

        /*Glide.with(viewHolder.itemView)
                .load(viewHolder.imgIcon)
                .override(300, 200)
                .error(R.drawable.ic_dashboard_black_24dp).placeholder(R.drawable.ic_home_black_24dp)
                .into(myListData.getImgId());*/
        //viewHolder.imgIcon.setImageResource(myListData.getImgId());

    }

    /**
     * @return
     */
    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgUSeImg;
        TextView txtName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgUSeImg = itemView.findViewById(R.id.imgUSeImg);
            txtName = itemView.findViewById(R.id.txtName);
        }
    }
}
