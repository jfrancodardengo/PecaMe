package com.example.guto.pecame.fragmentos;


import android.annotation.SuppressLint;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.guto.pecame.adaptadores.ProdutoAdaptador;
import com.example.guto.pecame.modelo.ProdutoModelo;
import com.example.guto.pecame.R;
import com.example.guto.pecame.ui.ListaProdutoActivity;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class HamburguerFragment extends Fragment{
    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    private final List<ProdutoModelo> mProdutoModeloList = new ArrayList<>();
    private ProdutoAdaptador mProdutoAdaptador;
    private ListaProdutoActivity mActivity;


    final FirebaseFirestore db = FirebaseFirestore.getInstance();

    public HamburguerFragment() {
    }

    public HamburguerFragment(ListaProdutoActivity listaProdutoActivity) {
        // Required empty public constructor
        this.mActivity = listaProdutoActivity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_hamburguer, container, false);
        ButterKnife.bind(this,rootView);

        ReadProduct();

        return rootView;
    }

    private void ReadProduct() {
        db.collection("Produtos").document("Grupo").collection("comidas").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
                for(DocumentSnapshot doc : documentSnapshots){
                    mProdutoModeloList.add(new ProdutoModelo(doc.getString("descricao"),doc.getString("preco"),doc.getString("observacao")));
                }

                mProdutoAdaptador = new ProdutoAdaptador(mProdutoModeloList,mActivity);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                recyclerView.setHasFixedSize(true);
                recyclerView.setAdapter(mProdutoAdaptador);
            }
        });
    }

}
