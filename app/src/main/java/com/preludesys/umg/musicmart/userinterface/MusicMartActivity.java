package com.preludesys.umg.musicmart.userinterface;

import android.app.Activity;
import android.app.ProgressDialog;

import com.preludesys.umg.musicmart.MusicMartApplication;
import com.preludesys.umg.musicmart.listener.TaskProgressListener;


public abstract class MusicMartActivity extends Activity {
	public static final String PREFS_NAME = "MyPrefFile";
		
	public MusicMartApplication getMusicMartApplication(){
		return (MusicMartApplication) getApplication();
	}

	protected TaskProgressListener getTaskProgressListener(){
		TaskProgressListener taskProgessListener = new TaskProgressListener() {
			private ProgressDialog dialog;
			@Override
			public void setProgressDialog(ProgressDialog dialog) {
				System.out.println(">>>>>> Inside Set Progress Dialog ");
				this.dialog = dialog;
			}
			
			@Override
			public void beginProgress() {
				System.out.println(">>>>>>>>> Begin Progress Dialog ");
				dialog.show();
			}
			
			@Override
			public void endProgress() {
				System.out.println(">>>>>>>>> End Progress Dialog ");
				dialog.dismiss();
			}
		};
		ProgressDialog progressDialog = new ProgressDialog(this);
		progressDialog.setMessage("Loading...");
		progressDialog.setCancelable(false);
		progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		progressDialog.setProgress(0); // set percentage completed to 0%
		taskProgessListener.setProgressDialog(progressDialog);
		return taskProgessListener;
	}
	
}
