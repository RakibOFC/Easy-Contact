package com.rakibofc.easycontact.viewmodel;

import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.rakibofc.easycontact.R;
import com.rakibofc.easycontact.model.ContactData;
import com.rakibofc.easycontact.model.ContactDbTable;
import com.rakibofc.easycontact.util.RoomDbHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainViewModel extends AndroidViewModel {

    private final MutableLiveData<List<ContactData>> contactList;

    public MainViewModel(@NonNull Application application) {
        super(application);

        contactList = new MutableLiveData<>();
        loadContactData();
    }

    public LiveData<List<ContactData>> getContactList() {
        return contactList;
    }

    private void loadContactData() {
        contactList.setValue(getContactData());
    }

    private List<ContactData> getContactData() {

        ArrayList<ContactData> contactDataList = new ArrayList<>();

        RoomDbHelper roomDbHelper = RoomDbHelper.getDatabase(getApplication());

        LiveData<List<ContactDbTable>> allContacts = roomDbHelper.contactDao().getAllContacts();

        for (int i = 0; i < Objects.requireNonNull(allContacts.getValue()).size(); i++) {

            ContactDbTable contactDbTable = allContacts.getValue().get(i);

            contactDataList.add(new ContactData(
                    getContactImage(contactDbTable.getContactPhoto()),
                    contactDbTable.getContactName(),
                    contactDbTable.getContactNo()));
        }

        RoomDbHelper dbHelper = RoomDbHelper.getDatabase(getApplication());
        dbHelper.contactDao().insert(new ContactDbTable(null, "Rakibul Islam", "+8801728329482"));

        return contactDataList;
    }

    private Bitmap getContactImage(byte[] contactPhoto) {
        return BitmapFactory.decodeResource(getApplication().getResources(), R.drawable.user_256dp);
    }

    private Bitmap getContactImage() {
        return BitmapFactory.decodeResource(getApplication().getResources(), R.drawable.user_256dp);
    }
}
