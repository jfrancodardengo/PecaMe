package com.example.guto.pecame.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.guto.pecame.R;
import com.example.guto.pecame.adaptadores.ProdutoAdaptador;
import com.example.guto.pecame.modelo.ProdutoModelo;

public class DetalheProdutoActivity extends AppCompatActivity {

    private ProdutoModelo produtoModelo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_produto);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        if (intent.hasExtra(ProdutoAdaptador.PRODUTO_PARCELABLE)) {
            produtoModelo = intent.getExtras().getParcelable(ProdutoAdaptador.PRODUTO_PARCELABLE);
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
