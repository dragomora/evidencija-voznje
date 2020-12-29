package com.example.android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.android.R;
import com.example.android.data.adapter.VoznjaAdapter;
import com.example.android.data.api.VoznjaApi;
import com.example.android.data.model.Voznja;

import java.util.List;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VoznjaActivity extends AppCompatActivity {
    List<Voznja> voznjaList;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voznja);
        getVoznjaIdData();

        recyclerView=findViewById(R.id.recyclerVoznja);
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

    public void getVoznjaIdData(){
        (VoznjaApi.getClient().getVoznjaList()).enqueue(new Callback<List<Voznja>>() {
            @Override
            public void onResponse(Call<List<Voznja>> call, Response<List<Voznja>> response) {
                assert response.body() != null;
                voznjaList = response.body();
                setDataInRecyclerView();
            }
            @Override
            public void onFailure(Call<List<Voznja>> call, Throwable t) {
                Toast.makeText(VoznjaActivity.this, t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void setDataInRecyclerView(){
        VoznjaAdapter adapter = new VoznjaAdapter(this,voznjaList);
        recyclerView.setAdapter(adapter);
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

    @Override
    public void onBackPressed() {
        Intent i = new Intent(this, AdminActivity.class);
        startActivity(i);
        finish();
    }
}
