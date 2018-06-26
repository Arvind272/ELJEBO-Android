package com.eljebo.serviceprovider.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Parcel;

import com.eljebo.common.utils.Const;
import com.eljebo.common.utils.DBItem;

import java.util.ArrayList;

public class MyTable extends BaseTable {
    public static final String MY_TABLE = "MyTable";
    public static final String CREATE_MY_TABLE = "CREATE TABLE " + MY_TABLE
            + "(" + KEY_ID + " INTEGER PRIMARY KEY autoincrement, "
            + Const.ROW_ID + " INTEGER, "
            + Const.TITLE + " TEXT " + ");";

    public MyTable(MySQLiteOpenHelper dbHelper, Context context) {
        super(dbHelper, context);
    }

    @Override
    String getTableName() {
        return MY_TABLE;
    }


    public void deleteTable() {

        Cursor cursor;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("DELETE FROM " + "'" + getTableName() + "'", null);
        log(" query(): cursor closing");
        cursor.close();
        db.close();

    }

    private ArrayList<DBItem> loadDataToList(Cursor cursor) {

        ArrayList<DBItem> userArrayList = new ArrayList<DBItem>();
        if (cursor != null) {
            log(" getSingleTypeList() cursor size =" + cursor.getCount());
            int index = 0;
            cursor.moveToFirst();
            while (index < cursor.getCount()) {
                DBItem item = new DBItem(Parcel.obtain());
                item.id = (cursor.getLong(cursor.getColumnIndex(Const.ROW_ID)));
                item.title = (cursor.getString(cursor.getColumnIndex(Const.TITLE)));
                index++;
                userArrayList.add(item);
                cursor.moveToNext();
            }

            log(" getSingleTypeList query(): cursor closing");
            cursor.close();

        }
        return userArrayList;
    }

    public void insertAll(ArrayList<DBItem> DBItems) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.beginTransaction();
        try {
            for (DBItem dbItem : DBItems) {
                ContentValues values = new ContentValues();
                values.put(Const.ROW_ID, dbItem.id);
                values.put(Const.TITLE, dbItem.title);
                db.insert(getTableName(), null, values);
            }
        } catch (Exception e) {
            log("Error in transaction" + e.toString());
        } finally {
            db.setTransactionSuccessful();
            db.endTransaction();
            db.close();
        }
    }

    public long insertSingle(DBItem dbItem) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Const.ROW_ID, dbItem.id);
        values.put(Const.TITLE, dbItem.title);
        db.beginTransaction();
        long row = 0;
        try {
            row = db.insert(getTableName(), null, values);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            log("Error in transaction" + e.toString());
        } finally {
            db.endTransaction();
        }

        return row;
    }

    public void updateTitle(DBItem dbItem) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Const.TITLE, dbItem.title);
        db.beginTransaction();
        try {
            String whereClause = Const.ROW_ID + "='" + dbItem.id + "'";
            db.update(getTableName(), values, whereClause, null);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            log("Error in transaction" + e.toString());
        } finally {
            db.endTransaction();
            db.close();
        }
    }

    public boolean hasRecord(long id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        try {
            Cursor cursor = db.rawQuery("SELECT * FROM " + getTableName()
                            + " where " + Const.ROW_ID + " = '" + id + "'", null);
            if (cursor.getCount() == 0) {
                cursor.close();
                db.close();
                return false;
            } else {
                cursor.close();
                db.close();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
