package com.example.pavas.myphonebook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by pavas on 10/3/16.
 */
public class UserDbHelper extends SQLiteOpenHelper {
    private static final String DatabaseName="Phonebook.db";
    private static final int DatabaseVersion=1;
    private static final String CREATE_TABLE=
            "CREATE TABLE "+ UserContract.UserContact.TableName+"("+ UserContract.UserContact.contactname+" TEXT,"+
            UserContract.UserContact.phonenumber+" TEXT);";
    public UserDbHelper(Context context){

        super(context,DatabaseName,null,DatabaseVersion);
        Log.e("Database Operations","Database Created");
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(CREATE_TABLE);
        Log.e("Database Operations","Table Created");

    }
    public void createContact(String name,String phone,SQLiteDatabase sqLiteDatabase){

        ContentValues contentValues=new ContentValues();
        contentValues.put(UserContract.UserContact.contactname,name);
        contentValues.put(UserContract.UserContact.phonenumber,phone);
        sqLiteDatabase.insert(UserContract.UserContact.TableName,null,contentValues);
        Log.e("Database Operations","New Contact Created");
    }
   public Cursor displayContact(SQLiteDatabase sqLiteDatabase){

       Cursor cursor;
       String[] disp= {UserContract.UserContact.contactname, UserContract.UserContact.phonenumber};
       cursor=sqLiteDatabase.query(UserContract.UserContact.TableName,disp,null,null,null,null,null);
        return cursor;
   }

    public void deleteContact(String name,SQLiteDatabase sqLiteDatabase){

        sqLiteDatabase.delete(UserContract.UserContact.TableName, UserContract.UserContact.contactname +"=?", new String[] { name });

    }
    public void updateContact(String name, String phone,SQLiteDatabase sqLiteDatabase){

        ContentValues contentValues=new ContentValues();
        contentValues.put(UserContract.UserContact.contactname,name);
        contentValues.put(UserContract.UserContact.phonenumber,phone);
        sqLiteDatabase.update(UserContract.UserContact.TableName,contentValues, UserContract.UserContact.contactname +" = ?", new String[] {name});


    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS"+ UserContract.UserContact.TableName);
        onCreate(sqLiteDatabase);
    }
}
