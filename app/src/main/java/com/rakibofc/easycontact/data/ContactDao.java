package com.rakibofc.easycontact.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.rakibofc.easycontact.model.ContactDbTable;

import java.util.List;

@Dao
public interface ContactDao {

    // CRUD - Create, Read, Update, Delete
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(ContactDbTable contactDbTable);

    @Query("DELETE FROM contact_table")
    void deleteAll();

    @Query("SELECT * FROM contact_table")
    LiveData<List<ContactDbTable>> getAllContacts();

    @Query("SELECT * FROM contact_table")
    List<ContactDbTable> getContactList();
}
