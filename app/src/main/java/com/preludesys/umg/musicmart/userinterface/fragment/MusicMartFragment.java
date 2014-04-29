package com.preludesys.umg.musicmart.userinterface.fragment;

import android.app.Fragment;

import com.preludesys.umg.musicmart.userinterface.activity.MusicMartActivity;

/**
 * Created by varunsundaramoorthy on 4/28/14.
 */
public abstract class MusicMartFragment<T> extends Fragment {

    public MusicMartActivity getMusicMartActivity() {
        return (MusicMartActivity) getActivity();
    }

}
