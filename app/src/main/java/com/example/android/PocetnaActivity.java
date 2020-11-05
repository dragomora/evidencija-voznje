package com.example.android;

import android.content.Intent;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class PocetnaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pocetna);
    }
    public void goToSpisakVozila(View view){
        Intent i = new Intent(this, SpisakVozilaActivity.class);
        startActivity(i);
    }
}
