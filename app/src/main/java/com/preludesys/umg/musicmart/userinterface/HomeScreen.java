package com.preludesys.umg.musicmart.userinterface;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.preludesys.anb.listener.PostTaskExecuteListener;
import com.preludesys.anb.model.Home;
import com.preludesys.anb.task.AnBHomeTask;

public class HomeScreen extends MusicMartActivity {
	private ImageButton loginButton;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_screen);
		loginButton = (ImageButton) findViewById(R.id.loginButton);

		loginButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				System.out.println(":::::::::Inside on click home:::::::::");
				AnBHomeTask homeTask = new AnBHomeTask(getAnBApplication());
				homeTask.setTaskProgressListener(getTaskProgressListener());
				homeTask.setPostTaskExecuteListener(new PostTaskExecuteListener<Home>() {
							public void performOperation(Home home) {
							try{
									Intent intent = new Intent(HomeScreen.this, ListScreen.class);
									System.out.println("home data:::::"+home.getModuleList().get(0));
									Bundle bundle = new Bundle();
									bundle.putSerializable("modules", home.getModuleList().get(0));
									intent.putExtras(bundle);
									intent.putExtra("module", home.getModuleList().get(0));
									startActivity(intent);
								} 
								catch (Exception e) {
									System.out.println("Error in Home Listener : " + e);
								}
							}
						});
				homeTask.execute("");
			}
		});
	}

	/**
	 * Adding menu to Homescreen
	 */

	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.homemenu, menu);
		return true;
	}

	public void logout() {
		Intent loginIntent = new Intent(HomeScreen.this, LoginScreen.class);
		startActivity(loginIntent);
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle item selection
		switch (item.getItemId()) {
		case R.id.logout:
			logout();
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void onBackPressed() {
		// Do Here what ever you want do on back press;
	}
	

}
