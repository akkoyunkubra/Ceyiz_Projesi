package com.ceyizuygulamasi.kubra.ceyizprojesi.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ceyizuygulamasi.kubra.ceyizprojesi.Adapter.KategoriAdapter;
import com.ceyizuygulamasi.kubra.ceyizprojesi.Model.Kategori;
import com.ceyizuygulamasi.kubra.ceyizprojesi.R;

import java.util.ArrayList;
import java.util.List;

public class KategoriFragment extends Fragment {
    RecyclerView recyclerView;
    KategoriAdapter kategoriAdapter;
    List<Kategori> kategoris;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_kategori, container, false);
        kategoris=new ArrayList<>();
        kategoris.add(
                new Kategori(1,"Mutfak Gereçleri",5,"https://images.unsplash.com/photo-1522790979484-0ca297a0b0f6?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1534&q=80")
        ) ;

        kategoris.add(
                new Kategori(2,"Banyo Gereçleri",10,"https://images.unsplash.com/photo-1536858974309-969976df0d4d?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1500&q=80")
        );
        kategoris.add(
                new Kategori(3,"Mobilya",10,"https://images.unsplash.com/photo-1503174971373-b1f69850bded?ixlib=rb-1.2.1&auto=format&fit=crop&w=787&q=80")
        );
        kategoris.add(
                new Kategori(4,"Ev Aksesuarları",3,"https://images.unsplash.com/photo-1519710164239-da123dc03ef4?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=334&q=80")
        );

        recyclerView=v.findViewById(R.id.recyclerview);
        kategoriAdapter=new KategoriAdapter(kategoris);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(kategoriAdapter);

        return  v;
    }

}
