package com.jhorje18.calculartiemposeries.Adaptadores

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
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

		var foto :ImageView? = null
		var nombre :TextView? = null

		init {
			foto = vista.celda_img
			nombre = vista.celda_texto
		}
	}
}