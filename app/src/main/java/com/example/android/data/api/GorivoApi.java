package com.example.android.data.api;

import com.example.android.data.Interface.gorivoInt;
import com.example.android.data.Interface.korisniciInt;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GorivoApi {
    private static Retrofit retrofit = null;


    public static gorivoInt getClient(){
        if(retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://192.168.0.15:8080/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(gorivoInt.class);
    }

}
