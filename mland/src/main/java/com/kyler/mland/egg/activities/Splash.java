package com.kyler.mland.egg.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.kyler.mland.egg.R;

/** Created by kyler on 1/4/16. */
@SuppressWarnings("DefaultFileTemplate")
@SuppressLint("Registered")
public class Splash extends AppCompatActivity {

  private static final int SPLASH_DISPLAY_LENGTH = 3000;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    setTheme(R.style.Theme_MLand_Splash);

    super.onCreate(savedInstanceState);

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      Window window = getWindow();
      window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
      window.setStatusBarColor(Color.BLUE);
    }

    new Handler()
        .postDelayed(
            () -> {
              Intent mainIntent = new Intent(Splash.this, Home.class);
              Splash.this.startActivity(mainIntent);
              Splash.this.finish();
            },
            SPLASH_DISPLAY_LENGTH);
  }
}
