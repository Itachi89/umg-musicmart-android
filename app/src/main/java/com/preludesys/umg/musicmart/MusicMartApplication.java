package com.preludesys.umg.musicmart;

import android.app.Application;
import android.graphics.Typeface;

import com.preludesys.umg.musicmart.model.User;

import java.util.HashMap;
import java.util.Map;

public class MusicMartApplication extends Application {
	
	private User user;
    private Map<String, String> applicationProperties;
    Typeface tf;

    public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public void onCreate()
	{
		super.onCreate();
   		// Initialize the singletons so their instances
		// are bound to the application process.
		initialize();
	}

	protected void initialize()
	{
		initializeApplicationProperties();
	}

    public Typeface getTypeFace(){
        if(tf == null)
            tf = Typeface.createFromAsset(getAssets(), "fonts/hc.ttf");
        return tf;
    }

	protected void initializeApplicationProperties(){
		Map<String, String> applicationProperties = new HashMap<String, String>();
        applicationProperties.put("rest.services.url", "http://192.168.3.226:8280/nbc-timekeeper-webservices/rest/todo");
		//ApplicationProperties.initialize(properties);
	}
	
}
