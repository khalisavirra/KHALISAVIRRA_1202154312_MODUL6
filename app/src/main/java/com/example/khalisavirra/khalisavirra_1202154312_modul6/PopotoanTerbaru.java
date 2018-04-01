package com.example.khalisavirra.khalisavirra_1202154312_modul6;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PopotoanTerbaru extends android.support.v4.app.Fragment{
    //Deklarasi variabel
    RecyclerView rc;
    DatabaseReference dataref;
    ArrayList<DBPost> list;
    AdapterPost adapterPost;

    public PopotoanTerbaru() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //Inisialisasi semua objek
        View v = inflater.inflate(R.layout.activity_popotoan_terbaru, container, false);
        rc = v.findViewById(R.id.rchometerbaru);
        list = new ArrayList<>();
        dataref = FirebaseDatabase.getInstance().getReference().child("post");
        adapterPost = new AdapterPost(list, this.getContext());

        //Get jumlah kolom yang sesuai
        int gridColumnCount = getResources().getInteger(R.integer.grid_column_count);
        //Menampilkan recyclerview
        rc.setLayoutManager(new GridLayoutManager(this.getContext(),gridColumnCount));
        rc.setAdapter(adapterPost);

        //Event listener ketika value pada Firebase berubah
        dataref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                DBPost cur = dataSnapshot.getValue(DBPost.class);
                cur.key = dataSnapshot.getKey();
                list.add(cur);
                adapterPost.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return v;
    }
}