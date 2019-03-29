package com.example.userdetailsusingmvp.model.service.local;

import android.arch.persistence.room.TypeConverter;
import android.util.Log;

import com.example.userdetailsusingmvp.model.Company;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class CompanyTypeConverter {

    private static Gson gson = new Gson();

    @TypeConverter
    public static Company stringToCompany(String data){
        if(data == null){
            return new Company();
        }

        Type type = new TypeToken<Company>() {}.getType();


        return gson.fromJson(data, type);
    }

    @TypeConverter
    public static String CompanyToString(Company company){
        return gson.toJson(company);
    }
}
