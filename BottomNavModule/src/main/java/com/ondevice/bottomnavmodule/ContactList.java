package com.ondevice.bottomnavmodule;

import android.graphics.Bitmap;

import java.sql.Blob;

public class ContactList {

    String fname, salry;

    byte[] uImage;

    public ContactList(String fname, String salry, byte[] uImage) {
        this.fname = fname;
        this.salry = salry;
        this.uImage = uImage;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getSalry() {
        return salry;
    }

    public void setSalry(String salry) {
        this.salry = salry;
    }

    public byte[] getuImage() {
        return uImage;
    }

    public void setuImage(byte[] uImage) {
        this.uImage = uImage;
    }
}
