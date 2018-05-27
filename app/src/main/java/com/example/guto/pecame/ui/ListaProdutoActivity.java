package com.example.guto.pecame.ui;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.guto.pecame.R;
import com.example.guto.pecame.adaptadores.ProdutoAdaptador;
import com.example.guto.pecame.fragmentos.BebidaFragment;
import com.example.guto.pecame.fragmentos.SobremesaFragment;
import com.example.guto.pecame.fragmentos.HamburguerFragment;
import com.example.guto.pecame.modelo.MesaModelo;
import com.example.guto.pecame.modelo.ProdutoModelo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListaProdutoActivity extends AppCompatActivity {
    @BindView(R.id.tabs)
    TabLayout tabLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.text_numero_mesa)
    TextView textNumeroMesa;
    @BindView(R.id.floating_button_adicionar_produto)
    FloatingActionButton buttonAdicionarProduto;

    public Context context;
    private ProdutoModelo mProdutoModelo;
    private MesaModelo mesa;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_produto);

        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        insertBD();

        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        toolbar.setTitle(R.string.title_list_product);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_hamburguer);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_drink);
//        tabLayout.getTabAt(2).setIcon(R.drawable.ic_dessert);

        Intent it = getIntent();
//        if (it.hasExtra(ProdutoAdaptador.PRODUTO_PARCELABLE)) {
//            mProdutoModelo = it.getExtras().getParcelable(ProdutoAdaptador.PRODUTO_PARCELABLE);
//        }
        if (it.hasExtra(EscolhaMesaActivity.EDIT_MESA)) {
            mesa = it.getExtras().getParcelable(EscolhaMesaActivity.EDIT_MESA);
        }

        String numMesa = String.valueOf(mesa.getmCodMesa());
        Log.v("Codigo mesa: ",numMesa);

        textNumeroMesa.setText(numMesa);

        buttonAdicionarProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(context, PedidoActivity.class);
                context.startActivity(it);
            }
        });

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new HamburguerFragment(), "Comidas");
        adapter.addFragment(new BebidaFragment(), "Bebidas");
//        adapter.addFragment(new SobremesaFragment(), "Sobremesa");
        viewPager.setAdapter(adapter);
    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {
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


    public void insertBD() {
        myRef.child("ProdutoModelo").child("hamburguers").push().setValue(new ProdutoModelo("Mister", "R$ 15,00"));
        myRef.child("ProdutoModelo").child("hamburguers").push().setValue(new ProdutoModelo("Brutus", "R$ 22,00"));
        myRef.child("ProdutoModelo").child("hamburguers").push().setValue(new ProdutoModelo("Classic", "R$ 11,00"));
        myRef.child("ProdutoModelo").child("hamburguers").push().setValue(new ProdutoModelo("Levíssimo", "R$ 17,00"));
        myRef.child("ProdutoModelo").child("hamburguers").push().setValue(new ProdutoModelo("Espetacular", "R$ 17,00"));
        myRef.child("ProdutoModelo").child("hamburguers").push().setValue(new ProdutoModelo("Modesto", "R$ 13,00"));
        myRef.child("ProdutoModelo").child("hamburguers").push().setValue(new ProdutoModelo("Fabuloso", "R$ 15,00"));
        myRef.child("ProdutoModelo").child("hamburguers").push().setValue(new ProdutoModelo("Frescão", "R$ 14,00"));

        myRef.child("ProdutoModelo").child("drinks").push().setValue(new ProdutoModelo("Refrigerante Coca Cola 600 ml", "R$ 7,00"));
        myRef.child("ProdutoModelo").child("drinks").push().setValue(new ProdutoModelo("Refrigerante Guaraná Antartica 350 ml", "R$ 5,00"));
        myRef.child("ProdutoModelo").child("drinks").push().setValue(new ProdutoModelo("Cerveja Long Neck Stella Artois 275 ml", "R$ 7,00"));
        myRef.child("ProdutoModelo").child("drinks").push().setValue(new ProdutoModelo("Energetico Monster Energy 473 ml ", "R$ 8,00"));

        myRef.child("ProdutoModelo").child("desserts").push().setValue(new ProdutoModelo("Americano", "R$ 15,00"));
        myRef.child("ProdutoModelo").child("desserts").push().setValue(new ProdutoModelo("Brownie", "R$ 17,00"));
        myRef.child("ProdutoModelo").child("desserts").push().setValue(new ProdutoModelo("Picolé brigadeiro", "R$ 7,00"));
    }

}


