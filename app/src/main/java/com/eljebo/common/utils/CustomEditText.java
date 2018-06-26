package com.eljebo.common.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

/**
 * Created by TOXSL\parwinder.deep on 14/12/17.
 */

public class CustomEditText extends  android.support.v7.widget.AppCompatEditText {

    Context context;
    int defStyleAttr;
    private AttributeSet attrs;
    String font;

    public CustomEditText(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        this.attrs = attrs;
        init();
    }

    public CustomEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        this.attrs = attrs;
        this.defStyleAttr = defStyleAttr;
        init();
    }

    @Override
    public void setTypeface(Typeface tf, int style) {

        tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/unicodearialr.ttf");
        super.setTypeface(tf, style);
    }


    private void init() {
        Typeface font = Typeface.createFromAsset(getContext().getAssets(), "fonts/unicodearialr.ttf");
        this.setTypeface(font);
    }

    @Override
    public void setTypeface(Typeface tf) {
        tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/unicodearialr.ttf");
        super.setTypeface(tf);
    }
}