package com.jhorje18.calculartiemposeries

import android.net.Uri
import android.opengl.Visibility
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.widget.*
import com.jhorje18.calculartiemposeries.Fragmentos.HistorialFragment
import com.jhorje18.calculartiemposeries.Fragmentos.NuevoCalculoFragment
import com.jhorje18.calculartiemposeries.Fragmentos.PrincipalFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_principal.*

class MainActivity : AppCompatActivity(), PrincipalFragment.OnFragmentInteractionListener, NuevoCalculoFragment.OnFragmentInteractionListener, HistorialFragment.OnFragmentInteractionListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Animaciones para elementos

        // Cargamos Fragment por defecto
        addFragment(PrincipalFragment(), false, "Principal")
    }

    // Añade Fragmentos al principal
    fun addFragment(fragment: Fragment, addToBackStack: Boolean, tag: String) {
        val manager = supportFragmentManager
        val ft = manager.beginTransaction()
        ft.setCustomAnimations(R.anim.design_bottom_sheet_slide_in, R.anim.design_bottom_sheet_slide_out, R.anim.design_bottom_sheet_slide_in, R.anim.design_bottom_sheet_slide_out);

        if (addToBackStack) {
            ft.addToBackStack(tag)
        }
        ft.replace(R.id.fragment_Principal, fragment, tag)
        ft.commitAllowingStateLoss()
    }

    public fun onCllick (View :View) {
        when (View.id) {
            backButton.id ->{
                onBackPressed()
            }
        }
    }

    // Eventos Click
    override fun onButtonClick(Btn: Button) {
        when (Btn.id){
            btnNuevo.id ->{
                // Cambiar Fragment a Nuevo Calculo
                addFragment(NuevoCalculoFragment(), true, "Principal")
            }
            btnHistorial.id ->{
                addFragment(HistorialFragment(), true, "Historial")
            }
        }
    }

    // Eventos titulo
    override fun setTitle(title: String) {
        txtTitulo.setText(title)
    }

    // Eventos BackButton
    override fun setBackButton(valor: Boolean) {
        if (valor) {
            backButton.visibility = View.VISIBLE
        } else {
            backButton.visibility = View.INVISIBLE
        }
    }

    override fun onFragmentInteraction(uri: Uri) {
    }
}
