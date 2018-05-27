package com.example.guto.pecame.ui;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.guto.pecame.R;
import com.example.guto.pecame.modelo.MesaModelo;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EscolhaMesaActivity extends AppCompatActivity {
    public static final String EDIT_MESA = "edit_mesa";

    @BindView(R.id.edit_mesa) EditText editMesa;
    @BindView(R.id.button_abrir_mesa) Button buttonAbrirMesa;

    public MesaModelo mesaModelo = new MesaModelo();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escolha_mesa);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        buttonAbrirMesa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ListaProdutoActivity.class);
                //envio p/ lista activity o objeto mesa
                int codMesa = Integer.parseInt(editMesa.getText().toString());

                Log.v("Codigo mesa: ",String.valueOf(codMesa));

//                mesaModelo = new MesaModelo(codMesa,true);
                mesaModelo.setmCodMesa(Integer.parseInt(editMesa.getText().toString()));
                mesaModelo.setmStatus(true);
                intent.putExtra(EDIT_MESA,mesaModelo);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        showMessage();
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

    public void showMessage(){
        Toast.makeText(this,"Mesa "+ editMesa.getText().toString() + " aberta.",Toast.LENGTH_LONG).show();
    }

}
