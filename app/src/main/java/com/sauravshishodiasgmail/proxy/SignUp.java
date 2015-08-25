package com.sauravshishodiasgmail.proxy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

/**
 * Created by Saurav on 8/11/2015.
 */
// Work left= saving data to database and opening login page after signUp.
public class SignUp extends Activity {
    private Button bSignUp, bLogin;
    EditText etRollNo, etFullName, etBranch, etSubGroup, etEmail, etMobileNo, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        init();
        bSignUp = (Button) findViewById(R.id.b_sign_up_signup);
        bSignUp.setOnClickListener(new View.OnClickListener() {
            InputStream is = null;
            public void onClick(View arg0) {

                String rollNo= "" + etRollNo.getText().toString();
                String fullName= "" + etFullName.getText().toString();
                String branch= "" + etBranch.getText().toString();
                String subGroup= "" + etSubGroup.getText().toString();
                String email= "" + etEmail.getText().toString();
                String mobileNo= "" + etMobileNo.getText().toString();
                String password= "" + etPassword.getText().toString();

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
                nameValuePairs.add(new BasicNameValuePair("rollNo", rollNo));
                nameValuePairs.add(new BasicNameValuePair("fullName", fullName));
                nameValuePairs.add(new BasicNameValuePair("branch", branch));
                nameValuePairs.add(new BasicNameValuePair("subGroup", subGroup));
                nameValuePairs.add(new BasicNameValuePair("email", email));
                nameValuePairs.add(new BasicNameValuePair("mobileNo", mobileNo));
                nameValuePairs.add(new BasicNameValuePair("password", password));

                Log.d("Üser info", "button clicked SignUp");

                try{
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost("http://10.0.2.2/mywebpages/pro_signUp.php");
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                    HttpResponse response= httpClient.execute(httpPost);
                    Log.d("Üser info","Query executed SignUp");
                    HttpEntity entity = response.getEntity();
                    is = entity.getContent();
                    Log.d("Üser info","before msg SignUp");

                    String msg = "signed up";
                    Toast.makeText(getApplicationContext(), "signed up", Toast.LENGTH_LONG).show();
                    Log.d("Üser info","after msg SignUp");

                }catch (ClientProtocolException e){
                    Log.e("ClientProtocol", "Log_tag");
                    e.printStackTrace();
                }catch (IOException e){
                    Log.e("Log_tag","IOException");
                    e.printStackTrace();
                }
                Log.d("Üser info", "before opening login page");
                Intent openMyActivity = new Intent(SignUp.this, MyActivity.class);
                SignUp.this.startActivity(openMyActivity);
                Log.d("Üser info", "after opening login page by S");
            }
        });

        bLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0){
                Log.d("Üser info", "before opening login page");
                Intent openMyActivity = new Intent(SignUp.this, Login.class);
                SignUp.this.startActivity(openMyActivity);
                Log.d("Üser info", "after opening login page by L");
            }
        });
    }

    private void init(){
        etRollNo = (EditText) findViewById(R.id.et_roll_no);
        etFullName = (EditText) findViewById(R.id.et_full_name);
        etBranch = (EditText) findViewById(R.id.et_branch);
        etSubGroup = (EditText) findViewById(R.id.et_sub_group);
        etEmail = (EditText) findViewById(R.id.et_email);
        etMobileNo = (EditText) findViewById(R.id.et_mobile_no);
        etPassword = (EditText) findViewById(R.id.et_password_signup);
        bLogin = (Button) findViewById(R.id.b_login);

    }


}