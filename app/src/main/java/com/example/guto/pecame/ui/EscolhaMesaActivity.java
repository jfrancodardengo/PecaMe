package com.example.guto.pecame.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.guto.pecame.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EscolhaMesaActivity extends AppCompatActivity {
    public static final String EDIT_MESA = "edit_mesa";

    @BindView(R.id.edit_mesa) EditText editMesa;
    @BindView(R.id.button_abrir_mesa) Button buttonAbrirMesa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escolha_mesa);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        buttonAbrirMesa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundleInformacoes = new Bundle();

                //envio p/ lista activity o objeto mesa
                Intent intent = new Intent(getApplicationContext(),ListaProdutoActivity.class);
                bundleInformacoes.putString(EDIT_MESA,editMesa.getText().toString());
                intent.putExtras(bundleInformacoes);
                startActivity(intent);
            }
        });
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
