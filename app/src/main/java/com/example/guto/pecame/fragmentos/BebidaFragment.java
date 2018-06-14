package com.example.guto.pecame.fragmentos;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.guto.pecame.AdapterCallback;
import com.example.guto.pecame.adaptadores.ProdutoAdaptador;
import com.example.guto.pecame.modelo.ProdutoModelo;
import com.example.guto.pecame.R;
import com.example.guto.pecame.ui.ListaProdutoActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
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
public class BebidaFragment extends Fragment{
    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    private List<ProdutoModelo> produtoModeloList = new ArrayList<>();
    private ProdutoAdaptador produtoAdaptador;
    private ListaProdutoActivity mActivity;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @SuppressLint("ValidFragment")
    public BebidaFragment(ListaProdutoActivity listaProdutoActivity) {
        // Required empty public constructor
        this.mActivity = listaProdutoActivity;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_bebida, container, false);
        ButterKnife.bind(this,rootView);

        ReadProduct();

        return rootView;
    }

    private void ReadProduct() {
        db.collection("Produtos").document("Grupo").collection("bebidas").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
                for(DocumentSnapshot doc : documentSnapshots){
                    produtoModeloList.add(new ProdutoModelo(doc.getString("descricao"),doc.getString("preco")));
                }

                produtoAdaptador = new ProdutoAdaptador(produtoModeloList,mActivity);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                recyclerView.setHasFixedSize(true);
                recyclerView.setAdapter(produtoAdaptador);
            }
        });
    }

}
