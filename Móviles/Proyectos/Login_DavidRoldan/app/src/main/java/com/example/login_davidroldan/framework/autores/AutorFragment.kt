package com.example.login_davidroldan.framework.autores

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.ActionMode
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.login_davidroldan.R
import com.example.login_davidroldan.databinding.FragmentAutoresBinding
import com.example.login_davidroldan.domain.modelo.Autor
import com.example.login_davidroldan.framework.main.MainActivity
import com.example.login_davidroldan.utils.Constants.SELECTED
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AutorFragment : Fragment() {

    private var _binding: FragmentAutoresBinding? = null
    private val binding get() = _binding!!
    private lateinit var autorAdapter: AutorAdapter

    private var anteriorState: AutorState? = null

    private val viewModel: AutorViewModel by viewModels()

    private val callback by lazy {
        configContextBar()
    }

    private var actionMode: ActionMode? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAutoresBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        autorAdapter = AutorAdapter(requireContext(),
            object : AutorAdapter.AutorActions {
                override fun onDelete(autor: Autor) =
                    viewModel.handleEvent(AutorEvent.DeleteAutor(autor))

                override fun onStartSelectMode(autor: Autor) {
                    viewModel.handleEvent(AutorEvent.StartSelectMode)
                    viewModel.handleEvent(AutorEvent.SeleccionaAutor(autor))
                }

                override fun itemHasClicked(autor: Autor) {
                    viewModel.handleEvent(AutorEvent.SeleccionaAutor(autor))
                }

                override fun onClickItem(autorId: Int) {
                    click(autorId)
                }
            })
        with(binding) {
            rvAutores.adapter = autorAdapter
            rvAutores.layoutManager = GridLayoutManager(requireContext(), 1)
        }

        val touchHelper = ItemTouchHelper(autorAdapter.swipeGesture)
        touchHelper.attachToRecyclerView(binding.rvAutores)

        observarViewModel()

        viewModel.handleEvent(AutorEvent.GetAutores)

        configAppBar()
    }

    private fun observarViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    if (state.autores != anteriorState?.autores) {
                        if (state.autores.isEmpty()) {
                            autorAdapter.submitList(emptyList())
                        } else {
                            autorAdapter.submitList(state.autores)
                        }
                    }

                    actionMode?.title =
                        "${state.autoresSelected.size} " + SELECTED
                    if (state.autoresSelected != anteriorState?.autoresSelected) {
                        autorAdapter.setSelectedItems(state.autoresSelected)
                    }

                    if (state.selectMode != anteriorState?.selectMode) {
                        if (state.selectMode) {
                            autorAdapter.startSelectMode()
                            (activity as MainActivity).startSupportActionMode(callback)?.let {
                                actionMode = it
                            }
                        } else {
                            autorAdapter.resetSelectMode()
                            actionMode?.finish()
                            binding.topAppBar.visibility = View.VISIBLE
                        }
                    }
                    state.error?.let {
                        Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                        viewModel.handleEvent(AutorEvent.ErrorVisto)
                    }

                    anteriorState = state
                }
            }
        }
    }

    private fun click(id: Int) {
        val action = AutorFragmentDirections.actionAutorFragmentToLibrosFragment(id)
        findNavController().navigate(action)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.context_bar, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun configContextBar() =
        object : ActionMode.Callback {

            override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                (activity as AppCompatActivity).menuInflater.inflate(R.menu.context_bar, menu)
                binding.topAppBar.visibility = View.GONE
                return true
            }

            override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                return false
            }

            override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
                return when (item?.itemId) {
                    R.id.delete -> {
                        viewModel.handleEvent(AutorEvent.DeleteAutoresSeleccionados())
                        true
                    }

                    else -> false
                }
            }

            override fun onDestroyActionMode(mode: ActionMode?) {
                binding.topAppBar.visibility = View.VISIBLE
                viewModel.handleEvent(AutorEvent.ResetSelectMode)
            }
        }

    private fun configAppBar() {
        binding.topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.add -> {
                    viewModel.handleEvent(AutorEvent.AddAutor())
                    true
                }

                else -> false
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}