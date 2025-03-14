// Generated by view binder compiler. Do not edit!
package com.example.examenmovprimertrim_davidroldan.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.examenmovprimertrim_davidroldan.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ViewCustomerBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Guideline guideline1;

  @NonNull
  public final Guideline guideline2;

  @NonNull
  public final CheckBox selected;

  @NonNull
  public final TextView tvId;

  @NonNull
  public final TextView tvNombre;

  private ViewCustomerBinding(@NonNull ConstraintLayout rootView, @NonNull Guideline guideline1,
      @NonNull Guideline guideline2, @NonNull CheckBox selected, @NonNull TextView tvId,
      @NonNull TextView tvNombre) {
    this.rootView = rootView;
    this.guideline1 = guideline1;
    this.guideline2 = guideline2;
    this.selected = selected;
    this.tvId = tvId;
    this.tvNombre = tvNombre;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ViewCustomerBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ViewCustomerBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.view_customer, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ViewCustomerBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.guideline1;
      Guideline guideline1 = ViewBindings.findChildViewById(rootView, id);
      if (guideline1 == null) {
        break missingId;
      }

      id = R.id.guideline2;
      Guideline guideline2 = ViewBindings.findChildViewById(rootView, id);
      if (guideline2 == null) {
        break missingId;
      }

      id = R.id.selected;
      CheckBox selected = ViewBindings.findChildViewById(rootView, id);
      if (selected == null) {
        break missingId;
      }

      id = R.id.tvId;
      TextView tvId = ViewBindings.findChildViewById(rootView, id);
      if (tvId == null) {
        break missingId;
      }

      id = R.id.tvNombre;
      TextView tvNombre = ViewBindings.findChildViewById(rootView, id);
      if (tvNombre == null) {
        break missingId;
      }

      return new ViewCustomerBinding((ConstraintLayout) rootView, guideline1, guideline2, selected,
          tvId, tvNombre);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
