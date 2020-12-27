package com.example.android.data.Interface;


import com.example.android.data.model.Vozilo;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

public interface vozilaInt {
    @GET("api/vozila")
    Call<List<Vozilo>> getVoziloList();
    @GET("api/voznje/testme")
    Call<List<Vozilo>> getVoziloSum();

}
