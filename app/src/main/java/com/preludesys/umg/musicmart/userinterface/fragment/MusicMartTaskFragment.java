package com.preludesys.umg.musicmart.userinterface.fragment;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.preludesys.umg.musicmart.R;
import com.preludesys.umg.musicmart.listener.CallBackListener;
import com.preludesys.umg.musicmart.listener.PostTaskExecuteListener;
import com.preludesys.umg.musicmart.task.MusicMartFragmentAsyncTask;

// This and the other inner class can be in separate files if you like.
// There's no reason they need to be inner classes other than keeping everything together.
public class MusicMartTaskFragment<Params, Result> extends android.support.v4.app.DialogFragment implements PostTaskExecuteListener<Result>
{
    private int fragmentId;  // The request code
    ProgressBar progressBar;
    CallBackListener callBackListener;
    MusicMartFragmentAsyncTask<Params, Result> task;
    Params[] values;

    public MusicMartTaskFragment(CallBackListener<Result> callBackListener, MusicMartFragmentAsyncTask task,int fragmentId){
        Log.d(this.getClass().toString(), ">>>>>> Inside Constructor");
        this.fragmentId = fragmentId;
        this.callBackListener = callBackListener;
        setTask(task);
    }

    public void setTask(MusicMartFragmentAsyncTask task)
    {
        this.task = task;
        task.setFragment(this);
    }

    public void setValues(Params[] values){
        this.values = values;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Log.d(this.getClass().toString(), ">>>>>> Inside onCreate");
        setRetainInstance(true);
        if (task != null) {
            Log.d(this.getClass().toString(), ">>>>>> Inside Task is executing");
            task.execute(values);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        Log.d(this.getClass().toString(), ">>>>>> Inside onCreateView");
        View view =  inflater.inflate(R.layout.fragment_task, container);
        progressBar = (ProgressBar)view.findViewById(R.id.progressBar);

        getDialog().setTitle("Progress Dialog");

        // If you're doing a long task, you probably don't want people to cancel
        // it just by tapping the screen!
        getDialog().setCanceledOnTouchOutside(false);

        return view;
    }

    // This is to work around what is apparently a bug. If you don't have it
    // here the dialog will be dismissed on rotation, so tell it not to dismiss.
    @Override
    public void onDestroyView()
    {
        if (getDialog() != null && getRetainInstance())
            getDialog().setDismissMessage(null);
        super.onDestroyView();
    }

    // Also when we are dismissed we need to cancel the task.
    @Override
    public void onDismiss(DialogInterface dialog)
    {
        super.onDismiss(dialog);
        // If true, the thread is interrupted immediately, which may do bad things.
        // If false, it guarantees a result is never returned (onPostExecute() isn't called)
        // but you have to repeatedly call isCancelled() in your doInBackground()
        // function to check if it should exit. For some tasks that might not be feasible.
        if (task != null) {
            task.cancel(false);
        }

        // You don't really need this if you don't want.
        if (getTargetFragment() != null)
            getTargetFragment().onActivityResult(fragmentId, Activity.RESULT_CANCELED, null);
    }

    @Override
    public void onResume()
    {
        super.onResume();
        // This is a little hacky, but we will see if the task has finished while we weren't
        // in this activity, and then we can dismiss ourselves.
        if (task == null)
            dismiss();
    }

    // This is called by the AsyncTask.
    public void updateProgress(Integer percent)
    {
        progressBar.setProgress(percent);
    }

    // This is also called by the AsyncTask.
    public void performOperation(Result result)
    {
        callBackListener.callBack(result);

        // Make sure we check if it is resumed because we will crash if trying to dismiss the dialog
        // after the user has switched to another app.
        if (isResumed())
            dismiss();

        // If we aren't resumed, setting the task to null will allow us to dimiss ourselves in
        // onResume().
        task = null;

        // Tell the fragment that we are done.
        if (getTargetFragment() != null)
            getTargetFragment().onActivityResult(fragmentId, Activity.RESULT_OK, null);

    }
}