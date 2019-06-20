package com.example.juegogato;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;

public class TablaActivity extends AppCompatActivity implements View.OnClickListener {

    private int totalPartidas;
    private int partidaActual = 1;
    private int movimiento = 0;
    private int scoreX = 0;
    private int scoreO = 0;
    private String signoActual;
    private Boolean ganador = false;

    private TextView lblJuego;
    private TextView lblTurno;
    private TextView lblJugadorX;
    private TextView lblJugadorO;
    private Button gato00;
    private Button gato01;
    private Button gato02;
    private Button gato10;
    private Button gato11;
    private Button gato12;
    private Button gato20;
    private Button gato21;
    private Button gato22;

    String [][] matriz = {{"","",""},{"","",""},{"","",""}};

    private Handler handler;
    private Runnable partidaNueva;
    private Runnable resultados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabla);

        handler = new Handler();

        partidaNueva = new Runnable() {
            public void run() {
                try {
                    partidaNueva();
                } catch (Exception e) {}
            }
        };

        resultados = new Runnable() {
            public void run() {
                resultados();
            }
        };

        totalPartidas = getIntent().getExtras().getInt("numPartidas");

        if (getIntent().getExtras().getInt("symbol") == 0)
            signoActual = "X";
        else
            signoActual = "O";

        lblJuego = findViewById(R.id.lblJuego);
        lblTurno = findViewById(R.id.lblTurno);
        lblJugadorX = findViewById(R.id.lblJugadorX);
        lblJugadorO = findViewById(R.id.lblJugadorO);

        gato00 = findViewById(R.id.gato00);
        gato01 = findViewById(R.id.gato01);
        gato02 = findViewById(R.id.gato02);
        gato10 = findViewById(R.id.gato10);
        gato11 = findViewById(R.id.gato11);
        gato12 = findViewById(R.id.gato12);
        gato20 = findViewById(R.id.gato20);
        gato21 = findViewById(R.id.gato21);
        gato22 = findViewById(R.id.gato22);

        initListeners();
        try {
            partidaNueva();
        } catch (Exception e) {}
    }

    private void initListeners() {
        gato00.setOnClickListener(this);
        gato01.setOnClickListener(this);
        gato02.setOnClickListener(this);
        gato10.setOnClickListener(this);
        gato11.setOnClickListener(this);
        gato12.setOnClickListener(this);
        gato20.setOnClickListener(this);
        gato21.setOnClickListener(this);
        gato22.setOnClickListener(this);
    }

    //Intereaccion del tablero
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.gato00:
                gato00.setText(signoActual);
                gato00.setEnabled(false);
                matriz[0][0] = signoActual;
                break;
            case R.id.gato01:
                gato01.setText(signoActual);
                gato01.setEnabled(false);
                matriz[0][1] = signoActual;
                break;
            case R.id.gato02:
                gato02.setText(signoActual);
                gato02.setEnabled(false);
                matriz[0][2] = signoActual;
                break;
            case R.id.gato10:
                gato10.setText(signoActual);
                gato10.setEnabled(false);
                matriz[1][0] = signoActual;
                break;
            case R.id.gato11:
                gato11.setText(signoActual);
                gato11.setEnabled(false);
                matriz[1][1] = signoActual;
                break;
            case R.id.gato12:
                gato12.setText(signoActual);
                gato12.setEnabled(false);
                matriz[1][2] = signoActual;
                break;
            case R.id.gato20:
                gato20.setText(signoActual);
                gato20.setEnabled(false);
                matriz[2][0] = signoActual;
                break;
            case R.id.gato21:
                gato21.setText(signoActual);
                gato21.setEnabled(false);
                matriz[2][1] = signoActual;
                break;
            case R.id.gato22:
                gato22.setText(signoActual);
                gato22.setEnabled(false);
                matriz[2][2] = signoActual;
        }

        movimiento++;

        if (movimiento > 4) {
            try {
                verificarGanador();
            } catch (Exception e) {}
        }

        if (movimiento == 9 || ganador) {
                //La parte que demuestra que es el ganador
            if (ganador) {
                Toast msg = Toast.makeText(getApplicationContext(), "Ganador: " + signoActual, Toast.LENGTH_LONG);
                msg.show();
            }

            partidaActual++;

            if (partidaActual <= totalPartidas)
                handler.postDelayed(partidaNueva, 2000);
            else {
                // Activity Resultados
                lblJugadorX.setText("" + scoreX);
                lblJugadorO.setText("" + scoreO);
                handler.postDelayed(resultados, 2000);
                return;
            }
        }

        cambiarTurno();
    }

    private void updateLabels() {
        lblJuego.setText("Partida " + partidaActual);
        lblTurno.setText(signoActual);
        lblJugadorX.setText("" + scoreX);
        lblJugadorO.setText("" + scoreO);
    }

    private void cambiarTurno() {
        if (signoActual == "X")
            signoActual = "O";
        else
            signoActual = "X";

        lblTurno.setText(signoActual);
    }

    private void partidaNueva() throws Exception {
        Button button;
        int buttonID;

        Class buttons = R.id.class;
        Field buttonField;

        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                String gato = "gato" + i + j;
                buttonField = buttons.getField(gato);
                buttonID = buttonField.getInt(null);
                button = findViewById(buttonID);

                button.setText("");
                button.setEnabled(true);
                button.setBackgroundColor(Color.parseColor("#e5e7e8"));
            }
        }

        ganador = false;
        movimiento = 0;

        for(int i = 0; i < 3; ++i)
            for(int j = 0; j < 3; ++j)
                matriz[i][j] = "";

        updateLabels();
    }

    private void disableButtons() throws Exception {
        Button button;
        int buttonID;

        Class buttons = R.id.class;
        Field buttonField;

        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                String gato = "gato" + i + j;
                buttonField = buttons.getField(gato);
                buttonID = buttonField.getInt(null);
                button = findViewById(buttonID);
                button.setEnabled(false);
            }
        }
    }

    private void verificarGanador() throws Exception {
        verificarColumnas();
        verificarFilas();
        verificarCruz();

        if (ganador) {
            if (signoActual == "X")
                scoreX++;
            else
                scoreO++;

            disableButtons();
        }
    }

    private void verificarColumnas() throws Exception {
        int suma;
        for(int j = 0; j < 3; ++j) {
            suma = 0;
            for(int i = 0; i < 3; ++i)
                if (matriz[i][j] == signoActual)
                    suma = suma + 1;
            if (suma == 3) {
                ganador = true;
                pintarColumna(j);
                return;
            }
        }
    }

    private void verificarFilas() throws Exception {
        int suma;
        for(int i = 0; i < 3; ++i) {
            suma = 0;
            for(int j = 0; j < 3; ++j)
                if (matriz[i][j] == signoActual)
                    suma = suma + 1;
            if (suma == 3) {
                ganador = true;
                pintarFila(i);
                return;
            }
        }
    }

    private void verificarCruz() {
        if (matriz[0][0] == signoActual &&
                matriz[1][1] == signoActual &&
                matriz[2][2] == signoActual) {
            ganador = true;
            pintarDiagonal();
            return;
        }
        if (matriz[0][2] == signoActual &&
                matriz[1][1] == signoActual &&
                matriz[2][0] == signoActual) {
            ganador = true;
            pintarDiagonalInvertida();
            return;
        }
    }

    private void pintarColumna(int columna) throws Exception {
        Button button;
        int buttonID;

        Class buttons = R.id.class;
        Field buttonField;

        for (int i = 0; i < 3; ++i) {
            String gato = "gato" + i + columna;
            buttonField = buttons.getField(gato);
            buttonID = buttonField.getInt(null);
            button = findViewById(buttonID);
            button.setBackgroundColor(Color.parseColor("#000066"));
        }
    }

    private void pintarFila(int fila) throws Exception {
        Button button;
        int buttonID;

        Class buttons = R.id.class;
        Field buttonField;

        for (int j = 0; j < 3; ++j) {
            String gato = "gato" + fila + j;
            buttonField = buttons.getField(gato);
            buttonID = buttonField.getInt(null);
            button = findViewById(buttonID);
            button.setBackgroundColor(Color.parseColor("#000066"));
        }
    }

    private void pintarDiagonal() {
        gato00.setBackgroundColor(Color.parseColor("#000066"));
        gato11.setBackgroundColor(Color.parseColor("#000066"));
        gato22.setBackgroundColor(Color.parseColor("#000066"));
    }

    private void pintarDiagonalInvertida() {
        gato02.setBackgroundColor(Color.parseColor("#000066"));
        gato11.setBackgroundColor(Color.parseColor("#000066"));
        gato20.setBackgroundColor(Color.parseColor("#000066"));
    }

    private void resultados() {
        Intent resultados_activity = new Intent(TablaActivity.this, finishActivity.class);
        resultados_activity.putExtra("totalPartidas", totalPartidas);
        resultados_activity.putExtra("scoreX", scoreX);
        resultados_activity.putExtra("scoreO", scoreO);
        startActivity(resultados_activity);
    }
}
