package com.kyler.mland.egg;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toolbar;

/**
 * Created by kyler on 10/6/15.
 */
public class About extends MLandBase {

    @Override
    protected int getSelfNavDrawerItem() {
        return NAVDRAWER_ITEM_ABOUT;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        setContentView(R.layout.about);
        getSupportActionBar().setTitle(null);
    }
}