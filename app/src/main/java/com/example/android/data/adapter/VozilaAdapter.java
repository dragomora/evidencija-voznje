package com.example.android.data.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;
import com.example.android.R;
import com.example.android.data.model.Korisnici;
import com.example.android.data.model.Vozilo;

import java.util.List;



public class VozilaAdapter extends RecyclerView.Adapter <VozilaAdapter.VozilaViewHolder> {
    Context context;
    List<Vozilo> vozilaListResponseData;
    public VozilaAdapter(Context context, List<Vozilo> vozilaListResponseData){
        this.vozilaListResponseData = vozilaListResponseData;
        this.context = context;
    }

    class VozilaViewHolder extends RecyclerView.ViewHolder{
        TextView ime, prezime;
        public VozilaViewHolder (View itemView){
            super(itemView);
            ime = (TextView) itemView.findViewById(R.id.name);
            prezime = (TextView) itemView.findViewById(R.id.email);
        }
    }


    @Override
    public  VozilaViewHolder onCreateViewHolder (ViewGroup parent, int viewType){
        View view = LayoutInflater.from(context).inflate(R.layout.list_view_text_items,null);
        VozilaViewHolder korisniciViewHolder = new VozilaViewHolder(view);
        return korisniciViewHolder;
    }
    @Override
    public void  onBindViewHolder(VozilaViewHolder holder, final int position) {
    holder.ime.setText("Ime: " + vozilaListResponseData.get(position).getNaziv());
    holder.prezime.setText("Prezime: " + vozilaListResponseData.get(position).getRegistarskiBroj());
    holder.itemView.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View view){
            Toast.makeText(context, vozilaListResponseData.get(position).getNaziv(),Toast.LENGTH_SHORT).show();
        }
    });

    }
    @Override
    public int getItemCount(){
        return vozilaListResponseData.size();
    }


}
