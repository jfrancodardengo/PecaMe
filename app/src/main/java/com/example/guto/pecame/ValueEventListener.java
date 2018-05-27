package com.example.guto.pecame;

import android.util.Log;

import com.example.guto.pecame.modelo.ProdutoModelo;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

/**
 * Created by GUTO on 21/05/2018.
 */

public class ValueEventListener implements ChildEventListener{

    @Override
    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//        for(DataSnapshot d : dataSnapshot.getChildren()){
//            ProdutoModelo p = d.getValue(ProdutoModelo.class);
//
//            Log.i("log","Descrição: "+p.getmDescProduto());
//            Log.i("log","Preço: "+p.getmPreco());
//        }
    }

    @Override
    public void onChildChanged(DataSnapshot dataSnapshot, String s) {
        for(DataSnapshot d : dataSnapshot.getChildren()){
            ProdutoModelo p = d.getValue(ProdutoModelo.class);

            Log.i("log","Descrição: "+p.getmDescProduto());
            Log.i("log","Preço: "+p.getmPreco());
        }
    }

    @Override
    public void onChildRemoved(DataSnapshot dataSnapshot) {

    }

    @Override
    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }
}
