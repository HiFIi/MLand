package com.kyler.mland.egg.activities;

import android.app.ActivityManager;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;

import com.kyler.mland.egg.MLandBase;
import com.kyler.mland.egg.R;

/**
 * Created by kyler on 10/26/15.
 */
public class Home extends MLandBase {
    private static Bitmap sIcon = null;

    @Override
    protected int getSelfNavDrawerItem() {
        return NAVDRAWER_ITEM_HOME;
    }

    @Override
    protected Context getContext() {
        return Home.this;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_MLand_Home);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.home);
        super.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        // I should perhaps do some research to see whether or not
        // setting the SupportActionBars title to an empty string is
        // more efficient than *.setTitle(null);

        getSupportActionBar().setTitle(null);

        Resources resources = this.getResources();
        String label = resources.getString(this.getApplicationInfo().labelRes);
        final int aboutPrimaryDark = resources.getColor(R.color.tealish_green__primaryDark);

        if (sIcon == null) {
            // Cache to avoid decoding the same bitmap on every Activity change
            sIcon = BitmapFactory.decodeResource(resources, R.drawable.icon_mland_home);
        }

        this.setTaskDescription(new ActivityManager.TaskDescription(label, sIcon, aboutPrimaryDark));
    }
}
