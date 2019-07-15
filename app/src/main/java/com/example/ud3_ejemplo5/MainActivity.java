package com.example.ud3_ejemplo5;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Los datos devueltos tendrán como nombre asociado el valor de la constante.
    public static final String DATO_DEVUELTO="DATO_DEVUELTO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Buscamos el TextView de la actividad 1 por su id (actividad1).
        TextView actividad1 = findViewById(R.id.actividad1);

        // Asignamos un Click Listener sobre el TextView
        actividad1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAct1 = new Intent(MainActivity.this, Actividad1.class);

                // Añadimos los datos extra para pasárselos a la actividad
                intentAct1.putExtra(Intent.EXTRA_TEXT,"Texto Actividad 1");
                startActivityForResult(intentAct1,1);
            }
        });

        // Buscamos el TextView de la actividad 2 por su id (actividad2).
        TextView actividad2 = findViewById(R.id.actividad2);

        // Asignamos un Click Listener sobre el TextView
        actividad2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAct2 = new Intent(MainActivity.this, Actividad2.class);

                // Añadimos los datos extra para pasárselos a la actividad
                intentAct2.putExtra(Intent.EXTRA_TEXT,"Texto Actividad 2");
                startActivityForResult(intentAct2,2);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            Toast.makeText(this, data.getStringExtra(DATO_DEVUELTO), Toast.LENGTH_LONG).show();
        }
        else
            if(requestCode == 2 && resultCode == RESULT_OK) {
                Toast.makeText(this, Integer.toString(data.getIntExtra(DATO_DEVUELTO,0)), Toast.LENGTH_LONG).show();
            }
    }
}
