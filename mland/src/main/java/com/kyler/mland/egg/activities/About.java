package com.kyler.mland.egg.activities;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;

import com.kyler.mland.egg.MLandBase;
import com.kyler.mland.egg.R;

/**
 * Created by kyler on 10/6/15.
 */
@SuppressWarnings({"ALL", "DefaultFileTemplate"})
public class About extends MLandBase {

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

        setContentView(R.layout.about);
        super.getWindow()
                .getDecorView()
                .setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        Resources resources = this.getResources();
        String label = resources.getString(this.getApplicationInfo().labelRes);
        final int aboutPrimaryDark = resources.getColor(R.color.colorPrimaryDark);
    }
}
