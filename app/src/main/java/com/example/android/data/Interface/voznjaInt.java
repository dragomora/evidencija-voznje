package com.example.android.data.Interface;

import com.example.android.data.model.Voznja;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface voznjaInt {
    @GET("api/voznje/")
    Call<List<Voznja>> getVoznjaList();
    @POST("api/voznje/")
    Call<Voznja> createVoznja(@Body Voznja voznja);
    @GET("api/voznje/{id}")
    Call<List<Voznja>> getVoznjaById(@Path("id") String id);
    @PUT("api/voznje/{id}")
    Call<Voznja> zatvoriVoznja(@Path("id") String id,@Body Voznja voznja);

}
