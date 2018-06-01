package com.example.guto.pecame.fragmentos;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.guto.pecame.R;
import com.example.guto.pecame.adaptadores.PedidoAdaptador;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class PedidoFragment extends Fragment {
    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    PedidoAdaptador pedidoAdaptador;


    public PedidoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_pedido, container, false);
        ButterKnife.bind(rootView);

        pedidoAdaptador = new PedidoAdaptador();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(pedidoAdaptador);

        return rootView;
    }

}
