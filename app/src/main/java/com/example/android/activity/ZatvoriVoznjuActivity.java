package com.example.android.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.*;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import com.example.android.R;
import com.example.android.data.api.VoznjaApi;
import com.example.android.data.model.StanjeVozila;
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
import java.util.Date;
import java.util.List;
import java.util.Objects;

@RequiresApi(api = Build.VERSION_CODES.O)
public class ZatvoriVoznjuActivity extends AppCompatActivity{
    SharedPreferences prefs;// = getSharedPreferences("Prefs", Context.MODE_PRIVATE);
    private List<Vozilo> vozilaResponseData;
    private List<Voznja> voznjaResponseData;
    private final LocalDateTime date = LocalDateTime.now();
    private String voziloId="";
    TextInputLayout voziloTxt;
    String voziloTxtData;
    TextInputLayout pocetnakm;
    Integer pocetnaKmData;
    TextInputLayout zavsnakm;
    String zavrsnaKmData;
    TextInputLayout destinacija;
    String destinacijaData;
    TextInputLayout svrhavoznje;
    String svrhaVoznjeData;
    TextInputLayout stanjeVozila;
    String stanjeVozilaData;
    StanjeVozila stVozila;
    TextInputLayout vreme_zavrsetka_voznje;
    TextInputLayout korisnik;
    String korisnikData;
    TextInputLayout napomena;
    Voznja voznja;
    OtvoriVoznjuActivity ov = new OtvoriVoznjuActivity();
    private ProgressBar spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zatvori_voznju);
        voziloTxt = findViewById(R.id.txtVoziloNaziv);
        pocetnakm = findViewById(R.id.pocetnaKm);
        zavsnakm = findViewById(R.id.zavrsnaKm);
        destinacija = findViewById(R.id.destinacija);
        svrhavoznje = findViewById(R.id.svrhaVoznje);
        stanjeVozila = findViewById(R.id.stanjeVozila);
        vreme_zavrsetka_voznje = findViewById(R.id.vreme_zavrsetka_voznje);
        korisnik = findViewById(R.id.korisnik);
        napomena = findViewById(R.id.napomenaMultiLine);
        spinner = findViewById(R.id.progressCirc);
        spinner.setVisibility(View.GONE);

        Button postButton = findViewById(R.id.buttonPostVoznja);
        postButton.setOnClickListener(view -> {
            spinner.setVisibility(View.VISIBLE);
            try {
                zatvoriVoznja(view);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        getVoznjaIdData();
        //setDataInKorisnik();
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    public void goToPocetna(View view){
        Intent i = new Intent(this, PocetnaActivity.class);
        finish();
        startActivity(i);
    }

    public void getVoznjaIdData(){
        prefs = getSharedPreferences("Prefs", Context.MODE_PRIVATE);
        String voznjaId = prefs.getString("voznjaIdKey",null);
        (VoznjaApi.getClient().getVoznjaById(voznjaId)).enqueue(new Callback<List<Voznja>>() {
            @Override
            public void onResponse(Call<List<Voznja>> call, Response<List<Voznja>> response) {
                assert response.body() != null;
                voznja = response.body().get(0);
                voziloTxtData = voznja.getVozilo().getNaziv() + " ("+voznja.getVozilo().getRegistarskiBroj()+")";
                pocetnaKmData = voznja.getPocetnaKm();
                svrhaVoznjeData = voznja.getSvrha().name();
                destinacijaData = voznja.getDestinacija();
                stanjeVozilaData = voznja.getStanjeVozila().name();
                korisnikData = voznja.getKorisnikId().getIme() + " "+ voznja.getKorisnikId().getPrezime() +" (" +voznja.getKorisnikId().getKorisnickoIme()+")";
                voznjaResponseData = response.body();
                setDataInZatvoriVoznju();
            }
            @Override
            public void onFailure(Call<List<Voznja>> call, Throwable t) {
                Toast.makeText(ZatvoriVoznjuActivity.this, t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setDataInZatvoriVoznju() {
        setDataInVozilo();
        setDataInPocetnaKm();
        setDateinVremeZavrsetkaVoznje();
        setDataInSvrhaVoznje();
        destinacija.getEditText().setText(destinacijaData);
        setDataInStanjeVozila();
        setDataInKorisnik();
    }

    public void setDataInVozilo (){
        voziloTxt.getEditText().setText(voziloTxtData);
        voziloTxt.getEditText().setInputType(InputType.TYPE_NULL);
    }

    public void setDataInPocetnaKm(){
        pocetnakm.getEditText().setText(Integer.toString(pocetnaKmData));
        pocetnakm.getEditText().setInputType(InputType.TYPE_NULL);
    }

    public void setDataInSvrhaVoznje(){
        svrhavoznje.getEditText().setText(voznja.getSvrha().name());
        svrhavoznje.getEditText().setInputType(InputType.TYPE_NULL);
    }

    public String getDate(LocalDateTime date){
        return date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy.  HH:mm"));
    }

    private void setDateinVremeZavrsetkaVoznje(){
        ov.getDate(date);
        Log.d("Datum",date.toString());
        Objects.requireNonNull(vreme_zavrsetka_voznje.getEditText()).setText(getDate(date));
        vreme_zavrsetka_voznje.getEditText().setInputType(InputType.TYPE_NULL);
    }

    public void setDataInStanjeVozila(){
        stanjeVozila.getEditText().setText(voznja.getStanjeVozila().name());
        stanjeVozila.getEditText().setInputType(InputType.TYPE_NULL);
    }

    private void setDataInKorisnik(){
        korisnik.getEditText().setText(korisnikData);
        korisnik.getEditText().setInputType(InputType.TYPE_NULL);
    }

    public void zatvoriVoznja(View view) throws ParseException {
        zavrsnaKmData = zavsnakm.getEditText().getText().toString();
        if(zavrsnaKmData.isEmpty()){
            Toast.makeText(ZatvoriVoznjuActivity.this, "Morate uneti završnu kilometražu!", Toast.LENGTH_LONG).show();
        }
        else {
            stVozila = StanjeVozila.valueOf(stanjeVozila.getEditText().getText().toString());
            Integer zavrsnaKm = Integer.parseInt(zavrsnaKmData);
            Integer predjenoKm = zavrsnaKm - pocetnaKmData;
            destinacijaData = destinacija.getEditText().getText().toString();
            String dt = vreme_zavrsetka_voznje.getEditText().getText().toString();
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.  HH:mm");
            Date vremeZavrsetka = sdf.parse(dt);
            String napomenaTxt = napomena.getEditText().getText().toString();
            boolean pranje = false;
            String vid = voznja.getId();
            Voznja voznja = new Voznja(zavrsnaKm, predjenoKm, vremeZavrsetka,stVozila, napomenaTxt,destinacijaData, pranje );
            Call<Voznja> call = VoznjaApi.getClient().zatvoriVoznja(vid,voznja);
            call.enqueue(new Callback<Voznja>() {

                @Override
                public void onResponse(Call<Voznja> call, Response<Voznja> response) {
                    String status = Integer.toString(response.code());
                    Toast.makeText(ZatvoriVoznjuActivity.this, "Voznja je uspešno zatvorena!\n" + "HTTP STATUS= " + status, Toast.LENGTH_LONG).show();
                    assert response.body() != null;
                }
                @Override
                public void onFailure(Call<Voznja> call, Throwable t) {
                    Log.d("testme", "Error: " + t.toString());
                }
            });
        }
        spinner.setVisibility(View.GONE);
        goToPocetna(view);
    }
}


