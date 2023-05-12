package com.rakibofc.easycontact.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "contact_table")
public class ContactDbTable {

    @PrimaryKey(autoGenerate = true)
    private int contactId;

    @ColumnInfo(name = "contact_photo")
    private byte[] contactPhoto;

    @ColumnInfo(name = "contact_name")
    private String contactName;

    @ColumnInfo(name = "contact_no")
    private String contactNo;

    public ContactDbTable(int contactId, @Nullable byte[] contactPhoto, @NonNull String contactName, @NonNull String contactNo) {

        this.contactId = contactId;
        this.contactPhoto = contactPhoto;
        this.contactName = contactName;
        this.contactNo = contactNo;
    }

    @Ignore
    public ContactDbTable(@Nullable byte[] contactPhoto, @NonNull String contactName, @NonNull String contactNo) {

        this.contactPhoto = contactPhoto;
        this.contactName = contactName;
        this.contactNo = contactNo;
    }

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    public byte[] getContactPhoto() {
        return contactPhoto;
    }

    public void setContactPhoto(byte[] contactPhoto) {
        this.contactPhoto = contactPhoto;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }
}
