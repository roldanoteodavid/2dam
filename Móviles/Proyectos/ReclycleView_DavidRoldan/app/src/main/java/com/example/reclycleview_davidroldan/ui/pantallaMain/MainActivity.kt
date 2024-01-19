package com.example.reclycleview_davidroldan.ui.pantallaMain

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.reclycleview_davidroldan.R
import com.example.reclycleview_davidroldan.databinding.ActivityMainBinding
import com.example.reclycleview_davidroldan.domain.usecases.GetSimpsonsUseCase
import com.example.reclycleview_davidroldan.ui.Constantes
import com.example.reclycleview_davidroldan.ui.pantallaPersonajes.SimpsonActivity
import com.example.reclycleview_davidroldan.utils.StringProvider
import com.example.recyclerview_davidroldan.data.SimpsonRepositoty


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: PersonasAdapter

    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory(
            StringProvider.instance(this),
            GetSimpsonsUseCase(SimpsonRepositoty(assets.open(Constantes.DATA_JSON))),
        )
    }

    override fun onResume() {
        super.onResume()
        viewModel.getSimpsons()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }
        with(binding) {
            viewModel.getSimpsons()
            adapter = PersonasAdapter(emptyList(), ::click)
            rvPersonajes.adapter = adapter
            rvPersonajes.layoutManager = GridLayoutManager(this@MainActivity, 1)
        }
        observarViewModel()
    }

    private fun observarViewModel() {
        viewModel.uiState.observe(this@MainActivity) { state ->
            state.error?.let { error ->
                Toast.makeText(this@MainActivity, error, Toast.LENGTH_SHORT).show()
                viewModel.errorMostrado()
            }
            adapter.cambiarLista(state.lista)
        }
    }


    private fun click(id: Int) {
        val intent = Intent(this, SimpsonActivity::class.java)
        intent.putExtra(Constantes.ID, id)
        startActivity(intent)
    }

}