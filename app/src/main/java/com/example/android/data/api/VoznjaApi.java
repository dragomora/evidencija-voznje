package com.example.android.data.api;

import android.os.Build;
import androidx.annotation.RequiresApi;
import com.example.android.data.Interface.voznjaInt;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.time.LocalDate;

public class VoznjaApi {
    private static Retrofit retrofit = null;
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static voznjaInt getClient(){
        if(retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://192.168.0.15:8080/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(voznjaInt.class);
    }

}
