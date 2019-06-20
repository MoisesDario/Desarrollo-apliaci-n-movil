package com.example.listas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView simpleList;
    String countryList[] = {"India","China","Australia","Rusia","Nueva Zelanda"};
    public static Adapter adaptador = null;
    static ArrayList<String> lista = null;
    public static Button btnEditar1;
    public static Button btnAgregar;
    public static TextView txtTexto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAgregar = findViewById(R.id.btnAgregar);
        txtTexto = findViewById(R.id.txtTexto);
        btnEditar1 = findViewById(R.id.btnEditar1);

        simpleList = findViewById(R.id.simpleListView);
        lista = new ArrayList<String>(Arrays.asList(countryList));


        adaptador = new Adapter(this, lista);

       /*
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(MainActivity.this, R.layout.activity_listview, R.id.textView,lista);

       arrayAdapter.add("Ecuador");
        simpleList.setAdapter(arrayAdapter);
        lista.add("India");
        arrayAdapter.notifyDataSetChanged(); //Guarda los cambios y lo ejecuta

        */
        lista.add("Holanda");
        simpleList.setAdapter(adaptador);

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String agregar = txtTexto.getText().toString();
                lista.add(agregar);
                adaptador.notifyDataSetChanged();
            }
        });

        btnEditar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lista.set(Adapter.pos, txtTexto.getText().toString());
                adaptador.notifyDataSetChanged();
                btnEditar1.setVisibility(View.INVISIBLE);
            }
        });

    }

    public static void delete(int pos) {
        lista.remove(pos);
        adaptador.notifyDataSetChanged();
    }


}
