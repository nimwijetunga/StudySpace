package com.studyspace.studyspace;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class DB extends SQLiteOpenHelper {

    public static final String data_DB = "Buildings.db";
    public static final String table = "Buildings_table";
    public static final String col1 = "id";
    public static final String col2 = "Building";
    public static final String col3 = "Time_start";
    public static final String col4 = "Time_end";
    public static final String col5 = "Course";
    public static final String col6 = "Distance";

    public DB(Context context) {
        super(context, data_DB, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //db.execSQL("DROP TABLE IF EXISTS "+table);
        String s = "CREATE TABLE IF NOT EXISTS "+table+" ("
                + "	ID integer PRIMARY KEY AUTOINCREMENT,"
                + "	Building text NOT NULL,"
                + "	Time_start text,"
                + "	Time_end text,"
                + "	Course INTEGER,"
                + "	Distance FLOAT"
                + ");";
        db.execSQL(s);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+table);
        onCreate(db);
    }

    public boolean insertData(String building, String st, String end, int course, double dist){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv= new ContentValues();
        cv.put(col2, building);
        cv.put(col3, st);
        cv.put(col4, end);
        cv.put(col5, course);
        cv.put(col6, dist);
        long results = db.insert(table, null, cv);
        if (results==-1)
            return false;
        else
            return true;
    }



}