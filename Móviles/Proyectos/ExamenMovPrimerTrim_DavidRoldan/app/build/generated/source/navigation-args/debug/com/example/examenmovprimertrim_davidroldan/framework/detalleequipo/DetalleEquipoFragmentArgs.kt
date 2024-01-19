package com.example.examenmovprimertrim_davidroldan.framework.detalleequipo

import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavArgs
import java.lang.IllegalArgumentException
import kotlin.String
import kotlin.jvm.JvmStatic

public data class DetalleEquipoFragmentArgs(
  public val id: String,
  public val name: String,
) : NavArgs {
  public fun toBundle(): Bundle {
    val result = Bundle()
    result.putString("id", this.id)
    result.putString("name", this.name)
    return result
  }

  public fun toSavedStateHandle(): SavedStateHandle {
    val result = SavedStateHandle()
    result.set("id", this.id)
    result.set("name", this.name)
    return result
  }

  public companion object {
    @JvmStatic
    public fun fromBundle(bundle: Bundle): DetalleEquipoFragmentArgs {
      bundle.setClassLoader(DetalleEquipoFragmentArgs::class.java.classLoader)
      val __id : String?
      if (bundle.containsKey("id")) {
        __id = bundle.getString("id")
        if (__id == null) {
          throw IllegalArgumentException("Argument \"id\" is marked as non-null but was passed a null value.")
        }
      } else {
        throw IllegalArgumentException("Required argument \"id\" is missing and does not have an android:defaultValue")
      }
      val __name : String?
      if (bundle.containsKey("name")) {
        __name = bundle.getString("name")
        if (__name == null) {
          throw IllegalArgumentException("Argument \"name\" is marked as non-null but was passed a null value.")
        }
      } else {
        throw IllegalArgumentException("Required argument \"name\" is missing and does not have an android:defaultValue")
      }
      return DetalleEquipoFragmentArgs(__id, __name)
    }

    @JvmStatic
    public fun fromSavedStateHandle(savedStateHandle: SavedStateHandle): DetalleEquipoFragmentArgs {
      val __id : String?
      if (savedStateHandle.contains("id")) {
        __id = savedStateHandle["id"]
        if (__id == null) {
          throw IllegalArgumentException("Argument \"id\" is marked as non-null but was passed a null value")
        }
      } else {
        throw IllegalArgumentException("Required argument \"id\" is missing and does not have an android:defaultValue")
      }
      val __name : String?
      if (savedStateHandle.contains("name")) {
        __name = savedStateHandle["name"]
        if (__name == null) {
          throw IllegalArgumentException("Argument \"name\" is marked as non-null but was passed a null value")
        }
      } else {
        throw IllegalArgumentException("Required argument \"name\" is missing and does not have an android:defaultValue")
      }
      return DetalleEquipoFragmentArgs(__id, __name)
    }
  }
}
