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

                if (progress < 1) view.btnMenos.isActivated = false ; view.btnMenos.isActivated = true
                if (progress == seekBar!!.max) view.btnMas.isActivated = false ; view.btnMas.isActivated = true
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })

        // Eventos botones


        return view
    }

    fun onClick(view:View){
        when(view.id){
            btnMas.id ->{
                // Incrementa
                sliderEpisodiosDia.progress += sliderEpisodiosDia.progress
            }
            btnMenos.id ->{
                // Disminuye
                sliderEpisodiosDia.progress -= sliderEpisodiosDia.progress
            }
        }
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
    }
}
