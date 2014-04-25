package com.preludesys.umg.musicmart;

import android.app.Application;

import com.preludesys.umg.musicmart.helper.font.CustomFontFactory;
import com.preludesys.umg.musicmart.model.User;

import java.util.HashMap;
import java.util.Map;

public class MusicMartApplication extends Application {
	
	private User user;
    private Map<String, String> applicationProperties;
    private CustomFontFactory factory;

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
        factory = new CustomFontFactory(this);
		// Initialize the singletons so their instances
		// are bound to the application process.
		initialize();
	}

	protected void initialize()
	{
		initializeApplicationProperties();
		/*RestUtil.setBaseUrl(ApplicationProperties.getRestServicesUrl());
		System.out.println(">>>>>>>>>> Base URL " + RestUtil.getBaseUrl());
		this.todDoWebService = new AnBToDoRestWebServiceClient();
		this.todDoWebService.setHttpService(new HttpService());
		*/
	}

    public  android.view.LayoutInflater getLayoutInflater() {
        return this.factory.getLayoutInflater();
    }

    public CustomFontFactory getCustomFontFactory() {
        return this.factory;
    }


	protected void initializeApplicationProperties(){
		Map<String, String> applicationProperties = new HashMap<String, String>();
        applicationProperties.put("rest.services.url", "http://192.168.3.226:8280/nbc-timekeeper-webservices/rest/todo");
		//ApplicationProperties.initialize(properties);
	}
	
}
