package com.example.pavas.myphonebook;

import android.content.Intent;
import android.os.Bundle;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import java.lang.String;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;

public class LoginActivity extends AppCompatActivity {

    TextView username,password;
    String name,pass;
    EditText usernametext,passwordtext;
    private Button login;
    private static final String TAG = "PhoneBook";
    public static final String MyPREFERENCES = "MyPreferences" ;
    public final static String EXTRA_MESSAGE = "com.example.pavas.MY_PHONEBOOK.message";
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("Username","PavasHanda");
        editor.putString("Password","pavas");
        editor.commit();
         name = sharedpreferences.getString("Username","");
        pass = sharedpreferences.getString("Password","");
       onclickbutton();
    }



    public void onclickbutton() {



        login= (Button) findViewById(R.id.loginbutton);
        username = (TextView) findViewById(R.id.username);
        password = (TextView) findViewById(R.id.password);
        usernametext=(EditText)findViewById(R.id.usernametext);
        passwordtext=(EditText)findViewById(R.id.passwordtext);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               if(usernametext.getText().toString().equals(name) && passwordtext.getText().toString().equals(pass)){

                   Intent intent = new Intent(getApplicationContext(), FunctionActivity.class);
                   intent.putExtra(EXTRA_MESSAGE,name);

                   startActivity(intent);
               }
                else{
                   Toast.makeText(getApplicationContext(), "INVALID Username Or Password !!!", Toast.LENGTH_LONG).show();
               }

            }
        });


    }
    @Override
    public void onSaveInstanceState (Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        usernametext=(EditText)findViewById(R.id.usernametext);
        passwordtext=(EditText)findViewById(R.id.passwordtext);
        savedInstanceState.putString("Uname", usernametext.getText().toString());
        savedInstanceState.putString("psswrd", passwordtext.getText().toString());
        Log.i(TAG, "Inside LoginActivity onSaveInstance");
    }
    @Override
    public void onRestoreInstanceState (Bundle restoreInstanceState){
        super.onRestoreInstanceState(restoreInstanceState);
        String uname = restoreInstanceState.getString("Uname");
        usernametext.setText(uname);
        String psswrd = restoreInstanceState.getString("psswrd");
        passwordtext.setText(psswrd);

        Log.i(TAG, "Inside LoginActivity onRestoreInstance");
    }
}
