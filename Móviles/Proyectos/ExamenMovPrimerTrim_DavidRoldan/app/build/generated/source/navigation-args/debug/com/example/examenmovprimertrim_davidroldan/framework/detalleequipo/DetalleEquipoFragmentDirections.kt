package com.example.examenmovprimertrim_davidroldan.framework.detalleequipo

import android.os.Bundle
import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.example.examenmovprimertrim_davidroldan.R
import kotlin.Int
import kotlin.String

public class DetalleEquipoFragmentDirections private constructor() {
  private data class ActionDetalleEquipoFragmentToNewJugadorFragment(
    public val id: String,
    public val name: String,
  ) : NavDirections {
    public override val actionId: Int = R.id.action_detalleEquipoFragment_to_newJugadorFragment

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putString("id", this.id)
        result.putString("name", this.name)
        return result
      }
  }

  public companion object {
    public fun actionDetalleEquipoFragmentToEquiposFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_detalleEquipoFragment_to_equiposFragment)

    public fun actionDetalleEquipoFragmentToNewJugadorFragment(id: String, name: String):
        NavDirections = ActionDetalleEquipoFragmentToNewJugadorFragment(id, name)
  }
}
