package com.ceyizuygulamasi.kubra.ceyizprojesi.Model;

public class Kategori {
    private int id;
    private String KategoriAdi;
    private int Kategori_Urunsayisi;
    private String Kategori_resim;

    public Kategori() {
    }

    public Kategori(int id, String kategoriAdi, int kategori_Urunsayisi, String kategori_resim) {
        this.id = id;
        this.KategoriAdi = kategoriAdi;
        this.Kategori_Urunsayisi = kategori_Urunsayisi;
        this.Kategori_resim = kategori_resim;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKategoriAdi() {
        return KategoriAdi;
    }

    public void setKategoriAdi(String kategoriAdi) {
        KategoriAdi = kategoriAdi;
    }

    public int getKategori_Urunsayisi() {
        return Kategori_Urunsayisi;
    }

    public void setKategori_Urunsayisi(int kategori_Urunsayisi) {
        Kategori_Urunsayisi = kategori_Urunsayisi;
    }

    public String getKategori_resim() {
        return Kategori_resim;
    }

    public void setKategori_resim(String kategori_resim) {
        Kategori_resim = kategori_resim;
    }
}
