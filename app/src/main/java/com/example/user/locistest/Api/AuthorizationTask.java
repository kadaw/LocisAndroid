package com.example.user.locistest.Api;

import android.os.AsyncTask;
import android.util.Base64;

import com.example.user.locistest.LoginActivity;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by ewigkeit on 20.02.17.
 */

public class AuthorizationTask extends AsyncTask {
    LoginActivity activity;
    String name;
    String password;
    String token;
    int responseCode;
    public AuthorizationTask(String name, String password){
        this.name=name;
        this.password=password;
    }
    public static String convertStreamToString(InputStream is) throws IOException {
        InputStreamReader r = new InputStreamReader(is);
        StringWriter sw = new StringWriter();
        char[] buffer = new char[1024];
        try {
            for (int n; (n = r.read(buffer)) != -1;)
                sw.write(buffer, 0, n);
        }
        finally{
            try {
                is.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        return sw.toString();
    }
    @Override
    protected Object doInBackground(Object[] params) {
        activity = (LoginActivity) params[0];
        try{
            JSONObject jsonObject = new JSONObject();
            jsonObject.accumulate("Email",name);
            jsonObject.accumulate("Password", password);
            String jsonString = jsonObject.toString();
            URL url = new URL("http://locis.lod-misis.ru/login");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type","application/json");
            connection.connect();
            OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
            wr.write(jsonString);
            wr.flush();
            responseCode = connection.getResponseCode();
            InputStream is = connection.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            token = convertStreamToString(is);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        if (activity != null & token!=null){
            activity.getToken(token,responseCode);

        }
    }
}

