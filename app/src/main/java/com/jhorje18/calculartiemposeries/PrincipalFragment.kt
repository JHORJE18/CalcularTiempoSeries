package com.jhorje18.calculartiemposeries


import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_principal.*
import kotlinx.android.synthetic.main.fragment_principal.view.*
import java.util.*

class PrincipalFragment : Fragment(),View.OnClickListener {

    var activityCallback :PrincipalFragment.OnFragmentInteractionListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_principal, container, false)
        activityCallback!!.setTitle(getString(R.string.app_name))

        view.btnNuevo.setOnClickListener(this)
        view.btnHistorial.setOnClickListener(this)

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

    override fun onClick(view: View){
        activityCallback!!.onButtonClick(view as Button)
    }

    interface OnFragmentInteractionListener {
        fun onButtonClick(btn: Button)
        fun setTitle(title: String)
    }
}
