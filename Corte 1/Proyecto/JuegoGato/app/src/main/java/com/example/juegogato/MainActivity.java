package com.example.juegogato;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private int symbolJugador1;
    private Boolean symbolSelected = false;
    private int numPartidas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RadioButton btnX = findViewById(R.id.rbtnO);
        final RadioButton btnO = findViewById(R.id.rbtnX);
        final EditText txtNumero = findViewById(R.id.txtNumero);
        final Button btnEmpezar = findViewById(R.id.btnEmpezar);


        //Simbolo de Jugador
        btnX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                symbolJugador1 = 0;
                symbolSelected = true;
                Toast.makeText(getApplicationContext(),"Eres Jugador 2",Toast.LENGTH_LONG).show();
                btnX.setEnabled(false);
                btnO.setEnabled(false);
            }
        });

        btnO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                symbolJugador1 = 1;
                symbolSelected = true;
                Toast.makeText(getApplicationContext(),"Eres Jugador 1", Toast.LENGTH_LONG).show();
                btnO.setEnabled(false);
                btnX.setEnabled(false);
            }
        });

        //Metodo para iniciar el juego
        btnEmpezar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String partidas = txtNumero.getText().toString();

                if(btnX.isChecked() == true)
                {
                    if (symbolSelected && partidas.compareTo("") != 0) {
                        numPartidas = Integer.parseInt(txtNumero.getText().toString());

                        if (numPartidas > 0) {
                            Intent tablaActivity = new Intent(MainActivity.this, TablaActivity.class);
                            tablaActivity.putExtra("symbol", symbolJugador1);
                            tablaActivity.putExtra("numPartidas", numPartidas);
                            startActivity(tablaActivity);
                        }
                        else {
                            Toast msg = Toast.makeText(getApplicationContext(),"Número de partidas debe ser mayor a cero", Toast.LENGTH_LONG);
                            msg.show();
                        }
                    }
                    else {
                        Toast msg = Toast.makeText(getApplicationContext(),"Selecciona símbolo y número de partidas", Toast.LENGTH_LONG);
                        msg.show();
                    }
                }else{
                    if(btnO.isChecked() == true)
                    {
                        if (symbolSelected && partidas.compareTo("") != 0) {
                            numPartidas = Integer.parseInt(txtNumero.getText().toString());

                            if (numPartidas > 0) {
                                Intent tablaActivity = new Intent(MainActivity.this, TablaActivity.class);
                                tablaActivity.putExtra("symbol", symbolJugador1);
                                tablaActivity.putExtra("numPartidas", numPartidas);
                                Toast.makeText(getApplicationContext(),"Empezemos el juego del gato", Toast.LENGTH_LONG).show();
                                startActivity(tablaActivity);
                            }
                            else {
                                Toast msg = Toast.makeText(getApplicationContext(),"Número de partidas debe ser mayor a cero", Toast.LENGTH_LONG);
                                msg.show();
                            }
                        }
                        else {
                            Toast msg = Toast.makeText(getApplicationContext(),"Selecciona símbolo y número de partidas", Toast.LENGTH_LONG);
                            msg.show();
                        }
                    }
                }

            }
        });




    }


}
