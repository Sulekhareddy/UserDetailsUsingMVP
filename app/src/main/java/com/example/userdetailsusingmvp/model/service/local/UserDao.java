package com.example.userdetailsusingmvp.model.service.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.userdetailsusingmvp.model.Users;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface UserDao {

    @Query("SELECT * FROM USERS_TABLE")
    Single<List<Users>> getUsers();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUsers(List<Users> users);
}