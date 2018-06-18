package com.example.guto.pecame.adaptadores;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guto.pecame.utils.PedidoCallback;
import com.example.guto.pecame.R;
import com.example.guto.pecame.modelo.PedidoModelo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PedidoAdaptador extends RecyclerView.Adapter<PedidoAdaptador.ProductViewHolder> {
    private Context mContext;
    private List<PedidoModelo> mPedidoModeloList;
    private PedidoCallback mListener;
    private PedidoModelo mPedidoModelo;
    private int quantidade = 0;

    public PedidoAdaptador(List<PedidoModelo> pedidos, PedidoCallback listener) {
        mPedidoModeloList = pedidos;
        mListener = listener;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View rootView = LayoutInflater.from(mContext).inflate(R.layout.produto_item_pedido,parent,false);

        return new ProductViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ProductViewHolder holder, final int position) {
        mPedidoModelo = mPedidoModeloList.get(position);

        holder.textProduct.setText(mPedidoModelo.getmProduto().getmDescProduto());
        holder.textPrice.setText(mPedidoModelo.getmProduto().getmPreco());
        holder.textQuantity.setText(String.valueOf(mPedidoModelo.getmQuantidade()));
        holder.editObservacao.setText(mPedidoModelo.getmProduto().getmObservacao());

        holder.buttonAumentar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantidade = mListener.incrementaQuantidade(mPedidoModelo.getmProduto().getmCodProduto(),Integer.parseInt(holder.textQuantity.getText().toString()));
                holder.displayQuantity(quantidade);
                mListener.totalPedido(mPedidoModelo.getmProduto().getmCodProduto(),Float.parseFloat(holder.textPrice.getText().toString()),holder.buttonAumentar.getId());
            }
        });

        holder.buttonDiminuir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantidade = mListener.decrementaQuantidade(mPedidoModelo.getmProduto().getmCodProduto(),Integer.parseInt(holder.textQuantity.getText().toString()));
                holder.displayQuantity(quantidade);
                mListener.totalPedido(mPedidoModelo.getmProduto().getmCodProduto(),Float.parseFloat(holder.textPrice.getText().toString()),holder.buttonDiminuir.getId());
            }
        });

        holder.buttonRemover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float totalItem=0;
                float preco = 0;
                int quantity;
                if ( null != mPedidoModeloList) {
                    mPedidoModeloList.remove(mPedidoModelo.getmProduto().getmCodProduto());
                    preco = Float.parseFloat(holder.textPrice.getText().toString());
                    quantity = Integer.parseInt(holder.textQuantity.getText().toString());
                    totalItem = quantity * preco;
                    mListener.totalPedido(mPedidoModelo.getmProduto().getmCodProduto(),totalItem,holder.buttonRemover.getId());
                    notifyDataSetChanged();
                }
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

        private void displayQuantity(int numberOfTeas) {
            textQuantity.setText(String.valueOf(numberOfTeas));
        }
    }
}
