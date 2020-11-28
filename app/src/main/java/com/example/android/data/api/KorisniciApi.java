package com.example.android.data.api;

import com.example.android.data.Interface.korisniciInt;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class KorisniciApi {
    private static Retrofit retrofit = null;
    public static korisniciInt getClient(){
        if(retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://10.0.2.2:8080/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(korisniciInt.class);
    }

}
