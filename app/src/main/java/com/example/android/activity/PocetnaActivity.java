package com.example.android.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.core.app.ComponentActivity;
import com.example.android.R;
import com.example.android.data.api.KorisniciApi;
import com.example.android.data.model.Korisnici;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class PocetnaActivity extends AppCompatActivity {
    MainActivity ma = new MainActivity();
    SharedPreferences prefs;
    String user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pocetna);

    }
    public void goToSpisakVozila(View view){
        Intent i = new Intent(this, VozilaActivity.class);
        startActivity(i);
    }

    public void goToOtvoriVoznju(View view){
        prefs = getSharedPreferences("Prefs", Context.MODE_PRIVATE);
        String korisnikLogIn = prefs.getString("userKey",null);
        KorisniciApi ka = new KorisniciApi();
        Intent i = new Intent(this, OtvoriVoznjuActivity.class);

        Callback<List<Korisnici>> responseCallback = new Callback<List<Korisnici>>() {
            @Override
            public void onResponse(Call<List<Korisnici>> call, Response<List<Korisnici>> response) {
                List<Korisnici> getUser = response.body();
                String otvorenaVoznja = getUser.get(0).getOtvorenaVoznjaId();
                if(otvorenaVoznja.isEmpty()){
                    startActivity(i);
                }
                else{
                    Toast.makeText(PocetnaActivity.this, "Već imate otvorenu vožnju! \n Morate zatvoriti vožnju ID= "+ otvorenaVoznja, Toast.LENGTH_LONG).show();
                    startActivity(i);

                }
            }
            @Override
            public void onFailure(Call<List<Korisnici>> call, Throwable t) {
            }
        };
        ka.getKorisnik(responseCallback,view);
    }


}
