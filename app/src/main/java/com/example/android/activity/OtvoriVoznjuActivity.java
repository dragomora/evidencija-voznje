package com.example.android.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.*;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.android.R;
import com.example.android.data.api.VoziloApi;
import com.example.android.data.api.VoznjaApi;
import com.example.android.data.model.StanjeVozila;
import com.example.android.data.model.SvrhaVoznje;
import com.example.android.data.model.Vozilo;
import com.example.android.data.model.Voznja;
import com.google.android.material.textfield.TextInputLayout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@RequiresApi(api = Build.VERSION_CODES.O)
public class OtvoriVoznjuActivity extends AppCompatActivity{
    SharedPreferences prefs;// = getSharedPreferences("Prefs", Context.MODE_PRIVATE);
    private List<Vozilo> vozilaResponseData;
    private List<Voznja> voznjaResponseData;
    private final ArrayList<String> svrhaVoznje = new ArrayList<>();
    private final ArrayList<String> stanjeVozila = new ArrayList<>();
    private final LocalDateTime date = LocalDateTime.now();
    private String voziloId="";
    TextInputLayout predjenokm;
    TextInputLayout destinacija;
    AutoCompleteTextView svrhaVoznjeDropdown;
    AutoCompleteTextView stanjeVozilaDropdown;
    TextInputLayout vreme_pocetka_voznje;
    TextInputLayout napomena;
    private ProgressBar spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otvori_voznju);
        predjenokm = findViewById(R.id.pocetnaKm);
        destinacija = findViewById(R.id.destinacija);
        svrhaVoznjeDropdown = findViewById(R.id.svrha_voznje_exposed_dropdown);
        stanjeVozilaDropdown = findViewById(R.id.stanje_vozila_exposed_dropdown);
        vreme_pocetka_voznje = findViewById(R.id.vreme_pocetka_voznje);
        napomena = findViewById(R.id.napomenaMultiLine);
        spinner = findViewById(R.id.progressCirc);
        spinner.setVisibility(View.GONE);
        Button postButton = findViewById(R.id.buttonPostVoznja);
        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spinner.setVisibility(View.VISIBLE);
                try {
                    postVoznja(view);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
        getVozilaData();
        setDataInSvrhaVoznjeDropDown();
        setDataInStanjeVozilaDropDown();
        setDateinVremePocetkaVoznje();
        setDataInKorisnik();
        }

    public void goToPocetna(View view){
        Intent i = new Intent(this, PocetnaActivity.class);
        finish();
        startActivity(i);
    }

    public void getVozilaData(){
        (VoziloApi.getClient().getVoziloList()).enqueue(new Callback<List<Vozilo>>() {
            @Override
            public void onResponse(Call<List<Vozilo>> call, Response<List<Vozilo>> response) {
                Log.d("responseGET", response.body().get(0).getNaziv());
                vozilaResponseData = response.body();
                setDataInVoziloDropDown();
            }
            @Override
            public void onFailure(Call<List<Vozilo>> call, Throwable t) {
                Toast.makeText(OtvoriVoznjuActivity.this, t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
    private void setDataInVoziloDropDown(){
        ArrayAdapter<Vozilo> voziloArrayAdapter = new ArrayAdapter<Vozilo>(getApplicationContext(), R.layout.dropdown_menu_popup_item, vozilaResponseData);
        vozilaResponseData.removeIf(Vozilo::getZauzeto);
        AutoCompleteTextView vozilaDropdown = findViewById(R.id.filled_exposed_dropdown);
        vozilaDropdown.setAdapter(voziloArrayAdapter);
        vozilaDropdown.setOnItemClickListener((adapterView, view, i, l) -> {
            Objects.requireNonNull(predjenokm.getEditText()).setText(vozilaResponseData.get((int)l).getTrenutnaKm().toString());
            voziloId = voziloArrayAdapter.getItem(i).getId();
            Log.d("vozilo ID", voziloId);
        });
    }

    private void setDataInSvrhaVoznjeDropDown(){
        ArrayAdapter<SvrhaVoznje> svrhaVoznjeArrayAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.dropdown_menu_popup_item, SvrhaVoznje.values());
        svrhaVoznjeDropdown.setAdapter(svrhaVoznjeArrayAdapter);
        svrhaVoznjeDropdown.setText(SvrhaVoznje.Službeno.toString(),false);
    }

    private void setDataInStanjeVozilaDropDown(){
        ArrayAdapter<StanjeVozila> stanjeVozilaArrayAdapter = new ArrayAdapter<StanjeVozila>(getApplicationContext(), R.layout.dropdown_menu_popup_item, StanjeVozila.values());
        stanjeVozilaDropdown.setAdapter(stanjeVozilaArrayAdapter);
        stanjeVozilaDropdown.setText(StanjeVozila.Neoštećeno.toString(),false);
    }

    public String getDate(LocalDateTime date){
        return date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy.  HH:mm"));
    }

    private void setDateinVremePocetkaVoznje(){
        Objects.requireNonNull(vreme_pocetka_voznje.getEditText()).setText(getDate(date));
        vreme_pocetka_voznje.getEditText().setInputType(InputType.TYPE_NULL);
    }

    private void setDataInKorisnik(){
        prefs = getSharedPreferences("Prefs", Context.MODE_PRIVATE);
        String korisnikLogIn = prefs.getString("userKey",null);
        TextInputLayout korisnik = findViewById(R.id.korisnik);
        Objects.requireNonNull(korisnik.getEditText()).setText(korisnikLogIn);
        korisnik.getEditText().setInputType(InputType.TYPE_NULL);
    }

/*
    public void getVoznjaData(){
        (VoznjaApi.getClient().getVoznjaList()).enqueue(new Callback<List<Voznja>>() {
            @Override
            public void onResponse(Call<List<Voznja>> call, Response<List<Voznja>> response) {
                assert response.body() != null;
                voznjaResponseData = response.body();
                setDataInVoziloDropDown();
            }
            @Override
            public void onFailure(Call<List<Voznja>> call, Throwable t) {
                Toast.makeText(OtvoriVoznjuActivity.this, t.toString(), Toast.LENGTH_LONG).show();
                Log.d(String.valueOf(OtvoriVoznjuActivity.this), t.toString());
            }
        });
    }
*/

    public void postVoznja(View view) throws ParseException {
        prefs = getSharedPreferences("Prefs", Context.MODE_PRIVATE);
        String korisnikLogInId = prefs.getString("idKey",null);
        String pocetnaKm = predjenokm.getEditText().getText().toString();
        if(pocetnaKm.isEmpty()){
            Toast.makeText(OtvoriVoznjuActivity.this, "Morate odabrati vozilo!", Toast.LENGTH_LONG).show();
        }
        else {
            Integer pocetnaKmInt = Integer.parseInt(pocetnaKm);
            Integer zavrsnaKm = null;
            Integer predjenoKm = null;
            Date krajVoznje = null;
            String dest = destinacija.getEditText().getText().toString();
            SvrhaVoznje svrha = SvrhaVoznje.valueOf(svrhaVoznjeDropdown.getText().toString());
            //String stanjeVozilaTxt = stanjeVozilaDropdown.getText().toString();
            StanjeVozila stanjeVozila = StanjeVozila.valueOf(stanjeVozilaDropdown.getText().toString());
            String dt = vreme_pocetka_voznje.getEditText().getText().toString();
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.  HH:mm");
            Date test = sdf.parse(dt);
            //LocalDateTime datum = LocalDateTime.parse(dt,DateTimeFormatter.ofPattern("dd.MM.yyyy.  HH:mm:ss"));
            String napomenaTxt = napomena.getEditText().getText().toString();
            Boolean pranje = false;
            Voznja voznja = new Voznja(test, pocetnaKmInt, zavrsnaKm, predjenoKm, krajVoznje, korisnikLogInId, voziloId, napomenaTxt,dest,svrha,stanjeVozila, pranje);
            Call<Voznja> call = VoznjaApi.getClient().createVoznja(voznja);
            call.enqueue(new Callback<Voznja>() {

                @Override

                public void onResponse(Call<Voznja> call, Response<Voznja> response) {
                    String status = Integer.toString(response.code());
                    Log.d("HTTP RETURN CODE: ",status);
                    Toast.makeText(OtvoriVoznjuActivity.this, "Voznja je uspešno otvorena!\n" + "HTTP STATUS= " + status , Toast.LENGTH_LONG).show();
                    assert response.body() != null;
                }

                @Override
                public void onFailure(Call<Voznja> call, Throwable t) {
                    Log.d("Došlo je do greške!", "Error: " + t.toString());
                }
            });
        }

        spinner.setVisibility(View.GONE);
        goToPocetna(view);
        finish();
    }
}

/*
    private void setDataInSpinner(){
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, vozilaResponseData);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        mySpinner.setAdapter(adapter);
        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long id) {
                TextView predjenokm = findViewById(R.id.predjenoKm);
                predjenokm.setText("Trenutna kilometraza: "+vozilaResponseData.get((int) id).getPredjenoKm().toString());
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        }
*/


