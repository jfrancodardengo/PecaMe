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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

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

}
