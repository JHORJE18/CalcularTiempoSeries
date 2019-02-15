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
import com.jhorje18.calculartiemposeries.Objetos.Serie
import com.jhorje18.calculartiemposeries.SQLite.SeriesSQLiteOpenHelper
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_principal.*

class MainActivity : AppCompatActivity(), PrincipalFragment.OnFragmentInteractionListener, NuevoCalculoFragment.OnFragmentInteractionListener, HistorialFragment.OnFragmentInteractionListener{

    var BBDD_Series: SeriesSQLiteOpenHelper? = null
    val TAG_PRUEBA = "#PRUEBA_TEMPORAL"
    var darkTheme: Boolean? = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Preparamos BBDD
        BBDD_Series = SeriesSQLiteOpenHelper(this)

        // Cargamos Fragment por defecto
        addFragment(PrincipalFragment(), false, "Principal")

        // Cargamos tema guardado en preferencias
        darkTheme = false
    }

    /**
     * Añade Fragments a la pantalla principal
     * @param  fragment Fragment que se va a añadir
     * @param addToBackStack Establece si se desea añadir a la cola ( En caso falso, se establece como el único Fragment )
     * @param tag Etiqueta para conocer que Fragment se esta cargando ( Only Dev )
     */
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

    fun onCllick (View :View) {
        when (View.id) {
            backButton.id ->{
                onBackPressed()
            }
            nightButton.id ->{
                if (darkTheme!!){
                    Log.d(TAG_PRUEBA, "Modo noche desactivado")
                    setTheme(R.style.AppTheme)
                    nightButton.setImageDrawable(getDrawable(R.drawable.night_white_24dp))
                    darkTheme = false
                } else {
                    Log.d(TAG_PRUEBA, "Modo noche activado")
                    setTheme(R.style.DarkTheme)
                    nightButton.setImageDrawable(getDrawable(R.drawable.ic_sun_black_24dp))
                    darkTheme = true
                }
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

    // CONTROLES BBDD SERIES
    fun obtenerListadoSeries() :ArrayList<Serie>{
        return BBDD_Series!!.getListadoSeries()
    }
    fun crearSerie(serie: Serie) {
        BBDD_Series!!.addSerie(serie)
    }

    /**
     * Metodo establece el titulo de la vista principal
     * @param title Titulo que se colocara
     */
    override fun setTitle(title: String) {
        txtTitulo.setText(title)
    }

    /**
     * Metodo que muestra o oculta el botón de atras
     * @param valor Establece si se debe mostrar o no
     */
    override fun setBackButton(valor: Boolean) {
        if (valor) {
            backButton.visibility = View.VISIBLE
            nightButton.visibility = View.VISIBLE
        } else {
            backButton.visibility = View.INVISIBLE
            nightButton.visibility = View.INVISIBLE
        }
    }

    override fun onFragmentInteraction(uri: Uri) {
    }
}
