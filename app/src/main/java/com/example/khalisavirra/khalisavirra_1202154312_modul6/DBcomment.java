package com.example.khalisavirra.khalisavirra_1202154312_modul6;

/**
 * Created by virra PC on 4/1/2018.
 */

public class DBcomment {
    //Deklarasi variabel
    String user_komentar, isi_komentar, fotoyangdikomen;

    //Dibutuhkan kosong untuk membaca data
    public DBcomment(){
    }

    //Konstruktor dari DBcomment
    public DBcomment(String user_komentar, String isi_komentar, String fotoyangdikomen) {
        this.user_komentar = user_komentar;
        this.isi_komentar = isi_komentar;
        this.fotoyangdikomen = fotoyangdikomen;
    }

    //Getter data
    public String getFotoyangdikomen() {
        return fotoyangdikomen;
    }

    public String getUser_komentar() {
        return user_komentar;
    }

    public String getIsi_komentar() {
        return isi_komentar;
    }

}
