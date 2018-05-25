package com.example.guto.pecame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.imageButtonComanda)
    ImageButton imageButtonComanda;
    @BindView(R.id.imageButtonMesa)
    ImageButton imageButtonMesa;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        insertBD();

        executeButton(imageButtonComanda,ChooseTableActivity.class);
        executeButton(imageButtonMesa,OrderActivity.class);
    }

    public void executeButton(View view, final Class classe){
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),classe);
                startActivity(intent);
            }
        });
    }

    public void insertBD(){
        myRef.child("Product").child("hamburguers").push().setValue(new Product("Mister","R$ 15,00"));
        myRef.child("Product").child("hamburguers").push().setValue(new Product("Brutus","R$ 22,00"));
        myRef.child("Product").child("hamburguers").push().setValue(new Product("Classic","R$ 11,00"));
        myRef.child("Product").child("hamburguers").push().setValue(new Product("Levíssimo","R$ 17,00"));
        myRef.child("Product").child("hamburguers").push().setValue(new Product("Espetacular","R$ 17,00"));
        myRef.child("Product").child("hamburguers").push().setValue(new Product("Modesto","R$ 13,00"));
        myRef.child("Product").child("hamburguers").push().setValue(new Product("Fabuloso","R$ 15,00"));
        myRef.child("Product").child("hamburguers").push().setValue(new Product("Frescão","R$ 14,00"));

        myRef.child("Product").child("drinks").push().setValue(new Product("Refrigerante Coca Cola 600 ml","R$ 7,00"));
        myRef.child("Product").child("drinks").push().setValue(new Product("Refrigerante Guaraná Antartica 350 ml","R$ 5,00"));
        myRef.child("Product").child("drinks").push().setValue(new Product("Cerveja Long Neck Stella Artois 275 ml","R$ 7,00"));
        myRef.child("Product").child("drinks").push().setValue(new Product("Energetico Monster Energy 473 ml ","R$ 8,00"));

        myRef.child("Product").child("desserts").push().setValue(new Product("Americano","R$ 15,00"));
        myRef.child("Product").child("desserts").push().setValue(new Product("Brownie","R$ 17,00"));
        myRef.child("Product").child("desserts").push().setValue(new Product("Picolé brigadeiro","R$ 7,00"));
    }

}
