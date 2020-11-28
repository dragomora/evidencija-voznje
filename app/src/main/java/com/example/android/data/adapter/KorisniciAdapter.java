package com.example.android.data.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;
import com.example.android.R;
import com.example.android.data.model.Korisnici;
import android.widget.TextView;

import java.util.List;

public class KorisniciAdapter extends RecyclerView.Adapter <KorisniciAdapter.KorisniciViewHolder> {
    Context context;
    List<Korisnici> korisniciListResponseData;
    public KorisniciAdapter(Context context, List<Korisnici> korisniciListResponseData){
        this.korisniciListResponseData = korisniciListResponseData;
        this.context = context;
    }
    @Override
    public  KorisniciViewHolder onCreateViewHolder (ViewGroup parent, int viewType){
        View view = LayoutInflater.from(context).inflate(R.layout.list_view_text_items,null);
        KorisniciViewHolder korisniciViewHolder = new KorisniciViewHolder(view);
        return korisniciViewHolder;
    }
    @Override
    public void  onBindViewHolder(KorisniciViewHolder holder, final int position) {
    holder.ime.setText("Ime: " + korisniciListResponseData.get(position).getIme());
    holder.prezime.setText("Prezime: " + korisniciListResponseData.get(position).getPrezime());
    holder.itemView.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View view){
            Toast.makeText(context, korisniciListResponseData.get(position).getIme(),Toast.LENGTH_SHORT).show();
        }
    });

    }
    @Override
    public int getItemCount(){
        return korisniciListResponseData.size();
    }

    class KorisniciViewHolder extends RecyclerView.ViewHolder{
        TextView ime, prezime;
        public KorisniciViewHolder (View itemView){
            super(itemView);
            ime = (TextView) itemView.findViewById(R.id.name);
            prezime = (TextView) itemView.findViewById(R.id.email);
        }
    }

}
