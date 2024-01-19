package com.example.login_davidroldan.framework.twofactor

import android.content.Intent
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
import com.example.login_davidroldan.databinding.FragmentLoginBinding
import com.example.login_davidroldan.databinding.FragmentTwofaBinding
import com.example.login_davidroldan.framework.main.MainActivity
import com.example.login_davidroldan.utils.Constants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch



@AndroidEntryPoint
class TwoFactorFragment : Fragment() {

    private var _binding: FragmentTwofaBinding? = null
    private val binding get() = _binding!!
    val args : TwoFactorFragmentArgs by navArgs()

    private val viewModel: TwoFactorViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTwofaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            etUsername.setText(args.username)
            etUsername.isEnabled = false
        }

        binding.btnVerify.setOnClickListener {
            val username = binding.etUsername.text.toString()
            val twofacode = binding.etTwoFa.text.toString()
            viewModel.handleEvent(TwoFactorEvent.TwoFactor(username, twofacode))
        }
        observarViewModel()
    }

    private fun observarViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    state.error?.let {
                        Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                        viewModel.handleEvent(TwoFactorEvent.ErrorVisto)
                    }
                    if (state.login) {
                        //intent para cambiar de activity al main
                        val intent = Intent(requireContext(), MainActivity::class.java)
                        startActivity(intent)
                        requireActivity().finish()
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