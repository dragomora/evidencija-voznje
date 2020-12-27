package com.example.android.data.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.example.android.R;
import com.example.android.data.model.Korisnici;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class KorisniciAdapter extends RecyclerView.Adapter <KorisniciAdapter.KorisniciViewHolder> {
    Context context;
    Date date;
    String dateTime;

    List<Korisnici> korisniciList;
    public KorisniciAdapter(Context context, List<Korisnici> korisniciList){
        this.korisniciList = korisniciList;
        this.context = context;
    }
    @Override
    public  KorisniciAdapter.KorisniciViewHolder onCreateViewHolder (ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_lista_korisnika,parent,false);
        return new KorisniciViewHolder(view);
    }

    class KorisniciViewHolder extends RecyclerView.ViewHolder{
        TextView txtNaslov, txtPodnaslov, txtAdminRole, txtSaobracajnaVaziDo, txtOtvorenaVoznjaId, txtKorisnikId, txtVazenjeDozvole;
        CardView cardView;
        ConstraintLayout cardDodatak;
        Button arrowBtn;
        public KorisniciViewHolder (View itemView){
            super(itemView);
            cardView = itemView.findViewById(R.id.cardListakorisnika);
            cardDodatak = itemView.findViewById(R.id.cardDodatak);
            arrowBtn = itemView.findViewById(R.id.arrowBtn);
            txtNaslov = itemView.findViewById(R.id.txtNaslov);
            txtPodnaslov = itemView.findViewById(R.id.txtPodnaslov);
            txtAdminRole = itemView.findViewById(R.id.txtAdminRole);
            txtVazenjeDozvole = itemView.findViewById(R.id.txtVazenjeVozacke);
            txtOtvorenaVoznjaId = itemView.findViewById(R.id.txtOtvorenaVoznjaId);
            txtKorisnikId = itemView.findViewById(R.id.txtKorisnikId);
            itemView.setOnClickListener(view -> {
                Korisnici korisnici = korisniciList.get(getAdapterPosition());
                korisnici.setExpanded(!korisnici.isExpanded());
                notifyItemChanged(getAdapterPosition());
            });
        }
    }

    @Override
    public void  onBindViewHolder(KorisniciAdapter.KorisniciViewHolder holder, final int position) {
        Korisnici korisnici = korisniciList.get(position);
        date = korisnici.getVazenjeDozvole();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.");
        dateTime = sdf.format(date);
        String ime = korisnici.getIme();
        String prezime= korisnici.getPrezime();
        String korisnickoIme = korisnici.getKorisnickoIme();
        boolean admin = korisnici.getAdmin();
        String voznja = korisnici.getOtvorenaVoznjaId();
        String adminStatus = admin ? " NE" : " DA";
        String voznjaStatus = voznja.isEmpty() ? "Nema aktivnu vožnju" : "Ima aktivnu vožnju";

        holder.txtNaslov.setText(ime + " "+ prezime + " (" + korisnickoIme + ")");
    holder.txtPodnaslov.setText("Status: " + voznjaStatus);
    holder.txtAdminRole.setText(adminStatus);
    holder.txtOtvorenaVoznjaId.setText(korisnici.getOtvorenaVoznjaId());
    holder.txtKorisnikId.setText(korisnici.getKorisnickoIme());
    boolean isExpanded = korisniciList.get(position).isExpanded();
    holder.txtVazenjeDozvole.setText(dateTime);
    holder.cardDodatak.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
    holder.arrowBtn.setBackgroundResource(isExpanded ? (R.drawable.ic_keyboard_arrow_up_black_24dp):(R.drawable.ic_keyboard_arrow_down_black_24dp));


    }
    @Override
    public int getItemCount(){
        return korisniciList.size();
    }


}

/*
    holder.itemView.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View view){
            Toast.makeText(context, korisniciList.get(position).getIme(),Toast.LENGTH_SHORT).show();
        }
    });
*/

