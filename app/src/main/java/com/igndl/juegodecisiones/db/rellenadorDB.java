package com.igndl.juegodecisiones.db;
import static androidx.core.content.ContentProviderCompat.requireContext;
import static com.igndl.juegodecisiones.db.dbHelper.*;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class rellenadorDB {
    public rellenadorDB(com.igndl.juegodecisiones.db.dbHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    dbHelper dbHelper;
    public void insertarPregunta(String pregunta, String tipoPregunta, String respuesta1, String respuesta2, int porcentaje1, int porcentaje2) {
        // Obtener una instancia de escritura de la base de datos
          // 'this' depende del contexto donde estés llamando a este método
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Crear un objeto ContentValues para almacenar los valores a insertar
        ContentValues valores = new ContentValues();
        valores.put("pregunta", pregunta);
        valores.put("tipoPregunta", tipoPregunta);
        valores.put("respuesta1", respuesta1);
        valores.put("respuesta2", respuesta2);
        valores.put("porcentaje1", porcentaje1);
        valores.put("porcentaje2", porcentaje2);

        // Insertar la nueva fila en la tabla
        long nuevoId = db.insert(NOMBRE_TABLA_PREGUNTAS, null, valores);

        // Verificar si la inserción fue exitosa
        if (nuevoId == -1) {
            // Hubo un error al insertar
            Log.e("DB", "Error al insertar la pregunta");
        } else {
            // Inserción exitosa
            Log.d("DB", "Pregunta insertada con ID: " + nuevoId);
        }

        // Cerrar la conexión con la base de datos
        db.close();
    }

    public void vaciarTablaPreguntas() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("DELETE FROM preguntas");  // Ejecuta la sentencia SQL para borrar todas las filas
        db.close();  // Cierra la base de datos
    }

    public void rellenarDB(SQLiteDatabase db, int oldVersion, int newVersion){
        vaciarTablaPreguntas();
        insertarPregunta(
                "¿Cuál es tu color favorito?",
                "moral","Rojo", "Azul", 50,  50
        );
        insertarPregunta(
                "¿Cual es el nombre de tu perro?",
                "moral","chico", "boby", 50,  50
        );
    }
}
