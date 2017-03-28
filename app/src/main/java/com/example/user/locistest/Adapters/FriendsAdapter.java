package com.example.user.locistest.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.user.locistest.FriendInList;
import com.example.user.locistest.R;

import java.util.List;

/**
 * Created by User on 01.03.2017.
 */

public class FriendsAdapter extends ArrayAdapter {
    Activity friendsActivity;
    int resource;
    List<FriendInList> friendInLists;

    public FriendsAdapter(Context context, int resource, List<FriendInList> objects){
        super(context, resource, objects);
        this.friendsActivity = (Activity) context;
        this.resource = resource;
        friendInLists = objects;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder viewHolder;
        if(convertView==null){
            LayoutInflater inflater = friendsActivity.getLayoutInflater();
            convertView = inflater.inflate(R.layout.item_friend, null);
            viewHolder = new ViewHolder();
            viewHolder.nameTextView = (TextView) convertView.findViewById(R.id.tv_friend_name);
            FriendInList selectedFriend = friendInLists.get(position);
            viewHolder.nameTextView.setText(selectedFriend.name);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }


        return convertView;

    }
    class ViewHolder{
        TextView nameTextView;


    }

}
