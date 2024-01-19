package com.example.login_davidroldan.framework.changepassword

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
import com.example.login_davidroldan.databinding.FragmentChangepasswordBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ChangepassFragment : Fragment() {

    private var _binding: FragmentChangepasswordBinding? = null
    private val binding get() = _binding!!
    val args: ChangepassFragmentArgs by navArgs()

    private val viewModel: ChangepassViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChangepasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            etUsername.setText(args.username)
            etUsername.isEnabled = false

            btnChangePassword.setOnClickListener {
                val username = etUsername.text.toString()
                val password = etPassword.text.toString()
                val temporal = etPassTemp.text.toString()
                viewModel.handleEvent(ChangepassEvent.Changepass(username, password, temporal))
            }
        }
        observarViewModel()
    }

    private fun observarViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    state.error?.let {
                        Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                        viewModel.handleEvent(ChangepassEvent.ErrorVisto)
                    }
                    if (state.changepass){
                        val action = ChangepassFragmentDirections.actionChangepassFragmentToLoginFragment()
                        findNavController().navigate(action)
                    }
                }
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}