package com.ceyizuygulamasi.kubra.ceyizprojesi.Holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ceyizuygulamasi.kubra.ceyizprojesi.R;

public class Kategori_ViewHolder extends RecyclerView.ViewHolder {

    public ImageView ivPhoto;
    public TextView tvTitle;
    public TextView tvUrunSayisi;
    public Kategori_ViewHolder(final View itemView) {
        super(itemView);
        ivPhoto=itemView.findViewById(R.id.ivPhoto);
        tvTitle=itemView.findViewById(R.id.tvTitle);
        tvUrunSayisi=itemView.findViewById(R.id.tvUrunSayisi);

    }
}
