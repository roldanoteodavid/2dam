package com.example.fragments_davidroldan.ui.pantallas

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.example.fragments_davidroldan.R

public class SextoFragmentDirections private constructor() {
  public companion object {
    public fun actionSextoFragmentToCuartoFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_sextoFragment_to_cuartoFragment)
  }
}
