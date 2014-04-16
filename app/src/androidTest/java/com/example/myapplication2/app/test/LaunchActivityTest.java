package com.example.myapplication2.app.test;

import android.test.ActivityInstrumentationTestCase2;

import com.preludesys.umg.musicmart.userinterface.MainActivity;


public class LaunchActivityTest
        extends ActivityInstrumentationTestCase2<MainActivity> {

    private MainActivity mActivity; // the activity under test

    public LaunchActivityTest() {
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        setActivityInitialTouchMode(false);
        mActivity = this.getActivity();
    }

    public void testPreconditions() {
        assertNotNull(mActivity);
    }
}