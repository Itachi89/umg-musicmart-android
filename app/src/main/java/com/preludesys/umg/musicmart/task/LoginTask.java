package com.preludesys.umg.musicmart.task;


import com.preludesys.umg.musicmart.MusicMartApplication;
import com.preludesys.umg.musicmart.model.User;

/**
 * @author Jay Palaniappan
 * 
 */
public class LoginTask extends MusicMartActivityAsyncTask<String, Void, User> {

	public LoginTask(MusicMartApplication application) {
		 super(application);
	}

	@Override
	protected User doInBackground(String... loginParameters) {
		System.out.println(">>>>>>Inside LoginTask - doInBackground");
		User user = new User();
		user.setUserName(loginParameters[0]);
		user.setPassword(loginParameters[1]);
		/*try {
			user = getApplication().getTodDoWebService().authenticate(user);
		} catch (AnBException e) {
			e.printStackTrace();
		}*/
		System.out
				.println(">>>>>>Inside LoginTask - Logging process complete "
						+ user.getUserId());
		return user;
	}
}
