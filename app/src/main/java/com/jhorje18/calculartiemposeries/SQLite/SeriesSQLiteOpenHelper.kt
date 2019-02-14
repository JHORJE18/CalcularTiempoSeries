package com.jhorje18.calculartiemposeries.SQLite

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.jhorje18.calculartiemposeries.Objetos.Serie

val DATABASE_NAME = "BBDD_CalculoSeries"
val DATABASE_VERSION  = 2
val TABLE_NAME = "SERIES"
val COL_ID = "ID"
val COL_NAME = "NOMBRE"
val COL_IMAGE = "IMAGEN"
class SeriesSQLiteOpenHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE "  + TABLE_NAME + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_NAME + " VARCHAR(256) NOT NULL, " +
                COL_IMAGE + " TEXT)"

        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db!!)
    }

    fun addSerie(serie: Serie){
        val db = this.writableDatabase
        var valores = ContentValues()
        valores.put(COL_NAME, serie.nombre)
        valores.put(COL_IMAGE, serie.foto)

        var resultado = db.insert(TABLE_NAME, null, valores)
    }

    fun getListadoSeries() : ArrayList<Serie> {
        var listado: ArrayList<Serie> = ArrayList()

        val db = this.readableDatabase
        val query = "SELECT * FROM " + TABLE_NAME
        val resultado = db.rawQuery(query, null)

        if (resultado.moveToFirst()) {
            do {
                var serieTEMP = Serie()
                serieTEMP.nombre = resultado.getString(resultado.getColumnIndex(COL_NAME))
                serieTEMP.foto = resultado.getString(resultado.getColumnIndex(COL_IMAGE))
                listado.add(serieTEMP)
            } while (resultado.moveToNext())
        }

        resultado.close()
        db.close()
        return listado
    }
}