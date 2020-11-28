package com.example.android.data.api;

import com.example.android.data.Interface.korisniciInt;
import com.example.android.data.Interface.vozilaInt;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VoziloApi {
    private static Retrofit retrofit = null;
    public static vozilaInt getClient(){
        if(retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://192.168.0.15:8080/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(vozilaInt.class);
    }

}
