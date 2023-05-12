package com.rakibofc.easycontact.util;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.rakibofc.easycontact.data.ContactDao;
import com.rakibofc.easycontact.model.ContactDbTable;

@Database(entities = {ContactDbTable.class}, version = 1, exportSchema = false)
public abstract class RoomDbHelper extends RoomDatabase {

    private static volatile RoomDbHelper INSTANCE;
    private static final String DB_NAME = "contactDb";

    public static RoomDbHelper getDatabase(Context context) {

        if (INSTANCE == null) {

            INSTANCE = Room.databaseBuilder(context, RoomDbHelper.class, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }

    public abstract ContactDao contactDao();
}
