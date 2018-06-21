package com.example.guto.pecame.fragmentos;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.guto.pecame.R;
import com.example.guto.pecame.adaptadores.PedidoAdaptador;
import com.example.guto.pecame.modelo.PedidoModelo;
import com.example.guto.pecame.ui.PedidoActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class PedidoFragment extends Fragment {
    @BindView(R.id.recyclerPedido)
    RecyclerView recyclerView;

    private PedidoAdaptador mPedidoAdaptador;
    private List<PedidoModelo> mPedidoModeloList = new ArrayList<>();
    private PedidoActivity mPedidoActivity;

    public PedidoFragment(PedidoActivity pedidoActivity) {
        mPedidoActivity = pedidoActivity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_pedido, container, false);

        ButterKnife.bind(this,rootView);

        receberPedidos(mPedidoActivity.getPedidos());

        return rootView;
    }


    public void receberPedidos(List<PedidoModelo> pedidos){
        PedidoModelo pedido=null;
        for (int i=0;i< pedidos.size();i++) {
            pedido = pedidos.get(i);
            mPedidoModeloList.add(pedido);
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        mPedidoAdaptador = new PedidoAdaptador(mPedidoModeloList,mPedidoActivity);
        recyclerView.setAdapter(mPedidoAdaptador);
    }


}
