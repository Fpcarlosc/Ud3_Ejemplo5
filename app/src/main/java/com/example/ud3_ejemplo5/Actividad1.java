package com.example.ud3_ejemplo5;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Actividad1 extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad1);

        TextView texto = findViewById(R.id.TextViewAct1);

        // Si hay datos extra los mostramos.
        if (getIntent() != null && getIntent().hasExtra(Intent.EXTRA_TEXT)) {
            texto.setText(getIntent().getStringExtra(Intent.EXTRA_TEXT));
        }

        // Asignamos un Click Listener al botón para devolver los datos a la actividad principal
        Button boton = findViewById(R.id.botonAct1);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();

                // Devolvemos un entero.
                intent.putExtra(MainActivity.DATO_DEVUELTO,"String devuelto");

                // Devolvemos un código de RESULT_OK
                setResult(RESULT_OK, intent);

                // Cerramos la actividad y volvemos a atrás.
                finish();
            }
        });
    }
}
