package com.rakibofc.easycontact.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.rakibofc.easycontact.data.ContactDao;
import com.rakibofc.easycontact.model.ContactDbTable;
import com.rakibofc.easycontact.util.RoomDbHelper;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private final LiveData<List<ContactDbTable>> allContacts;
    private final ContactDao contactDao;

    public MainViewModel(@NonNull Application application) {
        super(application);

        // Get an instance for Room database helper
        RoomDbHelper roomDbHelper = RoomDbHelper.getDatabase(getApplication());

        // Initialize instance for contact DAO
        contactDao = roomDbHelper.contactDao();

        // Initialize LiveData object containing all contacts in the database
        allContacts = contactDao.getAllContacts();
    }

    public LiveData<List<ContactDbTable>> getAllContacts() {
        return allContacts;
    }

    public void deleteAllContact() {
        contactDao.deleteAll();
    }
}
