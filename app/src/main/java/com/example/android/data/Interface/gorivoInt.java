package com.example.android.data.Interface;

import com.example.android.data.model.Gorivo;
import com.example.android.data.model.Voznja;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface gorivoInt {
    @POST("api/goriva/")
    Call<Gorivo> createGorivo(@Body Gorivo gorivo);

}
