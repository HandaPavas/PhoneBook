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

public class UpdateActivity extends AppCompatActivity {
    TextView contactname,phone;
    String cntctname,phn;
    EditText contactnametext,phonetext;
    private Button save;
    Context context=this;
    UserDbHelper userDbHelper;
    SQLiteDatabase sqLiteDatabase;
    private static final String TAG = "updateActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);



            save = (Button) findViewById(R.id.savebutton);
            contactname = (TextView) findViewById(R.id.name);
            phone = (TextView) findViewById(R.id.phone);
            contactnametext = (EditText) findViewById(R.id.nametext);
            phonetext = (EditText) findViewById(R.id.phonetext);
            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    cntctname=contactnametext.getText().toString();
                    phn=phonetext.getText().toString();
                    userDbHelper=new UserDbHelper(context);
                    sqLiteDatabase=userDbHelper.getWritableDatabase();
                    userDbHelper.updateContact(cntctname,phn,sqLiteDatabase);
                    Toast.makeText(getBaseContext(),"Contact Updated",Toast.LENGTH_LONG).show();

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
        savedInstanceState.putString("phone", phonetext.getText().toString());
        Log.i(TAG, "Inside UpdateActivity onSaveInstance");
    }
    @Override
    public void onRestoreInstanceState (Bundle restoreInstanceState){
        super.onRestoreInstanceState(restoreInstanceState);
        cntctname = restoreInstanceState.getString("name");
        contactnametext.setText(cntctname);
        phn = restoreInstanceState.getString("phone");
        phonetext.setText(phn);

        Log.i(TAG, "Inside UpdateActivity onRestoreInstance");
    }
}
