package com.example.user.locistest.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.user.locistest.R;
import com.example.user.locistest.RoomInList;

import java.util.List;

import static android.R.id.list;

/**
 * Created by User on 28.03.2017.
 */

public class RoomsAdapter extends ArrayAdapter{
    Activity friendsActivity;
    int resource;
    List<RoomInList> roomsInList;
    String token;

    public RoomsAdapter(Context context, int resource, List<RoomInList> objects, String token){
        super(context, resource, objects);
        this.friendsActivity = (Activity) context;
        this.resource = resource;
        this.token = token;
        roomsInList = objects;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = friendsActivity.getLayoutInflater();
            convertView = inflater.inflate(R.layout.item_room, null);
            viewHolder = new ViewHolder();

            viewHolder.nameTVUserRoom = (TextView) convertView.findViewById(R.id.tv_room_name);
            RoomInList selectedRoom = roomsInList.get(position);
            viewHolder.nameTVUserRoom.setText(selectedRoom.name);
        }
        return convertView;

    }
    class ViewHolder{
       TextView nameTVUserRoom;

    }

}
