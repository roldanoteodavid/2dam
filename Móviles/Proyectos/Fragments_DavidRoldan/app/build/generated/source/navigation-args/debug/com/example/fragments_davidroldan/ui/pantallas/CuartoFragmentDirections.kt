package com.example.fragments_davidroldan.ui.pantallas

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.example.fragments_davidroldan.R

public class CuartoFragmentDirections private constructor() {
  public companion object {
    public fun actionCuartoFragmentToQuintoFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_cuartoFragment_to_quintoFragment)

    public fun actionCuartoFragmentToSextoFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_cuartoFragment_to_sextoFragment)
  }
}
