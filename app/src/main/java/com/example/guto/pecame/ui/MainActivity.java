package com.example.guto.pecame.ui;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.guto.pecame.R;
import com.example.guto.pecame.utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.image_button_comanda)
    ImageButton imageButtonComanda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        new TarefaDownload().execute();

        executeButton(imageButtonComanda, EscolhaMesaActivity.class);
    }

    final class TarefaDownload extends AsyncTask<Void, Integer, String> {
        String resultado = "";
        private final String url = "https://firebasestorage.googleapis.com/v0/b/pecame-a5b91.appspot.com/o/promocao.json?alt=media&token=23c34ea6-03dc-466c-8f1d-bba5b94dcf5a";

        @Override
        public String doInBackground(Void... voids) {
            return download(url);
        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                JSONObject reader = new JSONObject(s);
                JSONObject jsonObject = reader.getJSONObject("promocao");
                String titulo = jsonObject.getString("titulo");
                String descricao = jsonObject.getString("descricao");

                resultado = titulo + "\n" + descricao;

            } catch (JSONException e) {
                e.printStackTrace();
            }
            Toast.makeText(MainActivity.this, "PROMOÇÃO \n" + resultado, Toast.LENGTH_SHORT).show();
        }
    }

    private void executeButton(View view, final Class classe) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), classe);
                startActivity(intent);
            }
        });
    }

    private static String download(String url) {
        Object connection = Utils.connect(url);
        if (connection.toString().startsWith("Error")) {
            return connection.toString();
        }
        try {
            HttpURLConnection con = (HttpURLConnection) connection;
            if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream is = new BufferedInputStream(con.getInputStream());
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String line;
                StringBuilder jsonData = new StringBuilder();

                while ((line = br.readLine()) != null) {
                    jsonData.append(line).append("\n");
                }
                br.close();
                is.close();
                return jsonData.toString();
            } else {
                return String.format("Error %s", con.getResponseMessage());
            }
        } catch (IOException e) {
            e.printStackTrace();
            return String.format("Error %s", e.getMessage());
        }
    }

}
