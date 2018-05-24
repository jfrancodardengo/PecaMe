package com.example.guto.pecame;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class DrinkFragment extends Fragment {
    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    private List<Product> productList = new ArrayList<>();
    private ProductAdapter productAdapter;

    public DrinkFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_drink, container, false);
        ButterKnife.bind(this,rootView);

        productAdapter = new ProductAdapter(productList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(productAdapter);

        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        productList.add(new Product("Refrigerante Coca Cola 600 ml","R$ 7,00"));
        productList.add(new Product("Refrigerante Guaraná Antartica 350 ml","R$ 5,00"));
        productList.add(new Product("Cerveja Long Neck Stella Artois 275 ml","R$ 7,00"));
        productList.add(new Product("Energetico Monster Energy 473 ml ","R$ 8,00"));
    }

}
