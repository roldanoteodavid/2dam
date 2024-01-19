package com.example.fragments_davidroldan.ui.pantallas

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.example.fragments_davidroldan.R

public class NovenoFragmentDirections private constructor() {
  public companion object {
    public fun actionNovenoFragmentToSeptimoFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_novenoFragment_to_septimoFragment)
  }
}
