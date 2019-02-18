package com.jhorje18.calculartiemposeries.Adaptadores

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.jhorje18.calculartiemposeries.Objetos.Serie
import com.jhorje18.calculartiemposeries.R
import kotlinx.android.synthetic.main.celda_historial.view.*

class AdapterHistory(items :ArrayList<Serie>): RecyclerView.Adapter<AdapterHistory.ViewHolder>() {

	// Variables
	var items :ArrayList<Serie>? =null
	var viewHolder :ViewHolder? = null

	init {
		this.items = items
	}

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		val item = items?.get(position)

		holder.nombre?.text= item?.nombre
		holder.numero_episodios?.text = item?.numero_Episodios.toString() + " Episodios"
		holder.numero_temporadas?.text = item?.numero_Temporadas.toString() + " Temporadas"
	}

	override fun getItemCount(): Int {
		return this.items!!.count()
	}

	override fun onCreateViewHolder(parent: ViewGroup, p1: Int): AdapterHistory.ViewHolder {
		var vista = LayoutInflater.from(parent?.context).inflate(R.layout.celda_historial, parent, false)

		viewHolder = ViewHolder(vista)
		return viewHolder!!
	}

	class ViewHolder(vista: View): RecyclerView.ViewHolder(vista){
		var vista = vista

		var nombre :TextView? = null
		var numero_episodios :TextView? = null
		var numero_temporadas :TextView? = null

		init {
			nombre = vista.celda_nombre
			numero_episodios = vista.celda_episodios
			numero_temporadas = vista.celda_temporadas
		}
	}
}