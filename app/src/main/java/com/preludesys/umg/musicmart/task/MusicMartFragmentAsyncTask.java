package com.preludesys.umg.musicmart.task;

import android.os.AsyncTask;

import com.preludesys.umg.musicmart.userinterface.fragment.MusicMartTaskFragment;

public abstract class MusicMartFragmentAsyncTask<Params, Result> extends AsyncTask<Params, Integer, Result> {
	private MusicMartTaskFragment<Params, Result> taskFragment;
    int progress = 0;

	public MusicMartTaskFragment getFragment(){
        return this.taskFragment;
    }

    public void setFragment(MusicMartTaskFragment taskFragment){
        this.taskFragment = taskFragment;
    }

    @Override
    protected void onProgressUpdate(Integer... values)
    {
        if (taskFragment == null)
            return;
        taskFragment.updateProgress(progress);
    }


    protected void onPostExecute(Result result)
    {
        if (taskFragment == null)
            return;
        taskFragment.performOperation(result);
    }
	
}
