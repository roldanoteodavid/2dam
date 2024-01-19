package com.example.fragments_davidroldan.ui.pantallas

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.example.fragments_davidroldan.R

public class PrimerFragmentDirections private constructor() {
  public companion object {
    public fun actionPrimerFragmentToSegundoFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_primerFragment_to_segundoFragment)

    public fun actionPrimerFragmentToTercerFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_primerFragment_to_tercerFragment)
  }
}
