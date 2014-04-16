package com.preludesys.umg.musicmart.userinterface;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;

import com.preludesys.anb.listener.PostTaskExecuteListener;
import com.preludesys.anb.model.User;
import com.preludesys.anb.task.AnBLoginTask;
import com.preludesys.umg.musicmart.R;

public class LoginScreen extends MusicMartActivity {

	private ImageButton loginButton;
	private EditText userName;
	private EditText password;
	private CheckBox autoSign;
	private boolean rememberMe;
	private static final String PREF_USERNAME = "";
	private static final String PREF_PASSWORD = "";

	protected void onCreate(Bundle savedInstanceState) {

		System.out.println(":::::::::Inside on create:::::::::");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_screen);

		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}

		loginButton = (ImageButton) findViewById(R.id.loginButton);

		loginButton.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus) {
					loginButton.setImageResource(R.drawable.loginhover);
				} else {
					loginButton.setImageResource(R.drawable.loginbutton);
				}
			}
		});

		loginButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				System.out.println(":::::::::Inside on click:::::::::");
				userName = (EditText) findViewById(R.id.txt_userName);
				password = (EditText) findViewById(R.id.txt_password);
				autoSign = (CheckBox) findViewById(R.id.chk_autoSignIn);
				rememberMe = autoSign.isChecked();
				if (rememberMe) {
					getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
							.edit()
							.putString(PREF_USERNAME,
									userName.getText().toString().trim())
							.putString(PREF_PASSWORD,
									password.getText().toString().trim()).commit();
				}
				AnBLoginTask loginTask = new AnBLoginTask(getAnBApplication());
				loginTask.setTaskProgressListener(getTaskProgressListener());
				loginTask.setPostTaskExecuteListener(getPostTaskExecuteListener());
				loginTask.execute(userName.getText().toString().trim(), password.getText().toString().trim());
			}
		});
	}

	protected PostTaskExecuteListener<User> getPostTaskExecuteListener(){
		PostTaskExecuteListener<User> listener = new PostTaskExecuteListener<User>() {
			public void performOperation(User user) {
				try{
					System.out.println(":::::::::Inside on Login Task Post Execute:::::::::" + user.getUserId() +"::::::::"+ user.getEmail());
					if (user.getUserId() != null) {
						getAnBApplication().setUser(user);
						Intent loginIntent = new Intent(LoginScreen.this, HomeScreen.class);
						startActivity(loginIntent);
					} 
					else{
						CustomAlert.alertDisplay(LoginScreen.this,
							"Invalid Username / Password");
					}
				} 
				catch (Exception e) {
					System.out.println("Error in login listener : " + e);
				}
			}
		};
		
		return listener;
	}
}
