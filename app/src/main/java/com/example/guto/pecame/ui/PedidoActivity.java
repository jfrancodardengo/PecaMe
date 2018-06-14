package com.example.guto.pecame.ui;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guto.pecame.R;
import com.example.guto.pecame.adaptadores.PedidoAdaptador;
import com.example.guto.pecame.adaptadores.ProdutoAdaptador;
import com.example.guto.pecame.fragmentos.PedidoFragment;
import com.example.guto.pecame.modelo.MesaModelo;
import com.example.guto.pecame.modelo.PedidoModelo;
import com.example.guto.pecame.modelo.ProdutoModelo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PedidoActivity extends AppCompatActivity {
    @BindView(R.id.text_numero_mesa)
    TextView textNumeroMesa;
    @BindView(R.id.text_valor_total)
    TextView textValorTotal;
    @BindView(R.id.floating_button_finalizar_pedido)
    FloatingActionButton buttonFinalizarPedido;

    Context context;
    private List<PedidoModelo> mPedidoModeloList;
    List<ProdutoModelo> selecionados;

    PedidoFragment pedidoFragment;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);

        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Intent intent = getIntent();
        Bundle informacoes = intent.getExtras();
        selecionados = informacoes.getParcelableArrayList("PRODUTOS_SELECIONADOS");
        textNumeroMesa.setText(informacoes.getString(EscolhaMesaActivity.EDIT_MESA));

        receberProdutos();

        pedidoFragment = new PedidoFragment();
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

    public void receberProdutos(){
        ProdutoModelo prod=null;
        StringBuffer responseText = new StringBuffer();
        responseText.append("Recebidos.\n");
        for (int i=0;i< selecionados.size();i++) {
            prod = selecionados.get(i);
//            mPedidoModeloList.add(new PedidoModelo(1,"",prod));
            responseText.append("\n" + prod.getmDescProduto().toString());
        }
        Toast.makeText(this,responseText,Toast.LENGTH_SHORT).show();
    }

}
