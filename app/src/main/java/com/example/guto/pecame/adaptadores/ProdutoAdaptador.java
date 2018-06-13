package com.example.guto.pecame.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guto.pecame.AdapterCallback;
import com.example.guto.pecame.modelo.ProdutoModelo;
import com.example.guto.pecame.R;
import com.example.guto.pecame.ui.ListaProdutoActivity;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ProdutoAdaptador extends RecyclerView.Adapter<ProdutoAdaptador.ProductViewHolder> {
    public static final String PRODUTO_PARCELABLE = "produto_parcelable";

    List<ProdutoModelo> selecionados = new ArrayList<ProdutoModelo>();
    List<ProdutoModelo> mProdutoModeloList;
    ProdutoModelo produtoModelo;
    Context context;
    private AdapterCallback mAdapterCallback;


    public ProdutoAdaptador(List<ProdutoModelo> mProdutoModeloList,AdapterCallback adapterCallback) {
        this.mProdutoModeloList = mProdutoModeloList;
        this.mAdapterCallback = adapterCallback;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View rootView = LayoutInflater.from(context).inflate(R.layout.produto_item, parent, false);
        return new ProductViewHolder(rootView);
    }


    @Override
    public void onBindViewHolder(@NonNull final ProductViewHolder holder, final int position) {
        produtoModelo = mProdutoModeloList.get(position);
        holder.textProduct.setText(produtoModelo.getmDescProduto());
        holder.textPrice.setText(produtoModelo.getmPreco());
        holder.editObservacao.setText(produtoModelo.getmObservacao());

        holder.checkItem.setChecked(produtoModelo.isSelected());
        holder.checkItem.setTag(produtoModelo);

        holder.checkItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox cb = (CheckBox) v;
                ProdutoModelo produto = (ProdutoModelo) cb.getTag();
                produto.setSelected(cb.isChecked());
                mAdapterCallback.onCheckItemCallback(produto);
                if (cb.isChecked()) {
                    //adiciona uma lista de selecionados
                    selecionados.add(produto);
//                    Toast.makeText(context,"Item: " + position,Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mProdutoModeloList.size();
    }

    public Object getItem(int posicao) {
        // TODO Auto-generated method stub
        return mProdutoModeloList.get(posicao);
    }

    public ProdutoModelo getProduto(){
        return this.produtoModelo;
    }

    public List<ProdutoModelo> getProdutos() {
        return this.selecionados;
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.text_product)
        TextView textProduct;
        @BindView(R.id.text_price)
        TextView textPrice;
        @BindView(R.id.check_item)
        CheckBox checkItem;
        @BindView(R.id.edit_observacao)
        EditText editObservacao;

        public ProductViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
