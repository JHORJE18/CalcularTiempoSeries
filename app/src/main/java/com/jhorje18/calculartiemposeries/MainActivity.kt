package com.jhorje18.calculartiemposeries

import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.fragment_principal.*

class MainActivity : AppCompatActivity(), PrincipalFragment.OnFragmentInteractionListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Cargamos Fragment por defecto
        addFragment(PrincipalFragment(), false, "Principal")
    }

    // Añade Fragmentos al principal
    fun addFragment(fragment: Fragment, addToBackStack: Boolean, tag: String) {
        val manager = supportFragmentManager
        val ft = manager.beginTransaction()

        if (addToBackStack) {
            ft.addToBackStack(tag)
        }
        ft.replace(R.id.fragment_Principal, fragment, tag)
        ft.commitAllowingStateLoss()
    }

    // Eventos Click
    override fun onButtonClick(Btn: Button) {
        when (Btn.id){
            btnNuevo.id ->{
                // Cambiar Fragment a Nuevo Calculo
                addFragment(NuevoCalculoFragment(), true, "Principal")
            }
            btnHistorial.id ->{
                addFragment(NuevoCalculoFragment(), true, "Principal")
            }
        }
    }
}
