package com.example.rest_davidroldan.framework.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.view.ActionMode
import com.example.hiltmenu.ui.main.PersonaAdapter
import com.example.rest_davidroldan.R
import com.example.rest_davidroldan.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var personasAdapter: PersonaAdapter


    private val viewModel: MainViewModel by viewModels()

    private val callback by lazy {
        configContextBar()
    }

    private lateinit var actionMode: ActionMode

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
        personasAdapter = PersonaAdapter(this,
            object : PersonaAdapter.PersonaActions {
                override fun onDelete(persona: Persona) = viewModel.handleEvent(MainEvent.DeletePersona(persona))

                override fun onStartSelectMode() {
                    startSupportActionMode(callback)?.let {
                        actionMode = it;
                        it.title = "1 selected"

                    }
                }

                override fun itemHasClicked(persona: Persona) {
                    actionMode.title =
                        "${personasAdapter.getSelectedItems().size.toString()} selected"
                }

                override fun isItemSelected(persona: Persona): Boolean {
                    return personasAdapter.getSelectedItems().contains(persona)
                }
            })
        binding.rvPersonas.adapter = personasAdapter


    }
}