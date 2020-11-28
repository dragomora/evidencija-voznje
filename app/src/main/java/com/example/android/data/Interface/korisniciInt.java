package com.example.android.data.Interface;

import com.example.android.data.model.Korisnici;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

public interface korisniciInt {
    @GET("api/korisnici")
    Call<List<Korisnici>> getUserList();

}
