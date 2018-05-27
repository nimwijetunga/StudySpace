package com.studyspace.studyspace;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DB extends SQLiteOpenHelper {

    public static final String data_DB = "Buildings.db";
    public static final String table = "Buildings_table";
    public static final String col1 = "ID";
    public static final String col2 = "Building";
    public static final String col3 = "Time_start";
    public static final String col4 = "Time_end";
    public static final String col5 = "Course";
    public static final String col6 = "Distance";
    public static final String col7 = "Room";

    public DB(Context context) {
        super(context, data_DB, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String s = "CREATE TABLE IF NOT EXISTS "+table+" ("
                + "	ID integer PRIMARY KEY AUTOINCREMENT,"
                + "	Building text NOT NULL,"
                + "	Time_start text,"
                + "	Time_end text,"
                + "	Course INTEGER,"
                + "	Distance FLOAT,"
                + " Room text"
                + ");";
        db.execSQL(s);

    }

    public ArrayList<FrontendData> get_data_list (){
        ArrayList<FrontendData> data = new ArrayList<FrontendData>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + table + " ORDER BY Distance LIMIT 10", null);

        if(c != null){
            if(c.moveToFirst()){
                do{
                    String building = c.getString(c.getColumnIndex("Building"));
                    String room = c.getString(c.getColumnIndex("Room"));
                    double dist = c.getDouble(c.getColumnIndex("Distance"));
                    data.add(new FrontendData(building, room, dist));
                }while(c.moveToNext());
            }
        }

        return data;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+table);
        onCreate(db);
    }

    public boolean isEmpty(){
        SQLiteDatabase db = this.getWritableDatabase();
        String count = "SELECT count(*) FROM " + table;
        Cursor mcursor = db.rawQuery(count, null);
        mcursor.moveToFirst();
        int icount = mcursor.getInt(0);
        if(icount>0) return false;
        return true;
    }



    public double get_dist(String building){
        SQLiteDatabase db = this.getWritableDatabase();
        String[] params = {"-1", building};
        Cursor c = db.rawQuery("SELECT Distance from Buildings_Table WHERE (NOT (Distance = ?)) AND Building = ? LIMIT 1"
        , params);
        String str = null;
        if (c.moveToFirst()) {
            str = c.getString(c.getColumnIndex("Distance"));
        }
        if(str == null) return -1.0;
        return Double.valueOf(str);
    }

    public boolean insertData(String building, String st, String end, int course, double dist, String room){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv= new ContentValues();
        cv.put(col2, building);
        cv.put(col3, st);
        cv.put(col4, end);
        cv.put(col5, course);
        cv.put(col6, dist);
        cv.put(col7, room);
        long results = db.insert(table, null, cv);
        if (results==-1)
            return false;
        else
            return true;
    }

    public boolean update_dist(double dist, String build_code){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(col6, dist);
        db.update(table, cv, "Building = ?", new String[]{build_code});
        return true;
    }
}