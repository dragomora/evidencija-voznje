package com.example.android.data.api;

import com.example.android.data.Interface.korisniciInt;
import com.example.android.data.model.Korisnici;

import java.util.List;

import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class KorisniciApi {

    private static Retrofit retrofit = null;

    public KorisniciApi() {
    }

    public static korisniciInt getClient(){
        if(retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://192.168.0.102:8080/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(korisniciInt.class);
    }

    public void getKorisnikObjectById(Callback<List<Korisnici>> callback,String user){
        KorisniciApi.getClient().getKorisnickoIme(user).enqueue(callback);
    }
}
