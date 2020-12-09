package com.example.android.data.Interface;

import com.example.android.data.model.Voznja;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

import java.util.List;

public interface voznjaInt {
    @GET("api/voznje/")
    Call<List<Voznja>> getVoznjaList();
    @POST("api/voznje/")
    Call<Voznja> createVoznja(@Body Voznja voznja);

}
