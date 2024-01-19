package com.example.fragments_davidroldan.ui.pantallas

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.example.fragments_davidroldan.R

public class QuintoFragmentDirections private constructor() {
  public companion object {
    public fun actionQuintoFragmentToSextoFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_quintoFragment_to_sextoFragment)
  }
}
