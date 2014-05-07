package com.preludesys.umg.musicmart.task;

import android.os.AsyncTask;

import com.preludesys.umg.musicmart.MusicMartApplication;
import com.preludesys.umg.musicmart.listener.PostTaskExecuteListener;
import com.preludesys.umg.musicmart.listener.TaskProgressListener;

public abstract class MusicMartActivityAsyncTask<Params, Progress, Result> extends AsyncTask<Params, Progress, Result> {
	private TaskProgressListener taskProgressListener;
	private PostTaskExecuteListener<Result> postTaskExecuteListener;
	private MusicMartApplication application;

	public MusicMartActivityAsyncTask(MusicMartApplication application){
		this.application = application;
	}

	public MusicMartApplication getApplication() {
		return application;
	}

	public void setApplication(MusicMartApplication application) {
		this.application = application;
	}

	public TaskProgressListener getTaskProgressListener() {
		return taskProgressListener;
	}

	public void setTaskProgressListener(TaskProgressListener taskProgressListener) {
		this.taskProgressListener = taskProgressListener;
	}

	public PostTaskExecuteListener<Result> getPostTaskExecuteListener() {
		return postTaskExecuteListener;
	}

	public void setPostTaskExecuteListener(PostTaskExecuteListener<Result> postTaskExecuteListener){
		this.postTaskExecuteListener = postTaskExecuteListener;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		if(getTaskProgressListener() != null){
			getTaskProgressListener().beginProgress();
		}
	}

	@Override
	protected void onPostExecute(Result result) {
		if(getTaskProgressListener() != null){
			getTaskProgressListener().endProgress();
		}
		getPostTaskExecuteListener().performOperation(result);
	}

}
