package com.eljebo.common.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;


/**
 * Created by TOXSL\parwinder.deep on 14/12/17.
 */

public class CustomTextView extends  TextView {

    public CustomTextView(Context context) {
        super(context);
        if (!isInEditMode()) {
            setFont();
        }
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode()) {
            setFont();
        }
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        if (!isInEditMode()) {
            setFont();
        }
    }

    private void setFont() {
        Typeface font = Typeface.createFromAsset(getContext().getAssets(), "fonts/unicodearialr.ttf");
        setTypeface(font, Typeface.NORMAL);
    }


}
