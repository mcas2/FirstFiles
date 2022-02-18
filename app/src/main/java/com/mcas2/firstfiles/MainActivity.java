package com.mcas2.firstfiles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ContentHandler;
import java.nio.Buffer;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {

    private EditText et1;
    private TextView tv1;
    private Button btEscribirMemoriaSD;
    private Button btEscribirMemoriaInterna;
    private Button btLeerMemoriaInterna;
    private Button btLeerMemoriaSD;
    private Button btLeerFicheroRecursos;

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

        btEscribirMemoriaInterna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                escribirMemoriaInternaMejor();
            }
        });



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
            FileOutputStream fos = openFileOutput("Fichero_prueba.txt", Context.MODE_PRIVATE);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
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
}