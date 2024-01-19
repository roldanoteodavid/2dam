package com.example.reclycleview_davidroldan.ui.pantallaPersonajes

import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.reclycleview_davidroldan.R
import com.example.reclycleview_davidroldan.data.model.Simpsonjson
import com.example.reclycleview_davidroldan.databinding.ActivitySimpsonBinding
import com.example.reclycleview_davidroldan.domain.usecases.AddSimpsonUseCase
import com.example.reclycleview_davidroldan.domain.usecases.DeleteSimpsonUseCase
import com.example.reclycleview_davidroldan.domain.usecases.GetSimpsonUseCase
import com.example.reclycleview_davidroldan.ui.Constantes
import com.example.reclycleview_davidroldan.utils.StringProvider
import com.example.segundaapp_davidroldan.domain.usecases.simpsons.UpdateSimpsonUseCase

class SimpsonActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySimpsonBinding

    private val viewModel: SimpsonViewModel by viewModels {
        SimpsonViewModelFactory(
            StringProvider.instance(this),
            AddSimpsonUseCase(),
            DeleteSimpsonUseCase(),
            UpdateSimpsonUseCase(),
            GetSimpsonUseCase(),
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simpson)
        val intent = intent
        if (intent.hasExtra(Constantes.ID)) {
            val id = intent.getIntExtra(Constantes.ID, 0)
            viewModel.getSimpson(id)
        }

        binding = ActivitySimpsonBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }
        observarViewModel()
        eventos()
    }

    private fun observarViewModel() {
        viewModel.uiState.observe(this@SimpsonActivity) { state ->
            state.error?.let { error ->
                Toast.makeText(this@SimpsonActivity, error, Toast.LENGTH_SHORT).show()
                viewModel.errorMostrado()
            }
            if (state.error == null) {
                pintarSimpson(state)
            }
        }
    }

    private fun pintarSimpson(state: SimpsonState) {
        with(binding) {
            nombreField.editText?.setText(state.simpson.name)
            edadSlider.value = state.simpson.age.toFloat()
            checkBoxVivo.isChecked = state.simpson.vivo
            when (state.simpson.gender) {
                radioButtonHombre.text -> radioButtonHombre.isChecked = true
                radioButtonMujer.text -> radioButtonMujer.isChecked = true
                radioButtonOtro.text -> radioButtonOtro.isChecked = true
            }

            val profesiones = resources.getStringArray(R.array.Profesiones)
            val adapter =
                ArrayAdapter(
                    this@SimpsonActivity,
                    android.R.layout.simple_spinner_item,
                    profesiones
                )
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
            if (profesiones.contains(state.simpson.occupation)) {
                val posicionProfesion = profesiones.indexOf(state.simpson.occupation)
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

            addButton.setOnClickListener {
                var profesion = ""
                if (spinner.selectedItemPosition != AdapterView.INVALID_POSITION) {
                    profesion = profesiones[spinner.selectedItemPosition]
                }
                val nombre: String = nombreField.editText?.text.toString();
                val edad: Int = edadSlider.value.toInt();
                var vivo = false;
                if (checkBoxVivo.isChecked) {
                    vivo = true;
                }
                var genero = ""
                when (radioGroup.checkedRadioButtonId) {
                    radioButtonHombre.id -> genero = radioButtonHombre.text.toString()
                    radioButtonMujer.id -> genero = radioButtonMujer.text.toString()
                    radioButtonOtro.id -> genero = radioButtonOtro.text.toString()
                }
                val simpson = Simpsonjson(0, nombre, edad, vivo, genero, profesion)
                viewModel.addSimpson(simpson);
            }

            deleteButton.setOnClickListener {
                viewModel.deleteSimpson()
                finish()
            }

            updateButton.setOnClickListener {
                var profesion = ""
                if (spinner.selectedItemPosition != AdapterView.INVALID_POSITION) {
                    profesion = profesiones[spinner.selectedItemPosition]
                }
                val nombre: String = nombreField.editText?.text.toString();
                val edad: Int = edadSlider.value.toInt();
                var vivo = false;
                if (checkBoxVivo.isChecked) {
                    vivo = true;
                }
                var genero = ""
                when (radioGroup.checkedRadioButtonId) {
                    radioButtonHombre.id -> genero = radioButtonHombre.text.toString()
                    radioButtonMujer.id -> genero = radioButtonMujer.text.toString()
                    radioButtonOtro.id -> genero = radioButtonOtro.text.toString()
                }
                val newsimpson = Simpsonjson(0, nombre, edad, vivo, genero, profesion)
                viewModel.updateSimpson(newsimpson);
            }
        }
    }
}