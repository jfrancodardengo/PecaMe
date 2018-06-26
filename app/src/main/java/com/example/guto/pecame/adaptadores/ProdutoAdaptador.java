package com.example.guto.pecame.adaptadores;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.guto.pecame.utils.AdapterCallback;
import com.example.guto.pecame.modelo.ProdutoModelo;
import com.example.guto.pecame.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ProdutoAdaptador extends RecyclerView.Adapter<ProdutoAdaptador.ProductViewHolder> {

    private final List<ProdutoModelo> mProdutoModeloList;
    private final AdapterCallback mAdapterCallback;

    public ProdutoAdaptador(List<ProdutoModelo> mProdutoModeloList, AdapterCallback adapterCallback) {
        this.mProdutoModeloList = mProdutoModeloList;
        this.mAdapterCallback = adapterCallback;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context mContext = parent.getContext();
        View rootView = LayoutInflater.from(mContext).inflate(R.layout.produto_item, parent, false);
        return new ProductViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ProductViewHolder holder, final int position) {
        ProdutoModelo mProdutoModelo = mProdutoModeloList.get(position);
        holder.textProduct.setText(mProdutoModelo.getmDescProduto());
        holder.textPrice.setText(mProdutoModelo.getmPreco());
        holder.editObservacao.setText(mProdutoModelo.getmObservacao());

        mProdutoModelo.setmCodProduto(position);

        holder.checkItem.setChecked(mProdutoModelo.isSelected());
        holder.checkItem.setTag(mProdutoModelo);

        holder.checkItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox cb = (CheckBox) v;
                ProdutoModelo produto = (ProdutoModelo) cb.getTag();
                produto.setSelected(cb.isChecked());
                mAdapterCallback.onCheckItemCallback(produto,cb.isChecked());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mProdutoModeloList.size();
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

        ProductViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
