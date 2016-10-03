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
        import android.support.v4.app.ActivityCompat;
        import android.content.pm.PackageManager;
        import 	java.io.*;
        import android.os.Environment;
import java.io.File;
public class CreateActivity extends AppCompatActivity {

    TextView contactname,phone;
    Context context=this;
    UserDbHelper userDbHelper;
    SQLiteDatabase sqLiteDatabase;

    String cntctname,phn;
    EditText contactnametext,phonetext;
    private Button simbttn,phonebttn;


    private static final String TAG = "createActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        String[] permission_arr = {android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.READ_EXTERNAL_STORAGE};

        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, permission_arr, 1);

        simbttn= (Button) findViewById(R.id.simbutton);
        phonebttn= (Button) findViewById(R.id.phonebutton);
        contactname = (TextView) findViewById(R.id.name);
        phone = (TextView) findViewById(R.id.phone);
        contactnametext=(EditText)findViewById(R.id.nametext);
        phonetext=(EditText)findViewById(R.id.phonetext);


        simbttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                cntctname=contactnametext.getText().toString();
                phn=phonetext.getText().toString();
                userDbHelper=new UserDbHelper(context);
                FileOutputStream outputStream;

                try {
                    outputStream = openFileOutput("InternalContacts", Context.MODE_PRIVATE);
                    outputStream.write(cntctname.getBytes());
                    outputStream.write(phn.getBytes());
                    outputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                sqLiteDatabase=userDbHelper.getWritableDatabase();
                userDbHelper.createContact(cntctname,phn,sqLiteDatabase);
                Toast.makeText(getBaseContext(),"Contact Saved",Toast.LENGTH_LONG).show();

                userDbHelper.close();

                Intent intent = new Intent(getApplicationContext(), FunctionActivity.class);


                startActivity(intent);

            }

        });

        phonebttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cntctname=contactnametext.getText().toString();
                phn=phonetext.getText().toString();
                userDbHelper=new UserDbHelper(context);
                String state = Environment.getExternalStorageState();
                FileOutputStream outputStream;

                if (Environment.MEDIA_MOUNTED.equals(state)) {

                    File folder= new File(Environment.getExternalStorageDirectory().getAbsolutePath(),"Phonebook");
                    folder.mkdirs();
                    File file= new File(folder.getAbsolutePath(), "External_Contacts");
                    try {
                        file.createNewFile();
                        outputStream = new FileOutputStream(file);
                        outputStream.write(cntctname.getBytes());
                        outputStream.write(phn.getBytes());
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                   }
                sqLiteDatabase=userDbHelper.getWritableDatabase();
                userDbHelper.createContact(cntctname,phn,sqLiteDatabase);
                Toast.makeText(getBaseContext(),"CONTACT SAVED",Toast.LENGTH_LONG).show();

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
        Log.i(TAG, "Inside CreateActivity onSaveInstance");
    }
    @Override
    public void onRestoreInstanceState (Bundle restoreInstanceState){
        super.onRestoreInstanceState(restoreInstanceState);
        cntctname = restoreInstanceState.getString("name");
        contactnametext.setText(cntctname);
        phn = restoreInstanceState.getString("phone");
        phonetext.setText(phn);

        Log.i(TAG, "Inside CreateActivity onRestoreInstance");
    }
}
