// Generated by view binder compiler. Do not edit!
package com.example.fragments_davidroldan.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.fragments_davidroldan.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentSegundoBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button irTercero;

  @NonNull
  public final TextView texto;

  private FragmentSegundoBinding(@NonNull ConstraintLayout rootView, @NonNull Button irTercero,
      @NonNull TextView texto) {
    this.rootView = rootView;
    this.irTercero = irTercero;
    this.texto = texto;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentSegundoBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentSegundoBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_segundo, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentSegundoBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.irTercero;
      Button irTercero = ViewBindings.findChildViewById(rootView, id);
      if (irTercero == null) {
        break missingId;
      }

      id = R.id.texto;
      TextView texto = ViewBindings.findChildViewById(rootView, id);
      if (texto == null) {
        break missingId;
      }

      return new FragmentSegundoBinding((ConstraintLayout) rootView, irTercero, texto);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
