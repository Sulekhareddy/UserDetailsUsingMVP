package com.example.userdetailsusingmvp.model.repo;

import android.annotation.SuppressLint;
import android.content.Context;

import com.example.userdetailsusingmvp.model.Users;
import com.example.userdetailsusingmvp.model.service.local.UserDao;
import com.example.userdetailsusingmvp.model.service.local.UserDatabase;
import com.example.userdetailsusingmvp.model.service.remote.RetrofitProvider;
import com.example.userdetailsusingmvp.model.service.remote.UserService;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class Repository {

    private UserDao userDao;
    private UserDatabase userDatabase;
    private RetrofitProvider retrofitProvider;
    private UserService userService;

    public Repository(Context context){

        // Initialize local db Service
        userDatabase = UserDatabase.getInstance(context);
        userDao = userDatabase.getUserDao();

        // Initialize remote Service
        retrofitProvider = RetrofitProvider.getInstance();
        userService = retrofitProvider.getUserService();

    }

    public Single<List<Users>> getUsersList(){
        return userService.getUsers();
    }

    public Single<List<Users>> getLocalUsersList(){
        return userDao.getUsers();
    }

    @SuppressLint("CheckResult")
    public void insertUsers(List<Users> users) {

        Completable.fromSingle(emitter -> userDao.insertUsers(users))
                .subscribeOn(Schedulers.io())
                .subscribe();
    }

    public void onDestroy() {
        retrofitProvider.destroy();
        userDatabase.destroyDbInstance();
    }

}
