package com.jhorje18.calculartiemposeries.Fragmentos


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import com.jhorje18.calculartiemposeries.Objetos.Serie
import com.jhorje18.calculartiemposeries.R
import kotlinx.android.synthetic.main.fragment_nuevo_calculo.*
import kotlinx.android.synthetic.main.fragment_nuevo_calculo.view.*
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper

class NuevoCalculoFragment : Fragment() {

    var activityCallback : OnFragmentInteractionListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_nuevo_calculo, container, false)
        OverScrollDecoratorHelper.setUpOverScroll(view.scrollNuevoCalculo)

        activityCallback!!.setTitle("Nuevo calculo")
        activityCallback!!.setBackButton(true)

        view.inputNumEpisodios.addTextChangedListener(object :TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString().length >= 1){
                    view.sliderEpisodiosDia.max = (s.toString()).toInt()
                    view.sliderEpisodiosDia.progress = 0
                } else {
                    view.sliderEpisodiosDia.max =  0
                    view.sliderEpisodiosDia.progress = 0
                }
            }
        })

        view.sliderEpisodiosDia.setOnSeekBarChangeListener(object :SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                view.countEpisodiosDia.text = progress.toString()

                view.btnMenos.isActivated = (progress > 0)
                view.btnMas.isActivated = (progress != seekBar!!.max)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })

        // Eventos botones
        view.btnMas.setOnClickListener {
            sliderEpisodiosDia.progress = sliderEpisodiosDia.progress + 1
        }
        view.btnMenos.setOnClickListener {
            sliderEpisodiosDia.progress = sliderEpisodiosDia.progress - 1
        }

        view.btnCancel.setOnClickListener {
            activity?.onBackPressed()
        }
        view.btnSave.setOnClickListener {
            // Comprobaciones seguridad
            var campos_validos = true;

            if (view.inputNombre.text.toString().length == 0) {
                campos_validos = false
                view.inputNombre.setError("Es obligatorio introducir un nombre de la serie")
            }
            if (view.inputNumEpisodios.text.isEmpty() || view.inputNumEpisodios.text.toString().length < 1) {
                campos_validos = false
                view.inputNumEpisodios.setError("El mínimo de episodios es 1")
            }
            if (view.inputNumTemporadas.text.isEmpty() || view.inputNumTemporadas.text.toString().toInt() < 1) {
                campos_validos = false
                view.inputNumTemporadas.setError("El mínimo de temporadas es 1")
            }

            // Añadir valores
            var serie_Guardar :Serie = Serie()
            serie_Guardar.nombre = inputNombre.text.toString()

            // Guardar registro
            if (campos_validos){
                activityCallback?.saveSerie(serie_Guardar)
                activity?.onBackPressed()
            }
        }

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
        fun setBackButton(valor: Boolean)
        fun saveSerie(serie :Serie)
    }
}
