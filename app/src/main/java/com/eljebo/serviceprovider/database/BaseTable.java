package com.eljebo.serviceprovider.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.eljebo.BuildConfig;


abstract public class BaseTable {

    public static final String KEY_ID = "_id";
    public static final String KEY_REMOTE_ID = "remote_id";
    public static final String KEY_SYNC_STATUS = "sync_status";
    public Context mContext;
    protected SQLiteDatabase db;
    protected MySQLiteOpenHelper dbHelper;

    public BaseTable(MySQLiteOpenHelper dbHelper, Context context) {
        this.dbHelper = dbHelper;
        this.mContext = context;
    }

    abstract String getTableName();

    public void close() {
        dbHelper.close();
    }

    public long update(long id, ContentValues values) {
        log("insert() for ContentValues values : " + values);
        long rowId;
        db = dbHelper.getWritableDatabase();
        String where = KEY_ID + "='" + id + "'";
        rowId = db.update(getTableName(), values, where, null);
        db.close();
        return rowId;
    }

    public long insert(ContentValues values) {
        log("insert() for ContentValues values : " + values);
        long rowId;
        db = dbHelper.getWritableDatabase();
        rowId = db.insert(getTableName(), null, values);
        db.close();
        return rowId;
    }

    public Cursor query(String[] columns, String selection,
                        String[] selectionArgs, String groupBy, String having,
                        String orderBy) {
        log(" query() ");
        Cursor cursor = null;
        db = dbHelper.getReadableDatabase();
        cursor = db.query(getTableName(), columns, selection, selectionArgs,
                groupBy, having, orderBy);
        log(" query(): cursod returned -- " + cursor.getCount());
        db.close();
        return cursor;
    }

    public void log(String string) {
        if (BuildConfig.DEBUG)
            Log.i(this.toString(), string);
    }

    protected Cursor findBySyncStatus(int sync_status) {
        Cursor cursor = null;
        db = dbHelper.getReadableDatabase();
        String where = null;
        where = KEY_SYNC_STATUS + "='" + sync_status + "'";
        cursor = db.query(getTableName(), null, where, null, null, null, null);
        log("find() cursor : " + cursor.getCount());
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            return cursor;
        }
        return null;
    }

    public void removeAll() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(this.getTableName(), null, null);
        close();
    }
}
