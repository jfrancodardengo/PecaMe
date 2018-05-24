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
public class HamburguerFragment extends Fragment {
    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    private List<Product> productList = new ArrayList<>();
    ProductAdapter productAdapter;

    public HamburguerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_hamburguer, container, false);
        ButterKnife.bind(this,rootView);

        productAdapter = new ProductAdapter(productList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(productAdapter);

        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        productList.add(new Product("Burguer Zumbi dos Palmares","R$ 12,00"));
        productList.add(new Product("Burguer Barack Obama","R$ 12,00"));
        productList.add(new Product("Burguer Michael Jordan","R$ 16,00"));
        productList.add(new Product("Burguer Bob Marley","R$ 18,00"));
        productList.add(new Product("Burguer Usain Bolt","R$ 18,00"));
        productList.add(new Product("Burguer Usain Bolt","R$ 22,00"));

    }
}
