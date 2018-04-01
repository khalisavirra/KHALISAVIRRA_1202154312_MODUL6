package com.example.khalisavirra.khalisavirra_1202154312_modul6;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class PopotoanHome extends AppCompatActivity {
    //Deklarasi variabel
    FloatingActionButton fab;
    ViewPager vp;
    TabLayout tab;
    FirebaseAuth auth;
    AppBarLayout abl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popotoan_home);

        //Mencari variabel berdasarkan id
        vp = (ViewPager)findViewById(R.id.vp);
        tab = (TabLayout) findViewById(R.id.tabs);
        fab = (FloatingActionButton)findViewById(R.id.fab);
        abl = (AppBarLayout)findViewById(R.id.appbar);
        auth = FirebaseAuth.getInstance();

        //Listener ketika fab atau floating action button di klik
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PopotoanHome.this,PostPoto.class);
                startActivity(intent);
            }
        });

        setupPager(vp);

        tab.setupWithViewPager(vp);
        tab.getTabAt(0).setText("TERBARU");
        tab.getTabAt(1).setText("POTO SAYA");
        tab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            //Ketika tab dipilih atau selected
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getPosition()==0){
                    abl.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                }else{
                    abl.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                logoutFirebase();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //Method untuk logout dari Firebase
    private void logoutFirebase() {
        //Session logout dari firebase
        FirebaseAuth.getInstance().signOut();

        //Intent kembali ke login activity
        Intent intent = new Intent(PopotoanHome.this, Login.class);
        startActivity(intent);
        finish();
    }

    //Menetapkan adapter untuk viewpager
    public void setupPager(ViewPager v){
        VPAdapter adapter = new VPAdapter(getSupportFragmentManager());
        adapter.addFragment(new PopotoanTerbaru(), "TERBARU");
        adapter.addFragment(new PopotoanPotoSaya(),"POTO SAYA");

        v.setAdapter(adapter);
    }

    //Subclass sebagai adapter untuk Viewpager dengan fragmentnya
    class VPAdapter extends FragmentPagerAdapter {
        private final List<Fragment> listfragment = new ArrayList<>();
        private final List<String> listfragmenttitle = new ArrayList<>();
        public VPAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return listfragment.get(position);
        }

        public void addFragment(Fragment f, String title){
            listfragment.add(f);
            listfragmenttitle.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return null;
        }

        @Override
        public int getCount() {
            return listfragment.size();
        }
    }
}
