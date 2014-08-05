package com.preludesys.umg.musicmart.util;

import android.graphics.Paint;
import android.widget.TextView;

/**
 * Created by varunsundaramoorthy on 5/8/14.
 */
public class FontHelper {

public static void sePaintFlags(TextView textView){
    int flags = textView.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG
            | Paint.ANTI_ALIAS_FLAG;
    textView.setPaintFlags(flags) ;
}

}
