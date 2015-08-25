package com.sauravshishodiasgmail.proxy;

import android.content.Intent;
import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class MyActivity extends Activity {
    Button beditInfo, b_login, b_SignUp ;
    EditText et_id, et_pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //check
        StrictMode.ThreadPolicy policy= new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Log.d("Üser info","app started");
        //check

        setContentView(R.layout.activity_my);
        //check
        initialize();
        b_login=(Button) findViewById(R.id.b_login_id);

        b_login.setOnClickListener(new View.OnClickListener(){
        InputStream is = null;
        public void onClick(View arg0){
        String id= ""+et_id.getText().toString();
        String pass= ""+et_pass.getText().toString();
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
        nameValuePairs.add(new BasicNameValuePair("id", id));
        nameValuePairs.add(new BasicNameValuePair("pass", pass));
        Log.d("Üser info","button clicked");
     try{
         HttpClient httpClient = new DefaultHttpClient();
         HttpPost httpPost = new HttpPost("http://10.0.2.2/mywebpages/pro.php");
         httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
         HttpResponse response= httpClient.execute(httpPost);
         Log.d("Üser info","Query executed");
         HttpEntity entity = response.getEntity();
         is = entity.getContent();
         Log.d("Üser info","before msg");

         String msg = "signed up";
         Toast.makeText(getApplicationContext(),"signed up",Toast.LENGTH_LONG).show();
         Log.d("Üser info","after msg");

     }
     catch (ClientProtocolException e){
         Log.e("ClientProtocol", "Log_tag");
         e.printStackTrace();
     }catch (IOException e){
         Log.e("Log_tag","IOException");
         e.printStackTrace();
     }
    }});

//check
        beditInfo.setOnClickListener(new View.OnClickListener(){
           public void onClick(View v){
           Intent openEditinfo = new Intent(MyActivity.this, Editinfo.class);
               MyActivity.this.startActivity(openEditinfo);

           }
       });

        b_SignUp.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent openSignUp = new Intent(MyActivity.this, SignUp.class);
                MyActivity.this.startActivity(openSignUp);

            }
        });

    }


    private void initialize(){
        beditInfo = (Button) findViewById(R.id.bEI);
        et_id=(EditText) findViewById(R.id.et_id);
        et_pass=(EditText) findViewById(R.id.et_password);
        b_SignUp = (Button) findViewById(R.id.b_sign_up);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
