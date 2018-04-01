package com.example.khalisavirra.khalisavirra_1202154312_modul6;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by virra PC on 4/1/2018.
 */

public class AdapterComment extends  RecyclerView.Adapter<AdapterComment.CommentHolder>{

    //Deklarasi variabel
    Context con;
    List<DBcomment> list;
    //Konstruktor AdapterComment
    public AdapterComment(Context con, List<DBcomment> list) {
        this.con = con;
        this.list = list;
    }

    //Mengembalikkan atau return ViewHolder dari Recyclerview
    @Override
    public CommentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CommentHolder(LayoutInflater.from(con).inflate(R.layout.rec_comment, parent, false));
    }

    //Merupakan salah satu override untuk mengikat nilai dari list dengan view
    @Override
    public void onBindViewHolder(CommentHolder holder, int position) {
        DBcomment cur = list.get(position);
        holder.user_komentar.setText(cur.getUser_komentar());
        holder.isi_komentar.setText(cur.getIsi_komentar());
    }

    //Mendapatkan jumlah item pada recyclerview
    @Override
    public int getItemCount() {
        //return list dari jumlah item
        return list.size();
    }

    //Subclass sebagai viewholder
    class CommentHolder extends RecyclerView.ViewHolder{
        //Deklarasi variabel
        TextView user_komentar, isi_komentar;
        //Mencari variabel berdasarkan id
        public CommentHolder(View itemView) {
            super(itemView);
            user_komentar = itemView.findViewById(R.id.user_komentar);
            isi_komentar = itemView.findViewById(R.id.isi_komentar);
        }
    }
}


