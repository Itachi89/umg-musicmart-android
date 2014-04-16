package com.preludesys.umg.musicmart.listener;

public interface PostTaskExecuteListener<T> {
	void performOperation(T result);
}
