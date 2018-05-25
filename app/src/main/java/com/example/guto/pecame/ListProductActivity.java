package com.example.guto.pecame;

import android.print.PrintDocumentAdapter;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListProductActivity extends AppCompatActivity {
    @BindView(R.id.tabs)
    TabLayout tabLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.viewpager)
    ViewPager viewPager;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_product);

        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        insertBD();

        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        toolbar.setTitle(R.string.title_list_product);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_hamburguer);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_drink);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_dessert);

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new HamburguerFragment(), "Hambúrguer");
        adapter.addFragment(new DrinkFragment(), "Bebidas");
        adapter.addFragment(new DessertFragment(), "Sobremesa");
        viewPager.setAdapter(adapter);
    }

    private class ViewPagerAdapter extends FragmentPagerAdapter{
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager supportFragmentManager) {
            super(supportFragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

//    @Override
//    public boolean onCreateOptionsenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.main,menu);
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void insertBD(){
        myRef.child("Product").child("hamburguers").push().setValue(new Product("Mister","R$ 15,00"));
        myRef.child("Product").child("hamburguers").push().setValue(new Product("Brutus","R$ 22,00"));
        myRef.child("Product").child("hamburguers").push().setValue(new Product("Classic","R$ 11,00"));
        myRef.child("Product").child("hamburguers").push().setValue(new Product("Levíssimo","R$ 17,00"));
        myRef.child("Product").child("hamburguers").push().setValue(new Product("Espetacular","R$ 17,00"));
        myRef.child("Product").child("hamburguers").push().setValue(new Product("Modesto","R$ 13,00"));
        myRef.child("Product").child("hamburguers").push().setValue(new Product("Fabuloso","R$ 15,00"));
        myRef.child("Product").child("hamburguers").push().setValue(new Product("Frescão","R$ 14,00"));

        myRef.child("Product").child("drinks").push().setValue(new Product("Refrigerante Coca Cola 600 ml","R$ 7,00"));
        myRef.child("Product").child("drinks").push().setValue(new Product("Refrigerante Guaraná Antartica 350 ml","R$ 5,00"));
        myRef.child("Product").child("drinks").push().setValue(new Product("Cerveja Long Neck Stella Artois 275 ml","R$ 7,00"));
        myRef.child("Product").child("drinks").push().setValue(new Product("Energetico Monster Energy 473 ml ","R$ 8,00"));

        myRef.child("Product").child("desserts").push().setValue(new Product("Americano","R$ 15,00"));
        myRef.child("Product").child("desserts").push().setValue(new Product("Brownie","R$ 17,00"));
        myRef.child("Product").child("desserts").push().setValue(new Product("Picolé brigadeiro","R$ 7,00"));
    }

}


