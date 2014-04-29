package com.preludesys.umg.musicmart.userinterface.fragment;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.util.Log;

import com.preludesys.umg.musicmart.listener.PostTaskExecuteListener;
import com.preludesys.umg.musicmart.listener.TaskProgressListener;
import com.preludesys.umg.musicmart.userinterface.activity.MusicMartActivity;

/**
 * Created by varunsundaramoorthy on 4/28/14.
 */
public abstract class MusicMartFragment extends Fragment {


    public MusicMartActivity getMusicMartActivity() {
        return (MusicMartActivity) getActivity();
    }

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

        ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setProgress(0); // set percentage completed to 0%
        taskProgessListener.setProgressDialog(progressDialog);
        return taskProgessListener;
    }


    public abstract PostTaskExecuteListener getPostTaskExecutionListener();

}
