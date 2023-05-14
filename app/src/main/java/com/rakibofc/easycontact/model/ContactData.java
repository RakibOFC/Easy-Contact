package com.rakibofc.easycontact.model;

import android.graphics.Bitmap;

public class ContactData {

    private int contactId;
    private Bitmap contactImage;
    private String contactName;
    private String contactNumber;

    public ContactData(int contactId, Bitmap contactImage, String contactName, String contactNumber) {
        this.contactId = contactId;
        this.contactImage = contactImage;
        this.contactName = contactName;
        this.contactNumber = contactNumber;
    }

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
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
