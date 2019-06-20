package com.example.activividades_2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    EditText txtValor1;
    EditText txtValor2;

    Button btnResultado;
    TextView txtSolucion;

    Button btnActivity1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        txtValor1 = findViewById(R.id.txtValor1);
        txtValor2 = findViewById(R.id.txtValor2);

        btnResultado = findViewById(R.id.btnResultado);
        txtSolucion = findViewById(R.id.txtSolucion);

        btnActivity1 = findViewById(R.id.btnActivity1);

        btnResultado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String numero1 = String.valueOf(txtValor1.getText());
                int a = Integer.parseInt(numero1); //Primer valor para el divisor propio

                String numero2 = String.valueOf(txtValor2.getText());
                int b = Integer.parseInt(numero2); //Segundo valor para el divisor propio

                int divisores = 0;

                for(int i = 1 ; i < a; i++) //Divisores propios del primer valor
                {
                    if(a % i == 0)
                    {
                        divisores = divisores + i;
                    }
                }

                if(divisores == b)
                {
                    divisores = 0;
                    for(int i = 1; i < b; i++) //Divisores propios del segundo valor
                    {
                        if(b % i == 0)
                        {
                            divisores = divisores +i;
                        }
                    }

                    if(divisores == a)
                    {
                        txtSolucion.setText("El  numero son amigos");
                    }else
                    {
                        txtSolucion.setText("El numero no son amigos");
                    }
                }else
                {
                    txtSolucion.setText("El numero no son amigos");
                }

            }

        });

        btnActivity1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ventana2 = new Intent (Main2Activity.this, MainActivity.class);
                startActivity(ventana2);
            }
        });

    }
}
