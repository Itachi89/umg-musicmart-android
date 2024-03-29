package com.preludesys.umg.musicmart.userinterface.activity;

import android.support.v7.app.ActionBarActivity;

import com.preludesys.umg.musicmart.MusicMartApplication;


public abstract class MusicMartActivity extends ActionBarActivity {
	public static final String PREFS_NAME = "MyPrefFile";
		
	public MusicMartApplication getMusicMartApplication(){
		return (MusicMartApplication) getApplication();
	}

    /*

    public TaskProgressListener getTaskProgressListener(){
        TaskProgressListener taskProgessListener = new TaskProgressListener() {
            private ProgressDialog dialog;

            @Override
            public void setProgressDialog(ProgressDialog dialog) {
                Log.d(this.getClass().toString(), ">>>>>> Inside Set Progress Dialog ");
                this.dialog = dialog;
            }

            @Override
            public void beginProgress() {
                Log.d(this.getClass().toString(), ">>>>>>>>> Begin Progress Dialog ");
                dialog.show();
            }

            @Override
            public void endProgress() {
                Log.d(this.getClass().toString(), ">>>>>>>>> End Progress Dialog ");
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



    public abstract PostTaskExecuteListener getPostTaskExecutionListener();
    */
}
