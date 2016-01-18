package com.kyler.mland.egg.activities;

import android.content.Context;
import android.os.Bundle;

import com.kyler.mland.egg.MLandBase;
import com.kyler.mland.egg.R;

/**
 * Created by kyler on 10/6/15.
 */
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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
        getSupportActionBar().setTitle(null);
    }
}