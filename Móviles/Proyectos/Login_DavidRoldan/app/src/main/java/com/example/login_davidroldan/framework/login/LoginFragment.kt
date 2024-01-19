package com.example.login_davidroldan.framework.login

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
import com.example.login_davidroldan.databinding.FragmentLoginBinding
import com.example.login_davidroldan.utils.Constants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch



@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            etUsername.setText(Constants.ROOT)
            etPassword.setText(Constants.PASS2DAM)
        }

        observarViewModel()

        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()
            viewModel.handleEvent(LoginEvent.Login(username, password))
        }

        binding.btnRegister.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
            findNavController().navigate(action)
        }

        binding.btnForgotPassword.setOnClickListener {
            val username = binding.etUsername.text.toString()
            viewModel.handleEvent(LoginEvent.ForgotPassword(username))
        }
    }

    private fun observarViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    state.error?.let {
                        Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                        viewModel.handleEvent(LoginEvent.ErrorVisto)
                    }
                    if (state.login) {
                        val action = LoginFragmentDirections.actionLoginFragmentToTwoFactorFragment(binding.etUsername.text.toString())
                        findNavController().navigate(action)
                    }
                    if (state.forgotPassword) {
                        Toast.makeText(
                            requireContext(),
                            Constants.SE_HA_ENVIADO_UN_CORREO,
                            Toast.LENGTH_SHORT
                        ).show()
                        val action = LoginFragmentDirections.actionLoginFragmentToChangepassFragment(binding.etUsername.text.toString())
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