package com.preludesys.umg.musicmart.userinterface;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class AnBAndroidRest extends Activity {
	public static final String PREFS_NAME = "MyPrefFile";
	private static final String PREF_USERNAME = "";
	private static final String PREF_PASSWORD = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		 System.out.println(":::::::::Inside login activity:::::::::");
		
		
		/*******************************/
		
			
			SharedPreferences pref = getSharedPreferences(PREFS_NAME,MODE_PRIVATE);   
			String username = pref.getString(PREF_USERNAME, null);
			String password = pref.getString(PREF_PASSWORD, null);
			System.out.println("username::::::::::::"+username);
			System.out.println("password::::::::::"+password);
			if (username == null || password == null) {
					//Prompt for username and password
				Intent loginIntent = new Intent(AnBAndroidRest.this,
						LoginScreen.class);
				startActivity(loginIntent);
				
				}
		else{
			Intent loginIntent = new Intent(AnBAndroidRest.this,
					HomeScreen.class);
			startActivity(loginIntent);
		}
		


	}
}
