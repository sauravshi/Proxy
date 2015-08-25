package com.sauravshishodiasgmail.proxy;

import android.content.Intent;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


/**
 * Created by Saurav on 6/8/2015.
 */
public class Editinfo extends MyActivity {

    Button beditSubDet, bdone;
    EditText setname, setbranch, setsemester;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editinfo);
        initialize();
        beditSubDet.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent openSetSubjectDetails = new Intent(Editinfo.this, SetSubjectDetails.class);
                Editinfo.this.startActivity(openSetSubjectDetails);
            }
        });

    }

    public void Doit(View v){
        initialize();
        String strname = setname.getText().toString();
        String strbranch = "Branch: "+setbranch.getText().toString();
        String strsemester = "Semester: "+setsemester.getText().toString();
        Intent intent=new Intent(getApplicationContext(),MyActivity.class);
        intent.putExtra("key1", strname);
        intent.putExtra("key2", strbranch);
        intent.putExtra("key3", strsemester);
        startActivity(intent);

    }

    private void initialize(){

       beditSubDet = (Button) findViewById(R.id.bESD);
        bdone = (Button) findViewById(R.id.bDone);
         setname = (EditText) findViewById(R.id.etname);
         setbranch = (EditText) findViewById(R.id.etbranch);
       setsemester = (EditText) findViewById(R.id.etsemester);

    }
    }
