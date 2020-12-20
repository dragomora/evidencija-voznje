package com.example.android.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.content.Intent;
import com.example.android.R;
import com.example.android.activity.KorisniciActivity;
import com.example.android.data.api.KorisniciApi;
import com.example.android.data.model.Korisnici;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "logBr1";
    private TextView message;
    private int counter = 0;
    List<Korisnici> korisnickoIme;
    public static final String users_details = "Prefs" ;
    public static final String korisnik = "userKey";
    public static final String userId = "idKey";
    public static final String adminRole = "adminKey";
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToPocetna(View view){
        EditText text = findViewById(R.id.inputEmail);
        String user = text.getText().toString();
        getKorisnikObject(user);
    }

    public void getKorisnikObject(String user){
        (KorisniciApi.getClient().getKorisnickoIme(user)).enqueue(new Callback<List<Korisnici>>() {
            @Override
            public void onResponse(Call<List<Korisnici>> call, Response<List<Korisnici>> response) {
                korisnickoIme = response.body();
                proveriKorisnikObjekat();
            }
            @Override
            public void onFailure(Call<List<Korisnici>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void proveriKorisnikObjekat(){
        List<Korisnici> korisnikObject = korisnickoIme;
        if(korisnikObject==null || korisnikObject.isEmpty()){
            Toast.makeText(MainActivity.this, "Korisnik ne postoji!", Toast.LENGTH_LONG).show();
        }
        else{
            //Log.d("username",korisnickoIme.get(0).getIme());
            proveraKorisnika();
        }
    }

    public void proveraKorisnika(){
        Log.d("username",korisnickoIme.get(0).getKorisnickoIme());
        EditText text = findViewById(R.id.inputPassword);
        if(text.getText().toString().equals(korisnickoIme.get(0).getLozinka())){
            nastaviPocetna();
        }
        else{
            Toast.makeText(MainActivity.this, "Lozinka nije ispravna", Toast.LENGTH_LONG).show();
        }
    }

    public void nastaviPocetna(){
        String userSave = korisnickoIme.get(0).getKorisnickoIme();
        String userSaveId = korisnickoIme.get(0).getId();
        Boolean userSaveAdminRole = korisnickoIme.get(0).getAdmin();
        Log.d("rola", userSaveAdminRole.toString());
        sharedpreferences = getSharedPreferences(users_details, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(korisnik, userSave);
        editor.putString(userId, userSaveId);
        editor.putBoolean(adminRole, userSaveAdminRole);
        editor.commit();
        Intent i = new Intent(this, PocetnaActivity.class);
        startActivity(i);
    }






    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");
    }


    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstanceState");
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(TAG, "onRestoreInstanceState");
    }

}
