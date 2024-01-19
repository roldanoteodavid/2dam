package com.example.practicaexamenprimertrim.framework.neworder

import android.os.Bundle
import androidx.navigation.NavDirections
import com.example.examenmovprimertrim_davidroldan.R
import kotlin.Int

public class NewOrderFragmentDirections private constructor() {
  private data class ActionNewOrderFragmentToOrdersFragment(
    public val idcustomer: Int,
  ) : NavDirections {
    public override val actionId: Int = R.id.action_newOrderFragment_to_ordersFragment

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putInt("idcustomer", this.idcustomer)
        return result
      }
  }

  public companion object {
    public fun actionNewOrderFragmentToOrdersFragment(idcustomer: Int): NavDirections =
        ActionNewOrderFragmentToOrdersFragment(idcustomer)
  }
}
