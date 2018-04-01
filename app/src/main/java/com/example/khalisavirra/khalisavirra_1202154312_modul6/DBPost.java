package com.example.khalisavirra.khalisavirra_1202154312_modul6;

/**
 * Created by virra PC on 4/1/2018.
 */

public class DBPost {
    public String image, title, caption, user, key;

    //Dibutuhkan kosong untuk membaca data
    public DBPost() {
    }

    //Konstruktor dari DBPost
    public DBPost(String caption, String image, String title, String user) {
        this.image = image;
        this.title = title;
        this.caption = caption;
        this.user = user;
    }

    //Mendapatkan key dari Firebase
    public String getKey() {
        return key;
    }

    //Menentukan key dari Firebase
    public void setKey(String key) {
        this.key = key;
    }

    //Getter variabel dari class ini
    public String getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getCaption() {
        return caption;
    }

    public String getUser() {
        return user;
    }

}

