package com.jhorje18.calculartiemposeries.Objetos

import android.net.Uri

class Serie {

    var id = 0
    var nombre: String =""
    var numero_Episodios = 0
    var numero_Temporadas = 0
    var numero_horas = 0
    var numero_minutos = 0
    var numero_episodiosXDia = 0
    var dias_visualizacion = ""

    constructor()  {    }

    constructor(
        nombre: String,
        numero_Episodios: Int,
        numero_Temporadas: Int,
        numero_horas: Int,
        numero_minutos: Int,
        numero_episodiosXDia: Int,
        dias_visualizacion: String
    ) {
        this.nombre = nombre
        this.numero_Episodios = numero_Episodios
        this.numero_Temporadas = numero_Temporadas
        this.numero_horas = numero_horas
        this.numero_minutos = numero_minutos
        this.numero_episodiosXDia = numero_episodiosXDia
        this.dias_visualizacion = dias_visualizacion
    }


}