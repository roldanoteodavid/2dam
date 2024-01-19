package com.example.login_davidroldan.framework.main

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
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.login_davidroldan.R
import com.example.login_davidroldan.databinding.FragmentMainBinding
import com.example.login_davidroldan.utils.Constants.SELECTED
import com.example.login_davidroldan.domain.modelo.Customer
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var customersAdapter: CustomerAdapter

    private var anteriorState: MainState? = null

    private val viewModel: MainViewModel by viewModels()

    private val callback by lazy {
        configContextBar()
    }

    private var actionMode: ActionMode? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        customersAdapter = CustomerAdapter(requireContext(),
            object : CustomerAdapter.CustomerActions {
                override fun onDelete(customer: Customer) =
                    viewModel.handleEvent(MainEvent.DeleteCustomer(customer))

                override fun onStartSelectMode(customer: Customer) {
                    viewModel.handleEvent(MainEvent.StartSelectMode)
                    viewModel.handleEvent(MainEvent.SeleccionaCustomers(customer))
                }

                override fun itemHasClicked(customer: Customer) {
                    viewModel.handleEvent(MainEvent.SeleccionaCustomers(customer))
                }

                override fun onClickItem(customerId: Int) {
                    click(customerId)
                }
            })
        with(binding) {
            rvCustomers.adapter = customersAdapter
            rvCustomers.layoutManager = GridLayoutManager(requireContext(), 1)
        }

        val touchHelper = ItemTouchHelper(customersAdapter.swipeGesture)
        touchHelper.attachToRecyclerView(binding.rvCustomers)

        observarViewModel()

        viewModel.handleEvent(MainEvent.GetCustomers)

        configAppBar()
    }

    private fun observarViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    if (state.customers.isEmpty()) {
                        customersAdapter.submitList(emptyList())
                    } else {
                        customersAdapter.submitList(state.customers)
                    }

                    actionMode?.title =
                        "${state.customersSelected.size} " + SELECTED

                    if (state.customersSelected != anteriorState?.customersSelected) {
                        customersAdapter.setSelectedItems(state.customersSelected)
                    }

                    if (state.selectMode != anteriorState?.selectMode) {
                        if (state.selectMode) {
                            customersAdapter.startSelectMode()
                            (activity as MainActivity).startSupportActionMode(callback)?.let {
                                actionMode = it
                            }
                        } else {
                            customersAdapter.resetSelectMode()
                            actionMode?.finish()
                            binding.topAppBar.visibility = View.VISIBLE
                        }
                    }
                    state.error?.let {
                        Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                        viewModel.handleEvent(MainEvent.ErrorVisto)
                    }

                    anteriorState = state
                }
            }
        }
    }

    private fun click(id: Int) {
        val action = MainFragmentDirections.actionMainFragmentToOrdersFragment(id)
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
                        viewModel.handleEvent(MainEvent.DeleteCustomersSeleccionadas())
                        true
                    }

                    else -> false
                }
            }

            override fun onDestroyActionMode(mode: ActionMode?) {
                binding.topAppBar.visibility = View.VISIBLE
                viewModel.handleEvent(MainEvent.ResetSelectMode)
            }
        }

    private fun configAppBar() {
        val actionSearch = binding.topAppBar.menu.findItem(R.id.search).actionView as SearchView

        actionSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { filtro ->
                    viewModel.handleEvent(MainEvent.GetCustomersFiltradas(filtro))
                }
                return false
            }
        })

        binding.topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.search -> {
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