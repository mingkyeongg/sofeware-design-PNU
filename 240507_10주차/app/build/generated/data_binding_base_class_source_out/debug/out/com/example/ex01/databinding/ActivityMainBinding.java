// Generated by view binder compiler. Do not edit!
package com.example.ex01.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.ex01.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityMainBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final Button doButton1;

  @NonNull
  public final Button doButton2;

  @NonNull
  public final Button doButton3;

  @NonNull
  public final Button doButton4;

  @NonNull
  public final Button doButton5;

  @NonNull
  public final Button doButton6;

  @NonNull
  public final TextView output1;

  private ActivityMainBinding(@NonNull LinearLayout rootView, @NonNull Button doButton1,
      @NonNull Button doButton2, @NonNull Button doButton3, @NonNull Button doButton4,
      @NonNull Button doButton5, @NonNull Button doButton6, @NonNull TextView output1) {
    this.rootView = rootView;
    this.doButton1 = doButton1;
    this.doButton2 = doButton2;
    this.doButton3 = doButton3;
    this.doButton4 = doButton4;
    this.doButton5 = doButton5;
    this.doButton6 = doButton6;
    this.output1 = output1;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_main, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityMainBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.doButton1;
      Button doButton1 = ViewBindings.findChildViewById(rootView, id);
      if (doButton1 == null) {
        break missingId;
      }

      id = R.id.doButton2;
      Button doButton2 = ViewBindings.findChildViewById(rootView, id);
      if (doButton2 == null) {
        break missingId;
      }

      id = R.id.doButton3;
      Button doButton3 = ViewBindings.findChildViewById(rootView, id);
      if (doButton3 == null) {
        break missingId;
      }

      id = R.id.doButton4;
      Button doButton4 = ViewBindings.findChildViewById(rootView, id);
      if (doButton4 == null) {
        break missingId;
      }

      id = R.id.doButton5;
      Button doButton5 = ViewBindings.findChildViewById(rootView, id);
      if (doButton5 == null) {
        break missingId;
      }

      id = R.id.doButton6;
      Button doButton6 = ViewBindings.findChildViewById(rootView, id);
      if (doButton6 == null) {
        break missingId;
      }

      id = R.id.output1;
      TextView output1 = ViewBindings.findChildViewById(rootView, id);
      if (output1 == null) {
        break missingId;
      }

      return new ActivityMainBinding((LinearLayout) rootView, doButton1, doButton2, doButton3,
          doButton4, doButton5, doButton6, output1);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
