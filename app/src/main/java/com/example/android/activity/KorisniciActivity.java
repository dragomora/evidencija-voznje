package com.example.android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.android.R;
import com.example.android.data.adapter.KorisniciAdapter;
import com.example.android.data.api.KorisniciApi;
import com.example.android.data.model.Korisnici;

import java.util.List;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KorisniciActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Korisnici> korisniciList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spisak_korisnika);
        recyclerView = (RecyclerView) findViewById(R.id.rviewListaVozila);
        getKorisniciData();
        recyclerView = findViewById(R.id.rviewListaKorisnika);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Button btnBack = findViewById(R.id.buttonBack);
        btnBack.setOnClickListener(this::onBackBtn);
    }

    private void onBackBtn(View view) {
        Intent i = new Intent(this, AdminActivity.class);
        startActivity(i);
        finish();
    }

    private void getKorisniciData(){
        (KorisniciApi.getClient().getUserList()).enqueue(new Callback<List<Korisnici>>() {
            @Override
            public void onResponse(Call<List<Korisnici>> call, Response<List<Korisnici>> response) {
                Log.d("responseGET", response.body().get(0).getIme());
                korisniciList = response.body();
                setDataInRecyclerView();
            }

            @Override
            public void onFailure(Call<List<Korisnici>> call, Throwable t) {
                // if error occurs in network transaction then we can get the error in this method.
                Toast.makeText(KorisniciActivity.this, t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setDataInRecyclerView(){
        // set a LinearLayoutManager with default vertical orientation
        // call the constructor of UsersAdapter to send the reference and data to Adapter
        KorisniciAdapter adapter = new KorisniciAdapter(this, korisniciList);
        recyclerView.setAdapter(adapter); // set the Adapter to RecyclerView
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(this, AdminActivity.class);
        startActivity(i);
        finish();
    }
}
