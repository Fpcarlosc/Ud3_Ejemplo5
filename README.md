# Ud3_Ejemplo5
_Ejemplo 5 de la Unidad 3._

Vamos a devolver datos de una Actividad a otra creando Intets explícitos. 
Para ello vamos a basarnos en el proyecto [Ud3_Ejemplo4](https://github.com/Fpcarlosc/Ud3_Ejemplo4) y realizaremos los siguientes cambios (los demás ficheros no necesitarán cambios salvo el nombre del proyecto):

## _actividad1.xml_ y _actividad2.xml_

En este ejemplo vamos a añadir un botón que será el que devuelva los datos a la actividad principal.
Es necesario que tanto los botones como los _TextView_ tengan sus _ids_ para posteriormente buscarlos.

_actividad1.xml_:

```
...
    <Button
        android:id="@+id/botonAct1"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginStart="8dp"
        android:layout_marginLeft="8dp"
        android:layout_marginTop="24dp"
        android:layout_marginEnd="8dp"
        android:layout_marginRight="8dp"
        android:text="@string/boton"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/TextViewAct1" />

</android.support.constraint.ConstraintLayout>
```

_actividad2.xml_:

```
...
    <Button
        android:id="@+id/botonAct2"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginStart="8dp"
        android:layout_marginLeft="8dp"
        android:layout_marginTop="24dp"
        android:layout_marginEnd="8dp"
        android:layout_marginRight="8dp"
        android:text="@string/boton"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/TextViewAct2" />

</android.support.constraint.ConstraintLayout>
```

## _MainActivity.java_
En el fichero _MainActivity.java_ deberemos hacer varios cambios.

Primero lanzaremos el _Intent_ usando el método _startActivityForResult_ en lugar del método _startActivity_, de tal forma que además de lanzar
el _Intent_ esperaremos la devolución de un resultado. Sus parámetros serán el _Intent_ creado y un código para identificar las peticiones de 
los diferentes _Intents_ que lancemos:

```
...
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
...
```

Además sobreescribiremos el método _onActivityResult_ que será el que recoja los resultados devueltos. En él comprobaremos el código de petición
 y si el código de resultado es correcto. Si es así usaremos la clase _Toast_ para mostrar el mensaje por pantalla con el valor devuelto.

Observad que en el primer caso se ha devuelto un _String_ y en el segundo un entero:

```
...
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
```

## _Actividad1.java_ y _Actividad2.java_

El último cambio que debemos hacer es asignar un _Click Listener_ a los botones y devolver el resultado creando un _Intent_, 
rellenando los datos extra con el valor que se quiere devolver junto con el código _RESULT_OK_ para indicar que todo ha ido bien.
Por último llamamos al método _finish_ para cerrar la actividad y volver a atrás.

_Actividad1.java_:
```
...
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
```

_Actividad2.java_:
```
...
        // Asignamos un Click Listener al botón para devolver los datos a la actividad principal
        Button boton = findViewById(R.id.botonAct1);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();

                // Devolvemos un entero.
                intent.putExtra(MainActivity.DATO_DEVUELTO,101);

                // Devolvemos un código de RESULT_OK
                setResult(RESULT_OK, intent);

                // Cerramos la actividad y volvemos a atrás.
                finish();
            }
        });
    }
}
```
