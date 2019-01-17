package com.ceyizuygulamasi.kubra.ceyizprojesi.Adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.ceyizuygulamasi.kubra.ceyizprojesi.Activity.MainActivity;
import com.ceyizuygulamasi.kubra.ceyizprojesi.Fragment.UrunlerFragment;
import com.ceyizuygulamasi.kubra.ceyizprojesi.Holder.Kategori_ViewHolder;
import com.ceyizuygulamasi.kubra.ceyizprojesi.Model.Kategori;
import com.ceyizuygulamasi.kubra.ceyizprojesi.R;

import java.util.List;

public class KategoriAdapter extends RecyclerView.Adapter<Kategori_ViewHolder> {

    List<Kategori> kategoris;
    public KategoriAdapter(){

    }
    public KategoriAdapter(List<Kategori> kategoris){
        this.kategoris=kategoris;
    }
    @NonNull
    @Override
    public Kategori_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Adapter sınıfınun layout gorunumune baglanması işlemi saglar.
        View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.kategori_row,null);
        return new Kategori_ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Kategori_ViewHolder holder, final int position) {
        //nesnelerin tanımlanması ve olayların tanımlanması
        holder.tvTitle.setText(kategoris.get(position).getKategoriAdi());


      holder.tvUrunSayisi.setText(kategoris.get(position).getKategori_Urunsayisi()+"");
      Glide.with(holder.itemView.getContext())
              .load(kategoris.get(position).getKategori_resim())
              .into(holder.ivPhoto);
      holder.itemView.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent intent=new Intent(v.getContext(),MainActivity.class);
              intent.putExtra("id",kategoris.get(position).getId());
              v.getContext().startActivity(intent);
          }
      });

    }

    @Override
    public int getItemCount() {
        return kategoris.size();
    }
}
