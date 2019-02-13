package com.jhorje18.calculartiemposeries.Objetos

import android.net.Uri

class Serie (nombre: String, foto: String){

    var nombre: String =""
    var foto: String = ""

    var numero_Episodios = 0
    var numero_Temporadas = 0
    var numero_horas = 0
    var numero_minutos = 0

    init {
        this.nombre = nombre
        this.foto = foto
    }
}