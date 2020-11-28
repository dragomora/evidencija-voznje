package com.example.android.activity;

import android.os.Build;
import android.text.InputType;
import android.util.Log;
import android.widget.*;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.android.R;
import com.example.android.data.api.VoziloApi;
import com.example.android.data.model.Vozilo;
import com.google.android.material.textfield.TextInputLayout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RequiresApi(api = Build.VERSION_CODES.O)
public class OtvoriVoznjuActivity extends AppCompatActivity{
    private List<Vozilo> vozilaResponseData;
    private final ArrayList<String> svrhaVoznje = new ArrayList<>();
    private final ArrayList<String> stanjeVozila = new ArrayList<>();
    private final LocalDateTime date = LocalDateTime.now();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otvori_voznju);
        getVozilaData();
        setDataInSvrhaVoznjeDropDown();
        setDataInStanjeVozilaDropDown();
        setDateinVremePocetkaVoznje();
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
        AutoCompleteTextView vozilaDropdown = findViewById(R.id.filled_exposed_dropdown);
        vozilaDropdown.setAdapter(voziloArrayAdapter);
        vozilaDropdown.setOnItemClickListener((adapterView, view, i, l) -> {
            TextInputLayout predjenokm = findViewById(R.id.predjenoKm);
            Objects.requireNonNull(predjenokm.getEditText()).setText(vozilaResponseData.get((int) l).getPredjenoKm().toString());
        });
    }

    private void setDataInSvrhaVoznjeDropDown(){
        svrhaVoznje.add("POSLOVNO");
        svrhaVoznje.add("PRIVATNO");
        ArrayAdapter<String> svrhaVoznjeArrayAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.dropdown_menu_popup_item, svrhaVoznje);
        AutoCompleteTextView svrhaVoznjeDropdown = findViewById(R.id.svrha_voznje_exposed_dropdown);
        svrhaVoznjeDropdown.setAdapter(svrhaVoznjeArrayAdapter);
        svrhaVoznjeDropdown.setText(svrhaVoznje.get(0),false);
    }

    private void setDataInStanjeVozilaDropDown(){
        stanjeVozila.add("NEOŠTEĆENO");
        stanjeVozila.add("OŠTEĆENO");
        ArrayAdapter<String> stanjeVozilaArrayAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.dropdown_menu_popup_item, stanjeVozila);
        AutoCompleteTextView stanjeVozilaDropdown = findViewById(R.id.stanje_vozila_exposed_dropdown);
        stanjeVozilaDropdown.setAdapter(stanjeVozilaArrayAdapter);
        stanjeVozilaDropdown.setText(stanjeVozila.get(0),false);
    }

    public String getDate(LocalDateTime date){
        return date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy.  HH:mm:ss"));
    }


    private void setDateinVremePocetkaVoznje(){
        TextInputLayout vreme_pocetka_voznje = findViewById(R.id.vreme_pocetka_voznje);
        Objects.requireNonNull(vreme_pocetka_voznje.getEditText()).setText(getDate(date));
        vreme_pocetka_voznje.getEditText().setInputType(InputType.TYPE_NULL);
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


