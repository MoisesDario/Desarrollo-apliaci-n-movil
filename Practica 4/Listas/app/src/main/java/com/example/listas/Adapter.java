package com.example.listas;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter extends BaseAdapter {

    public static int pos = 0;

    Context context;
    private static LayoutInflater inflater = null;
    ArrayList<String> datos;

    public Adapter(Context context, ArrayList<String> arrayList){
        this.context = context;
        this.datos = arrayList;
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final View vista = inflater.inflate(R.layout.activity_listview, null);
        final View vistaMain = inflater.inflate(R.layout.activity_main, null);

        final TextView textView = vista.findViewById(R.id.textView);
        Button btnEdit = vista.findViewById(R.id.btnEdit);
        Button btnDelete = vista.findViewById(R.id.btnDelete);
        final EditText editText = vistaMain.findViewById(R.id.textView);
        textView.setText(datos.get(position));

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = textView.getText().toString();
                MainActivity.txtTexto.setText(name);
                MainActivity.btnEditar1.setVisibility(View.VISIBLE);
                pos = position;
                //editText.setText(name);
                //Toast toast = Toast.makeText(vistaMain.getContext(), name + "Hola", Toast.LENGTH_LONG);

            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pos = position;
                MainActivity.delete(pos);
            }
        });



        return vista;
    }


    @Override
    public int getCount() {
        return datos.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
}
