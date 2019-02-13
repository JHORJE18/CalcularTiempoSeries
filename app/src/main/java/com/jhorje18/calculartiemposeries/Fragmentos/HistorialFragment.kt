package com.jhorje18.calculartiemposeries.Fragmentos

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jhorje18.calculartiemposeries.Adaptadores.AdapterHistory
import com.jhorje18.calculartiemposeries.Objetos.Serie
import com.jhorje18.calculartiemposeries.R
import kotlinx.android.synthetic.main.fragment_historial.*
import kotlinx.android.synthetic.main.fragment_historial.view.*
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper

class HistorialFragment : Fragment() {
    private var listener: OnFragmentInteractionListener? = null

    // Variables
    var series :ArrayList<Serie>? = null
    var lista :RecyclerView? = null
    var layoutManager :RecyclerView.LayoutManager? = null
    var adaptador :AdapterHistory? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_historial, container, false)
        listener?.setTitle("Historial")
        listener?.setBackButton(true)



        series = ArrayList()
        series?.add(Serie("Por 13 Razones", "https://images-na.ssl-images-amazon.com/images/I/918KkftCUFL.jpg"))
        series?.add(Serie("Por 13 Razones", "https://images-na.ssl-images-amazon.com/images/I/918KkftCUFL.jpg"))
        series?.add(Serie("Por 13 Razones", "https://images-na.ssl-images-amazon.com/images/I/918KkftCUFL.jpg"))
        series?.add(Serie("Por 13 Razones", "https://images-na.ssl-images-amazon.com/images/I/918KkftCUFL.jpg"))
        series?.add(Serie("Silicon Valley", "https://i.blogs.es/6151cb/silicon-valley-opening/450_1000.jpg"))
        series?.add(Serie("Por 13 Razones", "https://images-na.ssl-images-amazon.com/images/I/918KkftCUFL.jpg"))

        lista = view.recyclerHistory
        layoutManager = LinearLayoutManager(activity!!) as RecyclerView.LayoutManager?

        adaptador = AdapterHistory(series!!)
        lista?.layoutManager = layoutManager
        lista?.adapter = adaptador

        return view
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(uri: Uri)
        fun setTitle(title: String)
        fun setBackButton(valor: Boolean)
    }
}
