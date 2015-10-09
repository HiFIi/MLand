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

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

public class MLandModifiedActivity extends MLandBase {
    MLandModified mLandModified;

    @Override
    protected int getSelfNavDrawerItem() {
        return NAVDRAWER_ITEM_MLANDMODIFIED;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        setContentView(R.layout.mland);
        getSupportActionBar().setTitle(null);
        mLandModified = (MLandModified) findViewById(R.id.world);
        mLandModified.setScoreFieldHolder((ViewGroup) findViewById(R.id.scores));
        final View welcome = findViewById(R.id.welcome);
        mLandModified.setSplash(welcome);
        final int numControllers = mLandModified.getGameControllers().size();
        if (numControllers > 0) {
            mLandModified.setupPlayers(numControllers);
        }
    }

    public void updateSplashPlayers() {
        final int N = mLandModified.getNumPlayers();
        final View minus = findViewById(R.id.player_minus_button);
        final View plus = findViewById(R.id.player_plus_button);
        if (N == 1) {
            minus.setVisibility(View.INVISIBLE);
            plus.setVisibility(View.VISIBLE);
            plus.requestFocus();
        } else if (N == mLandModified.MAX_PLAYERS) {
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
        mLandModified.stop();
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();

        mLandModified.onAttachedToWindow(); // resets and starts animation
        updateSplashPlayers();
        mLandModified.showSplash();
    }

    public void playerMinus(View v) {
        mLandModified.removePlayer();
        updateSplashPlayers();
    }

    public void playerPlus(View v) {
        mLandModified.addPlayer();
        updateSplashPlayers();
    }

    public void startButtonPressed(View v) {
        findViewById(R.id.player_minus_button).setVisibility(View.INVISIBLE);
        findViewById(R.id.player_plus_button).setVisibility(View.INVISIBLE);
        mLandModified.start(true);
    }
}
