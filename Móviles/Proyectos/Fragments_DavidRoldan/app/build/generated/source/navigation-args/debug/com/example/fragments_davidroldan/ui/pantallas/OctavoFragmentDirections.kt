package com.example.fragments_davidroldan.ui.pantallas

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.example.fragments_davidroldan.R

public class OctavoFragmentDirections private constructor() {
  public companion object {
    public fun actionOctavoFragmentToNovenoFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_octavoFragment_to_novenoFragment)
  }
}
