package com.example.user.locistest.Api;


import android.content.Intent;
import android.os.AsyncTask;
import android.support.test.espresso.core.deps.guava.io.CharStreams;

import com.example.user.locistest.CreateRoomActivity;
import com.example.user.locistest.FriendInList;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class SearchUsersTask extends AsyncTask{
    CreateRoomActivity activity;
    String token;
    String searchString;
    List<FriendInList> friends;

    public SearchUsersTask(String token, String searchString) {
        this.token = token;
        this.searchString = searchString;
    }

    @Override
    protected Object doInBackground(Object[] params) {
        activity = (CreateRoomActivity) params[0];
        try {
            URL url = new URL("http://locis.lod-misis.ru/user/search/"+searchString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type","application/json");
            connection.setRequestProperty("Authorization","Basic "+ token);
            connection.connect();
            String response = CharStreams.toString(new InputStreamReader(connection.getInputStream()));
            connection.disconnect();
            System.out.println(response);
            friends= new ArrayList<>();
            JSONArray jArray = new JSONArray(response);
            for (int i = 0; i < jArray.length(); i++) {
                JSONObject object = jArray.getJSONObject(i);
                int id = object.getInt("UserId");
                String name = object.getString("Nickname");
                boolean room = object.getBoolean("IsInRoom");
                friends.add(new FriendInList(name,id, room));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        if (friends!=null)
        activity.onJSONParsed(friends);
        else activity.onJSONParsed(friends=new ArrayList<FriendInList>());
    }
}
