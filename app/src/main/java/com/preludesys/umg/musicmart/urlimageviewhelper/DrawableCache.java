package com.preludesys.umg.musicmart.urlimageviewhelper;

import android.graphics.drawable.Drawable;

public final class DrawableCache extends SoftReferenceHashTable<String, Drawable> {
    private static DrawableCache mInstance = new DrawableCache();
    
    public static DrawableCache getInstance() {
        return mInstance;
    }
    
    private DrawableCache() {
    }
}
