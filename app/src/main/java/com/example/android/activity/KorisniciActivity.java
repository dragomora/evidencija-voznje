package com.example.android.activity;

import android.app.ProgressDialog;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.android.R;
import com.example.android.data.adapter.KorisniciAdapter;
import com.example.android.data.model.Korisnici;
import com.example.android.data.api.KorisniciApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class KorisniciActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Korisnici> korisniciResponseData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spisak_korisnika);
        recyclerView = (RecyclerView) findViewById(R.id.rviewListaVozila);
        getKorisniciData();
        //String[] foods = {"Bacon","Ham"};
        //ListAdapter spisakVozilaAdapter = new ArrayAdapter<String>(this, R.layout.list_view_text_items,foods);
        //ListView listSpisakVozila = (ListView) findViewById(R.id.listSpisakVozila);
        //listSpisakVozila.setAdapter(spisakVozilaAdapter);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
    private void getKorisniciData(){
        // display a progress dialog
        final ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(KorisniciActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show(); // show progress dialog

        (KorisniciApi.getClient().getUserList()).enqueue(new Callback<List<Korisnici>>() {
            @Override
            public void onResponse(Call<List<Korisnici>> call, Response<List<Korisnici>> response) {
                Log.d("responseGET", response.body().get(0).getIme());
                progressDialog.dismiss(); //dismiss progress dialog
                korisniciResponseData = response.body();
                setDataInRecyclerView();
            }

            @Override
            public void onFailure(Call<List<Korisnici>> call, Throwable t) {
// if error occurs in network transaction then we can get the error in this method.
                Toast.makeText(KorisniciActivity.this, t.toString(), Toast.LENGTH_LONG).show();
                progressDialog.dismiss(); //dismiss progress dialog
            }
        });
    }



    private void setDataInRecyclerView(){
        // set a LinearLayoutManager with default vertical orientation
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(KorisniciActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        // call the constructor of UsersAdapter to send the reference and data to Adapter
        KorisniciAdapter korisniciAdapter = new KorisniciAdapter(KorisniciActivity.this, korisniciResponseData);
        recyclerView.setAdapter(korisniciAdapter); // set the Adapter to RecyclerView
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

}
