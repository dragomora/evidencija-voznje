package com.example.android.data.api;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import com.example.android.data.Interface.korisniciInt;
import com.example.android.data.model.Korisnici;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.List;
public class KorisniciApi {
    SharedPreferences prefs;



    private static Retrofit retrofit = null;
    public static korisniciInt getClient(){
        if(retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://192.168.0.15:8080/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(korisniciInt.class);
    }

    public void getKorisnik(Callback<List<Korisnici>> callback,View view){
        prefs = view.getContext().getSharedPreferences("Prefs", Context.MODE_PRIVATE);
        String user = prefs.getString("userKey",null);
        KorisniciApi.getClient().getKorisnickoIme(user).enqueue(callback);
    }



}
