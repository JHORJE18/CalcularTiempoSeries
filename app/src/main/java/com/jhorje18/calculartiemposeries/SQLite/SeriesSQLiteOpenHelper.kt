package com.jhorje18.calculartiemposeries.SQLite

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.jhorje18.calculartiemposeries.Objetos.Serie
import java.security.spec.ECPoint

val DATABASE_NAME = "BBDD_CalculoSeries"
val DATABASE_VERSION  = 3
val TABLE_NAME = "SERIES"
val COL_ID = "ID"
val COL_NOMBRE = "Nombre"
val COL_NUMERO_EPISODIOS = "Numero_Episodios"
val COL_NUMERO_TEMPORADAS = "Numero_Temporadas"
val COL_NUMERO_HORAS = "Numero_Horas"
val COL_NUMERO_MINUTOS = "Numero_Minutos"
val COL_NUMERO_EPISODIOS_DIA = "Numero_Episodios_Dia"
val COL_DIAS_VISUALIZACION = "Dias_Visualizacion"

class SeriesSQLiteOpenHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE "+ TABLE_NAME + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_NOMBRE + " TEXT NOT NULL," +
                COL_NUMERO_EPISODIOS + " INTEGER DEFAULT 1," +
                COL_NUMERO_TEMPORADAS + " INTEGER DEFAULT 1," +
                COL_NUMERO_HORAS + " INTEGER DEFAULT 0," +
                COL_NUMERO_MINUTOS + " INTEGER DEFAULT 15," +
                COL_NUMERO_EPISODIOS_DIA + " INTEGER DEFAULT 1," +
                COL_DIAS_VISUALIZACION + " TEXT" +
                ");"

        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db!!)
    }

    /**
     * Registra una Serie en la BBDD
     * @param serie {Serie} Objeto Serie que se registrara en la BBDD con sus valores cargados
     */
    fun addSerie(serie: Serie){
        val db = this.writableDatabase
        var valores = ContentValues()
        valores.put(COL_NOMBRE, serie.nombre)
        valores.put(COL_NUMERO_EPISODIOS, serie.numero_Episodios)
        valores.put(COL_NUMERO_TEMPORADAS, serie.numero_Temporadas)
        valores.put(COL_NUMERO_HORAS, serie.numero_horas)
        valores.put(COL_NUMERO_MINUTOS, serie.numero_minutos)
        valores.put(COL_NUMERO_EPISODIOS_DIA, serie.numero_episodiosXDia)
        valores.put(COL_DIAS_VISUALIZACION, serie.dias_visualizacion)

        var resultado = db.insert(TABLE_NAME, null, valores)
    }

    /**
     * Obtiene un listado de todas las series registradas en la BBDD
     * @return {ArrayList<Serie>} Devuelve un ArrayList con todas las Series
     */
    fun getListadoSeries() : ArrayList<Serie> {
        var listado: ArrayList<Serie> = ArrayList()

        val db = this.readableDatabase
        val query = "SELECT * FROM " + TABLE_NAME
        val resultado = db.rawQuery(query, null)

        if (resultado.moveToFirst()) {
            do {
                var serieTEMP = Serie()
                serieTEMP.id = resultado.getInt(resultado.getColumnIndex(COL_ID))
                serieTEMP.nombre = resultado.getString(resultado.getColumnIndex(COL_NOMBRE))
                serieTEMP.numero_Episodios = resultado.getInt(resultado.getColumnIndex(COL_NUMERO_EPISODIOS))
                serieTEMP.numero_Temporadas = resultado.getInt(resultado.getColumnIndex(COL_NUMERO_TEMPORADAS))
                serieTEMP.numero_horas = resultado.getInt(resultado.getColumnIndex(COL_NUMERO_HORAS))
                serieTEMP.numero_minutos = resultado.getInt(resultado.getColumnIndex(COL_NUMERO_MINUTOS))
                serieTEMP.numero_episodiosXDia = resultado.getInt(resultado.getColumnIndex(COL_NUMERO_EPISODIOS_DIA))
                serieTEMP.dias_visualizacion = resultado.getString(resultado.getColumnIndex(COL_DIAS_VISUALIZACION))

                listado.add(serieTEMP)
            } while (resultado.moveToNext())
        }

        resultado.close()
        db.close()
        return listado
    }

    /**
     * Obtiene serie a partir de su ID
     * @param identificador {Int} ID de la serie que se quiere obtener
     * @return {Serie} Devuelve objeto Serie obtenida, en caso de no encontrar, devolvera una vacia
     */
    fun getSerie(identificador:Int) :Serie {
        val db = this.readableDatabase
        val query = "SELECT * FROM " + TABLE_NAME + " WHERE ID=" + identificador
        val resultado = db.rawQuery(query, null)

        var serieTEMP = Serie()

        if (resultado.moveToFirst()) {
                serieTEMP.id = resultado.getInt(resultado.getColumnIndex(COL_ID))
                serieTEMP.nombre = resultado.getString(resultado.getColumnIndex(COL_NOMBRE))
                serieTEMP.numero_Episodios = resultado.getInt(resultado.getColumnIndex(COL_NUMERO_EPISODIOS))
                serieTEMP.numero_Temporadas = resultado.getInt(resultado.getColumnIndex(COL_NUMERO_TEMPORADAS))
                serieTEMP.numero_horas = resultado.getInt(resultado.getColumnIndex(COL_NUMERO_HORAS))
                serieTEMP.numero_minutos = resultado.getInt(resultado.getColumnIndex(COL_NUMERO_MINUTOS))
                serieTEMP.numero_episodiosXDia = resultado.getInt(resultado.getColumnIndex(COL_NUMERO_EPISODIOS_DIA))
                serieTEMP.dias_visualizacion = resultado.getString(resultado.getColumnIndex(COL_DIAS_VISUALIZACION))
        }

        resultado.close()
        db.close()
        return serieTEMP
    }

    /**
     * Elimina una serie por su ID
     * @param identificador {Int} ID de la serie que se quiere eliminar de la BBDD
     */
    fun deleteSerie (identificador: Int) {
        val db = this.readableDatabase
        val query = "DELETE FROM " + TABLE_NAME + " WHERE ID=" + identificador
        val resultado = db.rawQuery(query, null)

        resultado.close()
        db.close()
    }

    /**
     * Elimina todos los registros de la BBDD Series
     */
    fun deleteAll () {
        val db = this.readableDatabase
        val query = "DELETE FROM " + TABLE_NAME
        val resultado = db.rawQuery(query, null)

        resultado.close()
        db.close()
    }
}