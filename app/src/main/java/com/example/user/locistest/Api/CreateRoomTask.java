package com.example.user.locistest.Api;

import android.os.AsyncTask;

import com.example.user.locistest.UserPage;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by User on 01.03.2017.
 */

public class CreateRoomTask extends AsyncTask {
    UserPage activity;
    String roomLabel;
    String token;
    int responseCode;

    public CreateRoomTask(String roomLabel, String token){
        this.roomLabel = roomLabel;
        this.token = token;
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
        activity = (UserPage) params[0];
        try{
            URL url = new URL("http://locis.lod-misis.ru/room");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type","application/json");
            connection.setRequestProperty("Authorization","Basic "+ token);
            connection.connect();
            OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
            wr.write('"' + roomLabel + '"');
            wr.flush();
            responseCode = connection.getResponseCode();
            System.out.println(responseCode);
        }catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        if (activity != null & token!=null){
            activity.getToken(token,responseCode);

        }
    }
}















