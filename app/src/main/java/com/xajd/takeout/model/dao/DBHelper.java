package com.xajd.takeout.model.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.xajd.takeout.MyApplication;

/*
 *  @项目名：  TakeOut 
 *  @包名：    com.xajd.takeout.model.dao
 *  @文件名:   DBHelper
 *  @创建者:   Administrator
 *  @创建时间:  2017/6/10 22:17
 *  @描述：    TODO
 */
public class DBHelper extends OrmLiteSqliteOpenHelper{
	private static final String TAG = "DBHelper";
	private static DBHelper instance;
	public static final int DATABASEVERSION=1;

	public DBHelper(Context context) {
		super(context, TAG, null, DATABASEVERSION);
	}



	public static DBHelper getInstance() {
		if (instance!=null){
			synchronized (DBHelper.class){
				if (instance!=null){
					instance=new DBHelper(MyApplication.getContext());
				}
			}
		}

		return instance;
	}

	@Override
	public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {

	}

	@Override
	public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

	}
}
