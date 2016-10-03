package com.example.pavas.myphonebook;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;
import android.support.v7.app.AppCompatActivity;
import android.database.sqlite.SQLiteOpenHelper;

public class DeleteActivity extends AppCompatActivity {

    TextView contactname;
    String cntctname;
    EditText contactnametext;
    private Button delete;
    Context context=this;
    UserDbHelper userDbHelper;
    SQLiteDatabase sqLiteDatabase;
    private static final String TAG = "deleteActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        delete= (Button) findViewById(R.id.deletebutton);
        contactname = (TextView) findViewById(R.id.name);
        contactnametext=(EditText)findViewById(R.id.nametext);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                cntctname=contactnametext.getText().toString();
                userDbHelper=new UserDbHelper(context);
                sqLiteDatabase=userDbHelper.getWritableDatabase();
                userDbHelper.deleteContact(cntctname,sqLiteDatabase);
                Toast.makeText(getBaseContext(),"Contact Deleted",Toast.LENGTH_LONG).show();

                userDbHelper.close();
                Intent intent = new Intent(getApplicationContext(), FunctionActivity.class);


                startActivity(intent);

            }

        });
    }

    @Override
    public void onSaveInstanceState (Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("name", contactnametext.getText().toString());
        Log.i(TAG, "Inside DeleteActivity onSaveInstance");
    }
    @Override
    public void onRestoreInstanceState (Bundle restoreInstanceState){
        super.onRestoreInstanceState(restoreInstanceState);
        cntctname = restoreInstanceState.getString("name");
        contactnametext.setText(cntctname);


        Log.i(TAG, "Inside DeleteActivity onRestoreInstance");
    }
}
