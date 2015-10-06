/*
 * Copyright (C) 2015 The Android Open Source Project
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

/* Please see https://goo.gl/YM2vRS
   for links to the original source
 */

package com.kyler.mland.egg;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class MLandActivity extends Activity {
    MLand mLand;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Let everyone know this isn't my original work. :)
        SharedPreferences first = PreferenceManager
                .getDefaultSharedPreferences(this);

        if (getIntent().getBooleanExtra("EXIT", false)) {
            super.finish();
        }

        if (!first.getBoolean("firstTime", false)) {

            new Handler().post(new Runnable() {
                @Override
                public void run() {
                    final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MLandActivity.this);
                    alertDialogBuilder.setTitle(R.string.dialog_title)
                            .setMessage(R.string.dialog_message);

                    alertDialogBuilder.setPositiveButton(R.string.okay, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            // It'll clear
                        }
                    });

                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                }
            });


            SharedPreferences.Editor editor = first.edit();

            editor.putBoolean("firstTime", true);
            editor.commit();

        }

        setContentView(R.layout.mland);
        mLand = (MLand) findViewById(R.id.world);
        mLand.setScoreFieldHolder((ViewGroup) findViewById(R.id.scores));
        final View welcome = findViewById(R.id.welcome);
        mLand.setSplash(welcome);
        final int numControllers = mLand.getGameControllers().size();
        if (numControllers > 0) {
            mLand.setupPlayers(numControllers);
        }
    }

    public void updateSplashPlayers() {
        final int N = mLand.getNumPlayers();
        final View minus = findViewById(R.id.player_minus_button);
        final View plus = findViewById(R.id.player_plus_button);
        if (N == 1) {
            minus.setVisibility(View.INVISIBLE);
            plus.setVisibility(View.VISIBLE);
            plus.requestFocus();
        } else if (N == mLand.MAX_PLAYERS) {
            minus.setVisibility(View.VISIBLE);
            plus.setVisibility(View.INVISIBLE);
            minus.requestFocus();
        } else {
            minus.setVisibility(View.VISIBLE);
            plus.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onPause() {
        mLand.stop();
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();

        mLand.onAttachedToWindow(); // resets and starts animation
        updateSplashPlayers();
        mLand.showSplash();
    }

    public void playerMinus(View v) {
        mLand.removePlayer();
        updateSplashPlayers();
    }

    public void playerPlus(View v) {
        mLand.addPlayer();
        updateSplashPlayers();
    }

    public void startButtonPressed(View v) {
        findViewById(R.id.player_minus_button).setVisibility(View.INVISIBLE);
        findViewById(R.id.player_plus_button).setVisibility(View.INVISIBLE);
        mLand.start(true);
    }
}
