package com.kyler.mland.egg.tstb;

import android.animation.Animator;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.Toolbar;
import android.text.format.DateUtils;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.kyler.mland.egg.MLandBase;
import com.kyler.mland.egg.R;
import com.kyler.mland.egg.activities.About;
import com.kyler.mland.egg.ui.MLandTextView;

import java.util.Calendar;

public class TimeSensitiveToolbar extends MLandBase {
    private static final AlphaAnimation fadeIn = new AlphaAnimation(0.0f, 1.0f);
    private static final int RevealDuration = 1000;
    private static final int toolBarColorChangeDuration = 1200;
    private static final int statusBarColorChangeDuration = 500;
    private static final int fadeInText = 400;
    private Handler mHandler;
    private Toolbar mToolbar;
    private  ImageView timeOfDayIV;
    private static MLandTextView timeOfDayText;
    private RelativeLayout rl;
    private static final String KEY_LAST_HOUR_COLOR = "last_hour_color";

    private final Runnable mBackgroundColorChanger = new Runnable() {
        @Override
        public void run() {
            setBackgroundColor();
            mHandler.postDelayed(this, BACKGROUND_COLOR_CHECK_DELAY_MILLIS);
        }
    };

    private static final int UNKNOWN_COLOR_ID = 0;
    private static final long BACKGROUND_COLOR_CHECK_DELAY_MILLIS = DateUtils.MINUTE_IN_MILLIS;
    private static final int BACKGROUND_COLOR_INITIAL_ANIMATION_DURATION_MILLIS = 3000;
    private int mLastHourColor = UNKNOWN_COLOR_ID;

    /** The background colors of the app, it changes thru out the day to mimic the sky. **/
    private static final String[] BACKGROUND_SPECTRUM = { "#212121", "#27232e", "#2d253a",
            "#332847", "#382a53", "#3e2c5f", "#442e6c", "#393a7a", "#2e4687", "#235395", "#185fa2",
            "#0d6baf", "#0277bd", "#0d6cb1", "#1861a6", "#23569b", "#2d4a8f", "#383f84", "#433478",
            "#3d3169", "#382e5b", "#322b4d", "#2c273e", "#272430" };

    @Override
    protected int getSelfNavDrawerItem() {
        return NAVDRAWER_ITEM_TSTB;
    }

    @Override
    protected Context getContext() {
        return TimeSensitiveToolbar.this;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time_sensitive_toolbar);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        //    mToolbar.setBackgroundColor(getResources().getColor(android.R.color.transparent));

        timeOfDayIV = (ImageView) findViewById(R.id.timeOfDayIV);
        timeOfDayIV.setVisibility(View.INVISIBLE);

        timeOfDayText = (MLandTextView) findViewById(R.id.timeOfDayText);

