package com.example.appcompras;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class maac <main_activity> extends AppCompatActivity implements Asynchtask, AdapterView.OnItemClickListener, main, act, MainActivity {
    ListView listaarticulos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listaarticulos = (ListView) findViewById(R.id.listaarticulos);
        View header = getLayoutInflater().inflate(R.layout.header,null);
        listaarticulos.addHeaderView(header);
        Map<String, String> datos = new HashMap<>();
        WebService ws = new WebService("https://fakestoreapi.com/products",
                datos, main_activity.this, Main_Activity.this);
        ws.execute("GET");
        listaarticulos.setOnItemClickListener(this);
    }


    @Override
    public void processFinish(String result) throws JSONException {
        JSONArray jsonArray = new JSONArray(result);
        ArrayList<Revistas> listaarticulos = Revistas.JsonObjectsBuild(jsonArray);
        AdaptadorRevistas adaptadorUsuario = new AdaptadorRevistas();
        listaarticulos.iterator();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Revistas revista = (Revistas) adapterView.getItemAtPosition(position);
        String titulo;
        String image;
        String precio;
        String categoria;
        titulo= revista.getTitle().toString();
        image= revista.getImage().toString();
        precio= revista.getPrice().toString();
        categoria= revista.getCategory().toString();
        Bundle b = new Bundle();
        b.putString("NOMBRE", titulo);
        b.putString("IMAGEN", image);
        b.putString("PRECIO", precio);
        b.putString("CATEGORIA", categoria);
        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
        intent.putExtras(b);
        startActivity(intent);

    }
}