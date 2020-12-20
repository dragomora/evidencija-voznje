package com.example.android.data.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.example.android.R;
import com.example.android.data.model.Korisnici;
import com.example.android.data.model.Vozilo;
import com.example.android.data.model.Voznja;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;



public class VozilaAdapter extends RecyclerView.Adapter <VozilaAdapter.VozilaViewHolder> {
    Date date;
    String dateTime;


    private Context context;
    private List<Vozilo> vozilaList;
    public VozilaAdapter(Context context, List<Vozilo> vozilaList){
        this.context = context;
        this.vozilaList = vozilaList;
    }

    @NonNull
    @Override
    public VozilaAdapter.VozilaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_lista_vozila,parent,false);
        return new VozilaAdapter.VozilaViewHolder(view);
    }

    class VozilaViewHolder extends RecyclerView.ViewHolder{
        TextView txtNaslov, txtPodnaslov, txtRegistracija,txtServisniInterval,txtDoServisaKm,txtTrenutnoKm;
        CardView card;
        ConstraintLayout cardDodatak;
        Button arrowBtn;
        public VozilaViewHolder(View itemView){
            super(itemView);
            card = itemView.findViewById(R.id.cardListaVozila);
            cardDodatak = itemView.findViewById(R.id.expandableView);
            arrowBtn = itemView.findViewById(R.id.arrowBtn);
            txtNaslov = itemView.findViewById(R.id.cardNaslov);
            txtPodnaslov = itemView.findViewById(R.id.cardPodnaslov);
            txtRegistracija = itemView.findViewById(R.id.txtRegistracija);
            txtServisniInterval = itemView.findViewById(R.id.txtServisniInterval);
            txtDoServisaKm = itemView.findViewById(R.id.txtDoServisaKm);
            txtTrenutnoKm = itemView.findViewById(R.id.txtTrenutnoKm);


            itemView.setOnClickListener(view -> {
                Vozilo vozilo= vozilaList.get(getAdapterPosition());
                vozilo.setExpanded(!vozilo.isExpanded());
                notifyItemChanged(getAdapterPosition());
            });
        }
    }
    @Override
    public int getItemCount(){
        return vozilaList.size();
    }

    public void onBindViewHolder(VozilaAdapter.VozilaViewHolder holder, int position){
        Vozilo vozilo = vozilaList.get(position);
        date = vozilo.getDatum_registracije();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.  HH:mm");
        dateTime = sdf.format(date);
        boolean zauzeto = vozilo.getZauzeto();
        String slobodnoZauzeto = zauzeto ? "AKTIVNA VOŽNJA" : "SLOBODNO";
        Integer poslednjiServis = vozilo.getPoslednjiServisKm();
        Integer servisniInterval = vozilo.getServisniInterval();
        Integer trenutnaKm = vozilo.getTrenutnaKm();
        int doServisa = servisniInterval - (trenutnaKm - poslednjiServis);

        holder.txtNaslov.setText(vozilo.getRegistarskiBroj() + " ("+ vozilo.getNaziv()+")");
        holder.txtPodnaslov.setText("Status vozila: " + slobodnoZauzeto);
        holder.txtRegistracija.setText(dateTime);
        holder.txtServisniInterval.setText(servisniInterval.toString());
        holder.txtDoServisaKm.setText((Integer.toString( doServisa)));
        holder.txtTrenutnoKm.setText(Integer.toString(trenutnaKm));
        boolean isExpanded = vozilaList.get(position).isExpanded();

        holder.cardDodatak.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
        holder.arrowBtn.setBackgroundResource(isExpanded ? (R.drawable.ic_keyboard_arrow_up_black_24dp):(R.drawable.ic_keyboard_arrow_down_black_24dp));

    }



}
