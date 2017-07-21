package com.example.aarondavid.tutorialapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener
{
    TextView username;
    TextView password;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button loginButton = (Button) findViewById(R.id.button);
        username = (TextView) findViewById(R.id.usernameText);
        password = (TextView) findViewById(R.id.passwordText);
        loginButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        String usernameString = username.getText().toString();
        String passwordString = password.getText().toString();

        //Toast.makeText(LoginActivity.this, "This is " + usernameString + " Using " + passwordString, Toast.LENGTH_SHORT).show();
        RunHttpsRequest runHttpsRequest = new RunHttpsRequest();
        runHttpsRequest.execute(usernameString, passwordString);
    }

    public void makeRequest(String un, String pass) throws IOException
    {
        Log.v("RESPONSE CODE", "Printing response code");
        System.out.println(getXMLFromUrl("https://dumc.ccbchurch.com/api.php?srv=individual_id_from_login_password", un, pass));
    }

    public String getXMLFromUrl(String url, String userN, String passW)
    {
        BufferedReader br = null;
        try
        {
            String auth = "dumcapp:JesusLORD0fa11!";
            byte[] authEncByte = Base64.encode(auth.getBytes(), Base64.DEFAULT);
            String authEncString = new String(authEncByte);

            HttpsURLConnection connection = (HttpsURLConnection)(new URL(url)).openConnection();
            connection.setRequestProperty("Authorization", "Basic " + authEncString);
            connection.setRequestMethod("POST");

            String params = "login="+userN+"&password=" + java.net.URLEncoder.encode(passW, "UTF-8");
            OutputStream os = connection.getOutputStream();
            BufferedWriter writer = new BufferedWriter( new OutputStreamWriter(os, "UTF-8"));
            writer.write(params);
            writer.flush();
            writer.close();
            os.close();
            br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String line;
            final StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null)
            {
                sb.append(line).append("\n");
            }

            return sb.toString();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
        finally
        {
            try
            {
                if (br != null) br.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    private class RunHttpsRequest extends AsyncTask
    {
        @Override
        protected Object doInBackground(Object[] params)
        {
            try {
                makeRequest(params[0].toString(), params[1].toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
