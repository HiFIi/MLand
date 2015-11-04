package com.kyler.mland.egg.activities;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.kyler.mland.egg.MLandBase;
import com.kyler.mland.egg.MLandHome;
import com.kyler.mland.egg.R;

/**
 * Created by kyler on 10/26/15.
 */
public class Home extends MLandBase {
    MLandHome mLandHome;

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
        super.onCreate(savedInstanceState);
        super.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        setContentView(R.layout.home);
        getSupportActionBar().setTitle(null);
        mLandHome = (MLandHome) findViewById(R.id.world2);
        mLandHome.hideSplash();

    }
}
