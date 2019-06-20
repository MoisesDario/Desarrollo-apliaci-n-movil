package com.example.activividades_2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private  EditText txtPalabra;
    private Button btnVerificacion;

    private TextView txtPalindroma;
    private TextView txtLongitud;
    private TextView txtInversa;
    private TextView txtComun;

    String inverso = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button btnActivity2 = findViewById(R.id.btnActivity2);

        btnVerificacion = findViewById(R.id.btnVerificacion);
        txtPalabra = findViewById(R.id.txtPalabra);

        txtPalindroma = findViewById(R.id.txtPalindroma); // Palindroma
        txtLongitud = findViewById(R.id.txtLongitud);   //Longitud
        txtInversa = findViewById(R.id.txtInversa);    // Inversa
        txtComun = findViewById(R.id.txtComun);// Letras Comunes

        btnVerificacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String texto = txtPalabra.getText().toString();
                String acumulador = "";

                int contador = 0;
                int repetidas = 0;

                for (int i= texto.length() - 1; i >= 0; i--){
                    inverso += texto.charAt(i);
                    for (int j= texto.length() - 1; j >= 0; j--)
                    {
                        if(texto.charAt(i) == texto.charAt(j));
                        {
                            repetidas ++;
                        }
                    }

                    if (repetidas > contador)
                    {
                        acumulador = "" + texto.charAt(i);
                        contador = repetidas;
                    }

                    repetidas = 0;
                }

                String inversamente = inverso;

                if(texto.equals(inversamente))
                {
                    txtPalindroma.setText("Palindroma: Si");
                }else{
                    txtPalindroma.setText("Palindroma: No");
                }

                txtLongitud.setText("Longitud: " + texto.length());
                txtInversa.setText("Inversa: " + inverso);
                txtComun.setText("Letra que coincidio: " + acumulador);

                inverso = "";
            }
        });
        btnActivity2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ventana1 = new Intent(MainActivity.this,Main2Activity.class);
                startActivity(ventana1);
            }
        });
    }
}
