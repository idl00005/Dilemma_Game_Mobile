package com.igndl.juegodecisiones.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class dbHelper extends SQLiteOpenHelper{
    private static final String NOMBRE_BASE_DATOS = "mi_base_de_datos.db";
    private static final int VERSION_BASE_DATOS = 1;

    // Nombre de la tabla
    public static final String NOMBRE_TABLA_PREGUNTAS = "preguntas";

    // Constructor
    public dbHelper(Context context) {
        super(context, NOMBRE_BASE_DATOS, null, VERSION_BASE_DATOS);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Crear tabla de preguntas
        String CREATE_PREGUNTAS_TABLE = "CREATE TABLE IF NOT EXISTS " + NOMBRE_TABLA_PREGUNTAS + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "pregunta TEXT NOT NULL," +
                "tipoPregunta TEXT NOT NULL," +
                "respuesta1 TEXT NOT NULL," +  // Campo existente
                "respuesta2 TEXT NOT NULL," +
                "porcentaje1 INTEGER NOT NULL," +
                "porcentaje2 INTEGER NOT NULL" +
                ");";
        db.execSQL(CREATE_PREGUNTAS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Si necesitas realizar cambios en la estructura de la base de datos
        db.execSQL("DROP TABLE IF EXISTS " + NOMBRE_TABLA_PREGUNTAS);
        onCreate(db);
    }

    public List<Pregunta> getPreguntasPorTipo(String tipoPregunta) {
        List<Pregunta> listaPreguntas = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // Definir las columnas que queremos obtener
        String[] columnas = {
                "id",
                "pregunta",
                "tipoPregunta",
                "respuesta1",
                "respuesta2",
                "porcentaje1",
                "porcentaje2"
        };

        // Definir el WHERE para filtrar por tipoPregunta
        String selection = "tipoPregunta = ?";
        String[] selectionArgs = { tipoPregunta };

        // Hacer la consulta con el filtro
        Cursor cursor = db.query(
                "preguntas",    // Nombre de la tabla
                columnas,       // Columnas que queremos obtener
                selection,      // WHERE clause
                selectionArgs,  // Valores del WHERE
                null,           // groupBy
                null,           // having
                null            // orderBy
        );

        // Iterar sobre los resultados
        if (cursor.moveToFirst()) {
            do {
                // Obtener los datos de la fila actual
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                String pregunta = cursor.getString(cursor.getColumnIndexOrThrow("pregunta"));
                String tipo = cursor.getString(cursor.getColumnIndexOrThrow("tipoPregunta"));
                String respuesta1 = cursor.getString(cursor.getColumnIndexOrThrow("respuesta1"));
                String respuesta2 = cursor.getString(cursor.getColumnIndexOrThrow("respuesta2"));
                int porcentaje1 = cursor.getInt(cursor.getColumnIndexOrThrow("porcentaje1"));
                int porcentaje2 = cursor.getInt(cursor.getColumnIndexOrThrow("porcentaje2"));

                // Crear una nueva instancia de Pregunta
                Pregunta preguntaObj = new Pregunta(id, pregunta, tipo, respuesta1, respuesta2, porcentaje1, porcentaje2);

                // Añadir a la lista
                listaPreguntas.add(preguntaObj);
            } while (cursor.moveToNext());
        }

        // Cerrar el cursor y la base de datos
        cursor.close();
        db.close();

        return listaPreguntas;
    }

}
