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
import com.example.android.data.adapter.VozilaAdapter;
import com.example.android.data.api.VoziloApi;
import com.example.android.data.model.Vozilo;

import java.util.Date;
import java.util.List;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VozilaActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Vozilo> vozilaList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spisak_vozila);
        getVozilaData();
        recyclerView=findViewById(R.id.rviewListaVozila);
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

    public void getVozilaData(){

        (VoziloApi.getClient().getVoziloSum()).enqueue(new Callback<List<Vozilo>>() {
            @Override
            public void onResponse(Call<List<Vozilo>> call, Response<List<Vozilo>> response) {
                //Log.d("responseGET", response.body().get(0).getNaziv());
                vozilaList = response.body();
                Date date = vozilaList.get(0).getSum().get(0).getDatumRegistracije();
                Log.d("hakaddd",date.toString());
                setDataInRecyclerView();
            }

            @Override
            public void onFailure(Call<List<Vozilo>> call, Throwable t) {
// if error occurs in network transaction then we can get the error in this method.
                Toast.makeText(VozilaActivity.this, t.toString(), Toast.LENGTH_LONG).show();
                //progressDialog.dismiss(); //dismiss progress dialog
            }
        });
    }

    public void setDataInRecyclerView(){
        VozilaAdapter adapter = new VozilaAdapter(this,vozilaList);
        recyclerView.setAdapter(adapter);
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
