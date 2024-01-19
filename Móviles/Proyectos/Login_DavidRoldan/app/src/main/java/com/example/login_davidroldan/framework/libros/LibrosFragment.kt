package com.example.login_davidroldan.framework.libros

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.login_davidroldan.R
import com.example.login_davidroldan.databinding.FragmentLibrosBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LibrosFragment : Fragment() {
    private var _binding: FragmentLibrosBinding? = null
    private val binding get() = _binding!!
    private val viewModel: LibrosViewModel by viewModels()
    val args: LibrosFragmentArgs by navArgs()

    private lateinit var libroAdapter: LibroAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLibrosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.handleEvent(LibrosEvent.GetLibros(args.idAutor))
        libroAdapter = LibroAdapter(requireContext()) { libro ->
            viewModel.handleEvent(LibrosEvent.DeleteLibro(libro))
        }

        with(binding) {
            rvLibros.adapter = libroAdapter
            rvLibros.layoutManager = GridLayoutManager(requireContext(), 1)
        }

        val touchHelper = ItemTouchHelper(libroAdapter.swipeGesture)
        touchHelper.attachToRecyclerView(binding.rvLibros)

        observarViewModel()
        configAppBar()

        // Manejo del botón de retroceso
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            val action = LibrosFragmentDirections.actionLibrosFragmentToAutorFragment()
            findNavController().navigate(action)
        }
    }

    private fun observarViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    state.error?.let { error ->
                        Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
                        viewModel.handleEvent(LibrosEvent.ErrorVisto)
                    }
                    if (state.libros.isNotEmpty()) {
                        libroAdapter.submitList(state.libros)
                    } else {
                        libroAdapter.submitList(emptyList())
                    }
                }
            }
        }
    }

    private fun configAppBar() {
        binding.topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.add -> {
                    viewModel.handleEvent(LibrosEvent.AddLibro())
                    true
                }

                else -> false
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.handleEvent(LibrosEvent.GetLibros(args.idAutor))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}