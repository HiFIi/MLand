package com.kyler.mland.egg.activities;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.view.View;
import android.view.Window;

import com.kyler.mland.egg.MLandBase;
import com.kyler.mland.egg.R;

import java.util.Objects;

/**
 * Created by kyler on 10/26/15.
 */
@SuppressWarnings("DefaultFileTemplate")
public class Home extends MLandBase {
    private static Bitmap sIcon = null;
    private static Window window;

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
        super.getWindow()
                .getDecorView()
                .setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        activateLightNavigationBar();
        activateLightStatusBar();

        // I should perhaps do some research to see whether or not
        // setting the SupportActionBars title to an empty string is
        // more efficient than *.setTitle(null);

        Objects.requireNonNull(getSupportActionBar()).setTitle(null);

        Resources resources = this.getResources();
        String label = resources.getString(this.getApplicationInfo().labelRes);
        final int aboutPrimaryDark = resources.getColor(R.color.colorPrimaryDark);

        if (sIcon == null) {
            // Cache to avoid decoding the same bitmap on every Activity change
            sIcon = BitmapFactory.decodeResource(resources, R.drawable.icon_mland_home);
        }

        this.setTaskDescription(new ActivityManager.TaskDescription(label, sIcon, aboutPrimaryDark));
    }

    /**
     * Sets the status bar to be light or not. Light status bar means dark icons.
     */

    @TargetApi(Build.VERSION_CODES.O)
    public void activateLightNavigationBar() {
        int oldSystemUiFlags = getWindow().getDecorView().getSystemUiVisibility();
        int newSystemUiFlags = oldSystemUiFlags;
        newSystemUiFlags |= View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR;
        getWindow().setNavigationBarColor(getResources().getColor(android.R.color.white));
        if (newSystemUiFlags != oldSystemUiFlags) {
            final int systemUiFlags = newSystemUiFlags;
            runOnUiThread(() -> getWindow().getDecorView().setSystemUiVisibility(systemUiFlags));
        }
    }

    public void activateLightStatusBar() {
        int oldSystemUiFlags = getWindow().getDecorView().getSystemUiVisibility();
        int newSystemUiFlags = oldSystemUiFlags;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            newSystemUiFlags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
        }
        if (newSystemUiFlags != oldSystemUiFlags) {
            final int systemUiFlags = newSystemUiFlags;
            runOnUiThread(() -> {
                getWindow().getDecorView().setSystemUiVisibility(systemUiFlags);
                activateLightStatusBar();
            });
        }
    }
}
