package com.eljebo.common.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

public class CustomButton extends android.support.v7.widget.AppCompatButton {


    public CustomButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        // TODO Auto-generated constructor stub
    }
    public CustomButton(Context context) {
        super(context);
        init();
        // TODO Auto-generated constructor stub
    }
    public CustomButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
        // TODO Auto-generated constructor stub
    }
    private void init(){
        Typeface font_type=Typeface.createFromAsset(getContext().getAssets(), "fonts/unicodearialr.ttf");
        setTypeface(font_type);
    }
}