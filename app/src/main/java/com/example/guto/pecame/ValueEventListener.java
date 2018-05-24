package com.example.guto.pecame;

import android.util.Log;

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
//            Product p = d.getValue(Product.class);
//
//            Log.i("log","Descrição: "+p.getDescription());
//            Log.i("log","Preço: "+p.getPrice());
//        }
    }

    @Override
    public void onChildChanged(DataSnapshot dataSnapshot, String s) {
        for(DataSnapshot d : dataSnapshot.getChildren()){
            Product p = d.getValue(Product.class);

            Log.i("log","Descrição: "+p.getDescription());
            Log.i("log","Preço: "+p.getPrice());
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
