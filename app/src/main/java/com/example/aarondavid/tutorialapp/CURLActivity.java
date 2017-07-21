package com.example.aarondavid.tutorialapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class CURLActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curl);
        RunHttpsRequest runHttp = new RunHttpsRequest();
        runHttp.execute();
    }

    public void makeRequest() throws IOException
    {
        Log.v("RESPONSE CODE", "Printing response code");
        System.out.println(getXMLFromUrl("https://dumc.ccbchurch.com/api.php?srv=individual_id_from_login_password"));
    }

    public String getXMLFromUrl(String url)
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

            String params = "login=aaron.david@dumc.my&password=" + java.net.URLEncoder.encode("Nrtplay8", "UTF-8");
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
                makeRequest();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
