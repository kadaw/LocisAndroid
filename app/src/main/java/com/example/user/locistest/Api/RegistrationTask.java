package com.example.user.locistest.Api;

import android.os.AsyncTask;

import com.example.user.locistest.LoginActivity;
import com.example.user.locistest.RegistrationActivity;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by ewigkeit on 21.02.17.
 */

public class RegistrationTask extends AsyncTask{
    RegistrationActivity activity;
    String email;
    String nickname;
    String password;
    int responseCode;
    public RegistrationTask(String nickname , String email, String password){
        this.nickname = nickname;
        this.email=email;
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
        activity = (RegistrationActivity) params[0];
        try{
            JSONObject jsonObject = new JSONObject();
            jsonObject.accumulate("Nickname",nickname);
            jsonObject.accumulate("Email",email);
            jsonObject.accumulate("Password", password);
            String jsonString = jsonObject.toString();
            URL url = new URL("http://locis.lod-misis.ru/user");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type","application/json");
            connection.connect();
            OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
            wr.write(jsonString);
            wr.flush();
            wr.close();
            responseCode = connection.getResponseCode();

        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        if (activity != null){
            activity.getToken(responseCode);

        }
    }
}
