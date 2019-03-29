package com.example.userdetailsusingmvp.model.service.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.example.userdetailsusingmvp.model.Users;

@Database(entities = Users.class, version = 1)
public abstract class UserDatabase extends RoomDatabase {

    private static final String DB_NAME = "user.db";

    private static UserDatabase instance;

    public abstract UserDao getUserDao();


    public static UserDatabase getInstance(Context context) {
        if (instance == null)  {
            instance = Room.databaseBuilder(context, UserDatabase.class, DB_NAME).build();
        }

        return instance;
    }

    public void destroyDbInstance() {
        instance = null;
    }
}
