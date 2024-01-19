package com.example.fragments_davidroldan.ui.pantallas

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.example.fragments_davidroldan.R

public class TercerFragmentDirections private constructor() {
  public companion object {
    public fun actionTercerFragmentToPrimerFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_tercerFragment_to_primerFragment)
  }
}
