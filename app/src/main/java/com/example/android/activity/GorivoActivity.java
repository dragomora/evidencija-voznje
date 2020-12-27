package com.example.android.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.android.R;
import com.example.android.data.api.GorivoApi;
import com.example.android.data.model.Gorivo;
import com.google.android.material.textfield.TextInputLayout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.text.ParseException;
import java.util.ArrayList;

public class GorivoActivity extends AppCompatActivity {
    private final ArrayList<String> vrstaGoriva = new ArrayList<>();
    SharedPreferences prefs;
    AutoCompleteTextView vrstaGorivaDropDown;
    TextInputLayout kolicinaGoriva;
    TextInputLayout iznosRacuna;
    String voznjaId;
    Integer iznos;
    Integer litre;
    String vrsta;
    private ProgressBar spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gorivo);
        prefs = getSharedPreferences("Prefs", Context.MODE_PRIVATE);
        vrstaGorivaDropDown = findViewById(R.id.filled_exposed_dropdown);
        kolicinaGoriva = findViewById(R.id.kolicinaGoriva);
        iznosRacuna = findViewById(R.id.iznosRacuna);
        voznjaId = prefs.getString("voznjaIdKey", null);
        spinner = findViewById(R.id.progressCirc1);
        spinner.setVisibility(View.GONE);

        Button postButton = findViewById(R.id.buttonPostVoznja);
        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spinner.setVisibility(View.VISIBLE);
                try {
                    postGorivo(view);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
        setDataInVrstaGoriva();
    }

    public void goToPocetna(View view){
        Intent i = new Intent(this, PocetnaActivity.class);
        finish();
        startActivity(i);
    }

    private void setDataInVrstaGoriva() {
        Log.d("VOZNJA ID GORIVO", "setDataInVrstaGoriva: " + voznjaId);
        vrstaGoriva.add("Dizel");
        vrstaGoriva.add("Super");
        ArrayAdapter<String> vrstaGorivaArrayAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.dropdown_menu_popup_item, vrstaGoriva);
        vrstaGorivaDropDown.setAdapter(vrstaGorivaArrayAdapter);
        vrstaGorivaDropDown.setText("Dizel", false);
    }

    public void postGorivo(View view) throws ParseException {

        iznos = Integer.parseInt(iznosRacuna.getEditText().getText().toString());
        litre = Integer.parseInt(kolicinaGoriva.getEditText().getText().toString());
        vrsta = vrstaGorivaDropDown.getText().toString();
        if(voznjaId.isEmpty()|| iznos == null||litre==null){
            Toast.makeText(GorivoActivity.this, "Morate popuniti sva polja!", Toast.LENGTH_LONG).show();
        }
        else{
            Gorivo gorivo = new Gorivo(litre,iznos,vrsta,voznjaId);
            Log.d("VOZNJA ID: ",voznjaId);
            Call<Gorivo> call =GorivoApi.getClient().createGorivo(gorivo);
            call.enqueue(new Callback<Gorivo>() {

                @Override
                public void onResponse(Call<Gorivo> call, Response<Gorivo> response) {

                    String x =Integer.toString(response.code());
                    Log.d("HTTP RETURN CODE: ",x);
                    if(!x.isEmpty()){
                            spinner.setVisibility(View.GONE);
                    }
                    Toast.makeText(GorivoActivity.this, "Sipanje goriva je uspešno zabeleženo!", Toast.LENGTH_LONG).show();
                    assert response.body() != null;
                    goToPocetna(view);
                }

                @Override
                public void onFailure(Call<Gorivo> call, Throwable t) {
                    Log.d("testme", "Error: " + t.toString());
                }
            });
        }
    }
}
