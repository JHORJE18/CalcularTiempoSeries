package com.jhorje18.calculartiemposeries.Objetos

import android.net.Uri

class Serie {

    var nombre: String =""
    var foto: String = ""

    var numero_Episodios = 0
    var numero_Temporadas = 0
    var numero_horas = 0
    var numero_minutos = 0

    constructor()  {    }

    constructor(nombre: String, imagen:String){
        this.nombre = nombre
        this.foto = imagen
    }
}