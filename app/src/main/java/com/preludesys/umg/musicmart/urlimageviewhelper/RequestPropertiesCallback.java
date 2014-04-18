package com.preludesys.umg.musicmart.urlimageviewhelper;

import android.content.Context;

import org.apache.http.NameValuePair;

import java.util.ArrayList;

/**
 * Created by varunsundaramoorthy on 4/17/14.
 */
public interface RequestPropertiesCallback {

    public ArrayList<NameValuePair> getHeadersForRequest(Context context, String url);
}
