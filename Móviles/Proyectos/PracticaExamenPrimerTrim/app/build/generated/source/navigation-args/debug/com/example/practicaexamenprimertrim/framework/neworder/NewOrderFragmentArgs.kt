package com.example.practicaexamenprimertrim.framework.neworder

import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavArgs
import java.lang.IllegalArgumentException
import kotlin.Int
import kotlin.jvm.JvmStatic

public data class NewOrderFragmentArgs(
  public val idcustomer: Int,
) : NavArgs {
  public fun toBundle(): Bundle {
    val result = Bundle()
    result.putInt("idcustomer", this.idcustomer)
    return result
  }

  public fun toSavedStateHandle(): SavedStateHandle {
    val result = SavedStateHandle()
    result.set("idcustomer", this.idcustomer)
    return result
  }

  public companion object {
    @JvmStatic
    public fun fromBundle(bundle: Bundle): NewOrderFragmentArgs {
      bundle.setClassLoader(NewOrderFragmentArgs::class.java.classLoader)
      val __idcustomer : Int
      if (bundle.containsKey("idcustomer")) {
        __idcustomer = bundle.getInt("idcustomer")
      } else {
        throw IllegalArgumentException("Required argument \"idcustomer\" is missing and does not have an android:defaultValue")
      }
      return NewOrderFragmentArgs(__idcustomer)
    }

    @JvmStatic
    public fun fromSavedStateHandle(savedStateHandle: SavedStateHandle): NewOrderFragmentArgs {
      val __idcustomer : Int?
      if (savedStateHandle.contains("idcustomer")) {
        __idcustomer = savedStateHandle["idcustomer"]
        if (__idcustomer == null) {
          throw IllegalArgumentException("Argument \"idcustomer\" of type integer does not support null values")
        }
      } else {
        throw IllegalArgumentException("Required argument \"idcustomer\" is missing and does not have an android:defaultValue")
      }
      return NewOrderFragmentArgs(__idcustomer)
    }
  }
}
