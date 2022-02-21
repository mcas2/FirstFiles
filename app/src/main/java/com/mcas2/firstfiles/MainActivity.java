package com.mcas2.firstfiles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ContentHandler;
import java.nio.Buffer;
import java.nio.charset.StandardCharsets;
import java.util.EventListener;

public class MainActivity extends AppCompatActivity {

    private EditText et1;
    private TextView tv1;
    private Button btEscribirMemoriaSD;
    private Button btEscribirMemoriaInterna;
    private Button btLeerMemoriaInterna;
    private Button btLeerMemoriaSD;
    private Button btLeerFicheroRecursos;
    private Button salir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = findViewById(R.id.et1);
        tv1 = findViewById(R.id.tv1);
        btEscribirMemoriaSD = findViewById(R.id.btEscribirMemoriaSD);
        btEscribirMemoriaInterna = findViewById(R.id.btEscribirMemoriaInterna);
        btLeerMemoriaInterna = findViewById(R.id.btLeerMemoriaInterna);
        btLeerMemoriaSD = findViewById(R.id.btLeerMemoriaSD);
        btLeerFicheroRecursos = findViewById(R.id.btLeerFicheroRecursos);
        salir = findViewById(R.id.salir);

        btEscribirMemoriaInterna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                escribirMemoriaInternaMejor();
            }
        });

        btLeerMemoriaInterna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leerMemoriaInterna();
            }
        });

        btEscribirMemoriaInterna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                escribirMemoriaSD();
            }
        });

        btLeerMemoriaSD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leerMemoriaSD();
            }
        });

        btLeerFicheroRecursos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leetFicheroRecursos();
            }
        });
    }

    private void leetFicheroRecursos() {
        try {
            //EL FICHERO DE RECURSOS EN MINUSCULAS
            InputStream is = getResources().openRawResource(R.raw.prueba_recursos);
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String cadena = br.readLine();
            tv1.setText(cadena);
            br.close();
            isr.close();
            is.close();
        } catch (IOException e){
            Log.e("ficheros", "Error");
        }
    }

    private void leerMemoriaSD() {
        String estadoMemoria = Environment.getExternalStorageState();
        if ((estadoMemoria.equals(Environment.MEDIA_MOUNTED)) ||
            estadoMemoria.equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {
            try {
                File fichero = new File(getExternalFilesDir(null), "FicheroPrueba.txt");
                FileInputStream fis = new FileInputStream(fichero);
                InputStreamReader isr = new InputStreamReader(fis);
                BufferedReader br = new BufferedReader(isr);
                String cadena = br.readLine();
                tv1.setText(cadena);
                br.close();
                isr.close();
                fis.close();
            } catch (IOException e){
                Log.e("ficheros", "Error en lectura.");
            }
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    private void escribirMemoriaSD() {
        String estadoMemoria = Environment.getExternalStorageState();
        if (estadoMemoria.equals(Environment.MEDIA_MOUNTED)){
            try {
                File fichero = new File(getExternalFilesDir(null), "FicheroPrueba.txt");
                OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(fichero));
                BufferedWriter bw = new BufferedWriter(osw);
                String cadena = et1.getText().toString();
                bw.write(cadena);
                bw.close();
                osw.close();
            } catch (IOException e){

            }
        } else {
            String texto;
            if (estadoMemoria.equals(Environment.MEDIA_MOUNTED_READ_ONLY)){
                texto = "SD en modo lectura";
            } else{
                texto = "SD no accesible";
            }
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, texto, duration);
            toast.show();
        }
    }

    public void escribirMemoriaInterna(){
        try {
            FileOutputStream fos = openFileOutput("FicheroPrueba.txt", Context.MODE_PRIVATE);
            String cadena = et1.getText().toString();
            fos.write(cadena.getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void escribirMemoriaInternaMejor (){
        try {
            FileOutputStream fos = openFileOutput("FicheroPrueba.txt", Context.MODE_PRIVATE);
            //Este osw convierte el flujo de string en caracteres
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            //El buffered lo escribe
            BufferedWriter bw = new BufferedWriter(osw);
            String cadena = et1.getText().toString();
            bw.write(cadena);
            bw.close();
            osw.close();
            fos.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void leerMemoriaInterna () {
        try {
            FileInputStream fis = openFileInput("FicheroPrueba.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            String cadena = br.readLine();
            tv1.setText(cadena);
            br.close();
            isr.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //public void salir(){
    //    MediaPlayer mediaPlayer = MediaPlayer.create(this, )
    //}
}