        rl = (RelativeLayout) findViewById(R.id.rl_tstb);

        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);

        setBackgroundColor();
    }

        // > greater than
        // < less than
        // :P

   /*     if (hour == 0) {
            setMidnight();
        } else if (hour >= 0 && hour <= 7) {
            setDawn();
        } else if (hour >= 7 && hour <= 12) {

        } else if (hour >= 12 && hour <= 15) {
            setAfternoon();
        } else if (hour >= 15 && hour <= 16) {
            setMidday();
        } else if (hour >= 16 && hour <= 18) {
            setEvening();
        } else if (hour >= 18 && hour <= 21) {
            setDusk();
        } else if (hour >= 21 && hour <= 23) {
            setNighttime();
        }
    } */

    private void setDawn() {
        timeOfDayText.setText(R.string.its_dawn);
        timeOfDayText.startAnimation(fadeIn);

        fadeIn.setDuration(fadeInText);
        fadeIn.setFillAfter(true);

        final Integer colorFrom = getResources().getColor(android.R.color.transparent);
        final Integer colorTo = getResources().getColor(R.color.dawn);
        ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
        colorAnimation.setDuration(toolBarColorChangeDuration);
        colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                mToolbar.setBackgroundColor((Integer) animator.getAnimatedValue());
            }

        });
        colorAnimation.start();

        setDawnStatusbarColor();
    }

    private void setDawnStatusbarColor() {
        final Integer colorFrom = getResources().getColor(R.color.midnight);
        final Integer colorTo = getResources().getColor(R.color.dawn_20);
        ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
        colorAnimation.setDuration(statusBarColorChangeDuration);
        colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.setStatusBarColor((Integer) animator.getAnimatedValue());
            }

        });
        colorAnimation.start();
        setAddDawnIV();
    }

    private void setMorning() {
        final Integer colorFrom = getResources().getColor(android.R.color.transparent);
        final Integer colorTo = getResources().getColor(R.color.morning);
        ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
        colorAnimation.setDuration(toolBarColorChangeDuration);
        colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                mToolbar.setBackgroundColor((Integer) animator.getAnimatedValue());
            }

        });
        colorAnimation.start();
        setMorningStatusbarColor();

        timeOfDayText.setText(R.string.its_morning);
        timeOfDayText.startAnimation(fadeIn);

        fadeIn.setDuration(fadeInText);
        fadeIn.setFillAfter(true);
    }

    private void setMorningStatusbarColor() {
        final Integer colorFrom = getResources().getColor(R.color.dawn);
        final Integer colorTo = getResources().getColor(R.color.morning_20);
        ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
        colorAnimation.setDuration(statusBarColorChangeDuration);
        colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.setStatusBarColor((Integer) animator.getAnimatedValue());
            }

        });
        colorAnimation.start();
        setAddMorningIV();
    }

    private void setAfternoon() {
        final Integer colorFrom = getResources().getColor(android.R.color.transparent);
        final Integer colorTo = getResources().getColor(R.color.afternoon);
        ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
        colorAnimation.setDuration(toolBarColorChangeDuration);
        colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                mToolbar.setBackgroundColor((Integer) animator.getAnimatedValue());
            }

        });
        colorAnimation.start();
        setAfternoonStatusbarColor();

        timeOfDayText.setText(R.string.its_afternoon);
        timeOfDayText.startAnimation(fadeIn);

        fadeIn.setDuration(fadeInText);
        fadeIn.setFillAfter(true);
    }

    private void setAfternoonStatusbarColor() {
        final Integer colorFrom = getResources().getColor(R.color.afternoon);
        final Integer colorTo = getResources().getColor(R.color.afternoon_20);
        ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
        colorAnimation.setDuration(statusBarColorChangeDuration);
        colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.setStatusBarColor((Integer) animator.getAnimatedValue());
            }

        });
        colorAnimation.start();
        setAddAfternoonIV();
    }

    private void setMidday() {
        final Integer colorFrom = getResources().getColor(android.R.color.transparent);
        final Integer colorTo = getResources().getColor(R.color.midday);
        ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
        colorAnimation.setDuration(toolBarColorChangeDuration);
        colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                mToolbar.setBackgroundColor((Integer) animator.getAnimatedValue());
            }

        });
        colorAnimation.start();
        setMiddayStatusbarColor();

        timeOfDayText.setText(R.string.its_midday);
        timeOfDayText.startAnimation(fadeIn);

        fadeIn.setDuration(fadeInText);
        fadeIn.setFillAfter(true);
    }

    @Override
    public void onStart() {
        super.onStart();

        mToolbar.setBackgroundDrawable(null);

        if (mLastHourColor != UNKNOWN_COLOR_ID) {
            mToolbar.setBackgroundColor(mLastHourColor);
        }

        if (mHandler == null) {
            mHandler = new Handler();
        }
        mHandler.postDelayed(mBackgroundColorChanger, BACKGROUND_COLOR_CHECK_DELAY_MILLIS);
    }

    private void setMiddayStatusbarColor() {
        final Integer colorFrom = getResources().getColor(R.color.midday);
        final Integer colorTo = getResources().getColor(R.color.midday_20);
        ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
        colorAnimation.setDuration(statusBarColorChangeDuration);
        colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.setStatusBarColor((Integer) animator.getAnimatedValue());
            }

        });
        colorAnimation.start();
        setAddMiddayIV();
    }

    private void setEvening() {
        final Integer colorFrom = getResources().getColor(android.R.color.transparent);
        final Integer colorTo = getResources().getColor(R.color.evening);
        ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
        colorAnimation.setDuration(toolBarColorChangeDuration);
        colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                mToolbar.setBackgroundColor((Integer) animator.getAnimatedValue());
            }

        });
        colorAnimation.start();
        setEveningStatusbarColor();

        timeOfDayText.setText(R.string.its_evening);
        timeOfDayText.startAnimation(fadeIn);

        fadeIn.setDuration(fadeInText);
        fadeIn.setFillAfter(true);
    }

    private void setEveningStatusbarColor() {
        final Integer colorFrom = getResources().getColor(R.color.evening);
        final Integer colorTo = getResources().getColor(R.color.evening_20);
        ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
        colorAnimation.setDuration(statusBarColorChangeDuration);
        colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.setStatusBarColor((Integer) animator.getAnimatedValue());
            }

        });
        colorAnimation.start();
        setAddEveningIV();
    }

    private void setDusk() {
        final Integer colorFrom = getResources().getColor(android.R.color.transparent);
        final Integer colorTo = getResources().getColor(R.color.dusk);
        ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
        colorAnimation.setDuration(toolBarColorChangeDuration);
        colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                mToolbar.setBackgroundColor((Integer) animator.getAnimatedValue());
            }

        });
        colorAnimation.start();
        setDuskStatusbarColor();

        timeOfDayText.setText(R.string.its_dusk);
        timeOfDayText.startAnimation(fadeIn);

        fadeIn.setDuration(1200);
        fadeIn.setFillAfter(true);
    }

    private void setDuskStatusbarColor() {
        final Integer colorFrom = getResources().getColor(R.color.dusk);
        final Integer colorTo = getResources().getColor(R.color.dusk_20);
        ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
        colorAnimation.setDuration(statusBarColorChangeDuration);
        colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.setStatusBarColor((Integer) animator.getAnimatedValue());
            }

        });
        colorAnimation.start();
        setAddDuskIV();
    }

    private void setNighttime() {
        final Integer colorFrom = getResources().getColor(android.R.color.transparent);
        final Integer colorTo = getResources().getColor(R.color.nighttime);
        ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
        colorAnimation.setDuration(toolBarColorChangeDuration);
        colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                mToolbar.setBackgroundColor((Integer) animator.getAnimatedValue());
            }

        });
        colorAnimation.start();
        setNighttimeStatusbarColor();
    }

    private void setNighttimeStatusbarColor() {
        final Integer colorFrom = getResources().getColor(R.color.nighttime);
        final Integer colorTo = getResources().getColor(R.color.nighttime_20);
        ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
        colorAnimation.setDuration(statusBarColorChangeDuration);
        colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.setStatusBarColor((Integer) animator.getAnimatedValue());
            }

        });
        colorAnimation.start();
        setAddNighttimeIV();
    }

    private void setMidnight() {
        final Integer colorFrom = getResources().getColor(android.R.color.transparent);
        final Integer colorTo = getResources().getColor(R.color.midnight);
        ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
        colorAnimation.setDuration(toolBarColorChangeDuration);
        colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                mToolbar.setBackgroundColor((Integer) animator.getAnimatedValue());

            }

        });
        colorAnimation.start();
        setMidnightStatusbarColor();
    }

    private void setMidnightStatusbarColor() {
        final Integer colorFrom = getResources().getColor(R.color.midnight);
        final Integer colorTo = getResources().getColor(R.color.midnight_20);
        ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
        colorAnimation.setDuration(statusBarColorChangeDuration);
        colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                getWindow().setStatusBarColor((Integer) animator.getAnimatedValue());

            }

        });
        colorAnimation.start();
        setAddMidnightIV();
    }

    private void setAddDawnIV() {
        timeOfDayIV.setImageDrawable(getResources().getDrawable(R.drawable.dawn));
        timeOfDayIV.postDelayed(new Runnable() {

            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public final void run() {
                // get the center for the clipping circle
                final int cx = (timeOfDayIV.getLeft() + timeOfDayIV.getRight()) / 2;
                final int cy = (timeOfDayIV.getTop() + timeOfDayIV.getBottom()) / 2;

                // get the final radius for the clipping circle
                final int finalRadius = Math.max(timeOfDayIV.getWidth(), timeOfDayIV.getHeight());

                // create the animator for this view (the start radius is zero)
                Animator anim =
                        ViewAnimationUtils.createCircularReveal(timeOfDayIV, cx, cy, 0, finalRadius);

                // make the view visible and start the animation
                timeOfDayIV.setVisibility(View.VISIBLE);
                anim.setDuration(RevealDuration);
                anim.start();

            }
        }, 3000);

    }

    private void setAddMorningIV() {
        timeOfDayIV.setImageDrawable(getResources().getDrawable(R.drawable.morning));
        timeOfDayIV.postDelayed(new Runnable() {

            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public final void run() {
                // get the center for the clipping circle
                final int cx = (timeOfDayIV.getLeft() + timeOfDayIV.getRight()) / 2;
                final int cy = (timeOfDayIV.getTop() + timeOfDayIV.getBottom()) / 2;

                // get the final radius for the clipping circle
                final int finalRadius = Math.max(timeOfDayIV.getWidth(), timeOfDayIV.getHeight());

                // create the animator for this view (the start radius is zero)
                Animator anim =
                        ViewAnimationUtils.createCircularReveal(timeOfDayIV, cx, cy, 0, finalRadius);

                // make the view visible and start the animation
                timeOfDayIV.setVisibility(View.VISIBLE);
                anim.setDuration(RevealDuration);
                anim.start();

            }
        }, 3000);

    }

    private void setAddAfternoonIV() {
        timeOfDayIV.setImageDrawable(getResources().getDrawable(R.drawable.afternoon));
        timeOfDayIV.postDelayed(new Runnable() {

            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public final void run() {
                // get the center for the clipping circle
                final int cx = (timeOfDayIV.getLeft() + timeOfDayIV.getRight()) / 2;
                final int cy = (timeOfDayIV.getTop() + timeOfDayIV.getBottom()) / 2;

                // get the final radius for the clipping circle
                final int finalRadius = Math.max(timeOfDayIV.getWidth(), timeOfDayIV.getHeight());

                // create the animator for this view (the start radius is zero)
                Animator anim =
                        ViewAnimationUtils.createCircularReveal(timeOfDayIV, cx, cy, 0, finalRadius);

                // make the view visible and start the animation
                timeOfDayIV.setVisibility(View.VISIBLE);
                anim.setDuration(RevealDuration);
                anim.start();

            }
        }, 3000);

    }

    private void setAddMiddayIV() {
        timeOfDayIV.setImageDrawable(getResources().getDrawable(R.drawable.midday));
        timeOfDayIV.postDelayed(new Runnable() {

            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public final void run() {
                // get the center for the clipping circle
                final int cx = (timeOfDayIV.getLeft() + timeOfDayIV.getRight()) / 2;
                final int cy = (timeOfDayIV.getTop() + timeOfDayIV.getBottom()) / 2;

                // get the final radius for the clipping circle
                final int finalRadius = Math.max(timeOfDayIV.getWidth(), timeOfDayIV.getHeight());

                // create the animator for this view (the start radius is zero)
                Animator anim =
                        ViewAnimationUtils.createCircularReveal(timeOfDayIV, cx, cy, 0, finalRadius);

                // make the view visible and start the animation
                timeOfDayIV.setVisibility(View.VISIBLE);
                anim.setDuration(RevealDuration);
                anim.start();

            }
        }, 3000);

    }

    private void setAddEveningIV() {
        timeOfDayIV.setImageDrawable(getResources().getDrawable(R.drawable.evening));
        timeOfDayIV.postDelayed(new Runnable() {

            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public final void run() {
                // get the center for the clipping circle
                final int cx = (timeOfDayIV.getLeft() + timeOfDayIV.getRight()) / 2;
                final int cy = (timeOfDayIV.getTop() + timeOfDayIV.getBottom()) / 2;

                // get the final radius for the clipping circle
                final int finalRadius = Math.max(timeOfDayIV.getWidth(), timeOfDayIV.getHeight());

                // create the animator for this view (the start radius is zero)
                Animator anim =
                        ViewAnimationUtils.createCircularReveal(timeOfDayIV, cx, cy, 0, finalRadius);

                // make the view visible and start the animation
                timeOfDayIV.setVisibility(View.VISIBLE);
                anim.setDuration(RevealDuration);
                anim.start();

            }
        }, 3000);

    }


    private void setAddDuskIV() {
        timeOfDayIV.setImageDrawable(getResources().getDrawable(R.drawable.dusk));
        timeOfDayIV.postDelayed(new Runnable() {

            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public final void run() {
                // get the center for the clipping circle
                final int cx = (timeOfDayIV.getLeft() + timeOfDayIV.getRight()) / 2;
                final int cy = (timeOfDayIV.getTop() + timeOfDayIV.getBottom()) / 2;

                // get the final radius for the clipping circle
                final int finalRadius = Math.max(timeOfDayIV.getWidth(), timeOfDayIV.getHeight());

                // create the animator for this view (the start radius is zero)
                Animator anim =
                        ViewAnimationUtils.createCircularReveal(timeOfDayIV, cx, cy, 0, finalRadius);

                // make the view visible and start the animation
                timeOfDayIV.setVisibility(View.VISIBLE);
                anim.setDuration(RevealDuration);
                anim.start();

            }
        }, 3000);

    }

    private void setAddNighttimeIV() {
        timeOfDayIV.setImageDrawable(getResources().getDrawable(R.drawable.nighttime));
        timeOfDayIV.postDelayed(new Runnable() {

            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public final void run() {
                // get the center for the clipping circle
                final int cx = (timeOfDayIV.getLeft() + timeOfDayIV.getRight()) / 2;
                final int cy = (timeOfDayIV.getTop() + timeOfDayIV.getBottom()) / 2;

                // get the final radius for the clipping circle
                final int finalRadius = Math.max(timeOfDayIV.getWidth(), timeOfDayIV.getHeight());

                // create the animator for this view (the start radius is zero)
                Animator anim =
                        ViewAnimationUtils.createCircularReveal(timeOfDayIV, cx, cy, 0, finalRadius);

                // make the view visible and start the animation
                timeOfDayIV.setVisibility(View.VISIBLE);
                anim.setDuration(RevealDuration);
                anim.start();

            }
        }, 3000);

        darkenRL();
    }

    private void darkenRL() {
        rl.postDelayed(new Runnable() {

            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public final void run() {
                final Integer colorFrom = getResources().getColor(android.R.color.white);
                final Integer colorTo = getResources().getColor(R.color.lightish_black);
                ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
                colorAnimation.setDuration(statusBarColorChangeDuration);
                colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

                    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    public void onAnimationUpdate(ValueAnimator animator) {
                        rl.setBackgroundColor((Integer) animator.getAnimatedValue());
                    }

                });
                colorAnimation.start();
            }
        }, 4500);

        fadeInTextView();
    }

    private void fadeInTextView() {
        timeOfDayText.postDelayed(new Runnable() {

            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public final void run() {

                timeOfDayText.setTextColor(getResources().getColor(android.R.color.white));
                timeOfDayText.setText(R.string.its_nighttime);
                timeOfDayText.startAnimation(fadeIn);

                fadeIn.setDuration(1200);
                fadeIn.setFillAfter(true);

            }
        }, 4000);

        setNavBarNightColor();
    }

    private void setNavBarNightColor() {
        final Integer colorFrom = getResources().getColor(android.R.color.black);
        final Integer colorTo = getResources().getColor(R.color.lightish_black_darker);
        ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
        colorAnimation.setDuration(statusBarColorChangeDuration);
        colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                getWindow().setNavigationBarColor((Integer) animator.getAnimatedValue());
            }

        });
        colorAnimation.start();
    }

    private void setAddMidnightIV() {
        timeOfDayIV.setImageDrawable(getResources().getDrawable(R.drawable.midnight));
        timeOfDayIV.postDelayed(new Runnable() {

            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public final void run() {
                // get the center for the clipping circle
                final int cx = (timeOfDayIV.getLeft() + timeOfDayIV.getRight()) / 2;
                final int cy = (timeOfDayIV.getTop() + timeOfDayIV.getBottom()) / 2;

                // get the final radius for the clipping circle
                final int finalRadius = Math.max(timeOfDayIV.getWidth(), timeOfDayIV.getHeight());

                // create the animator for this view (the start radius is zero)
                Animator anim =
                        ViewAnimationUtils.createCircularReveal(timeOfDayIV, cx, cy, 0, finalRadius);

                // make the view visible and start the animation
                timeOfDayIV.setVisibility(View.VISIBLE);
                anim.setDuration(RevealDuration);
                anim.start();

            }
        }, 3000);

    }
    private void setBackgroundColor() {
        final int duration;
        if (mLastHourColor == UNKNOWN_COLOR_ID) {
            mLastHourColor = getResources().getColor(R.color.tstb_default_toolbar_color);
            duration = BACKGROUND_COLOR_INITIAL_ANIMATION_DURATION_MILLIS;
        } else {
            duration = getResources().getInteger(android.R.integer.config_longAnimTime);
        }
        final int currHourColor = getCurrentHourColor();
        if (mLastHourColor != currHourColor) {
            final ObjectAnimator animator = ObjectAnimator.ofInt(getWindow().getDecorView(),
                    "backgroundColor", mLastHourColor, currHourColor);
            animator.setDuration(duration);
            animator.setEvaluator(new ArgbEvaluator());
            animator.start();
            mLastHourColor = currHourColor;

            mToolbar.setBackgroundColor(currHourColor);
        }
    }

    private static int getCurrentHourColor() {
        final int hourOfDay = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        return Color.parseColor(BACKGROUND_SPECTRUM[hourOfDay]);
    }

    public static int getNextHourColor() {
        final int currHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        return Color.parseColor(BACKGROUND_SPECTRUM[currHour < 24 ? currHour + 1 : 1]);
    }
}
