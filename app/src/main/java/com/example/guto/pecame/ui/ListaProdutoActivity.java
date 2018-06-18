package com.example.guto.pecame.ui;

import android.content.Intent;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.guto.pecame.utils.AdapterCallback;
import com.example.guto.pecame.R;
import com.example.guto.pecame.fragmentos.BebidaFragment;
import com.example.guto.pecame.fragmentos.HamburguerFragment;
import com.example.guto.pecame.modelo.ProdutoModelo;
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
    //TODO:lista dos mSelecionados
    private List<ProdutoModelo> mSelecionados = new ArrayList<>();
    private Intent mIntent;
    Bundle bundleInformacoes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_produto);

        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        buttonAdicionarProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO:Inserir em pedidoActivity os itens mSelecionados
                bundleInformacoes = new Bundle();
                //envio p/ pedido activity o array de produtos mSelecionados
                mIntent = new Intent(getApplicationContext(),PedidoActivity.class);
                bundleInformacoes.putParcelableArrayList("PRODUTOS_SELECIONADOS", (ArrayList<? extends Parcelable>) mSelecionados);
                bundleInformacoes.putFloat("VALOR_TOTAL",receberTotal(mSelecionados));
                mIntent.putExtras(bundleInformacoes);
                mIntent.putExtra(EscolhaMesaActivity.EDIT_MESA,textNumeroMesa.getText());
                startActivity(mIntent);

            }
        });

        if (null != savedInstanceState) {
            mSelecionados = savedInstanceState.getParcelableArrayList("chave");
        }

        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_hamburguer);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_drink);

        Intent it = getIntent();
        Bundle bundle = it.getExtras();
        textNumeroMesa.setText(bundle.getString(EscolhaMesaActivity.EDIT_MESA));

    }

    @Override
    public void onCheckItemCallback(ProdutoModelo produto,boolean isSelected) {
        synchronized (mSelecionados) {
            if (isSelected) {
                mSelecionados.add(produto);
            } else {
                mSelecionados.remove(produto);
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelableArrayList("chave", new ArrayList<>(mSelecionados));
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

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        //TODO: Criado uma referencia da activity no fragment
        adapter.addFragment(new HamburguerFragment(this), "Comidas");
        adapter.addFragment(new BebidaFragment(this), "Bebidas");
        viewPager.setAdapter(adapter);
    }

    public float receberTotal(List<ProdutoModelo> selecionados){
        ProdutoModelo prod;
        float total=0;
        for (int i = 0; i < selecionados.size(); i++) {
            prod = selecionados.get(i);
            total += Float.parseFloat(prod.getmPreco());
        }
        return total;
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


}


