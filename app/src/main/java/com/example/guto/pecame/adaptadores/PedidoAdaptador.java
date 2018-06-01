package com.example.guto.pecame.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guto.pecame.R;
import com.example.guto.pecame.modelo.PedidoModelo;
import com.example.guto.pecame.modelo.ProdutoModelo;
import com.example.guto.pecame.ui.ListaProdutoActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PedidoAdaptador extends RecyclerView.Adapter<PedidoAdaptador.ProductViewHolder> {
    Context context;
//    private List<ProdutoModelo> mProdutoModeloList;
    private ProdutoModelo mProdutoModelo;
    private List<PedidoModelo> mPedidoModeloList;

    public PedidoAdaptador() {
    }

    public PedidoAdaptador(List<PedidoModelo> mPedidoModeloList, ProdutoModelo mProdutoModelo) {
        this.mPedidoModeloList = mPedidoModeloList;
        this.mProdutoModelo = mProdutoModelo;
    }

//    public PedidoAdaptador(List<PedidoModelo> mPedidoModeloList) {
//        this.mPedidoModeloList = mPedidoModeloList;
//    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View rootView = LayoutInflater.from(context).inflate(R.layout.produto_item_pedido,parent,false);
        return new ProductViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ProductViewHolder holder, int position) {
        final PedidoModelo pedidoModelo = mPedidoModeloList.get(position);
//        ProdutoModelo produtoModelo = mProdutoModeloList.get(position);

        holder.textProduct.setText(mProdutoModelo.getmDescProduto());
        holder.textPrice.setText(mProdutoModelo.getmPreco());
        holder.textQuantity.setText(String.valueOf(pedidoModelo.getmQuantidade()));
        holder.editObservacao.setText(mProdutoModelo.getmObservacao());

        holder.buttonAumentar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"Aumentar um. ",Toast.LENGTH_SHORT).show();
            }
        });

        holder.buttonDiminuir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"Dimuir um. ",Toast.LENGTH_SHORT).show();
            }
        });

        holder.buttonRemover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"Remover item. ",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mPedidoModeloList.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.text_product)
        TextView textProduct;
        @BindView(R.id.text_price)
        TextView textPrice;
        @BindView(R.id.text_quantidade)
        TextView textQuantity;
        @BindView(R.id.edit_observacao)
        EditText editObservacao;
        @BindView(R.id.button_diminuir)
        Button buttonDiminuir;
        @BindView(R.id.button_aumentar)
        Button buttonAumentar;
        @BindView(R.id.button_remover)
        Button buttonRemover;

        public ProductViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
