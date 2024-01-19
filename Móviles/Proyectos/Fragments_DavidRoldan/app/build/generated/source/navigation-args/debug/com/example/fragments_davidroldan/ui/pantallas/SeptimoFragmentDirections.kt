package com.example.fragments_davidroldan.ui.pantallas

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.example.fragments_davidroldan.R

public class SeptimoFragmentDirections private constructor() {
  public companion object {
    public fun actionSeptimoFragmentToOctavoFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_septimoFragment_to_octavoFragment)

    public fun actionSeptimoFragmentToNovenoFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_septimoFragment_to_novenoFragment)
  }
}
