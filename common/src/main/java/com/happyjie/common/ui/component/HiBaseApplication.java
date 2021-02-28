package com.happyjie.common.ui.component;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;

public class HiBaseApplication extends Application {

    private static Context mContext;
    private static Thread mMainThread;
    private static int mMainThreadId;
    private static Looper mMainLooper;
    private static Handler mMainHandler;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        //主线程
        mMainThread = Thread.currentThread();
        //主线程id
        //mMainThreadId = mMainThread.getId();
        mMainThreadId = android.os.Process.myTid();
        mMainLooper = getMainLooper();
        mMainHandler = new Handler();
    }

    public static Context getContext() {
        return mContext;
    }

    public static Thread getMainThread() {
        return mMainThread;
    }

    public static int getMainThreadId() {
        return mMainThreadId;
    }

    @Override
    public Looper getMainLooper() {
        return mMainLooper;
    }

    public static Handler getMainHandler() {
        return mMainHandler;
    }
}
