package com.example.guto.pecame;


import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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

    private List<Product> productList = new ArrayList<>();
    ProductAdapter productAdapter;

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

        insertBD();

        valueListener();

//        productAdapter = new ProductAdapter(productList);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        recyclerView.setAdapter(productAdapter);

        return rootView;
    }

    public void valueListener(){
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    Log.v("Snapshot: ",dataSnapshot1.toString());
//                    Toast.makeText(getContext(),"Snapshot: "+dataSnapshot1.toString(),Toast.LENGTH_SHORT).show();

                    Product value = dataSnapshot1.getValue(Product.class);

                    Log.v("Product: ",value.toString());
//                    Toast.makeText(getContext(),"Product: "+value.toString(),Toast.LENGTH_LONG).show();
                    productList.add(value);
                }

                productAdapter = new ProductAdapter(productList);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                recyclerView.setAdapter(productAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void insertBD(){
        myRef.child("Product").push().setValue(new Product("Burguer Zumbi dos Palmares","R$ 12,00"));
        myRef.child("Product").push().setValue(new Product("Burguer Michael Jordan","R$ 16,00"));
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        productList.add(new Product("Burguer Zumbi dos Palmares","R$ 12,00"));
//        productList.add(new Product("Burguer Barack Obama","R$ 12,00"));
//        productList.add(new Product("Burguer Michael Jordan","R$ 16,00"));
//        productList.add(new Product("Burguer Bob Marley","R$ 18,00"));
//        productList.add(new Product("Burguer Usain Bolt","R$ 18,00"));
//        productList.add(new Product("Burguer Usain Bolt","R$ 22,00"));

    }
}
