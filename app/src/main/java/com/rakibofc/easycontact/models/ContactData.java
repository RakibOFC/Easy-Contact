package com.rakibofc.easycontact.models;

import android.graphics.Bitmap;

public class ContactData {

    private Bitmap contactImage;
    private String contactName;
    private String contactNumber;

    public ContactData(Bitmap contactImage, String contactName, String contactNumber) {
        this.contactImage = contactImage;
        this.contactName = contactName;
        this.contactNumber = contactNumber;
    }

    public Bitmap getContactImage() {
        return contactImage;
    }

    public void setContactImage(Bitmap contactImage) {
        this.contactImage = contactImage;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
}
