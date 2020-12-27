package com.example.android.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.android.R;
import com.example.android.data.api.KorisniciApi;
import com.example.android.data.model.Korisnici;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class PocetnaActivity extends AppCompatActivity {
    SharedPreferences prefs;
    KorisniciApi ka = new KorisniciApi();
    Callback<List<Korisnici>> responseCallback;
    String otvorenaVoznja = "";
    String voznjaId = "voznjaIdKey";
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pocetna);
        Button bv = findViewById(R.id.btnAdmin);
        i = new Intent(this, OtvoriVoznjuActivity.class);
        prefs = getSharedPreferences("Prefs", Context.MODE_PRIVATE);
        String korisnik = prefs.getString("userKey",null);
        ImageView adminCar = findViewById(R.id.imageView);

        if (!prefs.getBoolean("adminKey", false)){
            bv.setVisibility(View.GONE);
        }
        else{
            bv.setVisibility(View.GONE);
            adminCar.setOnClickListener(this::goToAdmin);

        }


        responseCallback = new Callback<List<Korisnici>>() {
            @Override
            public void onResponse(Call<List<Korisnici>> call, Response<List<Korisnici>> response) {
                List<Korisnici> getUser = response.body();
                assert getUser != null;
                otvorenaVoznja = getUser.get(0).getOtvorenaVoznjaId();
            }
            @Override
            public void onFailure(Call<List<Korisnici>> call, Throwable t) {
            }
        };
        ka.getKorisnikObjectById(responseCallback,korisnik);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }

    public void goToAdmin(View view){
        Intent i = new Intent(this, AdminActivity.class);
        startActivity(i);
        finish();
    }


    public void goToOtvoriVoznju(View view){
        i = new Intent(this, OtvoriVoznjuActivity.class);
        Log.d("Voznja ID",otvorenaVoznja);
        if(otvorenaVoznja.isEmpty()){
            startActivity(i);
            finish();
        }
        else{
            Toast.makeText(PocetnaActivity.this, "Već imate otvorenu vožnju! \n Morate zatvoriti vožnju ID= "+ otvorenaVoznja, Toast.LENGTH_LONG).show();
        }
    }

    public void goToZatvoriVoznju(View view){
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(voznjaId, otvorenaVoznja);
        editor.commit();
        i = new Intent(this, ZatvoriVoznjuActivity.class);
        if(otvorenaVoznja.isEmpty()){
            Toast.makeText(PocetnaActivity.this, "Nemate otvorenu vožnju!", Toast.LENGTH_LONG).show();
        }
        else{
            startActivity(i);
            finish();
        }
    }

    public void goToGorivo(View view){
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(voznjaId, otvorenaVoznja);
        editor.commit();
        i = new Intent(this, GorivoActivity.class);
        if(otvorenaVoznja.isEmpty()){
            Toast.makeText(PocetnaActivity.this, "Nemate otvorenu vožnju!", Toast.LENGTH_LONG).show();
        }
        else{
            startActivity(i);
            finish();
        }
    }

    public boolean onOptionsItemSelected(MenuItem item){
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }


}
