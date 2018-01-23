package com.sx.easytalk.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @Author sunxin
 * @Date 2018/1/23 21:32
 * @Description
 */

public class ContactOpenHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "contacts.db";
    private static final int VERSIONS = 1;

    public static final String TABLE_NAME = "t_contact";
    public static final String USERNAME = "username";
    public static final String CONTACT = "contact";

    private ContactOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public ContactOpenHelper(Context context) {
        super(context, DB_NAME, null, VERSIONS);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //建表
        db.execSQL("create table " + TABLE_NAME + "(_id integer primary key," + USERNAME + " varchar(20)," + CONTACT + " varchar(20))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
