package com.kyler.mland.egg.activities;

import android.app.ActivityManager;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Window;

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

        // Artificial slow startup - NOT TO BE USED IN REAL APP.
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        super.onCreate(savedInstanceState);

        Window window = getWindow();
        window.setStatusBarColor(getResources().getColor(android.R.color.transparent));
        setContentView(R.layout.home);
        getSupportActionBar().setTitle(null);

        Resources resources = this.getResources();
        String label = resources.getString(this.getApplicationInfo().labelRes);
        final int aboutPrimaryDark = resources.getColor(R.color.tealish_green__primaryDark);

        if (sIcon == null) {
            // Cache to avoid decoding the same bitmap on every Activity change
            //   sIcon = BitmapFactory.decodeResource(resources, R.drawable.tbmd_recents);
            sIcon = BitmapFactory.decodeResource(resources, R.drawable.icon_mland_home);
        }

        this.setTaskDescription(new ActivityManager.TaskDescription(label, sIcon, aboutPrimaryDark));
    }
}
