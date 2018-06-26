package com.eljebo.serviceprovider.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {
	
	public static final String DATABASE_NAME = "news_db.sqlite";
	private static final int DATABASE_VERSION = 1;
	public Context mContext;
													
	public MySQLiteOpenHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		mContext = context;
	}
				
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(MyTable.CREATE_MY_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + MyTable.CREATE_MY_TABLE);
		onCreate(db);
	}

}
