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
    Activity upActivity;
    int resource;
    List<RoomInList> roomsInList;
    public RoomsAdapter(Context context, int resource, List<RoomInList> objects){
        super(context, resource, objects);
        this.upActivity = (Activity) context;
        this.resource = resource;
        roomsInList = objects;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = upActivity.getLayoutInflater();
            convertView = inflater.inflate(resource, null);
            viewHolder = new ViewHolder();

            viewHolder.nameTVUserRoom = (TextView) convertView.findViewById(R.id.tv_room_name);
            viewHolder.countOfUsersTVUserRoom = (TextView) convertView.findViewById(R.id.tv_count_of_users);

            RoomInList selectedRoom = (RoomInList) roomsInList.get(position);

            viewHolder.nameTVUserRoom.setText(selectedRoom.name);
            viewHolder.countOfUsersTVUserRoom.setText(selectedRoom.usersCount);
        }
        return convertView;

    }
    class ViewHolder{
       TextView nameTVUserRoom;
        TextView countOfUsersTVUserRoom;

    }

}
