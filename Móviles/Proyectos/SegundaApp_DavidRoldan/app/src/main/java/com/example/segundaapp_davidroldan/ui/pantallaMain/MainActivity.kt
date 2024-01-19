package com.example.segundaapp_davidroldan.ui.pantallaMain

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.segundaapp_davidroldan.R
import com.example.segundaapp_davidroldan.databinding.ActivityMainBinding
import com.example.segundaapp_davidroldan.domain.modelo.Simpson
import com.example.segundaapp_davidroldan.domain.usecases.simpsons.AddSimpsonUseCase
import com.example.segundaapp_davidroldan.domain.usecases.simpsons.DeleteSimpsonUseCase
import com.example.segundaapp_davidroldan.domain.usecases.simpsons.GetSimpsonUseCase
import com.example.segundaapp_davidroldan.domain.usecases.simpsons.GetSimpsons
import com.example.segundaapp_davidroldan.domain.usecases.simpsons.UpdateSimpsonUseCase
import com.example.segundaapp_davidroldan.utils.StringProvider

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory(
            StringProvider.instance(this),
            AddSimpsonUseCase(),
            GetSimpsons(),
            DeleteSimpsonUseCase(),
            UpdateSimpsonUseCase(),
            GetSimpsonUseCase(),
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }
        observarViewModel()
        eventos()
    }

    private fun observarViewModel() {
        viewModel.uiState.observe(this@MainActivity) { state ->
            state.error?.let { error ->
                Toast.makeText(this@MainActivity, error, Toast.LENGTH_SHORT).show()
                viewModel.errorMostrado()
            }
            if (state.error == null) {
                pintarSimpson(state)
            }
            if (state.noanterior == false) {
                binding.anteriorButton.visibility = View.INVISIBLE
            } else {
                binding.anteriorButton.visibility = View.VISIBLE
            }
            if (state.nosiguiente == false) {
                binding.siguienteButton.visibility = View.INVISIBLE
            } else {
                binding.siguienteButton.visibility = View.VISIBLE
            }
            if (state.updatedelete == false) {
                binding.deleteButton.visibility = View.INVISIBLE
                binding.updateButton.visibility = View.INVISIBLE
            } else {
                binding.deleteButton.visibility = View.VISIBLE
                binding.updateButton.visibility = View.VISIBLE
            }
        }
    }

    private fun pintarSimpson(state: MainState) {
        with(binding) {
            nombreField.editText?.setText(state.simpson.nombre)
            edadSlider.value = state.simpson.edad.toFloat()
            checkBox.isChecked = state.simpson.vivo
            when (state.simpson.genero) {
                radioButtonHombre.text -> radioButtonHombre.isChecked = true
                radioButtonMujer.text -> radioButtonMujer.isChecked = true
                radioButtonOtro.text -> radioButtonOtro.isChecked = true
            }

            val profesiones = resources.getStringArray(R.array.Profesiones)
            val adapter =
                ArrayAdapter(this@MainActivity, android.R.layout.simple_spinner_item, profesiones)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
            if (profesiones.contains(state.simpson.profesion)) {
                val posicionProfesion = profesiones.indexOf(state.simpson.profesion)
                spinner.setSelection(posicionProfesion)
            }
        }
    }

    private fun eventos() {
        val profesiones = resources.getStringArray(R.array.Profesiones)
        val spinner = binding.spinner
        if (spinner != null) {
            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item, profesiones
            )
            spinner.adapter = adapter
        }
        with(binding) {
            observarViewModel()

            siguienteButton.setOnClickListener {
                viewModel.getSiguienteSimpson()
                observarViewModel()
            }

            anteriorButton.setOnClickListener {
                viewModel.getAnteriorSimpson()
                observarViewModel()
            }

            addButton.setOnClickListener {
                var profesion = ""
                if (spinner.selectedItemPosition != AdapterView.INVALID_POSITION) {
                    profesion = profesiones[spinner.selectedItemPosition]
                }
                val nombre: String = nombreField.editText?.text.toString();
                val edad: Int = edadSlider.value.toInt();
                var vivo = false;
                if (checkBox.isChecked) {
                    vivo = true;
                }
                var genero = ""
                when (radioGroup.checkedRadioButtonId) {
                    radioButtonHombre.id -> genero = radioButtonHombre.text.toString()
                    radioButtonMujer.id -> genero = radioButtonMujer.text.toString()
                    radioButtonOtro.id -> genero = radioButtonOtro.text.toString()
                }
                val simpson = Simpson(nombre, edad, vivo, genero, profesion)
                viewModel.addSimpson(simpson);
                observarViewModel()
            }

            deleteButton.setOnClickListener {
                viewModel.deleteSimpson()
                observarViewModel()
            }

            updateButton.setOnClickListener {
                var profesion = ""
                if (spinner.selectedItemPosition != AdapterView.INVALID_POSITION) {
                    profesion = profesiones[spinner.selectedItemPosition]
                }
                val nombre: String = nombreField.editText?.text.toString();
                val edad: Int = edadSlider.value.toInt();
                var vivo = false;
                if (checkBox.isChecked) {
                    vivo = true;
                }
                var genero = ""
                when (radioGroup.checkedRadioButtonId) {
                    radioButtonHombre.id -> genero = radioButtonHombre.text.toString()
                    radioButtonMujer.id -> genero = radioButtonMujer.text.toString()
                    radioButtonOtro.id -> genero = radioButtonOtro.text.toString()
                }
                val newsimpson = Simpson(nombre, edad, vivo, genero, profesion)
                viewModel.updateSimpson(newsimpson);
                observarViewModel()
            }
        }
    }
}
