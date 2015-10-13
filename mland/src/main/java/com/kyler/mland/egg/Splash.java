package com.kyler.mland.egg;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * Created by hoopz on 7/13/2015.
 */

public class Splash extends Activity {

    private static int SPLASH_LAUNCH_DELAY = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent i = new Intent(Splash.this, MLandBase.class);
                startActivity(i);
                //    finish();
            }
        }, SPLASH_LAUNCH_DELAY);
    }
}
