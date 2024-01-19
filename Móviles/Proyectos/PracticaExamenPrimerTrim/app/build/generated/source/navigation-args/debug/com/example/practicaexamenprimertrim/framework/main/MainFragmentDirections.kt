package com.example.practicaexamenprimertrim.framework.main

import android.os.Bundle
import androidx.navigation.NavDirections
import com.example.examenmovprimertrim_davidroldan.R
import kotlin.Int

public class MainFragmentDirections private constructor() {
  private data class ActionMainFragmentToOrdersFragment(
    public val idcustomer: Int,
  ) : NavDirections {
    public override val actionId: Int = R.id.action_mainFragment_to_ordersFragment

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putInt("idcustomer", this.idcustomer)
        return result
      }
  }

  public companion object {
    public fun actionMainFragmentToOrdersFragment(idcustomer: Int): NavDirections =
        ActionMainFragmentToOrdersFragment(idcustomer)
  }
}
