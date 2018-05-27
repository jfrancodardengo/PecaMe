package com.example.guto.pecame.fragmentos;


import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.guto.pecame.adaptadores.ProdutoAdaptador;
import com.example.guto.pecame.modelo.ProdutoModelo;
import com.example.guto.pecame.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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

    private List<ProdutoModelo> produtoModeloList = new ArrayList<>();
    ProdutoAdaptador produtoAdaptador;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();


    public HamburguerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_hamburguer, container, false);
        ButterKnife.bind(this,rootView);

//        insertBD();

        valueListener();
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
//        insertBD();
    }

//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        insertBD();
//    }

    public void valueListener(){

        myRef.child("Produto").child("grupos").child("comidas").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    Log.v("Snapshot: ",dataSnapshot1.toString());
                    ProdutoModelo value = dataSnapshot1.getValue(ProdutoModelo.class);
                    Log.v("ProdutoModelo: ",value.toString());
                    produtoModeloList.add(value);
                }

                produtoAdaptador = new ProdutoAdaptador(produtoModeloList);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                recyclerView.setAdapter(produtoAdaptador);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void insertBD(){
        myRef.child("ProdutoModelo").child("hamburguers").push().setValue(new ProdutoModelo("Mister","R$ 15,00"));
        myRef.child("ProdutoModelo").child("hamburguers").push().setValue(new ProdutoModelo("Brutus","R$ 22,00"));
        myRef.child("ProdutoModelo").child("hamburguers").push().setValue(new ProdutoModelo("Classic","R$ 11,00"));
        myRef.child("ProdutoModelo").child("hamburguers").push().setValue(new ProdutoModelo("Levíssimo","R$ 17,00"));
        myRef.child("ProdutoModelo").child("hamburguers").push().setValue(new ProdutoModelo("Espetacular","R$ 17,00"));
        myRef.child("ProdutoModelo").child("hamburguers").push().setValue(new ProdutoModelo("Modesto","R$ 13,00"));
        myRef.child("ProdutoModelo").child("hamburguers").push().setValue(new ProdutoModelo("Fabuloso","R$ 15,00"));
        myRef.child("ProdutoModelo").child("hamburguers").push().setValue(new ProdutoModelo("Frescão","R$ 14,00"));
    }
}
