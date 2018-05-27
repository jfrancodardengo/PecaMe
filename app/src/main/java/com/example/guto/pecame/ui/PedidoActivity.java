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
    @BindView(R.id.recycler)
    RecyclerView recyclerView;
    @BindView(R.id.text_numero_mesa)
    TextView textNumeroMesa;
    @BindView(R.id.text_valor_total)
    TextView textValorTotal;
    @BindView(R.id.floating_button_finalizar_pedido)
    FloatingActionButton buttonFinalizarPedido;

    Context context;
    private List<PedidoModelo> mPedidoModeloList;
    private PedidoAdaptador pedidoAdaptador;
    private ProdutoModelo mProdutoModelo;
    private MesaModelo mesa;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);

        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent it = getIntent();
        if (it.hasExtra(ProdutoAdaptador.PRODUTO_PARCELABLE)) {
            mProdutoModelo = it.getExtras().getParcelable(ProdutoAdaptador.PRODUTO_PARCELABLE);
        }

        if (it.hasExtra(EscolhaMesaActivity.EDIT_MESA)) {
            mesa = it.getExtras().getParcelable(EscolhaMesaActivity.EDIT_MESA);
        }

        textNumeroMesa.setText(mesa.getmCodMesa());

        buttonFinalizarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"Finalizado o pedido e enviado para a produção.",Toast.LENGTH_LONG).show();
                mesa.setmStatus(false);
            }
        });

        pedidoAdaptador = new PedidoAdaptador(mPedidoModeloList,mProdutoModelo);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(pedidoAdaptador);

//        produtoModeloList.add(new ProdutoModelo("Burguer Zumbi dos Palmares","R$ 12,00",1));
//        produtoModeloList.add(new ProdutoModelo("Burguer Barack Obama","R$ 12,00",1));
//        produtoModeloList.add(new ProdutoModelo("Burguer Michael Jordan","R$ 16,00",1));
//        produtoModeloList.add(new ProdutoModelo("Burguer Bob Marley","R$ 18,00",1));
//        produtoModeloList.add(new ProdutoModelo("Burguer Usain Bolt","R$ 18,00",1));
//        produtoModeloList.add(new ProdutoModelo("Burguer Usain Bolt","R$ 22,00",1));
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
