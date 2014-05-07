package com.preludesys.umg.musicmart.listener;

import android.app.ProgressDialog;

public interface TaskProgressListener {
    void setProgressDialog(ProgressDialog dialog);

    public void beginProgress();
	public void endProgress();
}
