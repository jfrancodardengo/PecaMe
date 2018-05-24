package com.example.guto.pecame;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by GUTO on 21/05/2018.
 */

public class Adapter extends ArrayAdapter<Product>{
    @BindView(R.id.text_product)
    TextView textProduct;
    @BindView(R.id.text_price)
    TextView textPrice;

    public Adapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    public Adapter(@NonNull Context context, int resource, @NonNull List<Product> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.product_item, parent, false);
        }
        ButterKnife.bind(convertView);

        Product product = getItem(position);

        textProduct.setText(product.getDescription());
        textPrice.setText(product.getPrice());

        return convertView;
    }
}
