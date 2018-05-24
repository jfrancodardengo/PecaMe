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
public class DessertFragment extends Fragment {
    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    private List<Product> productList = new ArrayList<>();
    private ProductAdapter productAdapter;

    public DessertFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_dessert, container, false);
        ButterKnife.bind(this,rootView);

        productAdapter = new ProductAdapter(productList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(productAdapter);

        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        productList.add(new Product("Negodonn Americano","R$ 15,00"));
        productList.add(new Product("Brownie Ray Charles","R$ 17,00"));
        productList.add(new Product("Picolé birgadeiro 75 g","R$ 4,00"));
    }

}
