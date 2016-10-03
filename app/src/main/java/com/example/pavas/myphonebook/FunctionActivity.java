package com.example.pavas.myphonebook;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;
/**
 * Created by pavas on 9/30/16.
 */
public class FunctionActivity extends AppCompatActivity{
    TextView usernametext;
    String name;
    Button create,update,delete,display;
    public final static String EXTRA_MESSAGE = "com.example.pavas.MY_PHONEBOOK.message";
    private static final String TAG = "FunctionActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_function);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            name= bundle.getString(LoginActivity.EXTRA_MESSAGE);
        }
        usernametext=(TextView)findViewById(R.id.username);
        usernametext.setText(name);


        create= (Button) findViewById(R.id.createbutton);
        update= (Button) findViewById(R.id.updatebutton);
        delete= (Button) findViewById(R.id.deletebutton);
        display= (Button) findViewById(R.id.viewbutton);


        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), CreateActivity.class);


                startActivity(intent);
            }



        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), UpdateActivity.class);

                startActivity(intent);
            }



        });


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), DeleteActivity.class);


                startActivity(intent);
            }



        });

        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), ViewActivity.class);


                startActivity(intent);
            }



        });



    }



    @Override
    public void onSaveInstanceState (Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("Username", name);
        Log.i(TAG, "Inside FunctionActivity onSaveInstance");
    }
    @Override
    public void onRestoreInstanceState (Bundle restoreInstanceState){
        super.onRestoreInstanceState(restoreInstanceState);
        name = restoreInstanceState.getString("Username");
        usernametext.setText(name);

        Log.i(TAG, "Inside FunctionActivity onRestoreInstance");
    }
}
