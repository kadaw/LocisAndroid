package com.example.user.locistest.Api;

import android.support.test.espresso.core.deps.guava.io.CharStreams;

import com.example.user.locistest.CreateRoomActivity;
import com.example.user.locistest.FriendInList;
import com.example.user.locistest.RoomInList;
import com.example.user.locistest.UserPage;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 10.04.2017.
 */

public class RoomsViewTask {
    UserPage activity;
    String token;
    List<RoomInList> rooms;

    public RoomsViewTask(String token){
        this.token = token;
    }

    protected Object doInBackground(Object[] params) {
        activity = (UserPage) params[0];
        try {
            URL url = new URL("http://locis.lod-misis.ru/user/rooms");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type","application/json");
            connection.setRequestProperty("Authorization","Basic "+ token);
            connection.connect();
            String response = CharStreams.toString(new InputStreamReader(connection.getInputStream()));
            connection.disconnect();
            System.out.println(response);
            rooms= new ArrayList<>();
            JSONArray jArray = new JSONArray(response);
            for (int i = 0; i < jArray.length(); i++) {
                JSONObject object = jArray.getJSONObject(i);
                String roomName = object.getString("RoomName");
                rooms.add(new RoomInList(roomName, i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void onPostExecute(Object o) {
        if (rooms!=null)
            activity.onJSONParsed(rooms);
        else activity.onJSONParsed(rooms=new ArrayList<RoomInList>());
    }

}
