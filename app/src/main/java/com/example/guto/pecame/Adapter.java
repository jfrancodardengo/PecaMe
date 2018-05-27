package com.example.guto.pecame;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.guto.pecame.modelo.ProdutoModelo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by GUTO on 21/05/2018.
 */

public class Adapter extends ArrayAdapter<ProdutoModelo>{
    @BindView(R.id.text_product)
    TextView textProduct;
    @BindView(R.id.text_price)
    TextView textPrice;

    public Adapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    public Adapter(@NonNull Context context, int resource, @NonNull List<ProdutoModelo> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.produto_item, parent, false);
        }
        ButterKnife.bind(convertView);

        ProdutoModelo produtoModelo = getItem(position);

        textProduct.setText(produtoModelo.getmDescProduto());
        textPrice.setText(produtoModelo.getmPreco());

        return convertView;
    }
}
