package com.example.guto.pecame.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.guto.pecame.R;
import com.example.guto.pecame.modelo.ProdutoModelo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.image_button_comanda)
    ImageButton imageButtonComanda;
    @BindView(R.id.image_button_mesa)
    ImageButton imageButtonMesa;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

//        insertBD();

        executeButton(imageButtonComanda,EscolhaMesaActivity.class);
        executeButton(imageButtonMesa,PedidoActivity.class);
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
        myRef.child("ProdutoModelo").child("hamburguers").push().setValue(new ProdutoModelo("Mister","R$ 15,00"));
        myRef.child("ProdutoModelo").child("hamburguers").push().setValue(new ProdutoModelo("Brutus","R$ 22,00"));
        myRef.child("ProdutoModelo").child("hamburguers").push().setValue(new ProdutoModelo("Classic","R$ 11,00"));
        myRef.child("ProdutoModelo").child("hamburguers").push().setValue(new ProdutoModelo("Levíssimo","R$ 17,00"));
        myRef.child("ProdutoModelo").child("hamburguers").push().setValue(new ProdutoModelo("Espetacular","R$ 17,00"));
        myRef.child("ProdutoModelo").child("hamburguers").push().setValue(new ProdutoModelo("Modesto","R$ 13,00"));
        myRef.child("ProdutoModelo").child("hamburguers").push().setValue(new ProdutoModelo("Fabuloso","R$ 15,00"));
        myRef.child("ProdutoModelo").child("hamburguers").push().setValue(new ProdutoModelo("Frescão","R$ 14,00"));

        myRef.child("ProdutoModelo").child("drinks").push().setValue(new ProdutoModelo("Refrigerante Coca Cola 600 ml","R$ 7,00"));
        myRef.child("ProdutoModelo").child("drinks").push().setValue(new ProdutoModelo("Refrigerante Guaraná Antartica 350 ml","R$ 5,00"));
        myRef.child("ProdutoModelo").child("drinks").push().setValue(new ProdutoModelo("Cerveja Long Neck Stella Artois 275 ml","R$ 7,00"));
        myRef.child("ProdutoModelo").child("drinks").push().setValue(new ProdutoModelo("Energetico Monster Energy 473 ml ","R$ 8,00"));

        myRef.child("ProdutoModelo").child("desserts").push().setValue(new ProdutoModelo("Americano","R$ 15,00"));
        myRef.child("ProdutoModelo").child("desserts").push().setValue(new ProdutoModelo("Brownie","R$ 17,00"));
        myRef.child("ProdutoModelo").child("desserts").push().setValue(new ProdutoModelo("Picolé brigadeiro","R$ 7,00"));
    }

}
