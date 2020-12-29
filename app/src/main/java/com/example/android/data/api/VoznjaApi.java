package com.example.android.data.api;

import android.os.Build;

import com.example.android.data.Interface.voznjaInt;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import androidx.annotation.RequiresApi;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VoznjaApi {
    private static Retrofit retrofit = null;
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static voznjaInt getClient(){
        if(retrofit==null){
            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                    .create();
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://192.168.0.102:8080/")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit.create(voznjaInt.class);
    }

}
