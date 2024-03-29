/*
 * Copyright 2014 Google Inc. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.kyler.mland.egg.utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.AnimatedStateListDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class LUtils {
  private static final int[] STATE_CHECKED = new int[] {android.R.attr.state_checked};
  private static final int[] STATE_UNCHECKED = new int[] {};

  private static Typeface sMediumTypeface;

  private final AppCompatActivity mActivity;
  private Handler mHandler = new Handler();

  private LUtils(AppCompatActivity activity) {
    mActivity = activity;
  }

  public static LUtils getInstance(AppCompatActivity activity) {
    return new LUtils(activity);
  }

  private static boolean hasL() {
    return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
  }

  public void startActivityWithTransition(
      Intent intent, final View clickedView, final String transitionName) {
    ActivityOptions options = null;
    if (hasL() && clickedView != null) {
      TextUtils.isEmpty(transitionName);
    }

    mActivity.startActivity(intent, null);
  }

  public void setMediumTypeface(TextView textView) {
    if (hasL()) {
      if (sMediumTypeface == null) {
        sMediumTypeface = Typeface.create("sans-serif-medium", Typeface.NORMAL);
      }

      textView.setTypeface(sMediumTypeface);
    } else {
      textView.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD);
    }
  }

  public int getStatusBarColor() {
    if (!hasL()) {
      // On pre-L devices, you can have any status bar color so long as it's black.
      return Color.BLACK;
    }

    return mActivity.getWindow().getStatusBarColor();
  }

  public void setStatusBarColor(int color) {
    if (!hasL()) {
      return;
    }

    mActivity.getWindow().setStatusBarColor(color);
  }

  public void setOrAnimatePlusCheckIcon(
      final ImageView imageView, boolean isCheck, boolean allowAnimate) {
    if (!hasL()) {
      compatSetOrAnimatePlusCheckIcon(imageView, isCheck, allowAnimate);
      return;
    }

    Drawable drawable = imageView.getDrawable();
    if (!(drawable instanceof AnimatedStateListDrawable)) {
      imageView.setImageDrawable(drawable);
    }
    if (allowAnimate) {
      imageView.setImageState(isCheck ? STATE_UNCHECKED : STATE_CHECKED, false);
      drawable.jumpToCurrentState();
      imageView.setImageState(isCheck ? STATE_CHECKED : STATE_UNCHECKED, false);
    } else {
      imageView.setImageState(isCheck ? STATE_CHECKED : STATE_UNCHECKED, false);
      drawable.jumpToCurrentState();
    }
  }

  private void compatSetOrAnimatePlusCheckIcon(
      final ImageView imageView, boolean isCheck, boolean allowAnimate) {

    if (imageView.getTag() != null) {
      if (imageView.getTag() instanceof Animator) {
        Animator anim = (Animator) imageView.getTag();
        anim.end();
        imageView.setAlpha(1f);
      }
    }

    if (allowAnimate && isCheck) {
      int duration = mActivity.getResources().getInteger(android.R.integer.config_shortAnimTime);

      Animator outAnimator = ObjectAnimator.ofFloat(imageView, View.ALPHA, 0f);
      outAnimator.setDuration(duration / 2);
      outAnimator.addListener(
          new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {}
          });

      AnimatorSet inAnimator = new AnimatorSet();
      outAnimator.setDuration(duration);
      inAnimator.playTogether(
          ObjectAnimator.ofFloat(imageView, View.ALPHA, 1f),
          ObjectAnimator.ofFloat(imageView, View.SCALE_X, 0f, 1f),
          ObjectAnimator.ofFloat(imageView, View.SCALE_Y, 0f, 1f));

      AnimatorSet set = new AnimatorSet();
      set.playSequentially(outAnimator, inAnimator);
      set.addListener(
          new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
              imageView.setTag(null);
            }
          });
      imageView.setTag(set);
      set.start();
    }
  }
}
