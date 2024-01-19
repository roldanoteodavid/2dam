package com.example.login_davidroldan.framework.neworder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.login_davidroldan.R
import com.example.login_davidroldan.databinding.FragmentNeworderBinding
import com.example.login_davidroldan.domain.modelo.Order
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.time.LocalDateTime

@AndroidEntryPoint
class NewOrderFragment : Fragment() {
    private var _binding: FragmentNeworderBinding? = null
    private val binding get() = _binding!!
    private val viewModel: NewOrderViewModel by viewModels()
    val args: NewOrderFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNeworderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.handleEvent(NewOrderEvent.GetId(args.idcustomer))
        observarViewModel()
        configAppBar()
    }

    private fun observarViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    state.error?.let { error ->
                        Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
                        viewModel.handleEvent(NewOrderEvent.ErrorVisto)
                    }
                    if (state.error == null) {
                        binding.tvCustomerId.text = state.idCustomer.toString()
                        binding.tvCustomerId.isActivated = false
                    }
                }
            }
        }
    }

    private fun configAppBar() {
        binding.topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.add -> {
                    addNewOrder()
                    val action =
                        NewOrderFragmentDirections.actionNewOrderFragmentToOrdersFragment(args.idcustomer)
                    findNavController().navigate(action)
                    true
                }

                else -> false
            }
        }
    }

    private fun addNewOrder() {
        val idCustomer = binding.tvCustomerId.text.toString().toInt()
        val tableId = binding.editTextTableId.text.toString().toInt()
        val order = Order(0, LocalDateTime.now(), idCustomer, tableId)
        viewModel.handleEvent(NewOrderEvent.AddOrder(order))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}