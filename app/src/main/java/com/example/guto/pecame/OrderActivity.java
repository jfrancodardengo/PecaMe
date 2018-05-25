package com.example.guto.pecame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderActivity extends AppCompatActivity {
    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    private List<Product> productList = new ArrayList<>();
    private OrderAdapter orderAdapter;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        orderAdapter = new OrderAdapter(productList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(orderAdapter);

        productList.add(new Product("Burguer Zumbi dos Palmares","R$ 12,00",1));
        productList.add(new Product("Burguer Barack Obama","R$ 12,00",1));
        productList.add(new Product("Burguer Michael Jordan","R$ 16,00",1));
        productList.add(new Product("Burguer Bob Marley","R$ 18,00",1));
        productList.add(new Product("Burguer Usain Bolt","R$ 18,00",1));
        productList.add(new Product("Burguer Usain Bolt","R$ 22,00",1));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
