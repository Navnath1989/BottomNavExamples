package com.ondevice.bottomnavexamples.ui.notifications;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.arch.lifecycle.ViewModelProvider;
import android.widget.Toast;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.ondevice.bottomnavexamples.ContactListAdapter;
import com.ondevice.bottomnavexamples.DatabaseHelper;
import com.ondevice.bottomnavexamples.R;
import com.ondevice.bottomnavexamples.databinding.FragmentNotificationsBinding;
import com.ondevice.bottomnavexamples.ui.home.HomeFragment;
import com.ondevice.bottomnavmodule.ContactList;
import com.ondevice.bottomnavmodule.LoginViewModel;
import com.ondevice.bottomnavmodule.MyListData;
import com.ondevice.bottomnavmodule.NotificationsViewModel;
import com.ondevice.bottomnavmodule.User;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;
    TextView text_notifications;
    EditText edName, edSalary;
    Button btnSubmit;
    ImageView imgUSer;
    Bitmap bitmap = null;
    byte img[];
    private Cursor c=null;
    RecyclerView listContact;
    ArrayList<ContactList> array_list;
    DatabaseHelper db;


    @SuppressLint("ResourceAsColor")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_notifications, container, false);

        /*if (root == null)
        {
            Fragment fragment = new NotificationsFragment();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.frame_container, fragment);
            transaction.addToBackStack(null);
            transaction.commit();

            return null;
        }*/

        text_notifications = root.findViewById(R.id.text_notifications);

        //((AppCompatActivity) getContext()).getSupportActionBar().setTitle("Notifications");

        NotificationsViewModel notificationsViewModel =
                new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(NotificationsViewModel.class);

        //binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        //View root = binding.getRoot();
        //final TextView textView = binding.textNotifications;
        notificationsViewModel.getText().observe(getViewLifecycleOwner(), text_notifications::setText);
        //notificationsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        edName = root.findViewById(R.id.edName);
        edSalary = root.findViewById(R.id.edSalary);
        btnSubmit = root.findViewById(R.id.btnSubmit);
        imgUSer = root.findViewById(R.id.imgUSer);

        listContact = root.findViewById(R.id.listContact);
        listContact.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false));


        imgUSer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,0);
            }
        });

        db = new DatabaseHelper(getActivity());
        //ArrayList array_list = db.getAllCotacts();
        array_list = new ArrayList<>();
        array_list = db.displayData();
        //array_list = displayData();
        ContactListAdapter arrayAdapter=new ContactListAdapter(array_list);
        listContact.setAdapter(arrayAdapter);

        System.out.println("Print List="+ array_list.toString());

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!edName.getText().toString().isEmpty() && !edSalary.getText().toString().isEmpty()) {
                    if (db.insert(edName.getText().toString(), edSalary.getText().toString(), bitmap)) {
                        Toast.makeText(getActivity(), "Inserted", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getActivity(), "NOT Inserted", Toast.LENGTH_LONG).show();
                    }
                } else {
                    edName.setError("Enter NAME");
                    edSalary.setError("Enter Salary");
                }
            }
        });


        return root;
    }

    /*private ArrayList<ContactList> displayData() {

        SQLiteDatabase sqLiteDatabase = db.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from SalaryDetails",null);
        ArrayList<ContactList> modelArrayList = new ArrayList<>();
        if (cursor.moveToFirst()){
            do {
                modelArrayList.add(new ContactList(cursor.getString(1),
                        cursor.getString(2), cursor.getBlob(3)));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return modelArrayList;
    }*/

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode== Activity.RESULT_OK && data !=null)
        {
            Uri selectedImage = data.getData();
            try{
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), selectedImage);
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,bos);
                img = bos.toByteArray();
                imgUSer.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();

            }

        }

    }


    /*@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }*/

}