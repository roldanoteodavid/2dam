package com.example.practicaexamenprimertrim.framework.orders

import android.os.Bundle
import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.example.examenmovprimertrim_davidroldan.R
import kotlin.Int

public class OrdersFragmentDirections private constructor() {
  private data class ActionOrdersFragmentToNewOrderFragment(
    public val idcustomer: Int,
  ) : NavDirections {
    public override val actionId: Int = R.id.action_ordersFragment_to_newOrderFragment

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putInt("idcustomer", this.idcustomer)
        return result
      }
  }

  public companion object {
    public fun actionOrdersFragmentToNewOrderFragment(idcustomer: Int): NavDirections =
        ActionOrdersFragmentToNewOrderFragment(idcustomer)

    public fun actionOrdersFragmentToMainFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_ordersFragment_to_mainFragment)
  }
}
