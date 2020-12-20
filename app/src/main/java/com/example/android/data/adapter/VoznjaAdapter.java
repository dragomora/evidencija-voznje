package com.example.android.data.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.example.android.R;
import com.example.android.data.model.Voznja;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class VoznjaAdapter extends RecyclerView.Adapter<VoznjaAdapter.VoznjaViewHolder> {
    Date vremePocetka;
    Date vremeZavrsetka;

    private Context context;
    private List<Voznja> voznjaList;
    public VoznjaAdapter(Context context, List<Voznja> voznjaList){
        this.context = context;
        this.voznjaList = voznjaList;
    }
    @NonNull
    @Override
    public VoznjaAdapter.VoznjaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_lista_voznji,parent,false);
    return new VoznjaViewHolder(view);
    }
    public void onBindViewHolder(VoznjaViewHolder holder, int position){
        Voznja voznja = voznjaList.get(position);
        vremePocetka = voznja.getPocetakVoznje();
        vremeZavrsetka = voznja.getKrajVoznje();
        Integer predjenoKmtmp = voznja.getPredjenoKm();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.  HH:mm");
        Date kv = voznja.getKrajVoznje();
        String aktivnaNeaktivna = kv == null ? "AKTIVNA" : "ZAVRŠENA";
        String predjenoKm = predjenoKmtmp == null ? "-" : voznja.getPredjenoKm().toString();
        String zav = kv == null ? "-" : sdf.format(vremeZavrsetka);

        holder.txtNaslov.setText(voznja.getKorisnikId().getIme() + " " + voznja.getKorisnikId().getPrezime() + " ("+ sdf.format(vremePocetka) + ")");
        holder.txtPodnaslov.setText(voznja.getVozilo().getRegistarskiBroj() + " ("+ voznja.getVozilo().getNaziv()+")");
        holder.txtAktivnaNeaktivna.setText("Status vožnje: "+ aktivnaNeaktivna);
        holder.txtStanje.setText(voznja.getStanjeVozila().name());
        holder.txtSvrha.setText(voznja.getSvrha().name());
        holder.txtPredjenoKm.setText(predjenoKm);
        holder.txtKorisnik.setText(voznja.getKorisnikId().getKorisnickoIme());
        holder.txtZavrsetak.setText(zav);
        holder.txtDestinacija.setText(voznja.getDestinacija());
        boolean isExpanded = voznjaList.get(position).isExpanded();
        holder.cardDodatak.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
        holder.arrowBtn.setBackgroundResource(isExpanded ? (R.drawable.ic_keyboard_arrow_up_black_24dp):(R.drawable.ic_keyboard_arrow_down_black_24dp));
    }

    public int getItemCount(){
        return voznjaList.size();
    }


    class VoznjaViewHolder extends RecyclerView.ViewHolder{
        TextView txtNaslov, txtPodnaslov, txtAktivnaNeaktivna,txtStanje,txtSvrha,txtPredjenoKm,txtKorisnik,txtZavrsetak,txtDestinacija;
        CardView card;
        ConstraintLayout cardDodatak;
        Button arrowBtn;
        public VoznjaViewHolder(View itemView){
            super(itemView);
            card = itemView.findViewById(R.id.cardListaVoznji);
            cardDodatak = itemView.findViewById(R.id.expandableView);
            arrowBtn = itemView.findViewById(R.id.arrowBtn);
            txtNaslov = itemView.findViewById(R.id.cardNaslov);
            txtPodnaslov = itemView.findViewById(R.id.cardPodnaslov);
            txtAktivnaNeaktivna = itemView.findViewById(R.id.txtAktivnaNeaktivna);
            txtStanje = itemView.findViewById(R.id.txtStanje);
            txtSvrha = itemView.findViewById(R.id.txtSvrha);
            txtPredjenoKm = itemView.findViewById(R.id.txtPredjenoKm);
            txtKorisnik = itemView.findViewById(R.id.txtKorisnik);
            txtZavrsetak = itemView.findViewById(R.id.txtZavrsetak);
            txtDestinacija = itemView.findViewById(R.id.txtDestinacija);


            itemView.setOnClickListener(view -> {
                Voznja voznja = voznjaList.get(getAdapterPosition());
                voznja.setExpanded(!voznja.isExpanded());
                notifyItemChanged(getAdapterPosition());
            });
        }
    }

}
