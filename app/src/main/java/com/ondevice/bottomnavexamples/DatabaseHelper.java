package com.ondevice.bottomnavexamples;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;


import com.ondevice.bottomnavmodule.ContactList;
import com.ondevice.bottomnavmodule.MyListData;

import java.io.ByteArrayOutputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MyDBName";
    public static final String CONTACTS_TABLE_NAME = "SalaryDetails";
    public static final String CONTACTS_COLUMN_NAME = "name";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME ,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table "+ CONTACTS_TABLE_NAME +"(id integer primary key, name text, salary text, images blob)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+CONTACTS_TABLE_NAME);
        onCreate(db);
    }

    public boolean insert(String s, String s1, Bitmap img) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", s);
        contentValues.put("salary", s1);
        byte[] data = getBitmapAsByteArray(img);
        contentValues.put("images", data);

        db.insert(CONTACTS_TABLE_NAME, null, contentValues);
        return true;
    }

    public static byte[] getBitmapAsByteArray(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        return outputStream.toByteArray();
    }

    /*@SuppressLint("Range")
    public ArrayList<String> getAllCotacts() {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from SalaryDetails", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(CONTACTS_COLUMN_NAME)));
            res.moveToNext();
        }
        return array_list;
    }*/

    public ArrayList<ContactList> displayData() {

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
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
    }

}
