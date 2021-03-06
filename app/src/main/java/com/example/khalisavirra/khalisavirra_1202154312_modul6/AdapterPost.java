package com.example.khalisavirra.khalisavirra_1202154312_modul6;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by virra PC on 4/1/2018.
 */

public class AdapterPost extends RecyclerView.Adapter<AdapterPost.PostViewHolder>{
        //Deklarasi variabel
        private List<DBPost> list;
        private Context con;

        //Konstruktor AdapterPost
        public AdapterPost(List<DBPost> list, Context con) {
            this.list = list;
            this.con = con;
        }

        //Mengembalikkan atau return ViewHolder untuk adapter
        @Override
        public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new PostViewHolder(LayoutInflater.from(con).inflate(R.layout.rec_feed, parent, false));
        }

        //Merupakan salah satu override untuk mengikat nilai dari list dengan view
        @Override
        public void onBindViewHolder(PostViewHolder holder, int position) {
            DBPost current = list.get(position);
            String [] user = current.user.split("@");
            holder.user.setText(user[0]);
            holder.user.setTag(current.getKey());
            holder.title.setText(current.getTitle());
            holder.caption.setText(current.getCaption());
            holder.caption.setTag(current.getImage());
            Glide.with(con).load(current.getImage()).placeholder(R.drawable.addimage).override(450, 450).into(holder.image);
        }

        //Mendapatkan jumlah item pada recyclerview
        @Override
        public int getItemCount() {
            //return list dari jumlah item
            return list.size();
        }

        //Subclass sebagai viewholder
        class PostViewHolder extends RecyclerView.ViewHolder{
            //Deklarasi variabel
            ImageView image; TextView user, title, caption;

            public PostViewHolder(View itemView) {
                super(itemView);
                //Mencari variabel berdasarkan id
                image = itemView.findViewById(R.id.postgambarnya);
                user = itemView.findViewById(R.id.postpengupload);
                title = itemView.findViewById(R.id.postjudul);
                caption = itemView.findViewById(R.id.postdeskripsi);

                //Aktivitas yang dilakukan ketika item pada recyclerview diklik
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Melakukan intent ke aktivitas selanjutnya setelah button diklik
                        Intent pindah = new Intent(con, Comment.class);
                        pindah.putExtra("user", user.getText());
                        pindah.putExtra("key", user.getTag().toString());
                        pindah.putExtra("title", title.getText());
                        pindah.putExtra("caption", caption.getText());
                        pindah.putExtra("image", caption.getTag().toString());
                        con.startActivity(pindah);
                    }
                });
            }
        }
    }


