package com.example.user.locistest.Api;

import android.os.AsyncTask;

import com.example.user.locistest.SearchUsersActivity;

import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Created by ewigkeit on 29.03.17.
 */

public class SendInvitationTask extends AsyncTask {
    SearchUsersActivity activity;
    int userId;
    int roomId;
    String token;

    public SendInvitationTask(int userId, int roomId, String token) {
        this.userId = userId;
        this.roomId = roomId;
        this.token = token;
    }

    @Override
    protected Object doInBackground(Object[] params) {
        activity = (SearchUsersActivity) params[0];
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.accumulate("RoomId", roomId);
            jsonObject.accumulate("TargetId", userId);
            URL url = new URL("http://locis.lod-misis.ru/invitation/send");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization","Basic "+ token);
            connection.connect();
            OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
            wr.write(jsonObject.toString());
            wr.flush();
            int responseCode = connection.getResponseCode();
            InputStream is = connection.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            System.out.println(responseCode);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);

    }
}

