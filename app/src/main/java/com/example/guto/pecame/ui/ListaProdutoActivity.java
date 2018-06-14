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
import android.widget.Toast;

import com.example.guto.pecame.AdapterCallback;
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

public class ListaProdutoActivity extends AppCompatActivity implements AdapterCallback {
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
    
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    //TODO:lista dos selecionados
    List<ProdutoModelo> selecionados = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_produto);

        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        buttonAdicionarProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuffer responseText = new StringBuffer();
        responseText.append("The following were selected...\n");
                for(ProdutoModelo produto : selecionados){
                    responseText.append("\n" + produto.getmDescProduto().toString());
                }

                Toast.makeText(getApplicationContext(),
                responseText, Toast.LENGTH_LONG).show();

            }
        });

        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_hamburguer);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_drink);

        Intent it = getIntent();
        Bundle bundle = it.getExtras();
        textNumeroMesa.setText(bundle.getString(EscolhaMesaActivity.EDIT_MESA));

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        //TODO: Criado uma referencia da activity no fragment
        adapter.addFragment(new HamburguerFragment(this), "Comidas");
        adapter.addFragment(new BebidaFragment(this), "Bebidas");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onCheckItemCallback(ProdutoModelo produto,boolean isSelected) {
        if (isSelected) {
            selecionados.add(produto);
//            Log.v("Selecionados: ", selecionados.toArray().toString());
            Toast.makeText(this,"Item adicionado: " + produto.getmDescProduto(),Toast.LENGTH_SHORT).show();
        } else {
            for (ProdutoModelo prod : selecionados) {
                if (prod.getmCodProduto() == produto.getmCodProduto()) {
                    selecionados.remove(prod);
                    Toast.makeText(this,"Item removido: " + prod.getmDescProduto(),Toast.LENGTH_LONG).show();
                }
            }
        }
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}


