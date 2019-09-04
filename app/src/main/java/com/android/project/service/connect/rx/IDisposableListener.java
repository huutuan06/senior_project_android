package com.android.project.service.connect.rx;

public interface IDisposableListener<T> {
    void onComplete();

    void onHandleData(T t);

    void onRequestWrongData(int code);

    void onApiFailure(Throwable error);
}
