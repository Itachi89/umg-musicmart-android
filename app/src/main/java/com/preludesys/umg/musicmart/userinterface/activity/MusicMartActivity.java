package com.preludesys.umg.musicmart.userinterface.activity;

import android.app.ProgressDialog;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import com.preludesys.umg.musicmart.MusicMartApplication;
import com.preludesys.umg.musicmart.listener.PostTaskExecuteListener;
import com.preludesys.umg.musicmart.listener.TaskProgressListener;


public abstract class MusicMartActivity extends ActionBarActivity {
	public static final String PREFS_NAME = "MyPrefFile";
		
	public MusicMartApplication getMusicMartApplication(){
		return (MusicMartApplication) getApplication();
	}


	
}
