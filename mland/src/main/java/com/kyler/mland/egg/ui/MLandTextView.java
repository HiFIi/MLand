package com.kyler.mland.egg.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import com.kyler.mland.egg.R;

public class MLandTextView extends TextView {

    public MLandTextView(Context context, AttributeSet attrs,
                         int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }

    public MLandTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        isInEditMode();
        init(attrs);

    }

    public MLandTextView(Context context) {
        super(context);
        isInEditMode();
        init(null);
    }

    private void init(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs,
                    R.styleable.MLandTextView);
            String fontName = a
                    .getString(R.styleable.MLandTextView_fontName);
            if (fontName != null) {
                Typeface myTypeface = Typeface.createFromAsset(getContext()
                        .getAssets(), "fonts/" + fontName);
                setTypeface(myTypeface);
            }
            a.recycle();
        }
    }

}