package com.sx.easytalk.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author sunxin
 * @Date 2018/1/23 21:45
 * @Description 数据库工具类
 */

public class DbUtils {

    private static Context sContext;
    private static boolean isInit;

    public static void initDb(Context context) {
        sContext = context;
        isInit = true;
    }


    public static List<String> getContacts(String username) {
        if (isInit) {
            throw new RuntimeException("请初始化DbUtils！");
        }
        ContactOpenHelper contactOpenHelper = new ContactOpenHelper(sContext);
        SQLiteDatabase database = contactOpenHelper.getReadableDatabase();
        Cursor cursor = database.query(ContactOpenHelper.TABLE_NAME, new String[]{ContactOpenHelper.CONTACT},
                ContactOpenHelper.USERNAME, new String[]{username},
                null, null, ContactOpenHelper.CONTACT);
        List<String> contactList = new ArrayList<>();
        while (cursor.moveToNext()) {
            String contact = cursor.getString(cursor.getColumnIndex(ContactOpenHelper.CONTACT));
            contactList.add(contact);
        }

        cursor.close();
        database.close();
        return contactList;
    }


    /**
     * 更新联系人，先把原来的都删除，在添加新的联系人
     * @param username
     * @param contacts
     */
    public static void updateContacts(String username,List<String> contacts){
        ContactOpenHelper openHelper = new ContactOpenHelper(sContext);
        SQLiteDatabase database = openHelper.getWritableDatabase();

        //开启事务
        database.beginTransaction();

        //删除
        database.delete(ContactOpenHelper.TABLE_NAME,ContactOpenHelper.USERNAME+"=?",new String[]{username});
        ContentValues values = new ContentValues();
        values.put(ContactOpenHelper.USERNAME,username);
        for (int i = 0; i < contacts.size(); i++) {
            values.put(ContactOpenHelper.CONTACT,contacts.get(i));
            database.insert(ContactOpenHelper.TABLE_NAME,null,values);
        }
        database.setTransactionSuccessful();
        database.endTransaction();
        database.close();
    }

}
