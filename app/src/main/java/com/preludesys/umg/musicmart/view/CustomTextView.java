package com.preludesys.umg.musicmart.view;

/**
 * Created by varunsundaramoorthy on 4/24/14.
 */

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

public class CustomTextView extends TextView {
    private static final String TAG = "TextView";

    public CustomTextView(Context context) {
        super(context);
        setCustomFont(context);
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setCustomFont(context);
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setCustomFont(context);
    }

    private void setCustomFont(Context ctx) {
        Typeface tf = null;
        try {
            tf = Typeface.createFromAsset(ctx.getAssets(), "fonts/hc.ttf");
            setTag(tf);
            Log.d(this.getClass().toString(), ">>>>>>>>>>> Finished Setting Font");
        } catch (Exception e) {
            Log.e(TAG, "Could not get typeface: "+e.getMessage());
        }
    }

}