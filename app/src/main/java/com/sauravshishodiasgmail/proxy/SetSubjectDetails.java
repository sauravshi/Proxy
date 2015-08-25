package com.sauravshishodiasgmail.proxy;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Saurav on 6/16/2015.
 */
public class SetSubjectDetails extends Activity implements View.OnClickListener {

    Button sqlUpdate, sqlView;
    EditText sqlName, sqlcode;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_subject_set);
        sqlcode = (EditText)findViewById(R.id.etsqlcode);
        sqlName = (EditText)findViewById(R.id.etsqlname);
        sqlUpdate = (Button)findViewById(R.id.bsqlupdate);
        sqlView = (Button)findViewById(R.id.bsqlview);

        sqlView.setOnClickListener(this);
        sqlUpdate.setOnClickListener(this);

    }

    public void onClick(View arg0){

        switch(arg0.getId()){
            case R.id.bsqlupdate:
                boolean didItWork = true;
                try {
                    String name = sqlName.getText().toString();
                    String code = sqlName.getText().toString();
                    HotOrNot entry = new HotOrNot(SetSubjectDetails.this);
                    entry.open();
                    entry.createEntry(name, code);
                    entry.close();
                }catch (Exception e){
                    didItWork =false;
                }finally {
                    if(didItWork){
                        Dialog d = new Dialog(this);
                        d.setTitle("heck yeah");
                        TextView tv = new TextView(this);
                        tv.setText("success");
                        d.setContentView(tv);
                        d.show();
                    }
                }
                break;

            case R.id.bsqlview:


        }
    }
}
