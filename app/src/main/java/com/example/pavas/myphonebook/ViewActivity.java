package com.example.pavas.myphonebook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.database.Cursor;
import android.app.Activity;
import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.*;
import java.util.ArrayList;
import java.util.List;
public class ViewActivity extends AppCompatActivity {

    SQLiteDatabase sqLiteDatabase;
    UserDbHelper userDbHelper;
    Cursor cursor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        TextView list_view=(TextView)findViewById(R.id.list_view);
        userDbHelper=new UserDbHelper(getApplicationContext());
        sqLiteDatabase=userDbHelper.getReadableDatabase();
        cursor=userDbHelper.displayContact(sqLiteDatabase);

        String temp="";
        if(cursor.moveToFirst()) {
            do{
            String name, phone;
            name = cursor.getString(0);
            phone = cursor.getString(1);
            temp=temp+"\n Name:"+name+"\tPhone:"+phone;
            }while (cursor.moveToNext());


        }


        list_view.setText(temp);

        sqLiteDatabase.close();
    }
  }


