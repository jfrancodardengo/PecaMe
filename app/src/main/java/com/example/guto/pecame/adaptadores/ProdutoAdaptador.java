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

import com.example.guto.pecame.modelo.ProdutoModelo;
import com.example.guto.pecame.R;
import com.example.guto.pecame.ui.ListaProdutoActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ProdutoAdaptador extends RecyclerView.Adapter<ProdutoAdaptador.ProductViewHolder> {
    public static final String PRODUTO_PARCELABLE = "produto_parcelable";

    private List<ProdutoModelo> mProdutoModeloList;
    ProdutoModelo produtoModelo;
    Context context;

    public ProdutoAdaptador() {
    }

    public ProdutoAdaptador(List<ProdutoModelo> mProdutoModeloList) {
        this.mProdutoModeloList = mProdutoModeloList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View rootView = LayoutInflater.from(context).inflate(R.layout.produto_item, parent, false);

//        rootView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent it = new Intent(context,DetalheProdutoActivity.class);
//                //envio p/ detalhe do o objeto produto
//                it.putExtra(EDIT_MESA,produtoModelo);
//                context.startActivity(it);
//            }
//        });

        Intent it = new Intent(context, ListaProdutoActivity.class);
        //envio p/ lista activity o objeto produto
        it.putExtra(PRODUTO_PARCELABLE, produtoModelo);
        context.startActivity(it);

        return new ProductViewHolder(rootView);
    }


    @Override
    public void onBindViewHolder(@NonNull final ProductViewHolder holder, final int position) {
        produtoModelo = mProdutoModeloList.get(position);
        holder.textProduct.setText(produtoModelo.getmDescProduto());
        holder.textPrice.setText(produtoModelo.getmPreco());
        holder.editObservacao.setText(produtoModelo.getmObservacao());

        holder.checkItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.checkItem.isChecked()) {
                    //ao marcar o check, eu associo a posição do item ao objeto produto
                    produtoModelo.setmCodProduto((int) getItemId(position));
                }
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

        public ProductViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
