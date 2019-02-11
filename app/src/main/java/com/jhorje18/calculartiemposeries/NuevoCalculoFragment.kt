package com.jhorje18.calculartiemposeries


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_nuevo_calculo.view.*
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper

class NuevoCalculoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val vista = inflater.inflate(R.layout.fragment_nuevo_calculo, container, false)
        OverScrollDecoratorHelper.setUpOverScroll(vista.scrollNuevoCalculo)

        return vista
    }

}
