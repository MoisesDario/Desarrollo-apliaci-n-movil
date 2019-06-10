package com.example.ejercicio4.ejercicio4;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;

public class MiReproductor extends AppCompatActivity {

    int[] sounds;
    MediaPlayer vector[]=new MediaPlayer[6];
    MediaController.MediaPlayerControl mp;
    ImageView imagesFotografia;
    int [] fotografia = {R.drawable.alguien,R.drawable.mirarte,R.drawable.quisiera,R.drawable.ginza,R.drawable.nene,R.drawable.solo};
    String [] artista = {"Sebastian Yatra ft. Wisin y Nacho","Sebastian Yatra","CNCO","JBalvin","Rochas y Chetas","Los Primos Mx"};
    String [] titulo = {"Alguien robo","Como mirarte","quisiera","Ginza","NeneMalo","Solo tu"};



    int item=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mi_reproductor);

        final TextView txtArtista = (TextView) findViewById(R.id.txtArtista);
        final TextView txtTitulo = (TextView) findViewById(R.id.txtTitulo);
        imagesFotografia = (ImageView) findViewById(R.id.imageFotografia);
        final Button btnPlay = (Button) findViewById(R.id.btnPlay);
        Button btnPause = (Button) findViewById(R.id.btnPause);
        Button btnStop = (Button) findViewById(R.id.btnStop);
        Button btnAnterior = (Button) findViewById(R.id.btnAnterior);
        Button btnSiguiente = (Button) findViewById(R.id.btnSiguiente);

        txtTitulo.setText(titulo[0]);
        txtArtista.setText(artista[0]);
        sounds = new  int[]{R.raw.alguien, R.raw.mirarte,R.raw.quisiera,R.raw.ginza,R.raw.nene,R.raw.solo};


        vector[0]= MediaPlayer.create(this,R.raw.alguien);
        vector[1]= MediaPlayer.create(this,R.raw.mirarte);
        vector[2]= MediaPlayer.create(this,R.raw.quisiera);
        vector[3]= MediaPlayer.create(this,R.raw.ginza);
        vector[4]= MediaPlayer.create(this,R.raw.nene);
        vector[5]= MediaPlayer.create(this,R.raw.solo);


        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast notificacion = Toast.makeText(getApplicationContext(),"Play",Toast.LENGTH_SHORT);
                notificacion.show();
                vector[item].start();
            }
        });

        btnPause.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast notificacion = Toast.makeText(getApplicationContext(),"pause",Toast.LENGTH_SHORT);
                notificacion.show();
                vector[item].pause();
            }
        });


        btnStop.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
              if(vector[item] != null)
              {
                  vector[item].stop();
                  vector[0]= MediaPlayer.create(MiReproductor.this,R.raw.alguien);
                  vector[1]= MediaPlayer.create(MiReproductor.this,R.raw.mirarte);
                  vector[2]= MediaPlayer.create(MiReproductor.this,R.raw.quisiera);
                  vector[3] = MediaPlayer.create(MiReproductor.this,R.raw.ginza);
                  vector[4] = MediaPlayer.create(MiReproductor.this,R.raw.nene);
                  vector[5] = MediaPlayer.create(MiReproductor.this,R.raw.solo);
                  item=0;
                  imagesFotografia.setImageResource(R.drawable.alguien);
                  txtTitulo.setText(titulo[0]);
                  txtArtista.setText(artista[0]);
                  Toast notificacion = Toast.makeText(getApplicationContext(),"Stop",Toast.LENGTH_SHORT);
                  notificacion.show();
              }
            }
        });

        btnAnterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(item > 0){
                   if(vector[item].isPlaying()) {
                       vector[item].stop();
                       vector[0] = MediaPlayer.create(MiReproductor.this, R.raw.alguien);
                       vector[1] = MediaPlayer.create(MiReproductor.this, R.raw.mirarte);
                       vector[2] = MediaPlayer.create(MiReproductor.this, R.raw.quisiera);
                       vector[3] = MediaPlayer.create(MiReproductor.this,R.raw.ginza);
                       vector[4] = MediaPlayer.create(MiReproductor.this,R.raw.nene);
                       vector[5] = MediaPlayer.create(MiReproductor.this,R.raw.solo);
                       Toast notificacion = Toast.makeText(getApplicationContext(), "anterior", Toast.LENGTH_SHORT);
                       notificacion.show();
                       item--;
                       vector[item].start();

                       if(item==0){
                            imagesFotografia.setImageResource(fotografia[0]);
                            txtTitulo.setText(titulo[0]);
                            txtArtista.setText(artista[0]);
                       }else if(item == 1){
                           imagesFotografia.setImageResource(fotografia[1]);
                           txtTitulo.setText(titulo[1]);
                           txtArtista.setText(artista[1]);
                       }else if(item == 2){
                           imagesFotografia.setImageResource(fotografia[2]);
                           txtTitulo.setText(titulo[2]);
                           txtArtista.setText(artista[2]);
                       }else if(item == 3){
                           imagesFotografia.setImageResource(fotografia[3]);
                           txtTitulo.setText(titulo[3]);
                           txtArtista.setText(artista[3]);
                       }else if(item == 4){
                           imagesFotografia.setImageResource(fotografia[4]);
                           txtTitulo.setText(titulo[4]);
                           txtArtista.setText(artista[4]);
                       }else if(item == 5){
                           imagesFotografia.setImageResource(fotografia[5]);
                           txtTitulo.setText(titulo[5]);
                           txtArtista.setText(artista[5]);
                       }
                   }else {
                       item--;

                       if(item == 0) {
                           imagesFotografia.setImageResource((fotografia[0]));
                           txtTitulo.setText(titulo[0]);
                           txtArtista.setText(artista[0]);
                       }else if(item == 1){
                           imagesFotografia.setImageResource(fotografia[1]);
                           txtTitulo.setText(titulo[1]);
                           txtArtista.setText(artista[1]);
                       }else if(item == 2){
                           imagesFotografia.setImageResource(fotografia[2]);
                           txtTitulo.setText(titulo[2]);
                           txtArtista.setText(artista[2]);
                       }else if(item == 3){
                           imagesFotografia.setImageResource(fotografia[3]);
                           txtTitulo.setText(titulo[3]);
                           txtArtista.setText(artista[3]);
                       }else if(item == 4){
                           imagesFotografia.setImageResource(fotografia[4]);
                           txtTitulo.setText(titulo[4]);
                           txtArtista.setText(artista[4]);
                       }else if(item == 5){
                           imagesFotografia.setImageResource(fotografia[5]);
                           txtTitulo.setText(titulo[5]);
                           txtArtista.setText(artista[5]);
                       }
                   }
                }else{
                    Toast.makeText(getApplicationContext(),"No hay mas canciones",Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(item < vector.length-1) {
                    if(vector[item].isPlaying()){
                        vector[item].stop();
                        item++;
                        vector[item].start();
                        Toast notificacion = Toast.makeText(getApplicationContext(), "siguiente", Toast.LENGTH_SHORT);
                        notificacion.show();

                        if(item==0){
                            imagesFotografia.setImageResource(fotografia[0]);
                            txtTitulo.setText(titulo[0]);
                            txtArtista.setText(artista[0]);
                        }else if(item == 1){
                            imagesFotografia.setImageResource(fotografia[1]);
                            txtTitulo.setText(titulo[1]);
                            txtArtista.setText(artista[1]);
                        }else if(item == 2){
                            imagesFotografia.setImageResource(fotografia[2]);
                            txtTitulo.setText(titulo[2]);
                            txtArtista.setText(artista[2]);
                        }else if(item == 3){
                            imagesFotografia.setImageResource(fotografia[3]);
                            txtTitulo.setText(titulo[3]);
                            txtArtista.setText(artista[3]);
                        }else if(item == 4){
                            imagesFotografia.setImageResource(fotografia[4]);
                            txtTitulo.setText(titulo[4]);
                            txtArtista.setText(artista[4]);
                        }else if(item == 5){
                            imagesFotografia.setImageResource(fotografia[5]);
                            txtTitulo.setText(titulo[5]);
                            txtArtista.setText(artista[5]);
                        }
                    }else{
                        item++;
                        if(item==0){
                            imagesFotografia.setImageResource(fotografia[0]);
                            txtTitulo.setText(titulo[0]);
                            txtArtista.setText(artista[0]);
                        }else if(item == 1){
                            imagesFotografia.setImageResource(fotografia[1]);
                            txtTitulo.setText(titulo[1]);
                            txtArtista.setText(artista[1]);
                        }else if(item == 2){
                            imagesFotografia.setImageResource(fotografia[2]);
                            txtTitulo.setText(titulo[2]);
                            txtArtista.setText(artista[2]);
                        }else if(item == 3){
                            imagesFotografia.setImageResource(fotografia[3]);
                            txtTitulo.setText(titulo[3]);
                            txtArtista.setText(artista[3]);
                        }else if(item == 4){
                            imagesFotografia.setImageResource(fotografia[4]);
                            txtTitulo.setText(titulo[4]);
                            txtArtista.setText(artista[4]);
                        }else if(item == 5){
                            imagesFotografia.setImageResource(fotografia[5]);
                            txtTitulo.setText(titulo[5]);
                            txtArtista.setText(artista[5]);
                        }
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"No hay mas canciones",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
