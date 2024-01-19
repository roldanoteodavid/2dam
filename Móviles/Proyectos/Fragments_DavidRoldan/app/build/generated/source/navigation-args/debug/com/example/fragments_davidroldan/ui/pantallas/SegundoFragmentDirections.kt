package com.example.fragments_davidroldan.ui.pantallas

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.example.fragments_davidroldan.R

public class SegundoFragmentDirections private constructor() {
  public companion object {
    public fun actionSegundoFragmentToTercerFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_segundoFragment_to_tercerFragment)
  }
}
