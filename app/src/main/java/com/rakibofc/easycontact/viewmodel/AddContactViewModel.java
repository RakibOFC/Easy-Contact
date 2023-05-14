package com.rakibofc.easycontact.viewmodel;

import android.app.Application;
import android.graphics.Bitmap;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.rakibofc.easycontact.data.ContactDao;
import com.rakibofc.easycontact.model.ContactDbTable;
import com.rakibofc.easycontact.util.RoomDbHelper;

public class AddContactViewModel extends AndroidViewModel {

    private final ContactDao contactDao;
    private final MutableLiveData<Bitmap> contactImage;

    public AddContactViewModel(@NonNull Application application) {
        super(application);

        // Get instance for Room database helper
        RoomDbHelper roomDbHelper = RoomDbHelper.getDatabase(getApplication());
        contactDao = roomDbHelper.contactDao();
        contactImage = new MutableLiveData<>();
    }

    public void setContactImage(Bitmap contactImage) {
        this.contactImage.setValue(contactImage);
    }

    public LiveData<Bitmap> getContactImage() {
        return contactImage;
    }

    public void addContact(ContactDbTable contact) {
        contactDao.insert(contact);
    }
}
