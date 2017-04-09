package com.example.user.locistest.Api;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.test.espresso.core.deps.guava.io.CharStreams;

import com.example.user.locistest.CreateRoomActivity;
import com.example.user.locistest.UserPage;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by User on 10.04.2017.
 */

public class AcceptInvitationTask extends AsyncTask {
    UserPage activity;
    String token;
    int userId;
    public AcceptInvitationTask(/*int userId,*/ String token){
        //this.userId = userId;
        this.token = token;
    }

    @Override
    protected Object doInBackground(Object[] params) {
        activity = (UserPage) params[0];
        try {

            URL url = new URL("http://locis.lod-misis.ru/user/invitations/unchecked");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type","application/json");
            connection.setRequestProperty("Authorization","Basic "+ token);
            connection.connect();
            String response = CharStreams.toString(new InputStreamReader(connection.getInputStream()));
            connection.disconnect();
            System.out.println(response);
            JSONArray jArray = new JSONArray(response);
            JSONObject object = jArray.getJSONObject(0);
            String roomName = object.getString("RoomName");
            System.out.println(roomName);
            Intent InvitationIntent = new Intent();
            InvitationIntent.putExtra("RoomName", roomName);



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
