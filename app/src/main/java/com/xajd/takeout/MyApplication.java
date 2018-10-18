package com.xajd.takeout;

import android.app.Application;
import android.content.Context;

/*
 *  @项目名：  TakeOut 
 *  @包名：    com.xajd.takeout
 *  @文件名:   MyApplication
 *  @创建者:   Administrator
 *  @创建时间:  2017/6/10 22:38
 *  @描述：    TODO
 */
public class MyApplication extends Application{
	private static final String TAG = "MyApplication";

	private static Context mContext;

	@Override
	public void onCreate() {
		super.onCreate();
		mContext=this;
	}

	public static Context getContext() {
		return mContext;
	}
}
