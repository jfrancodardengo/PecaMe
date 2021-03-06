package com.example.guto.pecame.ui;


import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guto.pecame.utils.PedidoCallback;
import com.example.guto.pecame.R;
import com.example.guto.pecame.fragmentos.PedidoFragment;
import com.example.guto.pecame.modelo.PedidoModelo;
import com.example.guto.pecame.modelo.ProdutoModelo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PedidoActivity extends AppCompatActivity implements PedidoCallback {
    @BindView(R.id.text_numero_mesa)
    TextView textNumeroMesa;
    @BindView(R.id.text_valor_total)
    TextView textValorTotal;
    @BindView(R.id.floating_button_finalizar_pedido)
    FloatingActionButton buttonFinalizarPedido;

    private final List<PedidoModelo> mPedidoModeloList = new ArrayList<>();
    private List<ProdutoModelo> mSelecionados;
    private float valorTotal = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);

        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        Bundle informacoes = intent.getExtras();
        mSelecionados = informacoes.getParcelableArrayList("PRODUTOS_SELECIONADOS");

        valorTotal = informacoes.getFloat("VALOR_TOTAL");
        textValorTotal.setText(String.valueOf(valorTotal));
        setListaPedido(mPedidoModeloList);

        textNumeroMesa.setText(informacoes.getString(EscolhaMesaActivity.EDIT_MESA));

        PedidoFragment mPedidoFragment = new PedidoFragment(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, mPedidoFragment).commit();

        buttonFinalizarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Pedido enviado para produção.",Toast.LENGTH_SHORT).show();
                mPedidoModeloList.clear();
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

    @Override
    public void onPedidoRemovido(int codproduto) {
        PedidoModelo pedido=null;
        for (int i=0;i< mPedidoModeloList.size();i++) {
            pedido = mPedidoModeloList.get(i);
            if(codproduto == pedido.getmProduto().getmCodProduto()){
                mPedidoModeloList.remove(codproduto);
            }
        }
    }

    @Override
    public void setListaPedido(List<PedidoModelo> pedidos) {
        for (ProdutoModelo produto : mSelecionados) {
            PedidoModelo pedido = new PedidoModelo(0,1,0, produto);
            pedidos.add(pedido);
        }
    }

    @Override
    public List<PedidoModelo> getPedidos() {
        return mPedidoModeloList;
    }

    @Override
    public int incrementaQuantidade(int codigo,int quantidade) {
        PedidoModelo pedido=null;
        for (int i=0;i< mPedidoModeloList.size();i++) {
            pedido = mPedidoModeloList.get(i);
            if(codigo == pedido.getmProduto().getmCodProduto()){
                quantidade += 1;
                pedido.setmQuantidade(quantidade);
            }
        }
//        ped.setmQuantidade(quantidade);
        return quantidade;
    }

    @Override
    public int decrementaQuantidade(int codigo,int quantidade) {
        PedidoModelo pedido=null;
        for (int i=0;i< mPedidoModeloList.size();i++) {
            pedido = mPedidoModeloList.get(i);
            if(codigo == pedido.getmProduto().getmCodProduto() && quantidade > 0){
                quantidade -= 1;
                pedido.setmQuantidade(quantidade);
            }
        }

//        if(quantidade > 0) {
//            quantidade -= 1;
//        }
        return quantidade;
    }

    @Override
    public void totalPedido(int codigo, float valorItem,int id) {
        switch (id){
            case R.id.button_aumentar:
                valorTotal += valorItem;
                textValorTotal.setText(String.valueOf(valorTotal));
                break;
            case R.id.button_diminuir:
                valorTotal -= valorItem;
                textValorTotal.setText(String.valueOf(valorTotal));
                break;
            case R.id.button_remover:
                valorTotal -= valorItem;
                textValorTotal.setText(String.valueOf(valorTotal));
                break;
        }

    }
}
