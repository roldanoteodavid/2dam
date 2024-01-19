package com.example.practicaexamenprimertrim.framework.orders

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
import com.example.login_davidroldan.databinding.FragmentOrdersBinding
import com.example.login_davidroldan.framework.orders.OrderAdapter
import com.example.login_davidroldan.framework.orders.OrdersEvent
import com.example.login_davidroldan.framework.orders.OrdersState
import com.example.login_davidroldan.framework.orders.OrdersViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class OrdersFragment : Fragment() {
    private var _binding: FragmentOrdersBinding? = null
    private val binding get() = _binding!!
    private val viewModel: OrdersViewModel by viewModels()
    val args: OrdersFragmentArgs by navArgs()

    private lateinit var ordersAdapter: OrderAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOrdersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.handleEvent(OrdersEvent.GetCustomersPorId(args.idcustomer))
        ordersAdapter = OrderAdapter(requireContext()) { order ->
            viewModel.handleEvent(OrdersEvent.DeleteOrder(order.id))
        }

        with(binding) {
            rvOrders.adapter = ordersAdapter
            rvOrders.layoutManager = GridLayoutManager(requireContext(), 1)
        }

        val touchHelper = ItemTouchHelper(ordersAdapter.swipeGesture)
        touchHelper.attachToRecyclerView(binding.rvOrders)

        observarViewModel()
        configAppBar()

        // Manejo del botón de retroceso
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            val action = OrdersFragmentDirections.actionOrdersFragmentToMainFragment()
            findNavController().navigate(action)
        }
    }

    private fun observarViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    state.error?.let { error ->
                        Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
                        viewModel.handleEvent(OrdersEvent.ErrorVisto)
                    }
                    if (state.error == null) {
                        pintarCustomer(state)
                    }
                    if (state.orders.isNotEmpty()) {
                        ordersAdapter.submitList(state.orders)
                    } else {
                        ordersAdapter.submitList(emptyList())
                    }
                }
            }
        }
    }

    private fun pintarCustomer(state: OrdersState) {
        with(binding) {
            if (state.customer != null) {
                tvId.text = state.customer.id.toString()
                tvFname.text = state.customer.firstname
                tvLname.text = state.customer.lastname
                tvPhone.text = state.customer.phone
                tvMail.text = state.customer.email
                tvDob.text = state.customer.dob.toString()
            }
        }
    }

    private fun configAppBar() {
        binding.topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.add -> {
                    val action =
                        OrdersFragmentDirections.actionOrdersFragmentToNewOrderFragment(args.idcustomer)
                    findNavController().navigate(action)
                    true
                }

                else -> false
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.handleEvent(OrdersEvent.GetCustomersPorId(args.idcustomer))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}