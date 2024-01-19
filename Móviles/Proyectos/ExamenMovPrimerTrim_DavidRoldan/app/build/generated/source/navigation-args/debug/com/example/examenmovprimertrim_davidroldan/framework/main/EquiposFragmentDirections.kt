package com.example.examenmovprimertrim_davidroldan.framework.main

import android.os.Bundle
import androidx.navigation.NavDirections
import com.example.examenmovprimertrim_davidroldan.R
import kotlin.Int
import kotlin.String

public class EquiposFragmentDirections private constructor() {
  private data class ActionEquiposFragmentToDetalleEquipoFragment(
    public val id: String,
    public val name: String,
  ) : NavDirections {
    public override val actionId: Int = R.id.action_equiposFragment_to_detalleEquipoFragment

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putString("id", this.id)
        result.putString("name", this.name)
        return result
      }
  }

  public companion object {
    public fun actionEquiposFragmentToDetalleEquipoFragment(id: String, name: String): NavDirections
        = ActionEquiposFragmentToDetalleEquipoFragment(id, name)
  }
}
