package com.jhorje18.calculartiemposeries


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import kotlinx.android.synthetic.main.fragment_nuevo_calculo.view.*
import kotlinx.android.synthetic.main.fragment_principal.view.*
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper

class NuevoCalculoFragment : Fragment() {


    var activityCallback :NuevoCalculoFragment.OnFragmentInteractionListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_nuevo_calculo, container, false)
        activityCallback!!.setTitle("Nuevo calculo")

        return view
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        try {
            activityCallback = context as OnFragmentInteractionListener
        } catch (e: ClassCastException) {
            throw ClassCastException(context?.toString()
                    + " must implement ToolbarListener")
        }
    }

    interface OnFragmentInteractionListener {
        fun setTitle(title: String)
    }
}
