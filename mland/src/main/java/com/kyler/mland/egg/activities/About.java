package com.kyler.mland.egg.activities;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.kyler.mland.egg.MLandBase;
import com.kyler.mland.egg.R;

/**
 * Created by kyler on 10/6/15.
 */
public class About extends MLandBase {

    public static void styleRecentTasksEntry(Activity activity) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            return;
        }

        Resources resources = activity.getResources();
        String label = resources.getString(activity.getApplicationInfo().labelRes);
        final int aboutPrimaryDark = resources.getColor(R.color.tealish_green__primaryDark);
        activity.setTaskDescription(new ActivityManager.TaskDescription(label, null, aboutPrimaryDark));
    }

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
        super.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        setContentView(R.layout.about);
        getSupportActionBar().setTitle(null);
    }
}