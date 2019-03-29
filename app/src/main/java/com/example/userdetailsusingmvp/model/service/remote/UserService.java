package com.example.userdetailsusingmvp.model.service.remote;

import com.example.userdetailsusingmvp.model.Users;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface UserService {

    @GET("users")
    Single<List<Users>> getUsers();
}
