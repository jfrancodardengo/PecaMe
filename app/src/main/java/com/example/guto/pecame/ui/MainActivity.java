package com.example.guto.pecame.ui;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.guto.pecame.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.image_button_comanda)
    ImageButton imageButtonComanda;

    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReferenceFromUrl("gs://pecame-a5b91.appspot.com/promocao.json");
    File localFile;
    String json = "";
    String resultado="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        try {
            localFile = File.createTempFile("promocao", "json");
        } catch (IOException e) {
            e.printStackTrace();
        }

        new TarefaDownload().execute();

        executeButton(imageButtonComanda,EscolhaMesaActivity.class);
    }

    public class TarefaDownload extends AsyncTask<Void, Integer, String> {

        @Override
        public String doInBackground(Void... voids) {

            storageRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    if (taskSnapshot.getTask().isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "Arquivo baixado.", Toast.LENGTH_SHORT).show();

                        try {
                            JSONObject reader = new JSONObject(json);
                            String titulo = reader.getString("titulo");
                            String descricao = reader.getString("descricao");

                            resultado = titulo + "\n"+ descricao;


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    //                Toast.makeText(getApplicationContext(),"Arquivo sendo baixado",Toast.LENGTH_SHORT).show();
                }
            });

            return resultado;
        }

        @Override
        protected void onPreExecute() {
            //Codigo
        }

        @Override
        protected void onPostExecute(String s) {
            Log.e("RESULTADO: \n",s);
//            Toast.makeText(getApplicationContext(),"PROMOÇÃO \n" + s,Toast.LENGTH_SHORT).show();
        }
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
