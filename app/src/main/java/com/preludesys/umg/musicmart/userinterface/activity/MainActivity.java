package com.preludesys.umg.musicmart.userinterface.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.preludesys.umg.musicmart.listener.PostTaskExecuteListener;

public class MainActivity extends MusicMartActivity {
	public static final String PREFS_NAME = "MyPrefFile";
	private static final String PREF_USERNAME = "";
	private static final String PREF_PASSWORD = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		 Log.d(this.getClass().toString(), ":::::::::Inside login activity:::::::::");
		
		
		/*******************************/
		
			
			SharedPreferences pref = getSharedPreferences(PREFS_NAME,MODE_PRIVATE);   
			String username = pref.getString(PREF_USERNAME, null);
			String password = pref.getString(PREF_PASSWORD, null);
			Log.d(this.getClass().toString(), "username::::::::::::" + username);
			Log.d(this.getClass().toString(), "password::::::::::"+password);
			if (username == null || password == null) {
					//Prompt for username and password
				Intent SlidingIntent = new Intent(MainActivity.this,
						ListScreen.class);
				startActivity(SlidingIntent);
				
				}
		else{
			Intent loginIntent = new Intent(MainActivity.this,
					ListScreen.class);
			startActivity(loginIntent);
		}
	}

    @Override
    public PostTaskExecuteListener getPostTaskExecutionListener(){
        return null;
    }
}
