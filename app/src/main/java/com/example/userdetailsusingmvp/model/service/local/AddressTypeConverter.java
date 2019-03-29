package com.example.userdetailsusingmvp.model.service.local;

import android.arch.persistence.room.TypeConverter;

import com.example.userdetailsusingmvp.model.Address;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class AddressTypeConverter {

    private static Gson gson = new Gson();

    @TypeConverter
    public static Address stringToAddress(String data) { // json
        if (data == null) {
            return new Address();
        }

        Type type = new TypeToken<Address>() {}.getType();
        return gson.fromJson(data, type);
    }

    @TypeConverter
    public static String AddressToString(Address address) {
        return gson.toJson(address);
    }
}
