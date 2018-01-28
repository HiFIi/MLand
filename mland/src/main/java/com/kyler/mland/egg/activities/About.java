package com.kyler.mland.egg.activities;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;

import com.kyler.mland.egg.BuildConfig;
import com.kyler.mland.egg.MLandBase;
import com.kyler.mland.egg.R;
import com.kyler.mland.egg.ui.MLandTextView;

/**
 * Created by kyler on 10/6/15.
 */
@SuppressWarnings({"ALL", "DefaultFileTemplate"})
public class About extends MLandBase {
    private static MLandTextView versionCodeTV;

    @Override
    protected int getSelfNavDrawerItem() {
        return NAVDRAWER_ITEM_ABOUT;
    }

    @Override
    protected Context getContext() {
        return About.this;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_MLand_Home);
        super.onCreate(savedInstanceState);

        versionCodeTV = (MLandTextView) findViewById(R.id.versionCodeTV);

        versionCodeTV.setText(BuildConfig.VERSION_CODE);

        setContentView(R.layout.about);
        super.getWindow()
                .getDecorView()
                .setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        // I should perhaps do some research to see whether or not
        // setting the SupportActionBars title to an empty string is
        // more efficient than *.setTitle(null);

        getSupportActionBar().setTitle(null);

        Resources resources = this.getResources();
        String label = resources.getString(this.getApplicationInfo().labelRes);
        final int aboutPrimaryDark = resources.getColor(R.color.colorPrimaryDark);
    }
}
