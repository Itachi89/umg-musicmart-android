package com.preludesys.umg.musicmart.task;

import android.os.AsyncTask;

import com.preludesys.umg.musicmart.userinterface.fragment.MusicMartTaskFragment;

public abstract class MusicMartFragmentAsyncTask<Params, Progress, Result> extends AsyncTask<Params, Progress, Result> {
	private MusicMartTaskFragment taskFragment;
    int progress = 0;

	public MusicMartFragmentAsyncTask(MusicMartTaskFragment taskFragment){
		this.taskFragment = taskFragment;
	}

	public MusicMartTaskFragment getFragment(){
        return this.taskFragment;
    }

    public void setFragment(MusicMartTaskFragment taskFragment){
        this.taskFragment = taskFragment;
    }

    @Override
    protected void onProgressUpdate(Progress... values)
    {
        if (taskFragment == null)
            return;
        taskFragment.updateProgress(progress);
    }


    protected void onPostExecute(Result result)
    {
        if (taskFragment == null)
            return;
        taskFragment.taskFinished(result);
    }
	
}
