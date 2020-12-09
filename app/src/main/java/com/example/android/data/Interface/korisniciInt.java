package com.example.android.data.Interface;


import com.example.android.data.model.Korisnici;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

public interface korisniciInt {
    @GET("api/korisnici/")
    Call<List<Korisnici>> getUserList();
    @GET("api/korisnici/user/{user}")
    Call<List<Korisnici>>getKorisnickoIme( @Path("user") String user);

}
