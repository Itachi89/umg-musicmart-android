package com.preludesys.umg.musicmart.task;

import java.util.List;


public interface AsyncTaskCallBackListener<T> {
    public void onTaskComplete(List<T> result);
}
